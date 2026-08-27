import { createClient } from "redis";

export const redisClient = createClient({
    url: process.env.REDIS_URL!,
    socket: {
        reconnectStrategy: retries => {
            if(retries > 5) return new Error('Too many retries');
            return Math.min(retries * 50, 500);
        }
    }
});

export const publisher = redisClient.duplicate();

redisClient.on("error", (err) => {
    console.error('Redis Client Error : ', err);
})

redisClient.on('connect', () => console.log('Redis Client connected'));


publisher.on("error", (err) => {
    console.error('Redist Publisher Error : ', err);
})

publisher.on('connect', () => console.log('Redis Publisher connected'));