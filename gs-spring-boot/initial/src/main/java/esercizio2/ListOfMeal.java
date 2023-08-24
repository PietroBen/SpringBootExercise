package esercizio2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


//Exercise 1: Create a GetMapping that returns a list of meals
//
// 1 - Annotate a new class with the @RestController annotation.
// 2 - Create a new endpoint "/meals" using the @GetMapping annotation.
// 3 - In the method, return a list of Meal objects.
@RestController
public class ListOfMeal {
    public List<Meal> mealList = Arrays.asList(
            new Meal("Spaghetti alla carbonara", "Uova, guanciale, formaggio pecorino", 8.00),
            new Meal("Penne alla norma", "Sugo di pomodoro, melanzane, ricotta, basilico", 7.00),
            new Meal("Lasagne alla bolognese", "Sugo di pomodoro, carne, besciamella, parmigiano", 8.00),
            new Meal("Linguine alle vongole", "Vongole veraci, prezemolo, vino bianco, aglio", 9.00),
            new Meal("Trofie al pesto", "Basilico, aglio, pinoli, parmigiano", 7.00)
    );

    @GetMapping(value = "/meals")
    public List<Meal> listaPiatti() {
        return mealList;
    }

    // Exercise 2: Create a GetMapping that returns a meal by name
// 1 - Annotate a new class with the @RestController annotation.
// 2 - Create a new endpoint "/meal/{name}" using the @GetMapping annotation.
// 3 - In the method, add a query parameter "name" using the @PathVariable annotation.
// 4 - Return a Meal object with the corresponding name.


    @RestController
    public class MealByName {
        ListOfMeal listOfMeal = new ListOfMeal();

        @GetMapping(value = "/meal/{name}")
        public ResponseEntity<?> nomePiatto(@PathVariable String name) {
            for (Meal meal : listOfMeal.listaPiatti()) {
                if (meal.getNome().equals(name)) {
                    return ResponseEntity.ok(meal.getNome());
                }
            }
            return ResponseEntity.status(404).body("Nome non trovato!");
            }
    }

    // Exercise 3: Create a GetMapping that returns a meal by any word of description
// 1 - Annotate a new class with the @RestController annotation.
// 2 - Create a new endpoint "/meal/description-match/{phrase}" using the @GetMapping annotation
// 3 - In the method, add a query parameter "description" using the @PathVariable annotation.
// 4 - Return a Meal object with the corresponding description.

    @RestController
    public class MealByAnyWordsOfDescription {
        ListOfMeal listOfMeal = new ListOfMeal();

        @GetMapping(value = "/meal/description-match/{description}")
        public ResponseEntity<?> descrizionePasto(@PathVariable String description) {
            for (Meal meal : listOfMeal.listaPiatti()) {
                if (meal.getDescrizione().equals(description)) {
                    return ResponseEntity.ok(meal.getDescrizione());
                }
            }
            return ResponseEntity.badRequest().body("Descrizione non trovata!");
        }

// Exercise 4: Create a GetMapping that returns a meal by price range
// 1 - Annotate a new class with the @RestController annotation.
// 2 - Create a new endpoint "/meal/price" using the @GetMapping annotation.
// 3 - In the method, add two query parameters "min" and "max" using the @RequestParam annotation.
// 4 - Return a list of Meal objects with prices within the specified range.

        @RestController
        public class MealByPrice {

            ListOfMeal listOfMeal = new ListOfMeal();

            @GetMapping(value = "/meal/price")
            public List<Meal> prezzoPasto(
                    @RequestParam("min") double min,
                    @RequestParam("max") double max) {
                for (Meal meal : listOfMeal.listaPiatti()) {
                    if (meal.getPrezzo() >= min && meal.getPrezzo() <= max) {
                        return listOfMeal.listaPiatti();
                    }
                }
                return listOfMeal.listaPiatti();
            }
        }
    }
}
