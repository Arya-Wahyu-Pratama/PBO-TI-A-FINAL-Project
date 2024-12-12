package minuman.views;

import minuman.entities.Drink;
import minuman.service.DrinkService;

import java.util.Scanner;

public class AdminView {
    private final DrinkService drinkService;

    public AdminView(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    public void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. View All Drinks");
            System.out.println("2. Add New Drink");
            System.out.println("3. Update Drink");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    drinkService.getAllDrinks().forEach(drink -> {
                        System.out.println("ID: " + drink.getId() + ", Name: " + drink.getName() + ", Size: " + drink.getSize() + ", Stock: " + drink.getStock() + ", Price: Rp" + drink.getPrice());
                    });
                    break;
                case 2:
                    System.out.print("Enter drink name: ");
                    String name = scanner.next();
                    System.out.print("Enter drink size (SMALL/MEDIUM/LARGE): ");
                    String size = scanner.next().toUpperCase();
                    System.out.print("Enter stock: ");
                    int stock = scanner.nextInt();
                    System.out.print("Enter price: Rp");
                    double price = scanner.nextDouble();

                    Drink newDrink = new Drink();
                    newDrink.setName(name);
                    newDrink.setSize(size);
                    newDrink.setStock(stock);
                    newDrink.setPrice(price);

                    drinkService.addDrink(newDrink);
                    System.out.println("Drink added successfully!");
                    break;
                case 3:
                    System.out.print("Enter the ID of the drink to update: ");
                    int id = scanner.nextInt();
                    Drink drinkToUpdate = drinkService.getDrinkById(id);

                    if (drinkToUpdate != null) {
                        System.out.print("Enter new name: ");
                        drinkToUpdate.setName(scanner.next());
                        System.out.print("Enter new size (SMALL/MEDIUM/LARGE): ");
                        drinkToUpdate.setSize(scanner.next().toUpperCase());
                        System.out.print("Enter new stock: ");
                        drinkToUpdate.setStock(scanner.nextInt());
                        System.out.print("Enter new price: Rp");
                        drinkToUpdate.setPrice(scanner.nextDouble());

                        drinkService.updateDrink(drinkToUpdate);
                        System.out.println("Drink updated successfully!");
                    } else {
                        System.out.println("Drink not found!");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }
}