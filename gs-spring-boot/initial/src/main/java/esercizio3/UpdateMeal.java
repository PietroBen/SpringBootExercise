package esercizio3;

import esercizio2.Meal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

// Exercise 1: Create a PutMapping to add a new meal

// 1 - Create a new endpoint "/meal" using the @PostMapping annotation.
// 2 - In the method, add a RequestBody for the new Meal object.
// 3 - Add the new meal to the list of meals.

@RestController
public class UpdateMeal {
    private List<Meal> meals = new ArrayList<>();
    //1)
    @PostMapping("/meal")
    public ResponseEntity<String> addMeal(@RequestBody Meal meal) {
        meals.add(meal);
        return ResponseEntity.ok("Meal added!");
    }

    // Exercise 2: Create a PostMapping to update a meal by name

    // 1 - Create a new endpoint "/meal/{name}" using the @PutMapping annotation.
    // 2 - In the method, add a PathVariable for the name and a RequestBody for the updated Meal object.
    // 3 - Update the meal with the corresponding name using the information from the RequestBody.

    @PutMapping("/meal/{name}")
    public ResponseEntity<String> updateMealByName(@PathVariable String name, @RequestBody Meal updatedMeal) {
        for (Meal meal : meals) {
            if (meal.getNome().equals(name)) {
                meal.setNome(updatedMeal.getNome());
                meal.setPrezzo(updatedMeal.getPrezzo());

                return ResponseEntity.ok("Meal updated!");
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Exercise 3: Create a DeleteMapping to delete a meal by name

    // 1 - Create a new endpoint "/meal/{name}" using the @DeleteMapping annotation.
    // 2 - In the method, add a PathVariable for the name.
    // 3 - Delete the meal with the corresponding name.


    @DeleteMapping("/meal/{name}")
    public ResponseEntity<String> deleteMeal(@PathVariable String name) {
        meals.removeIf(meal -> meal.getNome().equals(name));
        return ResponseEntity.ok("Meal deleted!");
    }


    // Exercise 4: Create a DeleteMapping to delete all meals above a certain price

    // 1 - Create a new endpoint "/meal/price/{price}" using the @DeleteMapping annotation.
    // 2 - In the method, add a PathVariable for the price.
    // 3 - Delete all meals with a price above the price from the PathVariable.

    @DeleteMapping("/meal/price/{price}")
    public ResponseEntity<String> deleteMealsAbovePrice(@PathVariable double prezzo) {
        meals.removeIf(meal -> meal.getPrezzo() > prezzo);
        return ResponseEntity.ok("Meals above price deleted!");
    }


    // Exercise 5: Create a PutMapping to update the price of a meal by name

    // 1 - Create a new endpoint "/meal/{name}/price" using the @PutMapping annotation.
    // 2 - In the method, add a PathVariable for the name and a RequestBody for the updated price.
    // 3 - Update the price of the meal with the corresponding name using the information from the RequestBody.

    @PutMapping("/meal/{name}/price")
    public ResponseEntity<String> updateMealPrice(@PathVariable String name, @RequestBody double updatedPrice) {
        for (Meal meal : meals) {
            if (meal.getNome().equals(name)) {
                meal.setPrezzo(updatedPrice);
                return ResponseEntity.ok("Meal price updated!");
            }
        }

        return ResponseEntity.notFound().build();
    }
}