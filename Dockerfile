FROM tomcat:8

COPY target/item-info-retrieval-app-1.0.0.war /usr/local/tomcat/webapps/ROOT.war
ENV DB_URL jdbc:postgresql://postgres-docker:5432/db
ENV BASE_URL https://api.mercadolibre.com/items/
ENV DB_DRIVER org.postgresql.Driver
ENV DB_USER docker
ENV DB_PASSWORD docker

CMD ["catalina.sh", "run"]