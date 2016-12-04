# spring-boot

A RESTFul API was created using Java (8) and Spring Boot. 
The web service is JSON based.

Maven has been used for dependency management. To get the application up and running,
simply download project, install maven and run: mvnw spring-boot:run


For each browser a state is stored, which can be adjusted via user input.
Different browsers on a single computer are treated as different users. 
Therefore, USER_AGENT was used. 

The following operations are supported:
  - GET /state - returns the current state
  - GET /sum - sums all numbers in a string, 
    e.g. “5abc141def” returns 146, if there are no numbers return 0
  - GET /chars - shows the current state without numbers, 
    e.g. “5abc141def” returns abcdef
  - POST /chars - adds the character/s to the string state, 
    e.g. with JSON input {“character”:”a”,”amount”:3} adds “aaa” to the state string
  - DELETE /chars/<character> - deletes the last occurrence of the character in the state string

Furthermore, the following cases have been taken care of:
  - return 400 if the POST request contains invalid JSON
  - character in DELETE has to be a single alphanumeric character, otherwise return 400
  - character in POST request has to be just one alphanumeric character and amount a number from 1 to 9, 
    otherwise return 400
  - if the length of the string state will exceed 200 characters after the POST request, 
    do not change the state and return 400
  - wrong url returns 404
  - each valid JSON response (200 class) includes a user hash/id

