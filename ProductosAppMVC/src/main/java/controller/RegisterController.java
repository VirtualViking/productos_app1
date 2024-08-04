package controller;

import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import repositories.UserRepositoryJdbc;
import repository.UserRepository;

import java.util.Map;
import java.util.HashMap;

public class RegisterController {
    private TextField usernameField;
    private PasswordField passwordField;
    private  Map<String, String> users = new HashMap<>();
    private final UserRepository userRepository = new UserRepositoryJdbc();

    public RegisterController(TextField usernameField, PasswordField passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;}

    public void handleRegisterButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validación de datos
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Por favor, ingrese todos los campos.");
            return;
        }

        // Verificar si el usuario ya existe en la db
        if (!validateNonExistingUsername(username)) {
            showAlert("Error", "El usuario ya existe.");
            return;
        }

        // Guardar usuario en el HashMap
        users.put(username, password);
        userRepository.saveUser(username,password);
        showAlert("Éxito", "Registro exitoso: " + username);
        clearFields();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
    }

    public boolean validateNonExistingUsername(String username){
        return userRepository.getUserByUsername(username).isEmpty();

    }
}


