#!/bin/bash

# DbUtil.sh: Contains common logic for managing the DB container.

# Function to manage DB container
manage_db_container() {
    DB_CONTAINER="neo4j-db-ctr"  # Container name (from docker-compose.yml)
    DB_SERVICE="neo4j-db-svc"    # Service name (from docker-compose.yml)

    # $1: Action (start/stop)

    # Check if the container is running
    if docker ps --filter "name=$DB_CONTAINER" --format "{{.Names}}" | grep -q "^$DB_CONTAINER$"; then
        if [ "$1" == "start" ]; then
            echo "Container [$DB_CONTAINER] is already running."
        elif [ "$1" == "stop" ]; then
            echo "Stopping container [$DB_CONTAINER]..."
            docker-compose down $DB_SERVICE
        else
            echo "Invalid action. Please use 'start' or 'stop'."
        fi
    else
        if [ "$1" == "start" ]; then
            echo "Starting container [$DB_CONTAINER]..."
            docker-compose up $DB_SERVICE -d
        elif [ "$1" == "stop" ]; then
            echo "Container [$DB_CONTAINER] is not running."
        else
            echo "Invalid action. Please use 'start' or 'stop'."
        fi
    fi
}
