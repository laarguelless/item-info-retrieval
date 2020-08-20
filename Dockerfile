FROM tomcat:8

RUN echo "export JAVA_OPTS=\"-Dapp.env=staging\"" > /usr/local/tomcat/bin/setenv.sh
COPY target/item-info-retrieval-app-1.0.0.war /usr/local/tomcat/webapps/staging.war
ENV DB_URL postgresql://docker:docker@app-db:5432/db
ENV BASE_URL https://api.mercadolibre.com/items/

CMD ["catalina.sh", "run"]