# certmanager for Certificate manager 

certmanager  is a spring boot microservice which uses to manage all incoming certificates from an IDP for example. Its work is to check if an incoming certificate is already inthe data base, a Mongodb for instance, if it has been blocked or not and if it's the first time it's arrive into the database.

## Building

git clone https://github.com/rjtch/certmanager.git
mvn clean install

## Running

mvn spring-boot:run 

then use http://localhost:8090/swagger-ui.html to see provided endpoints 
