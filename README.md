# SpringDataREST_ExploreMN
SpringBoot RESTful microservices project for managing and rating tour packages and tours 
<br> Tools:
Java 21, Maven, H2, Spring Data, Spring JPA, Spring Data RESTful API, Spring MVC,
Swagger, Open API, JSON, Lombok 
<br> Fat jar deployed localy exposes endpoints that can be tested with Swagger 



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

13) Add controller to implement business logic into API 
- add TourRating entity/DTO/Validation dependency to POM
- add TourRatingRepository
- add TourRatingService
- add web\TourRatingController


14) build and test
<br>a) Fixed Exception when starting -> changed POM version of open-api starter
<br>b) warning - Exception when opening http://localhost:8080/swagger-ui/index.html
java.lang.IllegalArgumentException: argument "t" is null

15) added GlobalExceptionHandler extending ResponseEntityExceptionHandler

16) add rollback handling with @Transactional

17) add runtime logger logging to log file \logs\myapp.logs

18) Unit testing with JUnit Mockito and SpringBootTest
<br>a) add TourRatingServiceTest.java for testing service methods using JUnit
and MockitoExtension
<br>b) add TourRatingControllerTest.java for endpoint testing (@SpringBootTest in controller)



