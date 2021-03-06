package boundary;

import control.ControleTelas;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import control.ControleCliente;
import control.GetenciadorPrincipal;
import entity.Cliente;
import javafx.beans.property.ReadOnlyStringWrapper;
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

/**
 * Classe tela pesquisa de cliente e implementa a interface ControleTelas do package control
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class TelaPesquisaCliente implements ControleTelas, EventHandler<ActionEvent>  {

	/** Propriedade controle */
	private ControleCliente controle = new ControleCliente();
	
	/** Propriedade btPesquisar */
	private Button btPesquisar;
	
	/** Propriedade btAlterar */
	private Button btAlterar;
	
	/** Propriedade tbvPesqCliente */
	private TableView<Cliente> tbvPesqCliente;
	
	/** Propriedade tfPesquisa */
	private TextField tfPesquisa;
	
	/** Propriedade tela */
	private BorderPane tela;
	
	/** Propriedade stage */
	public Stage stage;
	
	/** Propriedade cena */
	private Scene cena;
	
	/** Propriedade telaCliente */
	private TelaCadastroCliente telaCliente;

	/**
	 * Painel render
	 */
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
		tbvPesqCliente = new TableView<Cliente>();
		tbvPesqCliente.setPrefWidth(800);
		
		
		hbBotao.getChildren().add(lblPesquisa);
		lblPesquisa.setPadding(new Insets(5, 0, 0, 0));
		tfPesquisa.setPrefWidth(250);
		hbBotao.getChildren().add(tfPesquisa);
		hbBotao.getChildren().add(btPesquisar);
		hbBotao.getChildren().add(btAlterar);
		vbEs.getChildren().add(hbBotao);
		vbEs.getChildren().add(tbvPesqCliente);
		
		
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
		TableColumn<Cliente, Integer> colId = new TableColumn<>("id_Cliente");
		colId.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("idCliente"));
		
		TableColumn<Cliente, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nome"));
		colNome.setPrefWidth(170);
		
		TableColumn<Cliente, String> colEmail = new TableColumn<>("Email");
		colEmail.setCellValueFactory(new PropertyValueFactory<Cliente,String>("email"));
		colEmail.setPrefWidth(200);
		
		TableColumn<Cliente, String> colCpf = new TableColumn<>("CPF");
		colCpf.setCellValueFactory(new PropertyValueFactory<Cliente,String>("cpf"));
		colCpf.setPrefWidth(150);
		
		TableColumn<Cliente, String> colDataNasc = new TableColumn<>("Data Nascimento");
		colDataNasc.setCellValueFactory( 
				(item) -> {
					return new ReadOnlyStringWrapper(
							fmt.format(item.getValue().getDataNascimento().getTime())
							);
				}
		);
		colDataNasc.setPrefWidth(100);
		TableColumn<Cliente, String> colDataCadast = new TableColumn<>("Data Cadastro");
		colDataCadast.setCellValueFactory( 
				(item) -> {
					return new ReadOnlyStringWrapper(
							
							fmt.format(item.getValue().getDataCadastro().getTime())
							);
				}
		);
		colDataCadast.setPrefWidth(100);
		
		tbvPesqCliente.getColumns().addAll(colId,colNome,colEmail,colCpf,colDataNasc,colDataCadast);
		
		painel.getChildren().add(vbEs);
		
		tfPesquisa.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
			@Override
			public void handle(javafx.scene.input.KeyEvent event) {

					carregarTabela();
		
			}
		});


		

	
		return painel;
	}
	
	/**
	 * Metodo handle - acao aos botoes - pesquisar e alterar cliente.
	 */
	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btPesquisar) {
			carregarTabela();
		}else if(e.getTarget() == btAlterar) {
			if(tbvPesqCliente.getSelectionModel().getSelectedIndex() != -1) {
				Cliente c = tbvPesqCliente.getSelectionModel().getSelectedItem();
				telaCliente = new TelaCadastroCliente(c,this);
				abrirTelaClient();
			}else {
				Mensagens.informacao("Selecione um indice", "Selecione algum cliente antes de continuar", "");
			}
			
		}
	}

	/**
	 * Metodo que carrega tabela com os clientes
	 */
	public void carregarTabela() {
		try {
			controle.buscaClientesNome(tfPesquisa.getText());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tbvPesqCliente.setItems(controle.getLista());
	}
	
	/**
	 * Metodo que abre tela cliente para alteracao
	 */
	private void abrirTelaClient() {
		tela = new BorderPane();
		cena = new Scene(tela, 680, 570);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(cena);
		tela.setCenter(telaCliente.render());
		stage.show();
	}

	/**
	 * Gerenciador Principal
	 */
	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub
		
	}
	
	

}