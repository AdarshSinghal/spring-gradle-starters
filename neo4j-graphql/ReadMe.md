<style>
  .special{
    color: #aabbff;
    font-weight:bold;
  }
  .text-violet{
    color: #aabbff;
  }

.text-red{
    color: #ff7777;
  }
</style>

# Getting started

This application is developed and tested on [Arch Linux](https://en.wikipedia.org/wiki/Arch_Linux) with Docker.
The app should be compatible with any other linux / Mac as well.

> <span class="text-violet">The windows users should refer commands from the scripts and can execute manually in command
> prompt / powershell.</span>

## Prerequisite

- <span class="text-violet">Docker/Podman should be available to spin up the DB<span>


- <span class="text-violet">If Docker/Podman not available, then DB should be installed and should listen to the port mentioned in the application<span>

## What if you needed simple template app

This app is template for quick prod readiness. The script and dockerized version help to deploy in remote server quickly.
But, feel free to delete them if you don't require.

You can even move code from application-local.properties to application,properties and remove profile switching if not need to maintain multiple environment (local / prod)



## Testing after application starts

When you start application, it will start the neo4j db too.
In case, you're already running neo4j database, then you may need to alter behavior of application start event, in case
it conflicts.

GraphiQL UI is available at: http://localhost:8500/graphiql

Neo4j Graph UI available at http://localhost:7474
> - Username - neo4j
> - Password - neo4jTest

> These credentials also present in `docker-compose.yml`

## Important Files to review

- <span class="special">docker-compose.yml</span> - Neo4j DB credentials. You can also use variable ${DB_PASSWORD} and
  .env file if don't want to leave credentials in `docker-compose.yml` file.


- <span class="special">script directory</span> - Contains script which you can execute to quickly start / stop DB


- <span class="special">DbLifecycleManager.java</span> - `src/main/java/.../config/DbLifecycleManager.java`
    - Responsible to make sure
        - DB starts before application starts
        - DB stops before application stops.
    - This is for developer's convenience and not ideal for prod where DB deployment should be handled separately and must not bind with application lifecycle.
  
    - For prod readiness, developer should delete this file once convenience is not needed. Let application fail if DB not available to remind you that you missed important step.

- <span class="special">Neo4jWaiter.java</span> - `src/main/java/.../config/Neo4jWaiter.java`
    - Responsible to make sure that application should wait until neo4j is ready to accept connections.


- <span class="special">resources/migrations</span> - Responsible to prepare graph on application startup if not
  present.
    - Developer can refer query from the files if they're not familiar with Neo4j's Cypher Query Language.
    - It validates on application startup, and can fail if user changes the schema manually.
    - (Flyway / Liquibase) Migration works best when you use migration to change schema, rather than manually updating
      it.
    - In case migration fail, not allowing to start application, we can delete migration data (tables / rows in RDBMS,
      nodes / relations in graph) from database.
    - Alternatively, we can delete database data itself, or delete docker volume.

<br/>

> Note: DB shutdown will not work if application is forcefully shutdown without letting it do anything while shutting
> down process. You can refer how @Predestroy in Spring works.

## Common Errors

- If application failed to start due to unsuccessful migration, then stop container, delete volume and start container.