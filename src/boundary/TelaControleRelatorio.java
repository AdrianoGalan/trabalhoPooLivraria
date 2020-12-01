package boundary;

import java.sql.SQLException;

import control.ControleLivro;
import control.ControleRelatorio;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * Classe tela de controle Relatorio e implementa a interface ControleTelas do package control
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class TelaControleRelatorio implements ControleTelas, EventHandler<ActionEvent> {

	/** Propriedade cR */
	private ControleRelatorio cR;
	
	/** Propriedade tbvPesqLivro */
	private TableView<ModelTabelaLivro> tbvPesqLivro;
	
	/** Propriedade btnExibir */
	private Button btnExibir;
	
	/** Propriedade cbTipoRelat */
	private ComboBox<String> cbTipoRelat;
	
	/** Propriedade melhorDia */
	private TextField melhorDia;
	
	/** Propriedade lista */
	private ObservableList<ModelTabelaLivro> lista;

	/**
	 * Painel Render
	 */
	@Override
	public Pane render() {

		cR = new ControleRelatorio();

		Pane painel = new Pane();

		HBox hbCombo = new HBox();

		hbCombo.setSpacing(10);

		melhorDia = new TextField();
		melhorDia.setEditable(false);

		melhorDiarSemana();

		HBox hbMelhorDia = new HBox();
		hbMelhorDia.setSpacing(10);
		hbMelhorDia.getChildren().add(new Label("Melhor dia da semana para venda: "));
		hbMelhorDia.getChildren().add(melhorDia);

		btnExibir = new Button("EXIBIR");
		btnExibir.addEventHandler(ActionEvent.ACTION, this);
		tbvPesqLivro = new TableView<ModelTabelaLivro>();
		tbvPesqLivro.setPrefWidth(500);

		Label lblPesqCombo = new Label("Exibir Relat√≥tio por:");
		lblPesqCombo.setPadding(new Insets(15, 15, 15, 15));

		cbTipoRelat = new ComboBox<String>();
		cbTipoRelat.setPrefWidth(250);
		cbTipoRelat.getItems().addAll("Produtos mais vendidos", "Produtos Menos vendidos",
				"Produtos com baixo estoque < 5", "Data ultima venda ");
		cbTipoRelat.setPrefWidth(200);
		cbTipoRelat.getSelectionModel().select(0);

		hbCombo.getChildren().add(lblPesqCombo);
		hbCombo.getChildren().add(cbTipoRelat);
		hbCombo.getChildren().add(btnExibir);

		VBox vbEs = new VBox();

		vbEs.setPadding(new Insets(15, 15, 15, 250));
		vbEs.setSpacing(15);

		vbEs.getChildren().add(hbMelhorDia);
		vbEs.getChildren().add(hbCombo);

		vbEs.getChildren().add(tbvPesqLivro);

		painel.getChildren().add(vbEs);

		return painel;
	}

	/**
	 * MÈtodo handle - acao aos botoes - exibir relatorio
	 */
	@Override
	public void handle(ActionEvent e) {

		if (e.getTarget() == btnExibir) {

			selecaoRelatorio();

		}
	}

	/**
	 * MÈtodo selecao relatorio
	 */
	private void selecaoRelatorio() {

		switch (cbTipoRelat.getSelectionModel().getSelectedIndex()) {
		case 0:
			maisVendido();
			break;

		case 1:
			menoVendido();
			break;

		case 2:
			baixoEstoque();
			break;

		case 3:
			dataUltimaVenda();
			break;

		default:
			Mensagens.informacao("Selecao", "Selecao invalida", "Selecione uma opcao");
			break;

		}

	}

	/**
	 * MÈtodo que pega a data da ultima venda.
	 */
	private void dataUltimaVenda() {

		lista = FXCollections.observableArrayList();

		try {

			lista.addAll(cR.dataVenda());
			carregarDataVenda();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * MÈtodo que pega livros com estoque baixo - menor que 5.
	 */
	private void baixoEstoque() {

		lista = FXCollections.observableArrayList();

		try {

			lista.addAll(cR.livroEstoqueBaixo());
			carregarEstoqueBaixo();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * MÈtodo que pega os livros menos vendidos
	 */
	private void menoVendido() {

		lista = FXCollections.observableArrayList();

		try {

			lista.addAll(cR.livroMenosVendido());
			carregarMaisMenos();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * MÈtodo que pega os livros mais vendidos
	 */
	private void maisVendido() {

		lista = FXCollections.observableArrayList();

		try {

			lista.addAll(cR.livroMaisVendido());
			carregarMaisMenos();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * mÈtodo que carrega os livros mais e menos vendidos
	 */
	private void carregarMaisMenos() {

		if (tbvPesqLivro.getColumns().isEmpty()
				|| !tbvPesqLivro.getColumns().get(2).getText().equals("Quantidade venda")) {

			tbvPesqLivro.getColumns().clear();

			TableColumn<ModelTabelaLivro, String> colISBN = new TableColumn<>("ISBN");
			colISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
			colISBN.setPrefWidth(100);

			TableColumn<ModelTabelaLivro, String> colTitulo = new TableColumn<>("Titulo");
			colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
			colTitulo.setPrefWidth(200);

			TableColumn<ModelTabelaLivro, String> colQTD = new TableColumn<>("Quantidade venda");
			colQTD.setCellValueFactory(new PropertyValueFactory<>("qdtVenda"));
			colQTD.setPrefWidth(200);

			tbvPesqLivro.getColumns().addAll(colISBN, colTitulo, colQTD);

		}

		tbvPesqLivro.setItems(lista);

	}

	/**
	 * MÈtodo que carrega os livros com estoque baixo
	 */
	private void carregarEstoqueBaixo() {

		if (tbvPesqLivro.getColumns().isEmpty()
				|| !tbvPesqLivro.getColumns().get(2).getText().equals("Quantidade estoque")) {

			tbvPesqLivro.getColumns().clear();

			TableColumn<ModelTabelaLivro, String> colISBN = new TableColumn<>("ISBN");
			colISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
			colISBN.setPrefWidth(100);

			TableColumn<ModelTabelaLivro, String> colTitulo = new TableColumn<>("Titulo");
			colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
			colTitulo.setPrefWidth(200);

			TableColumn<ModelTabelaLivro, String> colQTD = new TableColumn<>("Quantidade estoque");
			colQTD.setCellValueFactory(new PropertyValueFactory<>("qtsEstoque"));
			colQTD.setPrefWidth(200);

			tbvPesqLivro.getColumns().addAll(colISBN, colTitulo, colQTD);
		}

		tbvPesqLivro.setItems(lista);

	}
	

	/**
	 * MÈtodo que carrega a ultima data da venda 
	 */
	private void carregarDataVenda() {

		if (tbvPesqLivro.getColumns().isEmpty()
				|| !tbvPesqLivro.getColumns().get(2).getText().equals("Data ult. venda")) {

			tbvPesqLivro.getColumns().clear();

			TableColumn<ModelTabelaLivro, String> colISBN = new TableColumn<>("ISBN");
			colISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
			colISBN.setPrefWidth(100);

			TableColumn<ModelTabelaLivro, String> colTitulo = new TableColumn<>("Titulo");
			colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
			colTitulo.setPrefWidth(200);

			TableColumn<ModelTabelaLivro, String> colData = new TableColumn<>("Data ult. venda");
			colData.setCellValueFactory(new PropertyValueFactory<>("dataUltimaVenda"));
			colData.setPrefWidth(200);

			tbvPesqLivro.getColumns().addAll(colISBN, colTitulo, colData);
		}

		tbvPesqLivro.setItems(lista);

	}

	/**
	 * MÈtodo que carrega o melhor dia da semana para venda de livros
	 */
	private void melhorDiarSemana() {

		int dia = 0;
		String semanaDia = null;

		try {
			dia = cR.melhorDiaSemana();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch (dia) {
		case 1:
			 semanaDia = "Domingo";
			break;
		case 2:
			semanaDia = "Segunda Feira";
			break;
		case 3:
			semanaDia = "Ter√ßa Feira";
			break;
		case 4:
			semanaDia = "Quarta Feira";
			break;
		case 5:
			semanaDia = "Quinta Feira";
			break;
		case 6:
			semanaDia = "Sexta Feira";
			break;
		case 7:
			semanaDia = "Sabado";
			break;

		default:
			break;
		}

		melhorDia.setEditable(true);
		melhorDia.setText(semanaDia);
		melhorDia.setEditable(false);
	}

	/**
	 * Gerenciador principal 
	 */
	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

}