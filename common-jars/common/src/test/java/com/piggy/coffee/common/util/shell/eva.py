# coding=utf-8
# -*- coding: UTF-8 -*-
import os
import base64
import sys
import subprocess
from pathlib import Path

timeLimit = int(sys.argv[1])
os.chdir( "D:\\tmp" )
myrepo = Path("myrepo")
if myrepo.exists():
    os.chdir( "D:\\tmp\\myrepo" )
    cp = subprocess.run("start /high git pull origin master", shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, encoding="utf-8", timeout=timeLimit)
    print("pull*****",cp)
else:
    cmd = "start /high git clone https://edugit:xinedugit%23@git.educoder.net/p48913605/qg59m8ln20200320003323.git myrepo"
    cp = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, encoding="utf-8", timeout=timeLimit)
    print("clone*****",cp)

os.chdir( "D:\\tmp\\myrepo" )

# 待执行的评测文件
sourceClassNames=("src/step1/step1.py", "src/step2/step2.py", "src/step3/step3.py", "src/step4/step4.py")

# 执行命令
executeCommand="python "

# 获取测试用例的输入(请勿改动此行语句)
ins=sys.argv[3].split(",")


compileResult = base64.encodebytes('compile successfully'.encode('utf8')) 
compileResult=str(compileResult,'utf-8')


# 执行函数
def execute():
    # 当前关卡的执行目标文件
    sourceClassName = sourceClassNames[ int(sys.argv[2]) ]
    
    output=''
    i=0
    while i < len(ins):  
        cmd = executeCommand + sourceClassName + " " + ins[i]
        cp = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, encoding="utf-8", timeout=timeLimit)

        result = cp.stdout;
        if cp.stdout == '':
            result = cp.stderr
        result = str(base64.encodebytes(cp.stdout.encode('utf8')), 'utf8')
        output = output + "\"" + result + "\","
        i += 1

    output = "[" + output[:-1] + "]"
    return output

output = execute()


# 返回评测结果
def returnResult():
    result="{\"compileResult\":\"" + compileResult + "\",\"out\":" + output +"}"
    print(result)


returnResult()
