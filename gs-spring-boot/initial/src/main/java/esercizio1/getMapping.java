package esercizio1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class getMapping {
    // Exercise 1: Create a GetMapping that returns a basic "string" as a response
    // - Create a new endpoint "/hello" using the @GetMapping annotation.
    // - In the method, return a simple string such as "Hello World!".
    @GetMapping(value = "/hello")
    public String helloWorld() {
        return "Hello World";
    }

    //Exercise 2: Create a GetMapping that returns a ResponseEntity as a response
    // - Create a new endpoint "/greeting" using the @GetMapping annotation.
    // - In the method, return a ResponseEntity object with a string message such as "Good Afternoon!".
    // - You can also set the HTTP status code in the ResponseEntity, such as "200 OK".
    @GetMapping(value = "/greeting")
    public ResponseEntity<String> responseEntity() {
        return ResponseEntity.status(200).body("Buon pomeriggio!");
    }

}
