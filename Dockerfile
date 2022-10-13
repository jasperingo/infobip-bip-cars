FROM tomee:latest

COPY ./target/bip-cars-1.0.war /usr/local/tomee/webapps/bip-cars-1.0.war

CMD ["catalina.sh", "run"]

EXPOSE 8080
