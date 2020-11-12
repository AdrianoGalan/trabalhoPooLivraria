package boundary;

import control.ControleTelas;
import control.GetenciadorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TelaPesquisaCliente implements ControleTelas, EventHandler<ActionEvent>  {

	@Override
	public void handle(ActionEvent e) {
		
		
	}

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
		TextField tfPesquisa = new TextField();
		Button btPesquisar = new Button("Pesquisar");
		TableView<String> tbvPesqCliente = new TableView<String>();
		TableColumn<String, String> coluna = new TableColumn<String, String>("Cliente");
		tbvPesqCliente.getColumns().add(coluna);
		tbvPesqCliente.setPrefWidth(600);
		
		
		hbBotao.getChildren().add(lblPesquisa);
		lblPesquisa.setPadding(new Insets(5, 0, 0, 0));
		tfPesquisa.setPrefWidth(250);
		hbBotao.getChildren().add(tfPesquisa);
		hbBotao.getChildren().add(btPesquisar);
		
		
	
		
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