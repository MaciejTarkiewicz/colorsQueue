# Colors Queue Micronaut microservice which sent message to RabbitMQ

## Technologies
<ul>
<li>Micronaut</li>
<li>RabbitMQ</li>
<li>Java</li>
<li>Spock</li>
<li>Gradle</li>
</ul>

## Installation
Set up a RabbitMQ instance with Docker

```bash
docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management 
```
Run application and tests:

```bash
 ./gradlew run

 ./gradlew test
```
