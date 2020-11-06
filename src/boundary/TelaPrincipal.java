package boundary;

import control.GetenciadorPrincipal;
import entity.Usuario;
import control.ControleTelas;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TelaPrincipal extends Application implements GetenciadorPrincipal, EventHandler<ActionEvent> {


	
	// painel
	private BorderPane painelLogin = new BorderPane();
	private BorderPane painelPrincipal = new BorderPane();

	// cena
	private Scene login = new Scene(painelLogin, 350, 140);
	private Scene principal = new Scene(painelPrincipal, 800, 600);

	private Stage stage = new Stage();

	// telas
	private ControleTelas telaLogin = new TelaLogi();

	// itensa do menu
	private MenuItem menuCadastrarCliente = new MenuItem("Cliente");
	private MenuItem menuCadastrarLivro = new MenuItem("Livro");
	private MenuItem menuCadastrarAutor = new MenuItem("Autor");
	private MenuItem menuPesquuisarCliente = new MenuItem("Cliente");
	private MenuItem menuPesquuisarLivro = new MenuItem("Livro");
	private MenuItem menuControleEstoque = new MenuItem("Estoque");
	private MenuItem menuControleRelatorio = new MenuItem("Relatorio");
	private MenuItem menuVendaLivro = new MenuItem("Livro");
	

	private ControleTelas telaAtual = telaLogin;

	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;

		telaLogin.setGerenciadorPrincipal(this);

		painelLogin.setCenter(telaAtual.render());

		stage.setScene(login);
		stage.setTitle("Login Livraria");
		stage.show();

	}

	public static void main(String[] args) {
		Application.launch(TelaPrincipal.class, args);
	}

	private void setTelaPrincipal() {

		MenuBar menuPrincipal = new MenuBar();

		Menu menuCadastrar = new Menu("Cadastrar");
		menuCadastrar.getItems().addAll(menuCadastrarCliente, menuCadastrarLivro, menuCadastrarAutor);

		Menu menuPesquisar = new Menu("Pesquisar");
		menuPesquisar.getItems().addAll(menuPesquuisarCliente, menuPesquuisarLivro);

		Menu menuControle = new Menu("Controle");
		menuControle.getItems().addAll(menuControleEstoque, menuControleRelatorio);
		
		Menu menuVenda = new Menu("Venda");
		menuVenda.getItems().addAll(menuVendaLivro);

		menuPrincipal.getMenus().addAll(menuCadastrar, menuPesquisar, menuControle, menuVenda);
		
		menuCadastrarCliente.setOnAction(this);
		menuCadastrarLivro.setOnAction(this);
		menuCadastrarAutor.setOnAction(this);
		menuPesquuisarCliente.setOnAction(this);
		menuPesquuisarLivro.setOnAction(this);
		menuControleEstoque.setOnAction(this);
		menuControleRelatorio.setOnAction(this);
		menuVendaLivro.setOnAction(this);
		
		painelPrincipal.setTop(menuPrincipal);
		

		this.stage.setMaximized(true);
		this.stage.setScene(principal);
		this.stage.setTitle("Sistema Livraria");

		this.stage.show();

	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == menuControleRelatorio) {
			comando("sair");
		}
		System.out.println(e.getTarget());

	}

	@Override
	public void comando(String cmd) {

		if (cmd.equals("foi")) {
			setTelaPrincipal();
		}else if (cmd.equals("sair")) {
			System.exit(0);
		}

	}

	@Override
	public void idFuncionari(int idFuncionario) {
	 
		 
		
	}

	

	

	
}
