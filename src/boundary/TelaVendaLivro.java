package boundary;

import java.sql.SQLException;

import control.ControleCliente;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import entity.Cliente;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import tabelaModel.ModelItesVenda;
import tabelaModel.ModelTabelaLivro;

public class TelaVendaLivro implements ControleTelas, EventHandler<ActionEvent> {

	private TextField tfNomeCliente;
	private TextField tfCpfCliente;
	private TextField tfLivro;
	private TextField tfISBN;
	private TableView<ModelItesVenda> tbItes;
	private Button btnBuscaCliente;
	private ComboBox<Cliente> cbNome;
	private Cliente cliente;

	@Override
	public void handle(ActionEvent e) {

		if(e.getTarget() == cbNome) {
			
		
			alimentaTelaCliente();
		}
		
	//	System.out.println(cbNome.getSelectionModel().getSelectedItem());

	}

	@Override
	public Pane render() {

		// cria painel principal
		BorderPane painel = new BorderPane();

		// cria tabbela com os itens da venda
		tbItes = new TableView<>();
		tbItes.setPrefWidth(500);

		// titulo da tela
		StackPane stitulo = new StackPane();
		Label lbTitulo = new Label("Venda Livros");
		lbTitulo.setFont(new Font("SansSerif", 20));
		lbTitulo.setPadding(new Insets(15, 15, 15, 15));
		stitulo.getChildren().add(lbTitulo);

		// cria vbox principal com todos os itens da tela
		VBox vbPrincipal = new VBox();

		tfNomeCliente = new TextField();
		tfNomeCliente.setPrefWidth(250);
		tfCpfCliente = new TextField();
		tfLivro = new TextField();
		tfISBN = new TextField();

		cbNome = new ComboBox<>();
		cbNome.setPrefWidth(200);

		cbNome.setEditable(true);
		cbNome.setOnAction(this);
		carregaCbCliente("");

		cbNome.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
			@Override
			public void handle(javafx.scene.input.KeyEvent event) {

				if (event.getCode() != KeyCode.ENTER && event.getCode() != KeyCode.DOWN) {

					carregaCbCliente(cbNome.getEditor().getText());
					cbNome.show();
				}

				if (event.getCode() == KeyCode.ENTER) {

				}

			}
		});

		btnBuscaCliente = new Button("Buscar");

		HBox hbCliente = new HBox();
		hbCliente.setPadding(new Insets(15, 15, 15, 15));
		hbCliente.setSpacing(20);
		vbPrincipal.getChildren().add(hbCliente);

		VBox vbEs = new VBox();
		vbEs.setPadding(new Insets(15, 15, 15, 15));
		vbEs.setSpacing(25);

		VBox vbDi = new VBox();
		vbDi.setPadding(new Insets(15, 15, 15, 15));
		vbDi.setSpacing(15);

		hbCliente.getChildren().add(new Label("Cliente: "));
		hbCliente.getChildren().add(cbNome);
		hbCliente.getChildren().add(new Label("cpf: "));
		hbCliente.getChildren().add(tfCpfCliente);
		hbCliente.getChildren().add(btnBuscaCliente);

		painel.setTop(stitulo);
		painel.setCenter(vbPrincipal);

		return painel;
	}

	private void alimentaTelaCliente() {
		
		cliente = cbNome.getSelectionModel().getSelectedItem();
		
		if (cliente != null) {
			tfCpfCliente.setText(cliente.getCpf());
		}
	}

	private void carregaCbCliente(String nome) {

		ControleCliente cc = new ControleCliente();

		try {

			cbNome.setItems(cc.buscaClienteNome(nome));

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

}