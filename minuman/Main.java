package minuman;

import minuman.config.DatabaseConfig;
import minuman.entities.User;
import minuman.repositories.DrinkRepository;
import minuman.repositories.DrinkRepositoryImpl;
import minuman.repositories.UserRepository;
import minuman.repositories.UserRepositoryImpl;
import minuman.service.DrinkService;
import minuman.service.DrinkServiceImpl;
import minuman.service.UserService;
import minuman.service.UserServiceImpl;
import minuman.views.AdminView;
import minuman.views.UserView;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            System.out.println("Database connected successfully!");

            // Initialize Repositories
            UserRepository userRepository = new UserRepositoryImpl();
            DrinkRepository drinkRepository = new DrinkRepositoryImpl();

            // Initialize Services
            UserService userService = new UserServiceImpl(userRepository);
            DrinkService drinkService = new DrinkServiceImpl(drinkRepository);

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("1. Admin View");
                System.out.println("2. User View");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Launch Admin View
                        AdminView adminView = new AdminView(drinkService);
                        adminView.showAdminMenu();
                        break;
                    case 2:
                        // Launch User View with Login/Register
                        UserView userView = new UserView(userService, drinkService);
                        userView.showUserMenu();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } while (choice != 3);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
