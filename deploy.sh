#linha de codigo que nao lembro
scp web/target/labrador.war root@srv-labrador.maps.com.br:~
ssh root@srv-labrador.maps.com.br
/etc/init.d/tomcat7 stop
cd /var/lib/tomcat7/webapps
rm -rf labrador.war
rm -rf labrador/
mv ~/labrador.war .
/etc/init.d/tomcat7 start 
