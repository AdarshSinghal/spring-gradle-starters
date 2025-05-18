#!/bin/bash

# Source the DbUtil.sh file to access the manage_db_container function
source ./common/DbUtil.sh

# Call the manage_db_container function to start the container
manage_db_container "start"


#echo 'Neo4j Graph UI available at http://localhost:7474'  #Already logging from ApplicationConfig.java
echo 'For credentials or bolt server port, please refer to docker-compose.yml file'