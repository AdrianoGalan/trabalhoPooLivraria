package boundary;

import java.sql.SQLException;

import control.ControleCliente;
import control.ControleLivro;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import entity.Cliente;
import entity.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import tabelaModel.ModelItensVenda;
import util.Mensagens;

public class TelaVendaLivro implements ControleTelas, EventHandler<ActionEvent> {

	private TextField tfCpfCliente;
	private ComboBox<Livro> cbLivro;
	private TextField tfISBN;
	private TableView<ModelItensVenda> tbItes;
	private Button btnAdd;
	private Button btnFinalizar;
	private Button btnRemover;
	private Button btnCancelar;
	private ComboBox<Cliente> cbNome;
	private Cliente cliente;
	private Livro livro;
	private ControleCliente cc;
	private ControleLivro cl;
	private ObservableList<ModelItensVenda> listaVenda;

	@Override
	public void handle(ActionEvent e) {

		if (e.getTarget() == cbNome && cbNome.getValue() != null) {

		
				cliente = cbNome.getValue();
				alimentaCamposCliente();
			

		} else if (e.getTarget() == cbLivro && cbLivro.getValue() != null) {

			livro = cbLivro.getValue();
			alimentaCamposLivro();

		} else

		if (e.getTarget() == btnAdd && cbLivro.getValue() != null) {

			adicionaItemLista();

		} else if (e.getTarget() == btnRemover) {

			removeItenLista();
		}

	}

