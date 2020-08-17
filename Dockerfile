FROM tomcat:8

RUN echo "export JAVA_OPTS=\"-Dapp.env=staging\"" > /usr/local/tomcat/bin/setenv.sh
COPY target/item-info-retrieval-app-1.0.0.war /usr/local/tomcat/webapps/staging.war

CMD ["catalina.sh", "run"]