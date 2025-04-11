# Hotel Booking System

A hotel management system API built using **Spring Boot** and **Maven**.

## Features

- Fetch all rooms in the hotel.
- Search/Filter rooms.

## Technologies Used

- **Java 21**
- **Spring Boot 2.x**
- **Maven** (dependency management)
- **PostgreSQL** (database)

## How to Get It Running

### Prerequisites

Before getting started, ensure you have the following software installed:

- **Java 21**: Spring Boot requires a JDK for compilation and running. You can download it from the [official OpenJDK site](https://jdk.java.net/21/).
- **Maven**: For dependency management and building the project. Install it from the [official Maven website](https://maven.apache.org/download.cgi).
- **Docker** (optional): If you want to run the database and services in Docker containers.
- **PostgreSQL**: Make sure you have PostgreSQL installed or use Docker to run PostgreSQL.

### Steps to Get the Hotel Booking System Running

#### 1. Clone the Repository

Clone the repository to your local machine:

```bash
git clone https://github.com/M-Moussa-De/HotelBookingSystem.git
cd HotelBookingSystem
```

#### 2. Change profile

In application.properties uncomment ```spring.profiles.active=dev``` and comment out ```spring.profiles.active=prod``` 
unless you want to run it in docker container, and in this case, docker process/desktop must
be running.

#### 3. Run this script

```bash
./build.sh
```

which will build and run the application using docker compose. No need to execute the script if you
intend to run the app locally.

#### 3. Visit
```http://localhost:8081``` if you run the app locally, 
or
```http://localhost:8080``` if you run the app inside a docker container

To be continued...