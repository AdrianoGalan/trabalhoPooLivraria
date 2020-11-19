package boundary;

import control.ControleTelas;
import control.GetenciadorPrincipal;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaControlRegUsuario{
	
	
	


	public void render() {
		BorderPane tela = new BorderPane();
		Pane painel = new Pane();
		
		Scene cena = new Scene(tela, 500, 250);
		Stage stage = new Stage();
		stage.setScene(cena);
		
		HBox hbFunc = new HBox();
		hbFunc.setSpacing(20);
		
		VBox vbDi = new VBox();
		vbDi.setPadding(new Insets(15, 12, 15, 12));
		vbDi.setSpacing(15);
		
		Label lblFunc = new Label("Funcionario:");
		hbFunc.getChildren().add(lblFunc);
		lblFunc.setPadding(new Insets(4, 0, 0, 0));
		ComboBox<String> cbFuncionario = new ComboBox<String>();
		hbFunc.getChildren().add(cbFuncionario);
		
		
		HBox hbLogin = new HBox();
		Label lblLogin = new Label("Login:");
		lblLogin.setPadding(new Insets(4, 0, 0, 0));
		TextField txtLogin = new TextField();
		hbLogin.setSpacing(20);
		hbLogin.getChildren().addAll(lblLogin,txtLogin);
		
		HBox hbSenha = new HBox();
		Label lblSenha = new Label("Senha:");
		lblSenha.setPadding(new Insets(4, 0, 0, 0));
		PasswordField txtSenha = new PasswordField();
		hbSenha.setSpacing(20);
		hbSenha.getChildren().addAll(lblSenha,txtSenha);
		
		HBox hbNovamSenha = new HBox();
		Label lblNovamSenha = new Label("Digite a senha novamente:");
		lblNovamSenha.setPadding(new Insets(4, 0, 0, 0));
		PasswordField txtNovamSenha = new PasswordField();
		hbNovamSenha.setSpacing(20);
		hbNovamSenha.getChildren().addAll(lblNovamSenha,txtNovamSenha);
		
		HBox hbBotao = new HBox();
		Button btnCadastrar = new Button("Cadastrar");
		hbBotao.setPadding(new Insets(25, 0, 15, 200));
		hbBotao.getChildren().add(btnCadastrar);
		
		
		vbDi.getChildren().addAll(hbFunc,hbLogin,hbSenha,hbNovamSenha,hbBotao);
		painel.getChildren().add(vbDi);
		tela.setCenter(painel);
		stage.show();
		
	}



}
