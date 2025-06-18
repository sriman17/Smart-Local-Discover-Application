Architectural Diagram:

 ![image](https://github.com/user-attachments/assets/a2fea8d1-4a3a-4bce-bd00-ba1b1f16daf1)


Business Document

Overview:
Bank Locator is a two-service microservice application that allows users to find banks within a 10-mile radius of a given ZIP code.

Key Features:
1.	User submits a ZIP code.
2.	 The system fetches coordinates using Google Maps.
3.	Nearby banks are listed based on the location.

User Flow:
1.	User provides a ZIP code via the ‘/api/banks/{ZIP}’ endpoint.
2.	bankService sends the ZIP code to mapsService.
3.	mapsService calls the Google Maps API to:
   i. Get geolocation (latitude/longitude)
   ii. Get nearby banks using Places API
4.	Bank-Service receives and returns the list of banks to the user.

Business Goals:
1.	Provide localized bank search to end-users
2.	Modular and scalable architecture
3.	Ready for deployment across multiple regions


User Enters ZIP code.
1. Bank Service --> Front-facing REST service.
2. Maps Service --> Handles geolocation and places lookup.









Technical Documentation:

Architecture Overview:

1. Microservices:
	i. ‘bankService’: User-facing REST API
ii. ‘mapsService’: Handles external API (Google Maps)

2.	Communication:
Internal REST calls between services.

3.	 Stack: 
i. Backend         : Java 17, Spring Boot 
ii. Build Tool      : Maven
iii. REST Client   : Rest Template
iv. API Docs        : Springdoc (Swagger)
v. External APIs : Google Maps Geocoding + Places API

4.	API Endpoints:

1.	‘bankService’ (‘localhost:8080’)
Endpoint and it’s Description:
 ‘/api/banks/76207’     Returns list of nearby banks for a ZIP code .

2.	‘mapsService’ (‘localhost:8081’)
 Endpoint  and it’s Description:
 ‘/api/maps/coordinates?zip={ZIP code}’ This retuens coordinates
 ‘/api/maps/places?lat=..&lng=..’  This returns banks nearby


Data Flow

 ![image](https://github.com/user-attachments/assets/14885bd7-6518-4447-b7a2-f1fa6fd1957c)
