# SpringDataREST_ExploreMN
SpringBoot RESTful microservices project for managing tour packages and tours 
Java 21, Maven, H2, Spring Data, Spring JPA, Spring Data RESTful API, Swagger, Open API, JSON
Fat jar deployed localy exposes endpoints that can be tested with SwaggerUI 



1) create spring boot project: https://start.spring.io/
2) download java 21 
https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html
3) install maven 
4) import maven project to eclipse build and run
maven clean install
java -jar target/exploremn-jpa-0.0.1-SNAPSHOT.jar
http://localhost:8080/
5) add entities Tour, TourPackage
6) add repos extending JpaRepository : TourRepository, TourPackageRepository
7) add services TourPackageService,  TourService
8) use Tour service to preload  tour data from JSON file into TourRepository
9) Add spring query services (via Property Expressions) to look for difficult tours or tours that are part of a tour package
11) expose endpoints with Spring Data Rest (default)
12) Add Swagger Documentation
maven clean install
java -jar target\exploremn-jpa-0.0.1-SNAPSHOT.jar 
http://localhost:8080/swagger-ui/index.html



