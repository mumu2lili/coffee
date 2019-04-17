#生成证书
keytool -genkey -alias tmp.key -keypass 123456 -keyalg RSA -keysize 2048 -validity 3650 -keystore C:/Users/mm/Desktop/tmp/keystore -storepass 123456

#jre/lib/security/cacerts 口令
changeit

#导出证书到jre/lib/security/cacerts
cd D:/Program Files/Java/jre/lib/security
#如果使用的是jdk/jre/...，则导入到相应目录
keytool -import -alias tmp.key -keystore cacerts -file C:/Users/mm/Desktop/tmp/jiang.crt
