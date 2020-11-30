package boundary;

import control.GetenciadorPrincipal;
import entity.Funcionario;
import entity.Usuario;

import java.awt.Dimension;
import java.sql.SQLException;

import com.sun.javafx.tk.Toolkit;

import DAO.FuncionarioDao;
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

/**
 * Classe Tela Principal que gerencia as outras telas. 
 * 
 * @author Adriano, Gustavo e Roberto.
 *
 */

public class TelaPrincipal extends Application implements GetenciadorPrincipal, EventHandler<ActionEvent> {

	Funcionario fLogado;

/**
 * Painel login e Principal.
 */
	private BorderPane painelLogin = new BorderPane();
	private BorderPane painelPrincipal = new BorderPane();

/**
 * Cena Login, principal e suas dimensões.
 */
	private Scene login = new Scene(painelLogin, 350, 140);
	private Scene principal = new Scene(painelPrincipal, 1000, 600);

	private Stage stage = new Stage();

/**
 * Telas do sistema.
 */
	private ControleTelas telaLogin = new TelaLogi();
	private ControleTelas telaInicial = new TelaInicial();
	private ControleTelas telaCadastroCliente = new TelaCadastroCliente();
	private ControleTelas telaCadastroLivro = new TelaCadastroLivro();
	private ControleTelas telaCadastroAutor = new TelaCadastroAutor();
	private ControleTelas telaCadastroFuncionario = new TelaCadastroFuncionario();
	private ControleTelas telaPesquisaCliente = new TelaPesquisaCliente();
	private ControleTelas telaPesquisaLivro = new TelaPesquisaLivro();
	private ControleTelas telaControleRelatorio = new TelaControleRelatorio();
	private ControleTelas telaControleEstoque = new TelaControleEstoque();
	private ControleTelas telaVendaLivro = new TelaVendaLivro();
	private ControleTelas telaControleUsuario = new TelaControleUsuario(stage);
	private ControleTelas telaPesquisaFuncionario = new TelaPesquisaFunc();

/**
 * Itens do menu.
 */
	private MenuItem menuInicioTelaInicio = new MenuItem("Tela Inicial");
	private MenuItem menuInicioSair = new MenuItem("Sair");

	private MenuItem menuCadastrarCliente = new MenuItem("Cliente");
	private MenuItem menuCadastrarFuncionario = new MenuItem("Funcionario");
	private MenuItem menuCadastrarLivro = new MenuItem("Livro");
	private MenuItem menuCadastrarAutor = new MenuItem("Autor");

	private MenuItem menuPesquuisarCliente = new MenuItem("Cliente");
	private MenuItem menuPesquuisarLivro = new MenuItem("Livro");

	private MenuItem menuControleEstoque = new MenuItem("Estoque");
	private MenuItem menuControleRelatorio = new MenuItem("Relatorio");

	private MenuItem menuVendaLivro = new MenuItem("Livro");

	private MenuItem menuControleUsuario = new MenuItem("Controle de usuarios");
	private MenuItem menuPesquisaFuncionario = new MenuItem("Funcionario");


	/**
	 * Método start que mostrará a tela
	 */
	
	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;

		menuInicioTelaInicio.setOnAction(this);
		menuInicioSair.setOnAction(this);
		menuCadastrarCliente.setOnAction(this);
		menuCadastrarFuncionario.setOnAction(this);
		menuCadastrarLivro.setOnAction(this);
		menuCadastrarAutor.setOnAction(this);
		menuPesquuisarCliente.setOnAction(this);
		menuPesquuisarLivro.setOnAction(this);
		menuControleEstoque.setOnAction(this);
		menuControleRelatorio.setOnAction(this);
		menuVendaLivro.setOnAction(this);
		menuControleUsuario.setOnAction(this);
		menuPesquisaFuncionario.setOnAction(this);

		telaLogin.setGerenciadorPrincipal(this);
		telaInicial.setGerenciadorPrincipal(this);

		telaCadastroCliente.setGerenciadorPrincipal(this);
		telaCadastroLivro.setGerenciadorPrincipal(this);
		telaCadastroFuncionario.setGerenciadorPrincipal(this);
		telaCadastroAutor.setGerenciadorPrincipal(this);
		telaPesquisaCliente.setGerenciadorPrincipal(this);
		telaPesquisaLivro.setGerenciadorPrincipal(this);
		telaPesquisaFuncionario.setGerenciadorPrincipal(this);
		telaControleUsuario.setGerenciadorPrincipal(this);
		telaControleEstoque.setGerenciadorPrincipal(this);
		telaControleRelatorio.setGerenciadorPrincipal(this);

		telaVendaLivro.setGerenciadorPrincipal(this);

		painelLogin.setCenter(telaLogin.render());

