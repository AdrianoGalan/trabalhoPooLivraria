package boundary;

import java.sql.SQLException;

import control.ControleLivro;
import control.ControlePreco;
import entity.Preco;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tabelaModel.ModelTabelaLivro;
import util.Mensagens;

public class TelaAlteraPrecoLivro implements EventHandler<ActionEvent> {

	private BorderPane tela;
	private Pane painel;
	private Stage stage;
	private Scene cena;
	private TextField txtPreco;
	private Button btnAlterar;
	private TableView<ModelTabelaLivro> tbvLivro;
	private ControleLivro controle;
	private ModelTabelaLivro livro;
	private TelaPesquisaLivro telaMae;
	
	private Preco p;

	

	TelaAlteraPrecoLivro(TableView<ModelTabelaLivro> tbvPesqLivro, ControleLivro controle, TelaPesquisaLivro telaM) {

		telaMae = telaM;
		this.controle = controle;
		tela = new BorderPane();
		cena = new Scene(tela, 300, 100);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(cena);
		tbvLivro = tbvPesqLivro;
	}

	public void render(ModelTabelaLivro livro) {
		painel = new Pane();
		this.livro = livro;
		VBox vbDi = new VBox();
		HBox hBox = new HBox();
		Label lbPreco = new Label("Novo preco: ");
		lbPreco.setPadding(new Insets(5, 0, 0, 0));
		hBox.getChildren().add(lbPreco);
		txtPreco = new TextField();
		hBox.getChildren().add(txtPreco);
		hBox.setSpacing(15);
		btnAlterar = new Button("Alterar");
		btnAlterar.setTranslateX(115);
		btnAlterar.setTranslateY(20);
		btnAlterar.addEventHandler(ActionEvent.ACTION, this);
		vbDi.getChildren().addAll(hBox, btnAlterar);
		vbDi.setPadding(new Insets(5, 0, 15, 5));
		vbDi.setSpacing(8);
		painel.getChildren().add(vbDi);
		tela.setCenter(painel);
		stage.setTitle("Alterar precos");
		stage.show();
	}

	private void criaPreco() {

		ControlePreco cp = new ControlePreco();
		p = new Preco();

		try {

			p.setValor(Double.parseDouble(txtPreco.getText()));

			if (p.getValor() >= 0) {
			
				p.setFkLivroPreco(livro.getIdLivro());
				p.setIdPreco( cp.addPreco(p));

			} else {
				Mensagens.erro("Erro Preço", "Preço invalido", "Digite um preço maior que 0");
			}

		} catch (Exception e) {

			Mensagens.erro("Erro Preço", "Preço invalido", "Digite um preço valido");
		}

	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == btnAlterar) {

			try {

				criaPreco();

				if (p.getFkLivroPreco() != 0) {

					controle.alteraPrecoLivro(p);
				} else {
					Mensagens.erro("Erro", "Erro alterar", "Erro a alterar preço");
				}
				
				telaMae.carregarTabela();
				stage.close();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
