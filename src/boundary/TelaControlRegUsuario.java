package boundary;

import java.sql.SQLException;

import control.ControlUsuario;
import entity.Funcionario;
import entity.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Mensagens;

/**
 * Classe tela de controle Usuario.
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class TelaControlRegUsuario implements EventHandler<ActionEvent> {

	/** Propriedade control */
	private ControlUsuario control;
	
	/** Propriedade cbFuncionario */
	private ComboBox<String> cbFuncionario;
	
	/** Propriedade txtFuncionario */
	private TextField txtFuncionario;
	
	/** Propriedade listaFunc */
	private ObservableList<Funcionario> listaFunc;
	
	/** Propriedade listaFuncStr */
	private ObservableList<String> listaFuncStr;
	
	/** Propriedade fun */
	private Funcionario fun;
	
	/** Propriedade txtSenha */
	private PasswordField txtSenha;
	
	/** Propriedade txtNovamSenha */
	private PasswordField txtNovamSenha;
	
	/** Propriedade txtLogin */
	private TextField txtLogin;
	
	/** Propriedade btnCadastrar */
	private Button btnCadastrar;
	
	/** Propriedade btnAlterar */
	private Button btnAlterar;
	
	/** Propriedade tela */
	private BorderPane tela;
	
	/** Propriedade painel */
	private Pane painel;
	
	/** Propriedade stage */
	private Stage stage;
	
	/** Propriedade cena */
	private Scene cena;
	
	/** Propriedade telaControle */
	private TelaControleUsuario telaControle;

	/**
	 * TelaControlRegUsuario
	 * 
	 * @param cont
	 * @param telaMae
	 */
	TelaControlRegUsuario(ControlUsuario cont,Stage telaMae) {
		control = cont;
		tela = new BorderPane();
		cena = new Scene(tela, 500, 250);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(telaMae);
		stage.setScene(cena);

	}

	/**
	 * Metodo render
	 * 
	 * @param telaM
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void render(TelaControleUsuario telaM) throws ClassNotFoundException, SQLException {

		telaControle = telaM;
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
		stage.showAndWait();

	}

	/**
	 * M�todo render
	 * 
	 * @param telaM
	 * @param lista
	 * @param tabela
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void render(TelaControleUsuario telaM,ObservableList<Usuario> lista, TableView<Usuario> tabela)
			throws ClassNotFoundException, SQLException {

		telaControle = telaM;
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
		stage.showAndWait();

	}

	/**
	 * Metodo criarTela
	 * 
	 * @param vbDi
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
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

	/**
	 * Metodo que carrega a comboBox.
	 * 
	 * @return listaFuncStr
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private ObservableList<String> carregaComboBox() throws ClassNotFoundException, SQLException {
		listaFunc = control.getListaFuncionarios();
		listaFuncStr = FXCollections.observableArrayList();
		for (Funcionario f : listaFunc) {
			listaFuncStr.add(f.getNome());
		}
		return listaFuncStr;
	}

	/**
	 * Metodo que verifica se os campos estao corretos.
	 * 
	 * @return true or false
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private boolean verificarCampos() throws ClassNotFoundException, SQLException {
		if(verificaFunc() && verificaSenhas() && verificaUser() ) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Metodo que verifica o campo senha.
	 * 
	 * @return true or false
	 */
	private boolean verificaSenhas() {
		if ((txtSenha.getText().equals(txtNovamSenha.getText()))
				&& !(txtSenha.getText().equals("") || txtNovamSenha.getText().equals(""))) {
			return true;
		} else if (txtSenha.getText().equals("") || txtNovamSenha.getText().equals("")) {
			Mensagens.informacao("Digite uma senha", "Por favor digite uma senha.", "");
			return false;
		} else {
			Mensagens.informacao("Senhas m�o coincidem", "Por favor digite a mesma senha em ambos os campos.", "");
			return false;
		}
	}

	/**
	 * Metodo que verifica se o funcionario foi selecionado na comboBox
	 * 
	 * @return true or false
	 */
	private boolean verificaFunc() {
		if(cbFuncionario == null) {
			if (txtFuncionario.equals("")){
				Mensagens.informacao("Funcionario n�o selecionado", "Por favor selecione algum funcionario.", "");
				return false;
			}else {
				return true;
			}
		}else {
			if(cbFuncionario.getSelectionModel().isEmpty()) {
				Mensagens.informacao("Funcionario n�o selecionado", "Por favor selecione algum funcionario.", "");
				return false;
			}else {
				return true;
			}
		}
	}
	
	/**
	 * Metodo que verifica o campo login.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private boolean verificaUser() throws ClassNotFoundException, SQLException {
		if (txtLogin.getText().equals("")) {
			Mensagens.informacao("Digite um login", "Por favor digite um login.", "");
			return false;
		} else if (control.verificaExistenciaLogin(txtLogin.getText())){
			Mensagens.informacao("Login inv�lido", "Login j� existe, por favor digite outro.", "");
			return false;
		}else {
			return true;
		}
	}
	

	/**
	 * M�todo handle - acao aos botoes - cadastrar e alterar
	 */
	@Override
	public void handle(ActionEvent event) {
		
		try {
			if(verificarCampos()) {
				if (event.getSource() == btnCadastrar) {
					control.cadastrarUsuario(listaFunc.get(cbFuncionario.getSelectionModel().getSelectedIndex()),
							txtLogin.getText(), txtSenha.getText());
					stage.close();
				} else if (event.getSource() == btnAlterar) {
					control.alterarUsuario(fun, txtLogin.getText(), txtSenha.getText());
					stage.close();
				}
			}
			telaControle.atualizaTabela();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
