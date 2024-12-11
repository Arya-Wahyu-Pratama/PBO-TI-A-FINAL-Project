package minuman.service;

import minuman.entities.Drink;

import java.util.List;

public interface DrinkService {
    List<Drink> getAllDrinks();
    void updateDrink(Drink drink);
    void addDrink(Drink drink);
    Drink getDrinkById(int id);
}
