# BipCars

## Description

This is a demo application for the Comms API meetup in Lagos and Accra, that show cases the use of 
Infobip's web API to develop an Interactive Voice Response (IVR) application. 

## Technologies

- Java
- Maven
- Jakarta EE
- Tom EE
- Docker
- HSql db

## Set up

1. Clone git repository.
2. Copy application.properties file to application.local.properties and input the necessary details.
3. If not using docker, download a Jakarta EE application server (Tom EE).

## Deployment

1. Package the application
    ```shell
    mvn package
   ```
2. Build docker image
    ```shell
    docker build -t bip-cars-image .
   ```
3. Run docker image
    ```shell
     docker run --name=bip-cars -d -p 8080:8080 bip-cars-image
   ```

## Usage

After deployment, you can use any rest client to make requests to the application end points.

More information on this can be found in the Postman documentation.

[Postman API Documentation](https://documenter.getpostman.com/view/18957803/2s83zjqN3H)