	@Override
	public Pane render() {

		// cria painel principal
		BorderPane painel = new BorderPane();

		// cria barra de botoes
		HBox bbBotoes = new HBox();
		bbBotoes.setPadding(new Insets(20, 20, 20, 150));
		bbBotoes.setSpacing(50);

		// cria tabbela com os itens da venda
		tbItes = new TableView<ModelItensVenda>();
		tbItes.setPadding(new Insets(15, 15, 15, 15));
		tbItes.setPrefWidth(500);

		TableColumn<ModelItensVenda, String> colTitulo = new TableColumn<>("Titulo");
		colTitulo.setCellValueFactory(new PropertyValueFactory<ModelItensVenda, String>("Titulo"));
		colTitulo.setPrefWidth(170);

		TableColumn<ModelItensVenda, String> colIsbn = new TableColumn<>("Isbn");
		colIsbn.setCellValueFactory(new PropertyValueFactory<ModelItensVenda, String>("Isbn"));
		colIsbn.setPrefWidth(170);

		tbItes.getColumns().addAll(colTitulo, colIsbn);

		// titulo da tela
		StackPane stitulo = new StackPane();
		Label lbTitulo = new Label("Venda Livros");
		lbTitulo.setFont(new Font("Chilanka", 20));
		lbTitulo.setPadding(new Insets(15, 15, 15, 15));
		stitulo.getChildren().add(lbTitulo);

		// cria vbox principal com todos os itens da tela
		VBox vbPrincipal = new VBox();
		vbPrincipal.setSpacing(20);

		tfISBN = new TextField();
		cbLivro = new ComboBox<>();
		cbLivro.setEditable(true);
		cbLivro.setOnAction(this);

		tfCpfCliente = new TextField();
		cbNome = new ComboBox<>();
		cbNome.setPrefWidth(200);
		cbNome.setEditable(true);
		cbNome.setOnAction(this);

		btnAdd = new Button("+");
		btnAdd.setOnAction(this);
		btnFinalizar = new Button("Finalizar");
		btnRemover = new Button("Remover");
		btnRemover.setOnAction(this);
		btnCancelar = new Button("Cancelar");
		// btnCancelar.setPrefWidth(80);

		carregaCbCliente("");
		carregaCbLivro("");

		// combobox livro para string
		cbLivro.setConverter(new StringConverter<Livro>() {

			@Override
			public String toString(Livro object) {

				if (object != null)
					return object.toString();
				return null;
			}

			@Override
			public Livro fromString(String string) {

				return cbLivro.getValue();
			}
		});

		// combobox livro ação teclado
		cbLivro.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
			@Override
			public void handle(javafx.scene.input.KeyEvent event) {

				if (event.getCode() != KeyCode.ENTER && event.getCode() != KeyCode.DOWN
						&& event.getCode() != KeyCode.UP) {

					carregaCbLivro(cbLivro.getEditor().getText());
					cbLivro.show();
				}

			}
		});

		// converter combobox cliente para string
		cbNome.setConverter(new StringConverter<Cliente>() {

			@Override
			public String toString(Cliente object) {

				if (object != null)
					return object.toString();
				return null;
			}

			@Override
			public Cliente fromString(String string) {

				return cbNome.getValue();
			}
		});

		// combobox cliente ação teclado
		cbNome.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
			@Override
			public void handle(javafx.scene.input.KeyEvent event) {

				if (event.getCode() != KeyCode.ENTER && event.getCode() != KeyCode.DOWN
						&& event.getCode() != KeyCode.UP) {

					carregaCbCliente(cbNome.getEditor().getText());
					cbNome.show();
				}

			}
		});

		HBox hbCliente1 = new HBox();
		hbCliente1.setPadding(new Insets(15, 15, 15, 15));
		hbCliente1.setSpacing(20);
		vbPrincipal.getChildren().add(hbCliente1);

		bbBotoes.getChildren().add(btnCancelar);
		bbBotoes.getChildren().add(btnRemover);
		bbBotoes.getChildren().add(btnFinalizar);

		HBox hbLivro = new HBox();
		hbLivro.setPadding(new Insets(15, 15, 15, 15));
		hbLivro.setSpacing(20);
		vbPrincipal.getChildren().add(hbLivro);

		vbPrincipal.getChildren().add(tbItes);

		hbCliente1.getChildren().add(new Label("Cliente: "));
		hbCliente1.getChildren().add(cbNome);
		hbCliente1.getChildren().add(new Label("Cpf:  "));
		hbCliente1.getChildren().add(tfCpfCliente);

		hbLivro.getChildren().add(new Label("Livro:   "));
		hbLivro.getChildren().add(cbLivro);
		hbLivro.getChildren().add(new Label("ISBN: "));
		hbLivro.getChildren().add(tfISBN);
		hbLivro.getChildren().add(btnAdd);

		painel.setTop(stitulo);
		painel.setCenter(vbPrincipal);
		painel.setBottom(bbBotoes);

		return painel;
	}

	private void alimentaCamposCliente() {

		tfCpfCliente.setEditable(true);

		tfCpfCliente.setText(cliente.getCpf());

		tfCpfCliente.setEditable(false);
	}

	private void alimentaCamposLivro() {
		tfISBN.setEditable(true);

		tfISBN.setText(livro.getIsbn());

		tfISBN.setEditable(false);

	}

	private void carregaCbCliente(String nome) {

		cc = new ControleCliente();

		try {

			cbNome.setItems(cc.buscaClienteNome(nome));

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void carregaCbLivro(String titulo) {

		cl = new ControleLivro();

		try {

			cbLivro.setItems(cl.buscaClienteNome(titulo));

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void adicionaItemLista() {

		if (cbNome.getValue() != null) {

			if (listaVenda == null) {

				listaVenda = FXCollections.observableArrayList();

				ModelItensVenda miv = new ModelItensVenda();

				miv.setTitulo(cbLivro.getValue().getTitulo());
				miv.setIsbn(tfISBN.getText());
				listaVenda.add(miv);

				limpaCampoLivro();

				tbItes.setItems(listaVenda);
			}

		} else {
			Mensagens.erro("Erro", "Erro Cliente", "Seleciona um cliente");
		}

	}

	private void removeItenLista() {

		if (listaVenda != null && tbItes.getSelectionModel().getSelectedItem() != null) {

			listaVenda.remove(tbItes.getSelectionModel().getSelectedItem());

			tbItes.setItems(listaVenda);

		}

	}

	private void limpaCampoLivro() {

		tfISBN.setEditable(true);
		tfISBN.setText("");
		tfISBN.setEditable(true);

		cbLivro.setValue(null);

	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

}