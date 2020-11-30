package boundary;

import java.sql.SQLException;

import control.ControleLivro;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tabelaModel.ModelTabelaLivro;
import util.Mensagens;

public class TelaPesquisaLivro implements ControleTelas, EventHandler<ActionEvent> {

	private ControleLivro controle = new ControleLivro();
	private TableView<ModelTabelaLivro> tbvPesqLivro;
	private Button btPesquisar;
	private Button btAlteraPreco;
	private TextField tfPesquisa;
	private ComboBox<String> cbOpcPesq;
	private TelaAlteraPrecoLivro telaPreco = new TelaAlteraPrecoLivro(tbvPesqLivro, controle, this);

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

		VBox vbEs = new VBox();
		// new Insets(y, ?, ?, x)
		vbEs.setPadding(new Insets(25, 0, 15, 100));
		vbEs.setSpacing(15);

		Label lblPesquisa = new Label("Digite:");
		tfPesquisa = new TextField();
		btPesquisar = new Button("Pesquisar");
		btAlteraPreco = new Button("Alterar preco");
		btPesquisar.addEventHandler(ActionEvent.ACTION, this);
		btAlteraPreco.addEventHandler(ActionEvent.ACTION, this);
		tbvPesqLivro = new TableView<ModelTabelaLivro>();
		tbvPesqLivro.setPrefWidth(800);

		hbBotao.getChildren().add(lblPesquisa);
		lblPesquisa.setPadding(new Insets(5, 0, 0, 0));
		tfPesquisa.setPrefWidth(250);
		hbBotao.getChildren().add(tfPesquisa);
		hbBotao.getChildren().addAll(btPesquisar, btAlteraPreco);

		vbEs.getChildren().add(hbCombo);
		vbEs.getChildren().add(hbBotao);
		vbEs.getChildren().add(tbvPesqLivro);

		carregarTela();

		painel.getChildren().add(vbEs);

		tfPesquisa.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
			@Override
			public void handle(javafx.scene.input.KeyEvent event) {

				try {
					controle.procurarLivro(tfPesquisa.getText(), cbOpcPesq.getSelectionModel().getSelectedIndex());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				carregarTabela();

			}
		});

		return painel;
	}

	@Override
	public void handle(ActionEvent e) {

		int i = tbvPesqLivro.getSelectionModel().getSelectedIndex();
		if (e.getSource() == btPesquisar) {
			
			carregarTabela();
			
		} else if (e.getSource() == btAlteraPreco) {
			
			if (i == -1 || i > tbvPesqLivro.getItems().size()) {
	
				Mensagens.informacao("Selecao", "Selecao ", "Por favor selecione um livro antes de realizar essa acao.");

			} else {
				telaPreco.render(tbvPesqLivro.getSelectionModel().getSelectedItem());
				
				carregarTabela();
			}
		}

	}

	public void carregarTabela() {
		try {
			controle.procurarLivro(tfPesquisa.getText(), cbOpcPesq.getSelectionModel().getSelectedIndex());
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		tbvPesqLivro.setItems(controle.getLista());
	}

	private void carregarTela() {

		TableColumn<ModelTabelaLivro, String> colPreco = new TableColumn<>("Preco");
		colPreco.setCellValueFactory(new PropertyValueFactory<>("Preco"));

		TableColumn<ModelTabelaLivro, String> colTitulo = new TableColumn<>("Titulo");
		colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		colTitulo.setPrefWidth(150);

		TableColumn<ModelTabelaLivro, String> colAutor = new TableColumn<>("Autor");
		colAutor.setCellValueFactory(new PropertyValueFactory<>("Autor"));
		colAutor.setPrefWidth(150);

		TableColumn<ModelTabelaLivro, String> colISBN = new TableColumn<>("ISBN");
		colISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));

		TableColumn<ModelTabelaLivro, String> colGenero = new TableColumn<>("Genero");
		colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));

		TableColumn<ModelTabelaLivro, String> colEdicao = new TableColumn<>("Edicao");
		colEdicao.setCellValueFactory(new PropertyValueFactory<>("edicao"));
		colEdicao.setPrefWidth(80);

		TableColumn<ModelTabelaLivro, String> colAno = new TableColumn<>("Ano");
		colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));

		TableColumn<ModelTabelaLivro, Integer> colEstoque = new TableColumn<>("Qnt estoque");
		colEstoque.setCellValueFactory(new PropertyValueFactory<>("qtsEstoque"));

		TableColumn<ModelTabelaLivro, String> colIdioma = new TableColumn<>("Idioma");
		colIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));

		TableColumn<ModelTabelaLivro, String> colDescricao = new TableColumn<>("Descricao");
		colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		colDescricao.setPrefWidth(200);

		tbvPesqLivro.getColumns().addAll(colPreco, colTitulo, colAutor, colISBN, colGenero, colEdicao, colAno,
				colEstoque, colIdioma, colDescricao);

	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub
	}

}