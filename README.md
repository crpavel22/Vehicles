# Vehicles API
This is an example of an REST API to manage Vehicles

## Rest API
The REST API to the example app is described below.

### Vehicles
``There are the operations allowed by Vehicles.``
```` http request
/* Retrive all Vehicles in the database.*/
GET / 
/* Delete the las vehicle added. */
DELETE /last 
````

### CAR
``There are the operations allowed by Car``
`````` http request
#Retrieve all cars
GET /car
#Retrieve a car by id
GET /car/{id}
#Retrieve all cars with model Mazda 3
GET /car?search=model:Mazda 3
#Add a car --> Car properties in json
PUT /car
#Remove a car
DELETE /car/{id}
#Update a car --> Car properties in json 
POST /car/{id}

``````
 
 ### AIRPLANE
 ``There are the operations allowed by Airplane``
 `````` http request
 #Retrieve all airplanes
 GET /airplane
 #Retrieve an airplane by id
 GET /airplane/{id}
 #Retrieve all airplanes with model Mazda 3
 GET /airplane?search=model:Mazda 3
 #Add an airplane --> airplane properties in json
 PUT /airplane
 #Remove a airplane
 DELETE /airplane/{id}
 #Update a airplane --> airplane properties in json 
 POST /airplane/{id}
 
 ``````
 
  ### AMPHIBIAN
  ``There are the operations allowed by Amphibian``
  `````` http request
  #Retrieve all amphibian
  GET /amphibian
  #Retrieve an amphibian by id
  GET /amphibian/{id}
  #Retrieve all amphibians with model Mazda 3
  GET /amphibian?search=model:Mazda 3
  #Add an amphibian --> amphibian properties in json
  PUT /amphibian
  #Remove a amphibian
  DELETE /amphibian/{id}
  #Update a amphibian --> amphibian properties in json 
  POST /amphibian/{id}
  
  ``````
  
### BOAT
``There are the operations allowed by Boat``
`````` http request
#Retrieve all boat
GET /boat
#Retrieve a boat by id
GET /boat/{id}
#Retrieve all boats with model Mazda 3
GET /boat?search=model:Mazda 3
#Add a boat --> boat properties in json
PUT /boat
#Remove a boat
DELETE /boat/{id}
#Update a boat --> boat properties in json 
POST /boat/{id}

``````

### DRONE
``There are the operations allowed by Drone``
`````` http request
#Retrieve all drones
GET /drone
#Retrieve a drone by id
GET /drone/{id}
#Retrieve all drones with model Mazda 3
GET /drone?search=model:Mazda 3
#Add a drone --> drone properties in json
PUT /drone
#Remove a drone
DELETE /drone/{id}
#Update a drone --> drone properties in json 
POST /drone/{id}

``````

### TRUCK
``There are the operations allowed by Truck``
`````` http request
#Retrieve all trucks
GET /truck
#Retrieve a truck by id
GET /truck/{id}
#Retrieve all trucks with model Mazda 3
GET /truck?search=model:Mazda 3
#Add a truck --> truck properties in json
PUT /truck
#Remove a truck
DELETE /truck/{id}
#Update a truck --> truck properties in json 
POST /truck/{id}

``````