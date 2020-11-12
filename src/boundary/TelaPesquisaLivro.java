package boundary;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TelaPesquisaLivro implements ControleTelas, EventHandler<ActionEvent>  {

	@Override
	public void handle(ActionEvent e) {
		
		
	}

	@Override
	public Pane render() {
		Pane painel = new Pane();
		
		HBox hbCombo = new HBox();
		hbCombo.setSpacing(10);
		
		Label lblPesqCombo = new Label("Selecione pesquisar por:");
		lblPesqCombo.setPadding(new Insets(5, 0, 0, 0));
		ComboBox<String> cbOpcPesq = new ComboBox<String>();
		cbOpcPesq.getItems().addAll("Nome","ISBN","Autor");
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
		TextField tfPesquisa = new TextField();
		Button btPesquisar = new Button("Pesquisar");
		TableView<String> tbvPesqCliente = new TableView<String>();
		TableColumn<String, String> coluna = new TableColumn<String, String>("Livro");
		tbvPesqCliente.getColumns().add(coluna);
		tbvPesqCliente.setPrefWidth(600);
		
		
		hbBotao.getChildren().add(lblPesquisa);
		lblPesquisa.setPadding(new Insets(5, 0, 0, 0));
		tfPesquisa.setPrefWidth(250);
		hbBotao.getChildren().add(tfPesquisa);
		hbBotao.getChildren().add(btPesquisar);
		
		
	
		vbEs.getChildren().add(hbCombo);
		vbEs.getChildren().add(hbBotao);
		vbEs.getChildren().add(tbvPesqCliente);
		
		painel.getChildren().add(vbEs);


		

	
		return painel;
	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub
		
	}

}