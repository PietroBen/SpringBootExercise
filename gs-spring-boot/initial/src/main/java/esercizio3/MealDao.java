package esercizio3;

import esercizio2.Meal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class MealDao {

    List<Meal> piatti = Arrays.asList(
            new Meal("Spaghetti alla carbonara", "Uova, guanciale, formaggio pecorino", 8.00),
            new Meal("Spaghetti alla norma", "Sugo di pomodoro, melanzane, ricotta, basilico", 7.00),
            new Meal("Spaghetti alla bolognese", "Sugo di pomodoro, ragù di carne, parmigiano", 8.00),
            new Meal("Spaghetti alle vongole", "Vongole veraci, pomodorino, vino bianco, aglio", 9.00),
            new Meal("Spaghetti alla siciliana", "Melanzane fritte, pomodoro, ricotta salata", 7.00)
    );

    List<Meal> nuovaListaPiatti = new ArrayList<>(piatti);

    public void aggiungiPiatto(Meal meal){
        this.nuovaListaPiatti.add(meal);
    }

    public void eliminaPiatto(String mealName){
        this.nuovaListaPiatti.removeIf(nomePiatto -> nomePiatto.getNome().equals(mealName));
    }

    public void aggiornaPiatto(Meal meal){
        this.nuovaListaPiatti.removeIf(piattoDaRimuovere -> piattoDaRimuovere.getNome().equals(meal.getNome()));
        this.nuovaListaPiatti.add(meal);
    }

    public List<Meal> getNuovaListaPiatti() {
        return this.nuovaListaPiatti;
    }
}
