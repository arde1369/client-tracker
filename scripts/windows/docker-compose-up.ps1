$env:SPRING_DATASOURCE_URL='jdbc:postgresql://db:5432/client-tracker'; `
$env:SPRING_DATASOURCE_USERNAME='postgres'; `
$env:SPRING_DATASOURCE_PASSWORD='password'; `
$env:OAUTH2_CLIENT_ISSUER='https://dev-aaxgbc4retagp02j.us.auth0.com/'; `
$env:OAUTH2_CLIENT_AUDIENCE='http://localhost:8080/clienttracker'; `
docker compose -f docker-compose.yml up --build
