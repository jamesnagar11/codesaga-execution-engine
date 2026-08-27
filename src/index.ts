import { publisher, redisClient } from "./config/redis";
import type { MessageType } from "./lib/types";
import { processor } from "./processor";
import { initGroup } from "./utils/init";

async function startWorker() {
    const consumer_id = Bun.randomUUIDv7();
    try {
        await redisClient.connect();
        await publisher.connect();
        
        setInterval(() => { redisClient.ping().catch(console.error) }, 60000);
        setInterval(() => { publisher.ping().catch(console.error) }, 60000);

        await initGroup(redisClient, process.env.STREAM_KEY! , process.env.CONSUMER_GROUP!);

        while(true) {
            console.log('Worker Waiting for new code...');

            const response = await redisClient.xReadGroup(
                process.env.CONSUMER_GROUP!,
                `consumer-${consumer_id}`,
                {
                    key: process.env.STREAM_KEY!,
                    id: '>'
                },
                {
                    COUNT: 2,
                    BLOCK: 3000
                }
            );

            if(response && response.length > 0 ) {
                for(const res of response) {
                    const messages = res.messages;
                    for(const message of messages) {
                        const payload = message.message as MessageType;
                        await processor(payload, publisher);
                        await redisClient.xAck(process.env.STREAM_KEY!, process.env.CONSUMER_GROUP!, message.id);
                        await redisClient.xDel(process.env.STREAM_KEY!, message.id);
                    }
                }
            }
        }

    } catch (error) {
        console.log(`Worker failed to start : ${error}`);
        await new Promise(resolve => setTimeout(resolve, 1000));
    }
}

startWorker();