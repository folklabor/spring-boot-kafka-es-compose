# spring-boot-kafka-es-compose
Example Spring Boot applications using Kafka for the integration layer and Elasticsearch for persistence and reporting. Packaged using Docker Compose.

# Application Components
This application consists of 4 different services each with discrete responsibilities.

---

# Customer Producer Service
Spring Boot Web Application http://localhost:8081

This service is responsible for collecting Customer data and preserving it in a Kafka topic. At this point, its job is done. Any consumers that are interested in this data may subscribe. One such consumer is the Customer Report Service.
To interact with the Customer Producer Service, you can use curl / postman as described in the Swagger docs or use the "Try It Now" functionality which is part of the documentation.

---

# Customer Report Service:
Spring Boot Web Application http://localhost:8080

This service is responsible for ingesting, persisting and reporting on customer data.
If it goes down for maintenance or fails for some reason, it will resume these tasks when it comes back online. No customers should be lost and ordering should be preserved.
To interact with the Customer Report Service, you can use curl / postman as described in the Swagger docs or use the "Try It Now" functionality which is part of the documentation.

---

# Kafka for messaging
Port:9092
This service is used for building real-time data pipelines and streaming apps. "It is horizontally scalable, fault-tolerant, wicked fast, and runs in production in thousands of companies."

In the context of this application, it is being used for its robust Publish / Subscribe functionality which will preserve Customers in a topic and track subscriber progress within a partition so that customers are processed only once and in the order in which they are sent.

---

# Elasticsearch
Port: 9200/9300
This service is being used to store Customers and report on them in an efficient way.

---

# Running the application
The application has been packaged in Docker Compose in order to orchestrate the creation of the systems with the correct versions etc. 

1. Open your terminal and run docker compose / build images

```bash
docker-compose up --build
```

(this could take a while because it has to download all dependencies)

2. When containers are up: Open up the Customer Producer Service in a browser
  
<http://localhost:8081>

3. Navigate to the Customer Producer api docs
<http://localhost:8081/swagger-ui.html#/customer-api

4. Create a couple of customers customers:
* Click on the "POST" / customer endpoint b) Click on the "Try it out" button
* Create a customer payload ie:
```javascript
{
"dateOfBirth": "1950-09-21", "firstName": "William", "lastName": "Murray"
}
```
* Press the "Execute" button

5. Navigate to the Customer Reporting application
<http://localhost:8080>

6. Navigate to the Customer Metrics api docs
<http://localhost:8080/swagger-ui.html#/customer-metrics>

7. Calculate the ​median age​ of customers within a date range:
* Click on the "GET" /customer/metrics/median-age endpoint
* Click on the "Try it out" button
* Enter a start and end date for the range (note the order. Swagger reverses them) d) Press the "Execute" button

8. Calculate the ​average age ​of customers within a date range:
* Click on the "GET" /customer/metrics/average-age endpoint
* Click on the "Try it out" button
* Enter a start and end date for the range (note the order. Swagger reverses them)   
* Press the "Execute" button

9. To see all of the services running, you can List docker processes by opening up a new terminal tab in the directory and entering the command

```bash
docker ps
```

10. When you are done testing, open up a new terminal tab in the directory and enter the command
```bash
docker-compose down
```
