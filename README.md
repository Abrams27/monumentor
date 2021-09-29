# monumentor

Mobile application (iOS) for finding and searching monuments in Poland. 

### Usage:
User can filter monuments by:
- categories,
- maximal number of monuments,
- distance from the current location

Application after applying filters returns list with proposed monuments and exports them to the Google Maps. User can add, remove monuments and modify order of elements.

### Running backend on the students:

    export JAVA_HOME=/opt/jdk-11.0.2
    mvn clean package spring-boot:repackage
    mvn clean install
    mvn spring-boot:run
