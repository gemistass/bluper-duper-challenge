# bluper-duper-challenge

Spring-Boot application for storing MongoDB documents by REST API calls 

## How to use service


* Install [Maven](https://maven.apache.org/download.cgi)
* Install [MongoDB](https://www.mongodb.com/)

* Clone the repository
	* git clone https://github.com/gemistass/bluper-duper-challenge.git
* Run with 
	* -mvn spring-boot:run
* When running, test with
	* -mvn test

## Available Endpoints
* GET  
		/security/tokenplease  
		/api/homes  
		/api/homes/{homeId}  
		/api/seniors  
		/api/seniors/{seniorId}  
		/api/sensors/  
		/api/sensors/{sensorId}  

* POST  
		/api/homes  
			&nbsp;&nbsp; e.g.: {"name":"Analipsi","type":"PRIVATE"}  
		/api/seniors  
			&nbsp;&nbsp; e.g.: {"homeId":"6139cc8aa6060634c0964f34","name":"Eichiro Oda"}  
		/api/sensors  
			&nbsp;&nbsp; e.g.: {"hardwareVersion":"v3.2","softwareVersion":"v2.3"}  
* DELETE  
		/api/homes/{homeId}  
		/api/seniors/{seniorId}  
		/api/sensors/{sensorId}  

* PUT  
		/assign/{sensorId}/{seniorId}  

## Mongo collections  

* Run MongoDB Shell  
&nbsp;&nbsp;&nbsp;&nbsp;db.homes.find()  
&nbsp;&nbsp;&nbsp;&nbsp;db.seniors.find()  
&nbsp;&nbsp;&nbsp;&nbsp;db.sensors.find()  

### Some more resources
API documentation [here](SimpleApiDocumentation.pdf).
Simple manual [here](BackendChallengeSimpleManual.pdf).

	
