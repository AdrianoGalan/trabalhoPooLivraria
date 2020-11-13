package boundary;

import control.ControlePesquisaLivro;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import entity.Livro;
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

public class TelaPesquisaLivro implements ControleTelas, EventHandler<ActionEvent>  {

	private ControlePesquisaLivro controle = new ControlePesquisaLivro();
	private TableView<Livro> tbvPesqLivro;
	private Button btPesquisar;
	private TextField tfPesquisa;
	private ComboBox<String> cbOpcPesq;

	@Override
	public Pane render() {
		Pane painel = new Pane();
		
		HBox hbCombo = new HBox();
		hbCombo.setSpacing(10);
		
		Label lblPesqCombo = new Label("Selecione pesquisar por:");
		lblPesqCombo.setPadding(new Insets(5, 0, 0, 0));
		cbOpcPesq = new ComboBox<String>();
		cbOpcPesq.getItems().addAll("Titulo","ISBN","Autor");
		cbOpcPesq.setPrefWidth(80);
		//Seleciona qual indice vai ficar selecionado, pode dar erro dps (vai saber)
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
		btPesquisar.addEventHandler(ActionEvent.ACTION,this);
		tbvPesqLivro = new TableView<Livro>();
		tbvPesqLivro.setPrefWidth(700);
		
		
		hbBotao.getChildren().add(lblPesquisa);
		lblPesquisa.setPadding(new Insets(5, 0, 0, 0));
		tfPesquisa.setPrefWidth(250);
		hbBotao.getChildren().add(tfPesquisa);
		hbBotao.getChildren().add(btPesquisar);
		
		
	
		vbEs.getChildren().add(hbCombo);
		vbEs.getChildren().add(hbBotao);
		vbEs.getChildren().add(tbvPesqLivro);
		
		painel.getChildren().add(vbEs);


	
		return painel;
	}
	
	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btPesquisar) {
			int indice = cbOpcPesq.getSelectionModel().getSelectedIndex();
			if(indice >= 0) {
				controle.procurarLivro(tfPesquisa.getText(), indice);
				carregarTela();
			}
			
			
		}
		
	}

	private void carregarTela() {
		TableColumn<Livro,Integer> colId = new TableColumn<>("Id Livro");
		colId.setCellValueFactory(new PropertyValueFactory<>("idLivro"));
		
		TableColumn<Livro,String> colTitulo = new TableColumn<>("Titulo");
		colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		
		TableColumn<Livro,String> colISBN = new TableColumn<>("ISBN");
		colISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		
		TableColumn<Livro,String> colGenero = new TableColumn<>("Genero");
		colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		
		TableColumn<Livro,String> colEdicao = new TableColumn<>("Edicao");
		colEdicao.setCellValueFactory(new PropertyValueFactory<>("edicao"));
		
		TableColumn<Livro,String> colAno = new TableColumn<>("Ano");
		colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
		
		TableColumn<Livro,Integer> colEstoque = new TableColumn<>("Qnt estoque");
		colEstoque.setCellValueFactory(new PropertyValueFactory<>("qtsEstoque"));
		
		TableColumn<Livro,String> colIdioma = new TableColumn<>("Idioma");
		colIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
		
		TableColumn<Livro,String> colDescricao = new TableColumn<>("Descricao");
		colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		tbvPesqLivro.getColumns().addAll(colId, colTitulo, colISBN, colGenero, colEdicao, colAno, colEstoque, colIdioma,
				colDescricao);
		tbvPesqLivro.setItems(controle.getLista());
		

	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub
		
	}

}