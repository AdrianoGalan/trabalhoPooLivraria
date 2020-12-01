package boundary;

import java.sql.SQLException;

import control.ControleCliente;
import control.ControleItensVenda;
import control.ControleLivro;
import control.ControlePreco;
import control.ControleTelas;
import control.ControleVenda;
import control.GetenciadorPrincipal;
import entity.Cliente;
import entity.Livro;
import entity.Preco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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

/**
 * Classe tela venda livro e implementa a interface ControleTelas do package control.
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class TelaVendaLivro implements ControleTelas, EventHandler<ActionEvent> {

	/** Propriedade tfCpfCliente */
	private TextField tfCpfCliente;
	
	/** Propriedade tfISBN */
	private TextField tfISBN;
	
	/** Propriedade tfPreco */
	private TextField tfPreco;
	
	/** Propriedade tfPrecoTotal */
	private TextField tfPrecoTotal;
	
	/** Propriedade tfQtsLivros */
	private TextField tfQtsLivros;

	
	/** Propriedade cbLivro */
	private ComboBox<Livro> cbLivro;
	
	/** Propriedade cbNome */
	private ComboBox<Cliente> cbNome;

	
	/** Propriedade tbItes */
	private TableView<ModelItensVenda> tbItes;

	
	/** Propriedade btnAdd */
	private Button btnAdd;
	
	/** Propriedade btnFinalizar */
	private Button btnFinalizar;
	
	/** Propriedade btnRemover */
	private Button btnRemover;

	
	/** Propriedade cliente */
	private Cliente cliente;
	
	/** Propriedade livro */
	private Livro livro;
	
	/** Propriedade preco */
	private Preco preco;

	
	/** Propriedade precoTotal */
	private double precoTotal = 0;
	
	/** Propriedade qtdLivro */
	private int qtdLivro = 0;

	
	/** Propriedade cc */
	private ControleCliente cc;
	
	/** Propriedade cl */
	private ControleLivro cl;
	
	/** Propriedade cp */
	private ControlePreco cp;

	
	/** Propriedade listaVenda */
	private ObservableList<ModelItensVenda> listaVenda;

	/**
	 * M�todo handle - acao aos botoes e eventos da tela venda de livro.
	 */
	@Override
	public void handle(ActionEvent e) {

		if (e.getTarget() == cbNome && cbNome.getValue() != null) {

			if (listaVenda.isEmpty()) {
				cliente = cbNome.getValue();
				alimentaCamposCliente();
			}

		} else if (e.getTarget() == cbLivro && cbLivro.getValue() != null) {

			livro = cbLivro.getValue();

			alimentaCamposLivro();

		} else

		if (e.getTarget() == btnAdd && cbLivro.getValue() != null) {

			adicionaItemLista();

		} else if (e.getTarget() == btnRemover) {

			removeItenLista();
		} else if (e.getTarget() == btnFinalizar && !listaVenda.isEmpty()) {

			finalizarVenda();
		}

	}

	/**
	 * Painel render
	 */
	@Override
	public Pane render() {

		cl = new ControleLivro();
		cc = new ControleCliente();
		cp = new ControlePreco();

		// cria lista de itens
		listaVenda = FXCollections.observableArrayList();

		// cria painel principal
		BorderPane painel = new BorderPane();

		// cria barra de botoes
		HBox bbBotoes = new HBox();
		bbBotoes.setPadding(new Insets(20, 20, 20, 150));
		bbBotoes.setSpacing(50);

		// cria tabbela com os itens da venda
		tbItes = new TableView<ModelItensVenda>();
		tbItes.setPadding(new Insets(0, 15, 15, 15));
		tbItes.setPrefWidth(400);

		TableColumn<ModelItensVenda, Integer> colIten = new TableColumn<>("Item");
		colIten.setCellValueFactory(new PropertyValueFactory<ModelItensVenda, Integer>("Iten"));
		colIten.setPrefWidth(80);

		TableColumn<ModelItensVenda, String> colTitulo = new TableColumn<>("Titulo");
		colTitulo.setCellValueFactory(new PropertyValueFactory<ModelItensVenda, String>("Titulo"));
		colTitulo.setPrefWidth(250);

		TableColumn<ModelItensVenda, String> colIsbn = new TableColumn<>("ISBN");
		colIsbn.setCellValueFactory(new PropertyValueFactory<ModelItensVenda, String>("Isbn"));
		colIsbn.setPrefWidth(50);

		TableColumn<ModelItensVenda, String> colGenero = new TableColumn<>("Genero");
		colGenero.setCellValueFactory(new PropertyValueFactory<ModelItensVenda, String>("generoLivro"));
		colGenero.setPrefWidth(80);

		TableColumn<ModelItensVenda, Integer> colEstoque = new TableColumn<>("Qts Estoque");
		colEstoque.setCellValueFactory(new PropertyValueFactory<ModelItensVenda, Integer>("estoqueLivro"));
		colEstoque.setPrefWidth(100);

		TableColumn<ModelItensVenda, Integer> colQtsVenda = new TableColumn<>("Quantidade");
		colQtsVenda.setCellValueFactory(new PropertyValueFactory<ModelItensVenda, Integer>("qtsVenda"));
		colQtsVenda.setPrefWidth(100);

		TableColumn<ModelItensVenda, Double> colPreco = new TableColumn<>("Preco");
		colPreco.setCellValueFactory(new PropertyValueFactory<ModelItensVenda, Double>("preco"));
		colPreco.setPrefWidth(100);

		tbItes.getColumns().addAll(colIten, colTitulo, colIsbn, colGenero, colEstoque, colQtsVenda, colPreco);

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
		tfISBN.setPrefWidth(80);
		tfQtsLivros = new TextField("1");
		tfQtsLivros.setPrefWidth(50);
		tfPreco = new TextField();
		tfPreco.setPrefWidth(80);
		tfPrecoTotal = new TextField();
		tfPrecoTotal.setPrefWidth(80);
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
		btnFinalizar.setOnAction(this);
		btnRemover = new Button("Remover");
		btnRemover.setOnAction(this);

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

		bbBotoes.getChildren().add(btnRemover);
		bbBotoes.getChildren().add(btnFinalizar);

		HBox hbLivro = new HBox();
		hbLivro.setPadding(new Insets(15, 15, 15, 15));
		hbLivro.setSpacing(20);
		vbPrincipal.getChildren().add(hbLivro);

		vbPrincipal.getChildren().add(tbItes);

		HBox hbPrecoTotal = new HBox();
		hbPrecoTotal.setPadding(new Insets(15, 15, 15, 15));
		hbPrecoTotal.setSpacing(5);
		hbPrecoTotal.getChildren().add(new Label("Total: "));
		hbPrecoTotal.getChildren().add(tfPrecoTotal);
		vbPrincipal.getChildren().add(hbPrecoTotal);

		hbCliente1.getChildren().add(new Label("Cliente: "));
		hbCliente1.getChildren().add(cbNome);
		hbCliente1.getChildren().add(new Label("Cpf:  "));
		hbCliente1.getChildren().add(tfCpfCliente);

		hbLivro.getChildren().add(new Label("Livro:   "));
		hbLivro.getChildren().add(cbLivro);
		hbLivro.getChildren().add(new Label("ISBN:"));
		hbLivro.getChildren().add(tfISBN);
		hbLivro.getChildren().add(new Label("Qtd:"));
		hbLivro.getChildren().add(tfQtsLivros);
		hbLivro.getChildren().add(new Label("Preço:"));
		hbLivro.getChildren().add(tfPreco);
		hbLivro.getChildren().add(btnAdd);

		painel.setTop(stitulo);
		painel.setCenter(vbPrincipal);
		painel.setBottom(bbBotoes);

		return painel;
	}

	/**
	 * M�todo que busca informacoes do cliente
	 */
	private void alimentaCamposCliente() {

		tfCpfCliente.setEditable(true);

		tfCpfCliente.setText(cliente.getCpf());

		tfCpfCliente.setEditable(false);
	}

	/**
	 * Metodo que busca informacoes do livro
	 */
	private void alimentaCamposLivro() {

		carregaPreco(livro.getPrecoAtual());

		tfISBN.setEditable(true);
		tfPreco.setEditable(true);

		tfISBN.setText(livro.getIsbn());
		tfPreco.setText(String.valueOf("R$:" + preco.getValor()));

		tfISBN.setEditable(false);
		tfPreco.setEditable(false);

	}

	/**
	 * M�todo que carrega comboBox do cliente.
	 * 
	 * @param nome
	 */
	private void carregaCbCliente(String nome) {

		try {

			cbNome.setItems(cc.buscaClienteNome(nome));

		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		}

	}

	/**
	 * Metodo que carrega a combobox do livro.
	 * 
	 * @param titulo
	 */
	private void carregaCbLivro(String titulo) {

		try {

			cbLivro.setItems(cl.buscaClienteNome(titulo));

		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		}

	}

	/**
	 * Metodo que carrega preco.
	 * 
	 * @param id
	 */
	private void carregaPreco(int id) {

		try {

			preco = cp.buscaPrecoId(id);

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * M�todo que adiciona um livro ma lista.
	 */
	private void adicionaItemLista() {

		try {

			qtdLivro = Integer.parseInt(tfQtsLivros.getText());

			if ((livro.getQtsEstoque() - qtdLivro) >= 0) {

				boolean flag = false;
				int i = 0;

				if (cbNome.getValue() != null) {

					for (i = 0; i < listaVenda.size(); i++) {

						if (listaVenda.get(i).getIdLivro() == livro.getIdLivro()) {
							flag = true;
							break;
						}

					}

					if (!flag) {

						ModelItensVenda miv = new ModelItensVenda();

						miv.setIdLivro(livro.getIdLivro());
						miv.setTitulo(livro.getTitulo());
						miv.setIsbn(livro.getIsbn());
						miv.setEstoqueLivro(livro.getQtsEstoque());
						miv.setPreco(preco.getValor());
						miv.setGeneroLivro(livro.getGenero());
						miv.setIten(listaVenda.size() + 1);
						miv.setQtsVenda(qtdLivro);
						listaVenda.add(miv);

						precoTotal += (preco.getValor() * qtdLivro);

						limpaCampoLivro();

						tfPrecoTotal.setEditable(true);
						tfPrecoTotal.setText(String.valueOf(precoTotal));
						tfPrecoTotal.setEditable(false);

						tbItes.setItems(listaVenda);

					} else {

						Mensagens.erro("Livro na lista", "Ja contem livro na lista", "Remova o livro da lista");

					}

				} else {
					Mensagens.erro("Erro", "Erro Cliente", "Seleciona um cliente");
				}
			} else {
				Mensagens.erro("Quantidade", "Quantidade invalida", "Não tem livro no estoque");
			}
		} catch (Exception e) {
			Mensagens.erro("Quantidade", "Quantidade invalida", "Digite uma quantidade valida");
		}

	}

	/**
	 * M�todo que remove um livro da lista.
	 */
	private void removeItenLista() {

		if (listaVenda != null && tbItes.getSelectionModel().getSelectedItem() != null) {

			precoTotal -= (tbItes.getSelectionModel().getSelectedItem().getPreco()
					* tbItes.getSelectionModel().getSelectedItem().getQtsVenda());

			listaVenda.remove(tbItes.getSelectionModel().getSelectedItem());

			ObservableList<ModelItensVenda> lista = FXCollections.observableArrayList();

			for (int i = 0; i < listaVenda.size(); i++) {

				listaVenda.get(i).setIten(i + 1);
				lista.add(listaVenda.get(i));
			}

			tfPrecoTotal.setEditable(true);
			tfPrecoTotal.setText(String.valueOf(precoTotal));
			tfPrecoTotal.setEditable(false);

			tbItes.setItems(lista);

			lista = null;

		}

	}

	/**
	 * Metodo que finaliza a venda dos livros.
	 */
	private void finalizarVenda() {

		int idVenda = -1;

		ControleVenda cv = new ControleVenda();
		ControleItensVenda civ = new ControleItensVenda();
		cl = new ControleLivro();

		try {

			idVenda = cv.addVenda(cliente.getIdCliente());

			if (idVenda != -1) {

				civ.addLivro(idVenda, listaVenda);
				
				for (int i = 0; i < listaVenda.size(); i++) {
					cl.atualizaEstoque(listaVenda.get(i).getIdLivro(), listaVenda.get(i).getEstoqueLivro() - listaVenda.get(i).getQtsVenda());
				}
				
				limpaCampoLivro();
				limpaCliente();
				limpaTabla();
				
				precoTotal = 0;
				qtdLivro = 0;
				carregaCbLivro("");
				
				

				Mensagens.informacao("Venda", "Venda finalizada", "Venda realizada com sucesso");

			}

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	/** 
	 * Metodo que limpa os campos.
	 */
	private void limpaCampoLivro() {

		tfISBN.setEditable(true);
		tfPreco.setEditable(true);

		tfPreco.setText("");
		tfISBN.setText("");
		tfQtsLivros.setText("1");
		
		tfPreco.setEditable(false);
		tfISBN.setEditable(true);

		cbLivro.setValue(null);

	}

	/**
	 * Metodo que limpa campo do cliente.
	 */
	private void limpaCliente() {

		tfCpfCliente.setEditable(true);
		tfCpfCliente.setText("");

		cbNome.setValue(null);

	}

	/**
	 * M�todo que limpa tabela.
	 */
	private void limpaTabla() {

		listaVenda.clear();
		tbItes.setItems(listaVenda);

		tfPrecoTotal.setEditable(true);
		tfPrecoTotal.setText("");

	}

	/**
	 * Gerenciador Principal
	 */
	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {

	}

}