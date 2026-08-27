import path from 'path';
import { promises as fs } from 'fs';
import { exec } from 'child_process';

export async function copyStringToFile(subfolder: string, fileName: string, content: string): Promise<void> {    
    const dirPath = path.join(process.cwd(), subfolder)
    const filePath = path.join(dirPath, fileName)
    console.log(dirPath);
    
    await fs.writeFile(filePath, content, 'utf8')
}

export const executeCommand = (command: string, possibleError: string) => {
    return new Promise((resolve, reject) => {
        exec(command, (error, stdout, stderr) => {
            if (error) {
                reject(possibleError + " : " + error.message.split('error:')[1])
            } else {
                resolve({ stdout: stdout.trim(), stderr: stderr.trim() })
            }
        })
    })
}

export const executeWithTimeout = (command: string, timeout: number) => {
    return Promise.race([
        executeCommand(command, 'Runtime Error'),
        new Promise((_, reject) => {
            setTimeout(() => reject(new Error('TLE')), timeout)
        })
    ])
}