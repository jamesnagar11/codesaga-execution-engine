import type { RedisClientType } from 'redis';

export async function initGroup(redisClient: RedisClientType, streamName: string, groupName: string) {
    try {
        await redisClient.xGroupCreate(streamName, groupName, '$', { MKSTREAM: true });
        console.log(`Consumer group '${groupName}' created on stream '${streamName}'`);
    } catch (error: any) {
        if (typeof error?.message === 'string' && error.message.includes('BUSYGROUP')) {
            // Consumer group already exists, safe to ignore
            return;
        }
        console.error('Error initializing consumer group:', error);
    }
}