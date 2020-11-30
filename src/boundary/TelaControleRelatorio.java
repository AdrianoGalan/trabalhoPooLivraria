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

public class TelaControleRelatorio implements ControleTelas, EventHandler<ActionEvent> {

	private ControleRelatorio cR;
	private TableView<ModelTabelaLivro> tbvPesqLivro;
	private Button btnExibir;
	private ComboBox<String> cbTipoRelat;
	private TextField melhorDia;
	private ObservableList<ModelTabelaLivro> lista;

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

		Label lblPesqCombo = new Label("Exibir Relatótio por:");
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

	@Override
	public void handle(ActionEvent e) {

		if (e.getTarget() == btnExibir) {

			selecaoRelatorio();

		}
	}

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
			Mensagens.informacao("Seleção", "Seleção invalida", "Seleciona uma opção");
			break;

		}

	}

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
			semanaDia = "Terça Feira";
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

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

}