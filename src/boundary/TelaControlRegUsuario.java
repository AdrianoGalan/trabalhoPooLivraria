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
import javafx.stage.Stage;

public class TelaControlRegUsuario implements EventHandler<ActionEvent> {

	private ControlUsuario control = new ControlUsuario();
	private ComboBox<String> cbFuncionario; //<--- isso aqui vai pro saco, full bugado n funfa direito
	private ObservableList<Funcionario> listaFunc;
	private ObservableList<String> listaFuncStr;
	private PasswordField txtSenha;
	private PasswordField txtNovamSenha;
	private TextField txtLogin;
	private Button btnCadastrar;
	private Button btnAlterar;
	private BorderPane tela;
	private Pane painel;
	private Stage stage;
	private Scene cena;
	private VBox vbDi;
	
	TelaControlRegUsuario(){
		
		tela = new BorderPane();
		painel = new Pane();

		cena = new Scene(tela, 500, 250);
		stage = new Stage();
		stage.setScene(cena);
		
		vbDi = new VBox();
		vbDi.setPadding(new Insets(15, 12, 15, 12));
		vbDi.setSpacing(15);
		painel.getChildren().add(vbDi);
		
	}

	public void render() throws ClassNotFoundException, SQLException {
		
		criarTela();
		HBox hbBotao = new HBox();
		btnCadastrar = new Button("Cadastrar");
		btnCadastrar.addEventHandler(ActionEvent.ACTION, this);
		hbBotao.getChildren().add(btnCadastrar);
		hbBotao.setPadding(new Insets(25, 0, 15, 200));

		vbDi.getChildren().addAll(hbBotao);
		tela.setCenter(painel);
		stage.show();

	}
	
	public void render(ObservableList<Usuario> lista,TableView<Usuario> tabela) throws ClassNotFoundException, SQLException {

		criarTela();
		// isso aqui vai ser refeito ta uma merda
		int index = tabela.getSelectionModel().getSelectedIndex();
		int fkFunc = lista.get(index).getfKFuncionario();
		int i = 0;
		
		for(Funcionario f:listaFunc) {
			if(fkFunc == f.getIdFuncionario()) {
				cbFuncionario.getSelectionModel().select(i);
				System.out.println(cbFuncionario.getSelectionModel().getSelectedIndex());
			}
			i++;
		}
		
		txtLogin.setText(lista.get(index).getLogin());
		txtSenha.setText(lista.get(index).getSenha());
		
		HBox hbBotao = new HBox();
		btnAlterar = new Button("Alterar");
		hbBotao.getChildren().add(btnAlterar);
		hbBotao.setPadding(new Insets(25, 0, 15, 200));
		
		vbDi.getChildren().addAll(hbBotao);
		tela.setCenter(painel);
		stage.show();
	}
	
	private void criarTela() throws ClassNotFoundException, SQLException {
		
		HBox hbFunc = new HBox();
		hbFunc.setSpacing(20);

		Label lblFunc = new Label("Funcionario:");
		hbFunc.getChildren().add(lblFunc);
		lblFunc.setPadding(new Insets(4, 0, 0, 0));
		cbFuncionario = new ComboBox<String>();
		cbFuncionario.setItems(carregaComboBox());
		hbFunc.getChildren().add(cbFuncionario);

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
		
		vbDi.getChildren().addAll(hbFunc, hbLogin, hbSenha, hbNovamSenha);
		
	}

	private ObservableList<String> carregaComboBox() throws ClassNotFoundException, SQLException {
		listaFunc = control.getListaFuncionarios();
		listaFuncStr = FXCollections.observableArrayList();
		for (Funcionario f : listaFunc) {
			listaFuncStr.add(f.getNome());
		}
		return listaFuncStr;
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == btnCadastrar) {
			System.out.println(listaFunc.get(cbFuncionario.getSelectionModel().getSelectedIndex()).toString());
			try {
				control.cadastrarUsuario(listaFunc.get(cbFuncionario.getSelectionModel().getSelectedIndex()),
						txtLogin.getText(), txtSenha.getText());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
