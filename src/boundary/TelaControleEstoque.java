package boundary;

import java.sql.SQLException;

import control.ControleLivro;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tabelaModel.ModelTabelaLivro;
import util.Mensagens;

/**
 * Classe tela de controle do estoque e implementa a interface ControleTelas do package control
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class TelaControleEstoque implements ControleTelas, EventHandler<ActionEvent> {

	/** Propriedade controle */
	private ControleLivro controle = new ControleLivro();
	
	/** Propriedade tbvPesqLivro */
	private TableView<ModelTabelaLivro> tbvPesqLivro;
	
	/** Propriedade btPesquisar */
	private Button btPesquisar;
	
	/** Propriedade btAtualizar */
	private Button btAtualizar;
	
	/** Propriedade tfPesquisa */
	private TextField tfPesquisa;
	
	/** Propriedade tfQtsLivros */
	private TextField tfQtsLivros;
	
	/** Propriedade cbOpcPesq */
	private ComboBox<String> cbOpcPesq;

	/**
	 * Painel Render
	 */
	@Override
	public Pane render() {
		Pane painel = new Pane();

		HBox hbCombo = new HBox();
		hbCombo.setSpacing(10);

		Label lblPesqCombo = new Label("Selecione pesquisar por:");
		lblPesqCombo.setPadding(new Insets(5, 0, 0, 0));
		cbOpcPesq = new ComboBox<String>();
		cbOpcPesq.getItems().addAll("Titulo", "ISBN", "Autor");
		cbOpcPesq.setPrefWidth(200);
		// Seleciona qual indice vai ficar selecionado, pode dar erro dps (vai saber...)
		cbOpcPesq.getSelectionModel().select(0);

		hbCombo.getChildren().add(lblPesqCombo);
		hbCombo.getChildren().add(cbOpcPesq);

		HBox hbBotao = new HBox();
		hbBotao.setSpacing(20);

		HBox hbBtnAtualizar = new HBox();
		hbBtnAtualizar.setSpacing(20);

		VBox vbEs = new VBox();
		// new Insets(y, ?, ?, x)
		vbEs.setPadding(new Insets(25, 0, 15, 100));
		vbEs.setSpacing(15);

		Label lblPesquisa = new Label("Digite:");
		tfPesquisa = new TextField();
		btPesquisar = new Button("Pesquisar");
		btPesquisar.addEventHandler(ActionEvent.ACTION, this);
		tbvPesqLivro = new TableView<ModelTabelaLivro>();
		tbvPesqLivro.setPrefWidth(800);

		tfQtsLivros = new TextField();
		tfQtsLivros.setPrefWidth(80);
		btAtualizar = new Button("Atualizar");
		btAtualizar.addEventHandler(ActionEvent.ACTION, this);

		hbBotao.getChildren().add(lblPesquisa);
		lblPesquisa.setPadding(new Insets(5, 0, 0, 0));
		tfPesquisa.setPrefWidth(250);
		hbBotao.getChildren().add(tfPesquisa);
		hbBotao.getChildren().add(btPesquisar);

		hbBtnAtualizar.getChildren().add(new Label("Digite a quantidade de livros:"));
		hbBtnAtualizar.getChildren().add(tfQtsLivros);
		hbBtnAtualizar.getChildren().add(btAtualizar);

		vbEs.getChildren().add(hbCombo);
		vbEs.getChildren().add(hbBotao);
		vbEs.getChildren().add(tbvPesqLivro);
		vbEs.getChildren().add(hbBtnAtualizar);

		carregarTela();

		painel.getChildren().add(vbEs);

		tfPesquisa.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
			@Override
			public void handle(javafx.scene.input.KeyEvent event) {

				buscaLivro();
				carregarTabela();

			}
		});

		return painel;
	}

	/**
	 * Metodo handle - acao aos botoes - pesquisar e atualizar estoque
	 */
	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btPesquisar) {

			buscaLivro();
			carregarTabela();

		} else if (e.getTarget() == btAtualizar) {

			atualizaEstoque();
		}

	}

	/**
	 * Metodo que atualiza a quantidade de livros no estoque
	 */
	private void atualizaEstoque() {

		try {

			int qts = Integer.parseInt(tfQtsLivros.getText());
			if (tbvPesqLivro.getSelectionModel().getSelectedItem() != null) {

				ControleLivro cl = new ControleLivro();
				cl.atualizaEstoque(tbvPesqLivro.getSelectionModel().getSelectedItem().getIdLivro(),
						tbvPesqLivro.getSelectionModel().getSelectedItem().getQtsEstoque() + qts);
				tfQtsLivros.setText("0");
				buscaLivro();
				carregarTabela();

			} else {
				Mensagens.erro("Erro", "Seleciona Livro", "Selecione um livro para atualizar");
			}

		} catch (Exception e) {
			Mensagens.erro("Quantidade Erro", "Quantidade invalida", "Digite uma quantidade valida");
		}

	}

	/**
	 * Metodo que carrega a tabela
	 */
	private void carregarTabela() {
		tbvPesqLivro.setItems(controle.getLista());
	}

	/**
	 * M�todo que carrega a tela
	 */
	private void carregarTela() {

		TableColumn<ModelTabelaLivro, String> colTitulo = new TableColumn<>("Titulo");
		colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		colTitulo.setPrefWidth(200);

		TableColumn<ModelTabelaLivro, String> colAutor = new TableColumn<>("Autor");
		colAutor.setCellValueFactory(new PropertyValueFactory<>("Autor"));
		colAutor.setPrefWidth(200);

		TableColumn<ModelTabelaLivro, String> colISBN = new TableColumn<>("ISBN");
		colISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		colISBN.setPrefWidth(80);

		TableColumn<ModelTabelaLivro, Integer> colEstoque = new TableColumn<>("Qnt estoque");
		colEstoque.setCellValueFactory(new PropertyValueFactory<>("qtsEstoque"));
		colEstoque.setPrefWidth(100);

		tbvPesqLivro.getColumns().addAll(colTitulo, colAutor, colISBN, colEstoque);

	}

	/**
	 * M�todo que faz a busca(pesquisa) de livros
	 */
	private void buscaLivro() {

		try {

			controle.procurarLivro(tfPesquisa.getText(), cbOpcPesq.getSelectionModel().getSelectedIndex());

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Gerenciador principal
	 */
	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

}