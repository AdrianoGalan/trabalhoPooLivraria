package boundary;

import java.sql.SQLException;
import java.util.Optional;

import control.ControlUsuario;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import entity.Usuario;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TelaControleUsuario implements ControleTelas, EventHandler<ActionEvent> {

	private TableView<Usuario> tbvPesqCliente;
	ObservableList<Usuario> listaUsuarios;
	private Button btNovoUsuario;
	private Button btAlterar;
	private Button btExcluir;
	private ControlUsuario controle = new ControlUsuario();
	private TelaControlRegUsuario controleTelaReg = new TelaControlRegUsuario(controle);
	private Alert alertaSelecao;

	@Override
	public Pane render() {

		Pane painel = new Pane();

		HBox hbBotao = new HBox();
		hbBotao.setSpacing(180);

		VBox vbEs = new VBox();
		// new Insets(y, ?, ?, x)
		vbEs.setPadding(new Insets(25, 0, 15, 100));
		vbEs.setSpacing(15);

		tbvPesqCliente = new TableView<Usuario>();
		tbvPesqCliente.setPrefWidth(600);

		btNovoUsuario = new Button("Novo usuário");
		btNovoUsuario.addEventHandler(ActionEvent.ACTION, this);
		btAlterar = new Button("Alterar usuário");
		btAlterar.addEventFilter(ActionEvent.ACTION, this);
		btExcluir = new Button("Excluir usuário");
		btExcluir.addEventHandler(ActionEvent.ACTION, this);
		hbBotao.getChildren().addAll(btNovoUsuario, btAlterar, btExcluir);

		vbEs.getChildren().add(tbvPesqCliente);
		vbEs.getChildren().add(hbBotao);
		alertaSelecao = new Alert(AlertType.INFORMATION);
		
		painel.getChildren().add(vbEs);
		criaTabela();

		return painel;
	}

	private void criaTabela() {

		TableColumn<Usuario, Integer> colId = new TableColumn<>("id_Usuario");
		colId.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("idUsuario"));

		TableColumn<Usuario, String> colLogin = new TableColumn<>("Login");
		colLogin.setCellValueFactory(new PropertyValueFactory<Usuario, String>("login"));

		TableColumn<Usuario, String> colSenha = new TableColumn<>("Senha");
		colSenha.setCellValueFactory(new PropertyValueFactory<Usuario, String>("senha"));

		tbvPesqCliente.getColumns().addAll(colId, colLogin, colSenha);
		atualizaTabela();

	}

	public void atualizaTabela() {
		try {
			listaUsuarios = controle.procurarUsuarios();
			tbvPesqCliente.setItems(listaUsuarios);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(ActionEvent event) {
		int i = tbvPesqCliente.getSelectionModel().getSelectedIndex();

		try {

			if (event.getTarget() == btNovoUsuario) {

				controleTelaReg.render(this);

			} else {
				if (i == -1 || i > tbvPesqCliente.getItems().size()) {
					alertaSelecao.setTitle("Seleção");
					alertaSelecao.setHeaderText("Por favor selecione um usuário antes de realizar essa ação.");
					alertaSelecao.showAndWait();
				} else {
					if (event.getTarget() == btAlterar) {
						controleTelaReg.render(this,listaUsuarios, tbvPesqCliente);
					} else if (event.getTarget() == btExcluir) {

						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirmação de exclusão");
						alert.setHeaderText("A operação de exclusão não pode ser desfeita");
						alert.setContentText("Escolha uma opção:");
						ButtonType buttonConfirma = new ButtonType("Sim");
						ButtonType buttonCancela = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
						alert.getButtonTypes().setAll(buttonConfirma, buttonCancela);
						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == buttonConfirma) {
							controle.deletarUsuario(listaUsuarios
									.get(tbvPesqCliente.getSelectionModel().getSelectedIndex()).getIdUsuario());
							alertaSelecao.setTitle("Exclusão");
							alertaSelecao.setHeaderText("Usuário excluido com sucesso.");
							alertaSelecao.showAndWait();
						}
					}
				}
			}
			atualizaTabela();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
