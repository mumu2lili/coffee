#coding=utf-8
import sys
import base64

param = base64.decodebytes(sys.argv[1].encode('utf8'))
param=str(param,'utf-8')
print("执行文件:",sys.argv[0])
print("参数:",sys.argv[1])
print("解密参数:",param)

