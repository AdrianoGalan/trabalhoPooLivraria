
package boundary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Login extends Application {

	@Override
	public void start(Stage stage) { 
		Pane painel = new Pane();
		Scene scn = new Scene(painel, 400, 200);
		
		Label lblUser = new Label("usu�rio: ");
		lblUser.relocate(20, 10);
		TextField txtUser = new TextField();
		txtUser.relocate(100, 10);
		txtUser.setPrefColumnCount(15);
		
		Label lblSenha = new Label("senha: ");
		lblSenha.relocate(20, 40);
		PasswordField txtSenha = new PasswordField();
		txtSenha.relocate(100, 40);
		txtSenha.setPrefColumnCount(15);
		
		Button btnEntrar = new Button("Entrar");
		btnEntrar.relocate(100, 80);
		
		Label txtEsqueci = new Label("Esqueci a senha?");
		txtEsqueci.relocate(180, 80);

		painel.getChildren().addAll(
				lblUser, txtUser, 
				lblSenha,  txtSenha,
				btnEntrar, txtEsqueci);

		stage.setScene(scn);
		stage.setTitle("Tela Login");
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(Login.class, args);
	}
}
