#!/bin/bash

# Source the DbUtil.sh file to access the manage_db_container function
source ./common/DbUtil.sh

# Call the manage_db_container function to stop the container
manage_db_container "stop"
