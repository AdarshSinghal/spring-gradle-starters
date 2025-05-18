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


## Testing

Start the mysql container using `docker compose up -d` in the root directory of the project
where `docker-componse.yml` file is present.

```shell
    docker compose up -d
```

## Common Errors

- The container is already in use
    - Check if you're able to connect to the existing running container.
    - Alternatively, you can rename container in `docker-compose.yml` and in `dkr-build.sh` files.