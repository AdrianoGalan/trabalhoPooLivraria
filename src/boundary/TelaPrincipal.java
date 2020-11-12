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

public class TelaPrincipal extends Application implements GetenciadorPrincipal, EventHandler<ActionEvent> {

	Funcionario fLogado;

	// painel
	private BorderPane painelLogin = new BorderPane();
	private BorderPane painelPrincipal = new BorderPane();

	// cena
	private Scene login = new Scene(painelLogin, 350, 140);
	private Scene principal = new Scene(painelPrincipal, 1000, 600);

	private Stage stage = new Stage();

	// telas
	private ControleTelas telaLogin = new TelaLogi();
	private ControleTelas telaInicial = new TelaInicial();
	private ControleTelas telaCadastroCliente = new TelaCadastroCliente();
	private ControleTelas telaCadastroLivro = new TelaCadastroLivro();
	private ControleTelas telaCadastroAutor = new TelaCadastroAutor();
	private ControleTelas telaPesquisaCliente = new TelaPesquisaCliente();
	private ControleTelas telaPesquisaLivro = new TelaPesquisaLivro();
	private ControleTelas telaControleRelatorio = new TelaControleRelatorio();
	private ControleTelas telaControleEstoque = new TelaControleEstoque();
	private ControleTelas telaVendaLivro = new TelaVendaLivro();
	private ControleTelas telaControleUsuario = new TelaControleUsuario();

	// itens do menu
	private MenuItem menuInicioTelaInicio = new MenuItem("Tela Inicial");
	private MenuItem menuInicioSair = new MenuItem("Sair");

	private MenuItem menuCadastrarCliente = new MenuItem("Cliente");
	private MenuItem menuCadastrarLivro = new MenuItem("Livro");
	private MenuItem menuCadastrarAutor = new MenuItem("Autor");

	private MenuItem menuPesquuisarCliente = new MenuItem("Cliente");
	private MenuItem menuPesquuisarLivro = new MenuItem("Livro");

	private MenuItem menuControleEstoque = new MenuItem("Estoque");
	private MenuItem menuControleRelatorio = new MenuItem("Relatorio");

	private MenuItem menuVendaLivro = new MenuItem("Livro");

	private MenuItem menuControleUsuario = new MenuItem("Controle de usu�rios");


	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;

		menuInicioTelaInicio.setOnAction(this);
		menuInicioSair.setOnAction(this);
		menuCadastrarCliente.setOnAction(this);
		menuCadastrarLivro.setOnAction(this);
		menuCadastrarAutor.setOnAction(this);
		menuPesquuisarCliente.setOnAction(this);
		menuPesquuisarLivro.setOnAction(this);
		menuControleEstoque.setOnAction(this);
		menuControleRelatorio.setOnAction(this);
		menuVendaLivro.setOnAction(this);
		menuControleUsuario.setOnAction(this);

		telaLogin.setGerenciadorPrincipal(this);
		telaInicial.setGerenciadorPrincipal(this);

		telaCadastroCliente.setGerenciadorPrincipal(this);
		telaCadastroLivro.setGerenciadorPrincipal(this);
		telaCadastroAutor.setGerenciadorPrincipal(this);

		telaPesquisaCliente.setGerenciadorPrincipal(this);
		telaPesquisaLivro.setGerenciadorPrincipal(this);
		
		telaControleUsuario.setGerenciadorPrincipal(this);
		telaControleEstoque.setGerenciadorPrincipal(this);
		telaControleRelatorio.setGerenciadorPrincipal(this);

		telaVendaLivro.setGerenciadorPrincipal(this);

		painelLogin.setCenter(telaLogin.render());

		stage.setScene(login);
		stage.setTitle("Login Livraria");
		stage.show();

	}

	public static void main(String[] args) {
		Application.launch(TelaPrincipal.class, args);
	}

	private void setTelaPrincipal() {

		MenuBar menuPrincipal = new MenuBar();

		Menu menuInicio = new Menu("Inicio");
		menuInicio.getItems().addAll(menuInicioTelaInicio, menuInicioSair);

		Menu menuCadastrar = new Menu("Cadastrar");
		menuCadastrar.getItems().addAll(menuCadastrarCliente, menuCadastrarLivro, menuCadastrarAutor);

		Menu menuPesquisar = new Menu("Pesquisar");
		menuPesquisar.getItems().addAll(menuPesquuisarCliente, menuPesquuisarLivro);

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
		painelPrincipal.setLeft(telaCadastroAutor.render());

	

		//this.stage.setMaximized(true);
		this.stage.setScene(principal);
		this.stage.setResizable(false);
		this.stage.setX(50);
		this.stage.setY(50);
		this.stage.setTitle("Sistema Livraria");

		this.stage.show();

	}

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

	@Override
	public void handle(ActionEvent e) {

		if (e.getTarget() == menuCadastrarCliente) {
			this.painelPrincipal.setCenter(telaCadastroCliente.render());
		} else if (e.getTarget() == menuCadastrarLivro) {
			this.painelPrincipal.setCenter(telaCadastroLivro.render());
		} else if (e.getTarget() == menuCadastrarAutor) {
			this.painelPrincipal.setCenter(telaCadastroAutor.render());
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
		} else if (e.getTarget() == menuInicioSair) {
			comando("sair");
		}

	}

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

	@Override
	public void id(int idFuncionario) {

		logarFuncionario(idFuncionario);

	}

}
