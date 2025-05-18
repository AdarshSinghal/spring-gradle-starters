#!/bin/bash

# Function to stop a server running on a specific port
stop_server() {
    local SERVER_PORT=$1

    # Find the PID of the process running on the specified port
    PID=$(lsof -ti tcp:"$SERVER_PORT")

    if [ -n "$PID" ]; then
        echo "Server is already running on port $SERVER_PORT with PID $PID. Stopping it now..."
        kill -9 "$PID"
        echo "Server stopped."
    else
        echo "No server running on port $SERVER_PORT."
    fi
}

#If need to kill server then uncomment temporarily
#stop_server 8403
#stop_server 8404