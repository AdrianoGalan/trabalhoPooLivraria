package boundary;

import java.sql.SQLException;

import control.ControlUsuario;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import entity.Funcionario;
import entity.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaControlRegUsuario implements EventHandler<ActionEvent> {

	private ControlUsuario control;
	private ComboBox<String> cbFuncionario; 
	private TextField txtFuncionario;
	private ObservableList<Funcionario> listaFunc;
	private ObservableList<String> listaFuncStr;
	private Funcionario fun;
	private PasswordField txtSenha;
	private PasswordField txtNovamSenha;
	private TextField txtLogin;
	private Button btnCadastrar;
	private Button btnAlterar;
	private BorderPane tela;
	private Pane painel;
	private Stage stage;
	private Scene cena;
	private Alert alerta;

	TelaControlRegUsuario(ControlUsuario cont) {
		control = cont;
		tela = new BorderPane();
		cena = new Scene(tela, 500, 250);
		stage = new Stage();
		stage.setScene(cena);

	}

	public void render() throws ClassNotFoundException, SQLException {

		VBox vbDi = new VBox();
		HBox hbFunc = new HBox();
		hbFunc.setSpacing(20);
		Label lblFunc = new Label("Funcionario:");
		hbFunc.getChildren().add(lblFunc);
		lblFunc.setPadding(new Insets(4, 0, 0, 0));
		cbFuncionario = new ComboBox<String>();
		cbFuncionario.setItems(carregaComboBox());
		hbFunc.getChildren().add(cbFuncionario);
		vbDi.getChildren().add(hbFunc);
		txtFuncionario = null;
		criarTela(vbDi);

		HBox hbBotao = new HBox();
		btnCadastrar = new Button("Cadastrar");
		btnCadastrar.addEventHandler(ActionEvent.ACTION, this);
		hbBotao.getChildren().add(btnCadastrar);
		hbBotao.setPadding(new Insets(25, 0, 15, 200));

		vbDi.getChildren().addAll(hbBotao);
		tela.setCenter(painel);
		stage.show();

	}

	public void render(ObservableList<Usuario> lista, TableView<Usuario> tabela)
			throws ClassNotFoundException, SQLException {

		VBox vbDi = new VBox();
		HBox hbFunc = new HBox();
		hbFunc.setSpacing(20);
		cbFuncionario = null;
		Label lblFunc = new Label("Funcionario:");
		hbFunc.getChildren().add(lblFunc);
		lblFunc.setPadding(new Insets(4, 0, 0, 0));
		txtFuncionario = new TextField();
		txtFuncionario.setDisable(true);
		hbFunc.getChildren().add(txtFuncionario);
		vbDi.getChildren().add(hbFunc);
		criarTela(vbDi);

		listaFunc = control.getListaFuncionarios();
		int index = tabela.getSelectionModel().getSelectedIndex();
		int fkFunc = lista.get(index).getfKFuncionario();

		for (Funcionario f : listaFunc) {
			if (fkFunc == f.getIdFuncionario()) {
				txtFuncionario.setText(f.getNome());
				fun = f;
			}
		}

		txtLogin.setText(lista.get(index).getLogin());
		txtSenha.setText(lista.get(index).getSenha());

		HBox hbBotao = new HBox();
		btnAlterar = new Button("Alterar");
		hbBotao.getChildren().add(btnAlterar);
		btnAlterar.addEventHandler(ActionEvent.ACTION, this);
		hbBotao.setPadding(new Insets(25, 0, 15, 200));

		vbDi.getChildren().addAll(hbBotao);
		tela.setCenter(painel);
		stage.show();

	}

	private void criarTela(VBox vbDi) throws ClassNotFoundException, SQLException {

		painel = new Pane();
		vbDi.setPadding(new Insets(15, 12, 15, 12));
		vbDi.setSpacing(15);
		painel.getChildren().add(vbDi);

		HBox hbLogin = new HBox();
		Label lblLogin = new Label("Login:");
		lblLogin.setPadding(new Insets(4, 0, 0, 0));
		txtLogin = new TextField();
		hbLogin.setSpacing(20);
		hbLogin.getChildren().addAll(lblLogin, txtLogin);

		HBox hbSenha = new HBox();
		Label lblSenha = new Label("Senha:");
		lblSenha.setPadding(new Insets(4, 0, 0, 0));
		txtSenha = new PasswordField();
		hbSenha.setSpacing(20);
		hbSenha.getChildren().addAll(lblSenha, txtSenha);

		HBox hbNovamSenha = new HBox();
		Label lblNovamSenha = new Label("Digite a senha novamente:");
		lblNovamSenha.setPadding(new Insets(4, 0, 0, 0));
		txtNovamSenha = new PasswordField();
		hbNovamSenha.setSpacing(20);
		hbNovamSenha.getChildren().addAll(lblNovamSenha, txtNovamSenha);

		vbDi.getChildren().addAll(hbLogin, hbSenha, hbNovamSenha);

	}

	private ObservableList<String> carregaComboBox() throws ClassNotFoundException, SQLException {
		listaFunc = control.getListaFuncionarios();
		listaFuncStr = FXCollections.observableArrayList();
		for (Funcionario f : listaFunc) {
			listaFuncStr.add(f.getNome());
		}
		return listaFuncStr;
	}

	private boolean verificarCampos() throws ClassNotFoundException, SQLException {
		if(verificaFunc() && verificaSenhas() && verificaUser() ) {
			return true;
		}else {
			return false;
		}
	}

	private boolean verificaSenhas() {
		if ((txtSenha.getText().equals(txtNovamSenha.getText()))
				&& !(txtSenha.getText().equals("") || txtNovamSenha.getText().equals(""))) {
			return true;
		} else if (txtSenha.getText().equals("") || txtNovamSenha.getText().equals("")) {
			alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Digite uma senha");
			alerta.setHeaderText("Por favor digite uma senha.");
			alerta.showAndWait();
			return false;
		} else {
			alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Senhas mão coincidem");
			alerta.setHeaderText("Por favor digite a mesma senha em ambos os campos.");
			alerta.showAndWait();
			return false;
		}
	}

	private boolean verificaFunc() {
		if(cbFuncionario == null) {
			if (txtFuncionario.equals("")){
				alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Funcionario não selecionado");
				alerta.setHeaderText("Por favor selecione algum funcionario.");
				alerta.showAndWait();
				return false;
			}else {
				return true;
			}
		}else {
			if(cbFuncionario.getSelectionModel().isEmpty()) {
				alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Funcionario não selecionado");
				alerta.setHeaderText("Por favor selecione algum funcionario.");
				alerta.showAndWait();
				return false;
			}else {
				return true;
			}
		}
	}
	
	private boolean verificaUser() throws ClassNotFoundException, SQLException {
		if (txtLogin.getText().equals("")) {
			alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Digite um login");
			alerta.setHeaderText("Por favor digite um login.");
			alerta.showAndWait();
			return false;
		} else if (control.verificaExistenciaLogin(txtLogin.getText())){
			alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Login inválido");
			alerta.setHeaderText("Login já existe, por favor digite outro.");
			alerta.showAndWait();
			return false;
		}else {
			return true;
		}
	}
	

	@Override
	public void handle(ActionEvent event) {
		
		try {
			if(verificarCampos()) {
				if (event.getSource() == btnCadastrar) {
					control.cadastrarUsuario(listaFunc.get(cbFuncionario.getSelectionModel().getSelectedIndex()),
							txtLogin.getText(), txtSenha.getText());
				} else if (event.getSource() == btnAlterar) {
					control.alterarUsuario(fun, txtLogin.getText(), txtSenha.getText());
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
