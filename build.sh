#!/bin/bash

# Stop and remove all containers, images, volumes, and orphaned containers
echo "Stopping and removing containers, networks, volumes, and orphaned containers..."
docker-compose down --rmi all --volumes --remove-orphans

# Check if the above command was successful
if [ $? -ne 0 ]; then
  echo "Error: Docker Compose down failed. Exiting..."
  exit 1
fi

# Bring Docker Compose services back up
echo "Docker Compose is starting up..."
docker-compose up --build --detach

# Check if the up command was successful
if [ $? -ne 0 ]; then
  echo "Error: Docker Compose up failed. Exiting..."
  exit 1
fi

echo "Docker Compose is up and running in the background."