<style>
  .special{
    color: #aabbff;
    font-weight:bold;
  }
  .text-violet{
    color: #aabbff;
  }
</style>


# Getting Started

This application is developed and tested on [Arch Linux](https://en.wikipedia.org/wiki/Arch_Linux). 
The app should be compatible with any other linux / Mac as well. 

> <span class="text-violet">The windows users should refer commands from the scripts and can execute manually in command prompt / powershell.</span>

## Prerequisite

- <span class="text-violet">Docker/Podman should be available to spin up the DB<span>


- <span class="text-violet">If Docker/Podman not available, then DB should be installed and should listen to the port mentioned in the application<span>


## What if you needed simple template app

This app is template for quick prod readiness. The script and dockerized version help to deploy in remote server quickly.
But, feel free to delete them if you don't require.

You can even move code from application-local.properties to application,properties and remove profile switching if not need to maintain multiple environment (local / prod)

## Testing

Run the following script to test. 
But, it is highly recommended to go through script fist, what is it doing.

```shell
    ./dkr-build.sh
```

> - <span class="special">This script takes around 2 minutes. Gradle download dependencies. Please be patient.</span>
> - <span class="special">If you already build app image, then you can just issue `docker compose` commands  instead of this script.</span>

The script `./dkr-build.sh` uses following files:-


- <span class="special">Dockerfile</span> - Responsible for building image for Java app


- <span class="special">docker-compose.yml</span> - Responsible for spinning up postgres db and java app stack.
  - Make sure that java-app image already exist, before you run `docker compose up -d`
  - Even `./dkr-build.sh` build the java-app image first.
  

- <span class="special">.env</span> - This file is used to inject credentials.
  - This file should not be committed to the git. 
  - I have committed this in git so that you can clone it, and can run the application.


If you just need to test in local, not in docker. Then still, you need postgres to be up atleast.
You can refer `docker-compose.yml` and just start DB service

```shell
    docker compose up -d postgresql
```

This should start DB container on port mentioned in `docker-compose.yml`.

## Common Errors

- The container name "/postgres-local-db" is already in use
  - Resolution:- You can rename container in `docker-compose.yml` and in `dkr-build.sh` files.