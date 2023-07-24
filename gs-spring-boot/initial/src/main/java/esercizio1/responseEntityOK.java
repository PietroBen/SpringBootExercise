package esercizio1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Exercise 3: Create a new class with a GetMapping that returns a ResponseEntity a
// - Annotate a new class with the @RestController annotation.
// - Create a new endpoint "/info" using the @GetMapping annotation.
// - In the method, return a ResponseEntity with 200 OK
@RestController
public class responseEntityOK {

    @GetMapping(value = "/info")
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("Get info");
    }
}