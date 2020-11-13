package boundary;

import control.ControleTelas;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import control.ControlePesquisaCliente;
import control.GetenciadorPrincipal;
import entity.Cliente;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TelaPesquisaCliente implements ControleTelas, EventHandler<ActionEvent>  {

	private ControlePesquisaCliente controle = new ControlePesquisaCliente();
	private Button btPesquisar;
	private TableView<Cliente> tbvPesqCliente;
	private TextField tfPesquisa;
	
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
		btPesquisar.addEventHandler(ActionEvent.ACTION,this);
		tbvPesqCliente = new TableView<Cliente>();
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
	public void handle(ActionEvent e) {
		if (e.getTarget() == btPesquisar) {
			controle.procurarClientes(tfPesquisa.getText());
			carregarTabela();
		}
	}

	private void carregarTabela() {
		
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
		TableColumn<Cliente, Integer> colId = new TableColumn<>("id_Cliente");
		colId.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("idCliente"));
		
		TableColumn<Cliente, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nome"));
		
		TableColumn<Cliente, String> colEmail = new TableColumn<>("Email");
		colEmail.setCellValueFactory(new PropertyValueFactory<Cliente,String>("email"));
		
		TableColumn<Cliente, String> colCpf = new TableColumn<>("CPF");
		colCpf.setCellValueFactory(new PropertyValueFactory<Cliente,String>("cpf"));
		
		TableColumn<Cliente, String> colDataNasc = new TableColumn<>("Data Nascimento");
		colDataNasc.setCellValueFactory( 
				(item) -> {
					return new ReadOnlyStringWrapper(
							fmt.format(item.getValue().getDataNascimento().getTime())
							);
				}
		);
		TableColumn<Cliente, String> colDataCadast = new TableColumn<>("Data Cadastro");
		colDataCadast.setCellValueFactory( 
				(item) -> {
					return new ReadOnlyStringWrapper(
							
							fmt.format(item.getValue().getDataCadastro().getTime())
							);
				}
		);
		
		tbvPesqCliente.getColumns().addAll(colId,colNome,colEmail,colCpf,colDataNasc,colDataCadast);
		tbvPesqCliente.setItems(controle.getLista());
	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub
		
	}
	
	

}