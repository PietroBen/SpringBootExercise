package esercizio3;

import esercizio2.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Exercise 1: Create a PutMapping to add a new meal

// 1 - Create a new endpoint "/meal" using the @PostMapping annotation.
// 2 - In the method, add a RequestBody for the new Meal object.
// 3 - Add the new meal to the list of meals.

@RestController
public class UpdateMeal {

    private MealService mealService;


    @Autowired
    public UpdateMeal(MealService mealService) {
        this.mealService = mealService;
    }

   public List<Meal> mealList = Arrays.asList(
            new Meal("Spaghetti alla carbonara", "Uova, guanciale, formaggio pecorino", 8.00),
            new Meal("Penne alla norma", "Sugo di pomodoro, melanzane, ricotta, basilico", 7.00),
            new Meal("Lasagne alla bolognese", "Sugo di pomodoro, carne, besciamella, parmigiano", 8.00),
            new Meal("Linguine alle vongole", "Vongole veraci, prezemolo, vino bianco, aglio", 9.00),
            new Meal("Trofie al pesto", "Basilico, aglio, pinoli, parmigiano", 7.00)
    );


    // Trasformazione da Arrays.asList ad ArrayList per attuare la richiesta PUT e le altre richieste e aggiungere nuovi piatti,
    // in quanto la lista di prima è immutabile.
    List<Meal> nuovaListaPiatti = new ArrayList<>(mealList);

    @GetMapping(value = "/get/meals")
    public ResponseEntity<List<Meal>> ottieniListaPiatti() {
        return ResponseEntity.ok(mealService.getNuovaListaPiatti());
    }


    @org.springframework.web.bind.annotation.PutMapping(value = "/meal")
    public ResponseEntity<String> aggiungiPiatto(@RequestBody Meal meal) {
        try {
            mealService.aggiungiPiatto(meal);
            return ResponseEntity.ok("Piatto aggiunto!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // Exercise 2: Create a PostMapping to update a meal by name

    // 1 - Create a new endpoint "/meal/{name}" using the @PutMapping annotation.
    // 2 - In the method, add a PathVariable for the name and a RequestBody for the updated Meal object.
    // 3 - Update the meal with the corresponding name using the information from the RequestBody.


    @PostMapping(value = "/meal/{name}")
    public ResponseEntity<String> aggiornaNomePiatto(@PathVariable String nome, @RequestBody Meal meal) {
        this.nuovaListaPiatti.removeIf(piatto -> piatto.getNome().equals(nome));
        this.nuovaListaPiatti.add(meal);
        return ResponseEntity.ok("Nome del piatto aggiornato!");
    }


    // Exercise 3: Create a DeleteMapping to delete a meal by name

    // 1 - Create a new endpoint "/meal/{name}" using the @DeleteMapping annotation.
    // 2 - In the method, add a PathVariable for the name.
    // 3 - Delete the meal with the corresponding name.

    @DeleteMapping(value = "/meal/{name}")
    public ResponseEntity<String> eliminaNomePiatto(@PathVariable String name) {
        mealService.eliminaPiatto(name);
        return ResponseEntity.ok("Nome del piatto eliminato!");
    }


    // Exercise 4: Create a DeleteMapping to delete all meals above a certain price

    // 1 - Create a new endpoint "/meal/price/{price}" using the @DeleteMapping annotation.
    // 2 - In the method, add a PathVariable for the price.
    // 3 - Delete all meals with a price above the price from the PathVariable.


    @DeleteMapping(value = "/meal/price/{price}")
    public ResponseEntity<String> eliminaAlcuniPiatti(@PathVariable double prezzo) {
        for (Meal meal : nuovaListaPiatti) {
            if (meal.getPrezzo() >= prezzo) {
                nuovaListaPiatti.remove(0);
                nuovaListaPiatti.remove(1);
                nuovaListaPiatti.remove(1);
            }

        }
        return ResponseEntity.ok("I piatti sono stati eliminati!");
    }


    // Exercise 5: Create a PutMapping to update the price of a meal by name

    // 1 - Create a new endpoint "/meal/{name}/price" using the @PutMapping annotation.
    // 2 - In the method, add a PathVariable for the name and a RequestBody for the updated price.
    // 3 - Update the price of the meal with the corresponding name using the information from the RequestBody.


    @PutMapping(value = "/meal/{name}/price")
    public ResponseEntity<String> aggiornaPrezzoPiattoSpecifico(@PathVariable String name, @RequestBody double price) {
        for (Meal meal : nuovaListaPiatti) {
            if (meal.getNome().equals(name) && meal.getPrezzo() != price) {
                meal.setPrezzo(price);
                return ResponseEntity.ok("Ecco il nuovo prezzo del piatto " + name + " aggiornato a" + price);
            } else {
                return ResponseEntity.badRequest().body("Piatto non trovato");
            }
        }
        return ResponseEntity.ok("Ecco il nuovo prezzo del piatto " + name + " aggiornato a" + price);
    }
}