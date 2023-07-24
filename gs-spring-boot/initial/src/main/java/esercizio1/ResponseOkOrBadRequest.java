package esercizio1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

// Exercise 4: Create a GetMapping that returns 400 - Bad request or 200 - OK based on a random boolean
//  - Annotate a new class with the @RestController annotation.
//  - Create a new endpoint "/random" using the @GetMapping annotation.
//  - In the method, return a ResponseEntity with 200 OK or 400 Bad Request based on the result of new Random().nextBoolean()


@RestController
public class ResponseOkOrBadRequest {
    @GetMapping(value = "/random")
    public ResponseEntity<Boolean> okOrBad() {
        boolean trueOrFalse = new Random().nextBoolean();
        if (trueOrFalse == true) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

}
