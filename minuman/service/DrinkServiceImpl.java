package minuman.service;

import minuman.entities.Drink;
import minuman.repositories.DrinkRepository;

import java.util.List;

public class DrinkServiceImpl implements DrinkService {
    private final DrinkRepository drinkRepository;

    public DrinkServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public List<Drink> getAllDrinks() {
        return drinkRepository.getAllDrinks();
    }

    @Override
    public void updateDrink(Drink drink) {
        drinkRepository.updateDrink(drink);
    }

    @Override
    public void addDrink(Drink drink) {
        drinkRepository.addDrink(drink);
    }

    @Override
    public Drink getDrinkById(int id) {
        return drinkRepository.getDrinkById(id);
    }
}