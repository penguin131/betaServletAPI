For run app:

Precondition:
- java 8
- maven 3.6.1
- docker

Building:
1) Clone project and go to project folder
2) docker-compose up -d
3) set storage.path for images directory and database.url for database in application.properties
4) mvn clean package
5) mvn cargo:run for start project into embedded server
