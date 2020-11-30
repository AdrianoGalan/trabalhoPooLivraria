package boundary;

import java.sql.SQLException;

import control.ControleAutor;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import entity.Autor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Mensagens;

public class TelaPesquisaAutor implements ControleTelas, EventHandler<ActionEvent> {
	
	private ControleAutor control = new ControleAutor();
	private Button btPesquisar;
	private Button btAlterar;
	private TableView<Autor> tbvPesqAutor;
	private TextField tfPesquisa;
	private BorderPane tela;
	public Stage stage;
	private Scene cena;
	private TelaCadastroAutor telaAutor;

	@Override
	public Pane render() {
Pane painel = new Pane();
		
		HBox hbBotao = new HBox();
		hbBotao.setSpacing(20);
		
		VBox vbEs = new VBox();
		// new Insets(y, ?, ?, x)
		vbEs.setPadding(new Insets(25, 0, 15, 100));
		vbEs.setSpacing(15);
		
		Label lblPesquisa = new Label("Digite:");
		tfPesquisa = new TextField();
		btPesquisar = new Button("Pesquisar");
		btAlterar = new Button("Alterar");
		btPesquisar.addEventHandler(ActionEvent.ACTION,this);
		btAlterar.addEventHandler(ActionEvent.ACTION, this);
		tbvPesqAutor = new TableView<Autor>();
		tbvPesqAutor.setPrefWidth(800);
		
		
		hbBotao.getChildren().add(lblPesquisa);
		lblPesquisa.setPadding(new Insets(5, 0, 0, 0));
		tfPesquisa.setPrefWidth(250);
		hbBotao.getChildren().add(tfPesquisa);
		hbBotao.getChildren().add(btPesquisar);
		hbBotao.getChildren().add(btAlterar);
		vbEs.getChildren().add(hbBotao);
		vbEs.getChildren().add(tbvPesqAutor);
		
		TableColumn<Autor, Integer> colId = new TableColumn<>("id_Autor");
		colId.setCellValueFactory(new PropertyValueFactory<Autor,Integer>("idAutor"));
		
		TableColumn<Autor, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<Autor,String>("nome"));
		colNome.setPrefWidth(170);
		
		TableColumn<Autor, String> colNascinalidade = new TableColumn<>("Nascionalidade");
		colNascinalidade.setCellValueFactory(new PropertyValueFactory<Autor,String>("nacionalidade"));
		colNascinalidade.setPrefWidth(200);
		
		tbvPesqAutor.getColumns().addAll(colId,colNome,colNascinalidade);
		
		painel.getChildren().add(vbEs);
		
		tfPesquisa.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
			@Override
			public void handle(javafx.scene.input.KeyEvent event) {

					carregarTabela();
			}
		});


	
		return painel;
		
		
	}
	
	private void abrirTelaClient() {
		tela = new BorderPane();
		cena = new Scene(tela, 680, 130);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(cena);
		tela.setCenter(telaAutor.render());
		stage.show();
	}
	
	public void carregarTabela(){
		try {
			tbvPesqAutor.setItems(control.buscaClienteNome(tfPesquisa.getText()));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == btPesquisar) {
			carregarTabela();
		}else if(event.getSource() == btAlterar) {
			if(tbvPesqAutor.getSelectionModel().getSelectedIndex() != -1){
				Autor a = tbvPesqAutor.getSelectionModel().getSelectedItem();
				telaAutor = new TelaCadastroAutor(a,this);
				abrirTelaClient();
			}else {
				Mensagens.informacao("Selecione um indice", "Selecione algum autor antes de continuar", "");
			}

		}
		
	}
	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub
		
	}

}
