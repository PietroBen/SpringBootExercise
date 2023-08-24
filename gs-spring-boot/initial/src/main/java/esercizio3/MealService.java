package esercizio3;

import esercizio2.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    // N.B: La classe MealDao contrassegnata dall'annotazione @Repository si trova nel package apposito denominato "dao"


    // preparazione all'@Autowired attraverso l'istanza della classe MealDao (che si trova nel package dao) e del suo costruttore
    private MealDao mealDao;

    @Autowired
    public MealService(MealDao mealDao) {
        this.mealDao = mealDao;
    }

    // (seguendo la videolezione) Controlli che generano eccezioni nel caso in cui il piatto non abbia un nome,
    // una descrizione o un prezzo inferiore o pari a 0
    public void aggiungiPiatto(Meal meal){
        if (meal == null) throw new IllegalArgumentException("Il piatto non può essere nullo!");
        if (meal.getNome() == null || meal.getNome().isEmpty()) throw new IllegalArgumentException("Il nome del piatto non può essere nullo o vuoto!");
        if (meal.getDescrizione() == null || meal.getDescrizione().isEmpty()) throw new IllegalArgumentException("La descrizione del piatto non può essere nulla o vuota!");
        if (meal.getPrezzo() <= 0) throw new IllegalArgumentException("Il costo del piatto non può essere pari a/o minore di 0!");
        mealDao.aggiungiPiatto(meal);
    }

    public void eliminaPiatto(String mealName){
        mealDao.eliminaPiatto(mealName);
    }

    public void aggiornaPiatto(Meal meal){
        mealDao.aggiornaPiatto(meal);
    }

    public List<Meal> getNuovaListaPiatti() {
        return mealDao.getNuovaListaPiatti();
    }
}
