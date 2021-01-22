# coding=utf-8
# -*- coding: UTF-8 -*-
import os
import base64
import sys
import subprocess

os.chdir( "D:\\tmp\\py" )

# 待执行的评测文件
sourceClassNames=("step1.py")

# 执行命令
executeCommand="python "

# 获取测试用例的输入(请勿改动此行语句)
ins=sys.argv[3].split(",")


compileResult = base64.encodebytes('compile successfully'.encode('utf8')) 
compileResult=str(compileResult,'utf-8')


# 执行函数
def execute():
    # 当前关卡的执行目标文件
    #sourceClassName=${sourceClassNames[$1 - 1]}
    sourceClassName="step" + sys.argv[2] + ".py"
    
    output=''
    i=0
    while i < len(ins):  
        cmd = executeCommand + sourceClassName + " " + ins[i]
        cp = subprocess.run(cmd, shell=False, stdout=subprocess.PIPE, stderr=subprocess.PIPE, encoding="utf-8", timeout=1)

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
