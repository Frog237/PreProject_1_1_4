package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("Таблица users создана");
        }
        catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }


    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement()) {
            String sql = "DROP TABLE IF EXISTS users";
            stmt.executeUpdate(sql);
            System.out.println("Таблица users удалена");
        }
        catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.executeUpdate();
            System.out.println("User с именем — " + name + " добавлен в базу данных");
        }
        catch (SQLException e) {
            System.out.println("Ошибка при добавлении пользователя: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Пользователь с id " + id + " удалён.");
            } else {
                System.out.println("Пользователь с id " + id + " не найден.");
            }
        }
        catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении пользователей: " + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement()) {
            String sql = "TRUNCATE users";
            stmt.executeUpdate(sql);
            System.out.println("Таблица users очищена");
        }
        catch (SQLException e) {
            System.out.println("Ошибка при очистке таблицы: " + e.getMessage());
        }
    }
}
