package repositories;

import model.Usuario;
import repository.UserRepository;
import util.DatabaseConnection;

import java.sql.*;
import java.util.Optional;

public class UserRepositoryJdbc implements UserRepository {

    private Connection connection;
    public UserRepositoryJdbc() {
        try{
            this.connection = DatabaseConnection.getConnection();
        }catch (SQLException exception){
            System.out.println("There  was  an error connecting to the database");
        }}

    @Override
    public void saveUser(String username, String password) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error saving user", e);
        }
    }

    @Override
    public Optional<Usuario> getUserByUsername(String username) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE username = ?")){
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                return Optional.empty();
            }
            return Optional.of(
                    new Usuario(resultSet.getString("username"), resultSet.getString("password"))
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
