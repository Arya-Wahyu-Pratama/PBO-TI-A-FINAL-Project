package minuman.repositories;

import minuman.entities.Drink;
import java.util.List;

public interface DrinkRepository {
    List<Drink> getAllDrinks();
    void updateDrink(Drink drink);
    void addDrink(Drink drink);
    Drink getDrinkById(int id);
}