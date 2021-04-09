# ADN Diginet Limited - Job assignment for managing advertising campaigns.
Good Day. Thanks for giving me an opportunity to learn something new.
This is a simple UI application that is developed without any security, custom functionality implementation and validation.

# Docker commands
## Step-1: Run MySql Server
docker run --name localhost -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=advertising-campaigns -e MYSQL_USER=mizan -e MYSQL_PASSWORD=mizan -d mysql:5.6
 
## Step-2: Run Project
### Step-2-1: Run API
docker run -p 8086:8086 --name advertising-campaigns-api --link localhost:mysql -d kmmizanurrahmanjp/advertising-campaigns-api:1.0.0
### Step-2-2: Run UI
docker run -it  -p 80:80/tcp --name advertising-campaigns-ui -d kmmizanurrahmanjp/advertising-campaigns-ui:1.0.0


## Technology Stacks:
ReactJs

