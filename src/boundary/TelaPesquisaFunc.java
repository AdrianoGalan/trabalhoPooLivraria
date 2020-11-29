package boundary;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import control.ControleFuncionario;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import entity.Funcionario;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
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

public class TelaPesquisaFunc implements ControleTelas, EventHandler<ActionEvent>{
	private Button btPesquisar;
	private TextField tfPesquisa;
	private TableView<Funcionario> tbvFuncionario;
	private ControleFuncionario control = new ControleFuncionario();
	private ObservableList<Funcionario> lista;
	private Button btAlterar;
	private TelaCadastroFuncionario telaFunci;
	private BorderPane tela;
	private Pane painel;
	public Stage stage;
	private Scene cena;

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
		tbvFuncionario = new TableView<Funcionario>();
		tbvFuncionario.setPrefWidth(800);
		
		hbBotao.getChildren().add(lblPesquisa);
		lblPesquisa.setPadding(new Insets(5, 0, 0, 0));
		tfPesquisa.setPrefWidth(250);
		hbBotao.getChildren().add(tfPesquisa);
		hbBotao.getChildren().addAll(btPesquisar,btAlterar);
		vbEs.getChildren().add(hbBotao);
		vbEs.getChildren().add(tbvFuncionario);
		//id, nome, cpf,num matricula ,data_admi
		
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
		TableColumn<Funcionario, Integer> colId = new TableColumn<>("id_Funcionario");
		colId.setCellValueFactory(new PropertyValueFactory<Funcionario,Integer>("idFuncionario"));
		
		TableColumn<Funcionario, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("nome"));
		
		TableColumn<Funcionario, String> colCpf = new TableColumn<>("Cpf");
		colCpf.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("cpf"));
		
		TableColumn<Funcionario, String> colNumMatri = new TableColumn<>("num_matricula");
		colNumMatri.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("matricula"));
		
		TableColumn<Funcionario, String> colDataAdm = new TableColumn<>("Data Admicao");
		colDataAdm.setCellValueFactory( 
				(item) -> {
					return new ReadOnlyStringWrapper(
							fmt.format(item.getValue().getDataAdmissao().getTime())
							);
				}
		);
		
		tbvFuncionario.getColumns().addAll(colId,colNome,colCpf,colNumMatri,colDataAdm);
		
		tfPesquisa.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
			@Override
			public void handle(javafx.scene.input.KeyEvent event) {
				carregaTabela();
			}
		});
		
		painel.getChildren().add(vbEs);
		
		return painel;
		
		
	}
	
	public void carregaTabela() {
		try {
			lista = control.buscarFuncionariosNome(tfPesquisa.getText());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tbvFuncionario.setItems(lista);
	}
	
	private void abrirTelaFuncio() {
		tela = new BorderPane();
		cena = new Scene(tela, 680, 570);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(cena);
		tela.setCenter(telaFunci.render());
		stage.show();
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == btPesquisar) {
			carregaTabela();
		}else if(event.getSource() == btAlterar) {
			Funcionario f = tbvFuncionario.getSelectionModel().getSelectedItem();
			telaFunci = new TelaCadastroFuncionario(f,this);
			abrirTelaFuncio();
		}
		
	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub
		
	}

}
