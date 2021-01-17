# taxi-booking-system

## 4 REST APIs

#### `GET /api/book`
Get history of booking input received by the system

#### `POST /api/book`

The system should pick the nearest available car to the customer location and return the total time taken to travel from the current car location to customer location then to customer destination.

- Request payload

```json
{
  "source": {
    "x": x1,
    "y": y1
  },
  "destination": {
    "x": x2,
    "y": y2
  }
}
```

- Response payload

```json
{
  "car_id": id,
  "total_time": t
}
```

- All car are available initially, and become booked once it is assigned to a customer. It will remain booked until it reaches its destination, and immediately become available again.
- In the event that there are more than one car near the customer location, your service should return the car with the smallest id.
- Only one car be assigned to a customer, and only one customer to a car.
- Cars can occupy the same spot, e.g. car 1 and 2 can be at point (1, 1) at the same time.
- If there is no available car that can satisfy the request, your service should return an empty response, not an error

#### `POST /api/tick`

When called should advance your service time stamp by 1 time unit.

#### `PUT /api/reset`

When called will reset all cars data back to the initial state regardless of cars that are currently booked.

## Run the application
Please use Maven, you can run the application by using

##### `./mvnw spring-boot:run`

Alternatively, you can build the JAR file with ./mvnw clean package and then run the JAR file, as follows:

##### `./mvnw clean package`
##### `java -jar target/rest-service-0.0.1-SNAPSHOT.jar`

## Run the application using Docker

You also need Docker, which only runs on 64-bit machines

Build the docker image

##### `./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=tanc0160/taxi-booking-system`

Run docker image

##### `docker run --memory="2g" -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 -t tanc0160/taxi-booking-system`

## Integration Tests

python basic_solution_checker.py

