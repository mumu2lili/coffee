##################### bridge #############
#bridge自签名证书
keytool -genkeypair -alias bridgeca -dname "CN=mumu, OU=湖南智擎科技有限公司, O=实训云, L=长沙, ST=湖南, C=中国" -keypass 123456 -keyalg RSA -keysize 2048 -validity 3650 -keystore bridge.jks -storepass 123456 -storetype PKCS12

#查看证书
keytool -list -keystore bridge.jks -v

#导出ca证书
keytool -exportcert -alias bridgeca -file bridge.pem -rfc -keystore bridge.jks


#####################湖南大学
#湖南大学自签名证书
keytool -genkeypair -alias hnu -dname "CN=管主任, OU=湖南大学, O=计算机实验室, L=长沙, ST=湖南, C=中国" -keypass 123456 -keyalg RSA -keysize 2048 -validity 3650 -keystore bridge.jks -storepass 123456 -storetype PKCS12

#签名请求
keytool -certreq -alias hnu -file hnu.csr -keystore bridge.jks -storepass 123456
keytool -printcertreq -file hnu.csr -v

#证书签名
keytool -gencert -infile hnu.csr -alias bridgeca -outfile hnu.pem -rfc -keystore bridge.jks -storepass 123456
keytool -printcert -file hnu.pem -v

#签名后的证书导回密钥库中
keytool -importcert -alias hnu -file hnu.pem -keystore bridge.jks -storepass 123456



