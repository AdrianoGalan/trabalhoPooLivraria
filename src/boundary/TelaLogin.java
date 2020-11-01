package boundary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaLogin extends Application {

	@Override
	public void start(Stage stage) {
		Pane painel = new Pane();
		Scene scn = new Scene(painel, 300, 140);
		stage.setResizable(false);
		Label lblUser = new Label("usu�rio: ");
		lblUser.relocate(20, 14);
		TextField txtUser = new TextField();
		txtUser.relocate(100, 10);
		txtUser.setPrefColumnCount(15);

		Label lblSenha = new Label("senha: ");
		lblSenha.relocate(20, 43);
		TextField txtSenha = new TextField();
		txtSenha.relocate(100, 40);
		txtSenha.setPrefColumnCount(15);

		Button btnEntrar = new Button("Entrar");
		btnEntrar.relocate(100, 80);
		Hyperlink hplEsqueci = new Hyperlink("Esqueci a senha?");

		hplEsqueci.relocate(180, 80);

		painel.getChildren().addAll(lblUser, txtUser, lblSenha, txtSenha, btnEntrar, hplEsqueci);

		stage.setScene(scn);
		stage.setTitle("Tela Login");
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(TelaLogin.class, args);
	}

}
