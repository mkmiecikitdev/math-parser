To build in "dev" profile run in project directory: `./mvnw clean package -P dev`

To build in "prod" profile run in project directory: `./mvnw clean package -P prod`

Tu run app with some parameter run in /target directory: `java -jar math-parser-0.0.1-SNAPSHOT.jar "2+2*3"
`

To run on `prod` profile you have to run mysql instance locally (or connect to remote). Then you have to change properties in `application-prod.properties`
