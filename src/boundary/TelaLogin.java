package boundary;

import java.sql.SQLException;

import control.ControlUsuario;
import entity.Usuario;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Mensagens;

public class TelaLogin extends Application implements EventHandler<ActionEvent> {

	TextField txtUser = new TextField();
	PasswordField txtSenha = new PasswordField();

	@Override
	public void start(Stage stage) {

		Pane painel = new Pane();
		Scene scn = new Scene(painel, 350, 140);
		stage.setResizable(false);
		Label lblUser = new Label("usuario: ");
		lblUser.relocate(20, 14);

		txtUser.relocate(100, 10);
		txtUser.setPrefColumnCount(15);

		Label lblSenha = new Label("senha: ");
		lblSenha.relocate(20, 43);

		txtSenha.relocate(100, 40);
		txtSenha.setPrefColumnCount(15);

		Button btnEntrar = new Button("Entrar");
		btnEntrar.relocate(100, 80);
		Hyperlink hplEsqueci = new Hyperlink("Esqueceu a senha?");

		hplEsqueci.relocate(180, 80);

		painel.getChildren().addAll(lblUser, txtUser, lblSenha, txtSenha, btnEntrar, hplEsqueci);

		// coloca uma ação aos botões
		btnEntrar.addEventHandler(ActionEvent.ACTION, this);
		hplEsqueci.addEventHandler(ActionEvent.ACTION, this);

		stage.setScene(scn);
		stage.setTitle("Tela Login");
		stage.show();
	}

	private Usuario boundaryParaUsuario() {
		Usuario u = new Usuario();
		u.setLogin(txtUser.getText());
		u.setSenha(txtSenha.getText());
		return u;
	}

	// executa a ação
	@Override
	public void handle(ActionEvent event) {
		
		ControlUsuario cU = new ControlUsuario();

		// verifica qual botão foi clicado
		if (event.getTarget().toString().contains("Entrar") && veridicaCampos()) {
			
			Usuario u = boundaryParaUsuario();
			try {
				
				if(cU.logarNoSistema(u)) {
					Mensagens.informacao("Entrou", "Usuairo logado", "Q coisa linda");
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		if (event.getTarget().toString().contains("a senha")) {
			System.out.println("esqueceu");
		}

	}

	// verifica se os campos foram preenchidos
	private boolean veridicaCampos() {

		if (txtUser.getText().equals("")) {

			Mensagens.erro("Usuario erro", "Usuario invalido", "Digite um usuario");

			return false;
		} else if (txtSenha.getText().equals("")) {

			Mensagens.erro("Senha erro", "Senha invalida", "Digite uma Senha");

			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Application.launch(TelaLogin.class, args);
	}

}