		stage.setScene(login);
		stage.setTitle("Login Livraria");
		stage.show();

	}
	
	/**
	 * Iniciando a aplicação
	 * @param args
	 */

	public static void main(String[] args) {
		Application.launch(TelaPrincipal.class, args);
	}

	/**
	 * MenuBar
	 */
	
	private void setTelaPrincipal() {

		MenuBar menuPrincipal = new MenuBar();

		Menu menuInicio = new Menu("Inicio");
		menuInicio.getItems().addAll(menuInicioTelaInicio, menuInicioSair);
		
		Menu menuCadastrar = new Menu("Cadastrar");
		
		if(fLogado.getCargo().equals("GERENTE")) {
			
		
			menuCadastrar.getItems().addAll(menuCadastrarCliente,menuCadastrarFuncionario, menuCadastrarLivro, menuCadastrarAutor);
			
		}else {
			
			menuCadastrar.getItems().addAll(menuCadastrarCliente, menuCadastrarLivro, menuCadastrarAutor);
		}

		Menu menuPesquisar = new Menu("Pesquisar");
		menuPesquisar.getItems().addAll(menuPesquuisarCliente, menuPesquuisarLivro,menuPesquisaFuncionario);

		Menu menuControle = new Menu("Controle");
		menuControle.getItems().addAll(menuControleEstoque, menuControleRelatorio);

		Menu menuVenda = new Menu("Venda");
		menuVenda.getItems().addAll(menuVendaLivro);

		

		if (fLogado.getCargo().equals("GERENTE")) {
			Menu menuUsuario = new Menu("Usuario");
			menuUsuario.getItems().addAll(menuControleUsuario);
			menuPrincipal.getMenus().addAll(menuInicio, menuCadastrar, menuPesquisar, menuControle, menuVenda, menuUsuario);

		} else {
			menuPrincipal.getMenus().addAll(menuInicio, menuCadastrar, menuPesquisar, menuControle, menuVenda);

		}
		
		

		
		painelPrincipal.setTop(menuPrincipal);
		painelPrincipal.setCenter(telaInicial.render());
//		painelPrincipal.setLeft(telaCadastroAutor.render());

	

		//this.stage.setMaximized(true);
		this.stage.setScene(principal);
		this.stage.setResizable(false);
		this.stage.setX(50);
		this.stage.setY(50);
		this.stage.setTitle("SISTEMA LIVRARIA");

		this.stage.show();

	}
	
	/**
	 * Método que loga um funcionário
	 * @param idFuncionario
	 */

	private void logarFuncionario(int idFuncionario) {

		try {
			FuncionarioDao fDao = new FuncionarioDao();
			fLogado = fDao.buscaFuncionarioId(idFuncionario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Método que aciona ação a tela Principal  
	 */
	
	@Override
	public void handle(ActionEvent e) {

		if (e.getTarget() == menuCadastrarCliente) {
			this.painelPrincipal.setCenter(telaCadastroCliente.render());
		} else if (e.getTarget() == menuCadastrarLivro) {
			this.painelPrincipal.setCenter(telaCadastroLivro.render());
		} else if (e.getTarget() == menuCadastrarFuncionario) {
			this.painelPrincipal.setCenter(telaCadastroFuncionario.render());
		} else if (e.getTarget() == menuPesquuisarCliente) {
			this.painelPrincipal.setCenter(telaPesquisaCliente.render());
		} else if (e.getTarget() == menuPesquuisarLivro) {
			this.painelPrincipal.setCenter(telaPesquisaLivro.render());
		} else if (e.getTarget() == menuControleEstoque) {
			this.painelPrincipal.setCenter(telaControleEstoque.render());
		} else if (e.getTarget() == menuControleRelatorio) {
			this.painelPrincipal.setCenter(telaControleRelatorio.render());
		} else if (e.getTarget() == menuVendaLivro) {
			this.painelPrincipal.setCenter(telaVendaLivro.render());
		} else if (e.getTarget() == menuInicioTelaInicio) {
			this.painelPrincipal.setCenter(telaInicial.render());
		} else if (e.getTarget() == menuControleUsuario) {
			this.painelPrincipal.setCenter(telaControleUsuario.render());
		} else if(e.getTarget() == menuPesquisaFuncionario) {
			this.painelPrincipal.setCenter(telaPesquisaFuncionario.render());
		} else if(e.getTarget() == menuCadastrarAutor) {
			this.painelPrincipal.setCenter(telaCadastroAutor.render());
		} else if (e.getTarget() == menuInicioSair) {
			comando("sair");
		}

	}
	
	/**
	 * Método que posiciona tela inicial no centro da cena 
	 * e aplica a função de sair da aplicação caso o usuário clique no menuInicioSair
	 */

	@Override
	public void comando(String cmd) {

		if (cmd.equals("telaInicial")) {
			this.painelPrincipal.setCenter(telaInicial.render());
			setTelaPrincipal();
		}

		if (cmd.equals("sair")) {
			System.exit(0);
		}

	}
	
	/**
	 * Método que faz a chamada do logarFuncionario
	 */

	@Override
	public void id(int idFuncionario) {

		logarFuncionario(idFuncionario);

	}

}
