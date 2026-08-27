import type { RedisClientType } from "redis"

export async function publishResult(result: object, publisher: RedisClientType, subscribedTo: string) {
    const payload = JSON.stringify(result)
    console.log('[Worker] Publishing result to workerResults channel:', JSON.stringify(result).slice(0, 120))
    await publisher.publish(subscribedTo, payload)
}