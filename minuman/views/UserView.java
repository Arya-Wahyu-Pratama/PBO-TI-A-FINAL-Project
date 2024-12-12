package minuman.views;

import minuman.entities.Drink;
import minuman.entities.User;
import minuman.service.DrinkService;
import minuman.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserService userService;
    private final DrinkService drinkService;

    public UserView(UserService userService, DrinkService drinkService) {
        this.userService = userService;
        this.drinkService = drinkService;
    }

    public void showUserMenu() {
        Scanner scanner = new Scanner(System.in);
        List<Drink> cart = new ArrayList<>();
        int choice;
        do {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Handle Login
                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    if (userService.login(username, password)) {
                        System.out.println("Login successful!");

                        do {
                            System.out.println("1. View All Drinks");
                            System.out.println("2. Add to Cart");
                            System.out.println("3. Checkout");
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
                                    System.out.print("Enter the ID of the drink to add to cart: ");
                                    int drinkId = scanner.nextInt();
                                    Drink drinkToAdd = drinkService.getDrinkById(drinkId);

                                    if (drinkToAdd != null) {
                                        System.out.print("Enter quantity: ");
                                        int quantity = scanner.nextInt();

                                        if (quantity > drinkToAdd.getStock()) {
                                            System.out.println("Insufficient stock!");
                                        } else {
                                            drinkToAdd.setStock(drinkToAdd.getStock() - quantity);
                                            drinkService.updateDrink(drinkToAdd);
                                            Drink cartItem = new Drink();
                                            cartItem.setId(drinkToAdd.getId());
                                            cartItem.setName(drinkToAdd.getName());
                                            cartItem.setSize(drinkToAdd.getSize());
                                            cartItem.setPrice(drinkToAdd.getPrice() * quantity);
                                            cart.add(cartItem);
                                            System.out.println("Added to cart.");
                                        }
                                    } else {
                                        System.out.println("Drink not found!");
                                    }
                                    break;
                                case 3:
                                    double total = 0;
                                    System.out.println("Your Cart:");
                                    for (Drink d : cart) {
                                        System.out.println("Name: " + d.getName() + ", Size: " + d.getSize() + ", Price: Rp" + d.getPrice());
                                        total += d.getPrice();
                                    }
                                    System.out.println("Total: Rp" + total);
                                    System.out.println("Payment successful. Thank you!");
                                    cart.clear();
                                    break;
                                case 4:
                                    System.out.println("Exiting...");
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                            }
                        } while (choice != 4);
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 2:
                    // Handle Registration
                    User newUser = new User();
                    System.out.print("Enter a username: ");
                    newUser.setUsername(scanner.next());
                    System.out.print("Enter a password: ");
                    newUser.setPassword(scanner.next());
                    System.out.print("Enter your role (ADMIN/USER): ");
                    newUser.setRole(scanner.next().toUpperCase());
                    if (userService.register(newUser)) {
                        System.out.println("Registration successful! You can now log in.");
                    } else {
                        System.out.println("Registration failed. Username might already exist.");
                    }
                    break;
                    case 3:
                        System.out.println("Exiting...");
                        break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        while (choice != 3);

    }
}
