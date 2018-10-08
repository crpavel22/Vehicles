# Vehicles API
This is an example of an REST API to manage Vehicles

##Rest API
The REST API to the example app is described below.

###Vehicles
``There are the operations allowed by Vehicles.``
```` http request
/* Retrive all Vehicles in the database.*/
GET / 
/* Delete the las vehicle added. */
DELETE /last 
````


###CAR
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
DELETE /cat/{id}
#Update a car --> Car properties in json 
POST /car/{id}

``````
 