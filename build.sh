#!/bin/bash

rm -rf target
mvn dependency:unpack compile war:war
cp target/wfs.war /opt/tomcat/apache-tomcat-8.5.20/webapps/wfs.war
curl --user tomcat:s3cret http://localhost:8080/manager/text/reload?path=/wfs
