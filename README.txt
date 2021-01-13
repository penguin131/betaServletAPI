For run app:</br></br>
Precondition:</br>
- java 8</br>
- maven 3.6.1</br>
- docker</br></br>
Building:</br>
1) Clone project and go to project folder</br>
2) docker-compose up -d</br>
3) set storage.path for images directory and database.url for database in application.properties</br>
4) mvn clean package</br>
5) mvn cargo:run for start project into embedded server</br>
