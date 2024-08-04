package view;

import controller.RegisterController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginView extends GridPane {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;

    public LoginView() {
        this.setPadding(new Insets(20));
        this.setVgap(10);
        this.setHgap(10);
        this.setBackground(createBackground());

        Label usernameLabel = new Label("Usuario:");
        usernameField = new TextField();
        Label passwordLabel = new Label("Contraseña:");
        passwordField = new PasswordField();
        loginButton = new Button("Iniciar Sesión");
        registerButton = new Button("Registrarse");

        this.add(usernameLabel, 0, 0);
        this.add(usernameField, 1, 0);
        this.add(passwordLabel, 0, 1);
        this.add(passwordField, 1, 1);
        this.add(loginButton, 1, 2);
        this.add(registerButton, 1, 3);

        // Agregar EventHandler para el botón de registro
        registerButton.setOnAction(event -> handleRegisterButtonClick());
    }

    private Background createBackground() {
        Image image = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScEMTl69DpUZSuxl_Q5WAPlCj7zUG3EIOeb5hA4sho7r2j4EgDeGLiYyt22sA5b_IGybc&usqp=CAU");
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        return new Background(backgroundImage);
    }

    private void handleRegisterButtonClick() {
        // Lógica para abrir una nueva ventana de registro
        Stage registerStage = new Stage();
        RegisterView registerView = new RegisterView();
        Scene registerScene = new Scene(registerView, 400, 300);
        registerStage.setTitle("Registro");
        registerStage.setScene(registerScene);
        registerStage.show();
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public static void main(String[] args) {
        javafx.application.Application.launch(LoginApp.class, args);
    }
}

class LoginApp extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView();
        Scene scene = new Scene(loginView, 400, 300);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class RegisterView extends GridPane {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button registerButton;
    private RegisterController registerController;

    public RegisterView() {
        this.setPadding(new Insets(20));
        this.setVgap(10);
        this.setHgap(10);

        Label usernameLabel = new Label("Usuario:");
        usernameField = new TextField();
        Label passwordLabel = new Label("Contraseña:");
        passwordField = new PasswordField();
        registerButton = new Button("Registrarse");
        registerController = new RegisterController(usernameField,passwordField);

        this.add(usernameLabel, 0, 0);
        this.add(usernameField, 1, 0);
        this.add(passwordLabel, 0, 1);
        this.add(passwordField, 1, 1);
        this.add(registerButton, 1, 2);

        // Agregar EventHandler para el botón de registro
        registerButton.setOnAction(event -> registerController.handleRegisterButtonClick());
    }

    private void handleRegisterButtonClick() {
        // Lógica para manejar la acción de registro
        System.out.println("Registro exitoso: " + usernameField.getText());
        // Aquí puedes agregar más lógica para manejar el registro
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getRegisterButton() {
        return registerButton;
    }
}

