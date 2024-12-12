package minuman.repositories;

import minuman.config.DatabaseConfig;
import minuman.entities.Drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DrinkRepositoryImpl implements DrinkRepository {
    @Override
    public List<Drink> getAllDrinks() {
        List<Drink> drinks = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Drinks")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Drink drink = new Drink();
                drink.setId(rs.getInt("id"));
                drink.setName(rs.getString("name"));
                drink.setSize(rs.getString("size"));
                drink.setStock(rs.getInt("stock"));
                drink.setPrice(rs.getDouble("price"));
                drinks.add(drink);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drinks;
    }

    @Override
    public void updateDrink(Drink drink) {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "UPDATE Drinks SET name = ?, size = ?, stock = ?, price = ? WHERE id = ?")) {
            stmt.setString(1, drink.getName());
            stmt.setString(2, drink.getSize());
            stmt.setInt(3, drink.getStock());
            stmt.setDouble(4, drink.getPrice());
            stmt.setInt(5, drink.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDrink(Drink drink) {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "INSERT INTO Drinks (name, size, stock, price) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, drink.getName());
            stmt.setString(2, drink.getSize());
            stmt.setInt(3, drink.getStock());
            stmt.setDouble(4, drink.getPrice());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Drink getDrinkById(int id) {
        Drink drink = null;
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Drinks WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                drink = new Drink();
                drink.setId(rs.getInt("id"));
                drink.setName(rs.getString("name"));
                drink.setSize(rs.getString("size"));
                drink.setStock(rs.getInt("stock"));
                drink.setPrice(rs.getDouble("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drink;
    }
}