# rest-api-with-db-template

Enkel template for et usikret REST-api som eier en underliggende database.

# Kom i gang
1. Bygge rest-api-with-db-template:
    * bygge og kjøre tester: `gradlew clean build`
2. Kjør i docker med siste versjon fra remote master: `docker-compose up -d`
3. Kjør fra cli mot docker-postgres 
    - `docker-compose up -d postgres`
    - `gradlew runServer` 