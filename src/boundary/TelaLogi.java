package boundary;

import control.GetenciadorPrincipal;
import entity.Usuario;

import java.sql.SQLException;

import control.ControlUsuario;
import control.ControleTelas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import util.Mensagens;

public class TelaLogi implements ControleTelas, EventHandler<ActionEvent> {

	private TextField txtUser = new TextField();
	private PasswordField txtSenha = new PasswordField();
	private GetenciadorPrincipal gp;

	@Override
	public Pane render() {

		Pane painel = new Pane();
		Scene scn = new Scene(painel, 350, 140);
		// stage.setResizable(false);
		Label lblUser = new Label("usuario: ");
		lblUser.relocate(20, 14);

		txtUser.relocate(100, 10);
		txtUser.setPrefColumnCount(15);

		Label lblSenha = new Label("senha: ");
		lblSenha.relocate(20, 43);

		txtSenha.relocate(100, 40);
		txtSenha.setPrefColumnCount(15);

		// para não digitar todo hora
		txtUser.setText("joao");
		txtSenha.setText("senha");

		Button btnEntrar = new Button("Entrar");
		btnEntrar.relocate(100, 80);
		Hyperlink hplEsqueci = new Hyperlink("Esqueceu a senha?");

		hplEsqueci.relocate(180, 80);

		painel.getChildren().addAll(lblUser, txtUser, lblSenha, txtSenha, btnEntrar, hplEsqueci);

		// coloca uma ação aos botões
		btnEntrar.addEventHandler(ActionEvent.ACTION, this);
		hplEsqueci.addEventHandler(ActionEvent.ACTION, this);

		txtSenha.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {

			@Override
			public void handle(javafx.scene.input.KeyEvent event) {

				if (event.getCode() == KeyCode.ENTER) {
					fazerLogin();
				}
			}
		});

		return painel;
	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal gp) {

		this.gp = gp;

	}

	@Override
	public void handle(ActionEvent event) {

		// verifica qual botão foi clicado
		if (event.getTarget().toString().contains("Entrar")) {

			fazerLogin();

		}

		if (event.getTarget().toString().contains("a senha")) {
			System.out.println("esqueceu");
		}

	}

	private void fazerLogin() {

		if (veridicaCampos()) {
			ControlUsuario cU = new ControlUsuario();

			Usuario u = boundaryParaUsuario();

			try {
				u = cU.logarNoSistema(u);
				if (u != null) {
					gp.id(u.getfKFuncionario());
					gp.comando("telaInicial");
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Mensagens.erro("Erro conexão", "Erro ao se conectar com o banco", "Procure o administrador");
			}
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

	private Usuario boundaryParaUsuario() {
		Usuario u = new Usuario();
		u.setLogin(txtUser.getText());
		u.setSenha(txtSenha.getText());
		return u;
	}

}
