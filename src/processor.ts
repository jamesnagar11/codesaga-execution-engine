import type { RedisClientType } from "redis";
import type { MessageType } from "./lib/types";
import path from 'path';
import { copyStringToFile, executeCommand, executeWithTimeout } from "./utils/process";
import { map } from "./utils/store";
import { publishResult } from "./utils/publisher";

export async function processor(message: MessageType, publisher: RedisClientType) {
    const { language, subscribedTo } = message;
    let code = message.code;
    let fdPath = '';
    let subfolder = '';
    let fileName = '';

    if (language === 'java') {
        code = `import java.util.*;\n` + code
        fdPath = path.join(process.cwd(), 'JavaFd')
        subfolder = 'JavaFd'
        fileName = 'Solution.java'
    } else if (language === 'cpp') {
        code = `#include <bits/stdc++.h>\n#include "ListNode.h"\nusing namespace std;\n` + code
        fdPath = path.join(process.cwd(), 'CppFd')
        subfolder = 'CppFd'
        fileName = 'Solution.cpp'
    } else if (language === 'python') {
        fdPath = path.join(process.cwd(), 'PythonFd')
        subfolder = 'PythonFd'
        fileName = 'Solution.py'
    } else {
        console.log('[Worker] Unsupported language:', language)
        return;
    }

    await copyStringToFile(subfolder, fileName, code);    

    const baseResult = {
        language,
        code: message.code,
        socketId: message.socketId,
        problemTitle: message.problemTitle,
        runnerType: message.runnerType,
        submissionTime: message.submissionTime,
        userId: message.userId,
        problemURL: message.problemURL,
        difficulty: message.difficulty,
        topics: message.topics,
    };

    let status = 'Not started';

    try {
        if(language === 'java') {            
            await executeCommand(
                `javac -sourcepath ${fdPath} -d ${fdPath} ${fdPath}/${map.get(message.problemTitle)}.java`,
                'Compilation Error'
            );
            const runOutput: any = await executeWithTimeout(
                `java -cp ${fdPath} ${map.get(message.problemTitle)}`,
                3500
            );

            console.log('[Worker] stdout:', runOutput.stdout);

            status = runOutput.stdout;

            if (runOutput.stderr) {
                await publishResult({ ...baseResult, status: runOutput.stderr }, publisher, subscribedTo)
                return;
            }
        }
        else if(language === 'cpp') {
            console.log(`g++ -o ${fdPath}/${map.get(message.problemTitle)} ${fdPath}/${map.get(message.problemTitle)}.cpp`);
            await executeCommand(
                `g++ -o ${fdPath}/${map.get(message.problemTitle)} ${fdPath}/${map.get(message.problemTitle)}.cpp`,
                'Compilation Error'
            )
            const runOutput: any = await executeWithTimeout(
                `${fdPath}/${map.get(message.problemTitle)}`,
                3500
            )
            console.log('[Worker] stdout:', runOutput.stdout)
            status = runOutput.stdout
            if (runOutput.stderr) {
                await publishResult({ ...baseResult, status: runOutput.stderr }, publisher, subscribedTo)
                return;
            }
        }
        else if(language === 'python') {
            const runOutput: any = await executeWithTimeout(
                `PYTHONPATH=${fdPath} python3 ${fdPath}/${map.get(message.problemTitle)}.py`,
                3500
            )
            console.log('[Worker] stdout:', runOutput.stdout)
            status = runOutput.stdout
            if (runOutput.stderr) {
                await publishResult({ ...baseResult, status: runOutput.stderr }, publisher, subscribedTo)
                return;
            }
        }
    } catch (error: any) {
        const errStatus = (error?.message === 'TLE') ? 'TLE' : String(error)
        console.log('[Worker] Execution error:', errStatus)
        await publishResult({ ...baseResult, status: errStatus }, publisher, subscribedTo)
        return;
    }

    console.log(`[Worker] Done — status: "${status}", sending to socketId: ${message.socketId}`)
    await publishResult({ ...baseResult, status }, publisher, subscribedTo);
}