package boundary;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import DAO.LivroDao;
import control.ControleAutor;
import control.ControleLivro;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import entity.Autor;
import entity.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.Mascaras;
import util.Mensagens;

public class TelaCadastroLivro implements ControleTelas, EventHandler<ActionEvent> {

	private Button btOk;
	private Button btCancelar;
	private TextField tfTitulo;
	private TextField tfIsbn;
	private TextField tfEdicao;
	private TextField tfAno;
	private TextField tfDescricao;
	private TextField tfQtd;
	private TextField tfPreco;
	private ObservableList<Autor> listaAutores;
	private ControleLivro control = new ControleLivro();
	private ComboBox<String> cbAutor;
	private ComboBox<String> cbIdioma;
	private ComboBox<String> cbGenero;

	@Override
	public void handle(ActionEvent e) {
		
		if (e.getTarget() == btOk && verificaCampos()) {
			if (verificaDuplicata()) {
				addLivro();
				limpaCampos();
			}

		}
		if (e.getTarget() == btCancelar) {
			System.out.println(listaAutores.get(cbAutor.getSelectionModel().getSelectedIndex()).getIdAutor());
		}

	}

	private void addLivro() {

		ControleLivro cl = new ControleLivro();

		Livro l = new Livro();
		Autor a;

		l.setAno(tfAno.getText());
		l.setDescricao(tfDescricao.getText());
		l.setEdicao(tfEdicao.getText());
		l.setIsbn(tfIsbn.getText()); // .replaceAll("[-]", ""));
		l.setTitulo(tfTitulo.getText());
		l.setQtsEstoque(Integer.parseInt(tfQtd.getText()));
		l.setPrecoAtual(Integer.parseInt(tfPreco.getText()));
		l.setGenero(cbGenero.getSelectionModel().getSelectedItem());
		l.setIdioma(cbIdioma.getSelectionModel().getSelectedItem());
		a = listaAutores.get(cbAutor.getSelectionModel().getSelectedIndex());


		try {
			cl.addLivro(l,a.getIdAutor());
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		Mensagens.informacao("Livro cadastrado", "O livro foi cadastrado com sucesso", "");
	}

	private boolean verificaDuplicata() {
		try {
			LivroDao lDao = new LivroDao();
			if (lDao.verificaDuplicIsbn(tfIsbn.getText())) {
				Mensagens.erro("ISBN ERRO", "ISBN inválido", "ISBN inválido ou já cadastrado");
				return false;
			} else {
				return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	private boolean verificaCampos() {
		// TODO Auto-generated method stub

		if (tfTitulo.getText().equals("") || tfTitulo.getText().length() > 200) {

			Mensagens.erro("TITULO ERRO", "Titulo invalido", "Digite um Titulo");

			return false;

		} else if (tfIsbn.getText().equals("") || tfIsbn.getText().length() > 13
				|| (!tfIsbn.getText().matches("\\d+"))) {

			Mensagens.erro("ISBN ERRO", "Isbn invalido", "Digite um Isbn: 13 Digitos");

			return false;

		} /*
			 * else if(cbAutor.getSelectionModel().isEmpty()){
			 * 
			 * Mensagens.erro("AUTOR ERRO", "Nome invalido", "Digite um Nome");
			 * 
			 * return false;
			 * 
			 * }
			 */ else if (tfEdicao.getText().equals("") || tfEdicao.getText().length() > 3) {

			Mensagens.erro("EDICAO ERRO", "Edicao invalida", "Digite uma Edicao");

			return false;

		} else if (tfAno.getText().equals("") || (!tfAno.getText().matches("\\d+")) || tfAno.getText().length() != 4 || !validaData()) {

			Mensagens.erro("ANO ERRO", "Ano invalido", "Digite um Ano");

			return false;
			
		} else if (tfDescricao.getText().equals("")){

			Mensagens.erro("DESCRICAO ERRO", "Descricao invalida", "Digite uma Descricao");

			return false;

		} else if (tfQtd.getText().equals("") || (!tfQtd.getText().matches("\\d+"))) {

			Mensagens.erro("ESTOQUE ERRO", "Estoque invalido", "Digite uma Quantidade");

			return false;

		} else if (tfPreco.getText().equals("") || (!tfPreco.getText().matches("^\\$?\\d+(\\.(\\d{2}))?$"))) {

			Mensagens.erro("PRECO ERRO", "Preco invalido", "Digite um valor");

			return false;

		} else {

			return true;
		}

	}
	
	private void limpaCampos() {
		tfTitulo.setText("");
		tfIsbn.setText("");
		tfEdicao.setText("");
		tfAno.setText("");
		tfDescricao.setText("");
		tfQtd.setText("");
		tfPreco.setText("");
	}

	private boolean validaData() {
		try {

			Calendar hoje = Calendar.getInstance();

			int anoAtual = hoje.get(Calendar.YEAR);

			int ano = Integer.parseInt(tfAno.getText());

			if (ano > anoAtual) {
				Mensagens.erro("ANO ERRO", "Ano invalido", "Digite um Ano válido");
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Mensagens.erro("ANO ERRO", "Ano invalido", "Digite um Ano válido");
			return false;
		}
	}

	@Override
	public Pane render() {

		HBox painel = new HBox();

		VBox vbEs = new VBox();
		vbEs.setPadding(new Insets(15, 12, 15, 12));
		vbEs.setSpacing(15);

		VBox vbDi = new VBox();
		vbDi.setPadding(new Insets(15, 12, 15, 12));
		vbDi.setSpacing(6.2);

		HBox hbBotao = new HBox();
		hbBotao.setSpacing(40);

		btOk = new Button("Cadastrar");
		btOk.addEventHandler(ActionEvent.ACTION, this);
		btCancelar = new Button("Cancelar");
		btCancelar.addEventHandler(ActionEvent.ACTION, this);

		hbBotao.getChildren().add(btOk);
		hbBotao.getChildren().add(btCancelar);

		vbEs.getChildren().add(new Label("Titulo:"));
		vbEs.getChildren().add(new Label("ISBN:"));
		vbEs.getChildren().add(new Label("Autor:"));
		vbEs.getChildren().add(new Label("Edicao:"));
		vbEs.getChildren().add(new Label("Ano:"));
		vbEs.getChildren().add(new Label("Genero:"));
		vbEs.getChildren().add(new Label("Descricao:"));
		vbEs.getChildren().add(new Label("Quantidade de livros no estoque:"));
		vbEs.getChildren().add(new Label("Idioma:"));
		vbEs.getChildren().add(new Label("Preco:"));

		vbEs.getChildren().add(hbBotao);

		tfTitulo = new TextField();
		tfTitulo.setPrefWidth(330);
		tfIsbn = new TextField();
//		Mascaras.mascaraIsbn(tfIsbn);
		tfEdicao = new TextField();
		tfAno = new TextField();
		tfDescricao = new TextField();
		tfQtd = new TextField();
		tfPreco = new TextField();

	
		cbAutor = new ComboBox<String>();
		try {
			listaAutores = control.listarAutores();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		cbAutor.setItems(criarListaStringAutores(listaAutores));
		 

		cbGenero = new ComboBox<String>();
		cbGenero.getItems().addAll("Terror", "Aventura", "Romance", "Suspense", "Ficcao");
		cbGenero.setPrefWidth(80);
		cbGenero.getSelectionModel().select(0);

		cbIdioma = new ComboBox<String>();
		cbIdioma.getItems().addAll("Portugues", "Ingles", "Espanhol", "Frances");
		cbIdioma.setPrefWidth(80);
		cbIdioma.getSelectionModel().select(0);

		vbDi.getChildren().add(tfTitulo);
		vbDi.getChildren().add(tfIsbn);
		vbDi.getChildren().add(cbAutor);
		vbDi.getChildren().add(tfEdicao);
		vbDi.getChildren().add(tfAno);
		vbDi.getChildren().add(cbGenero);
		vbDi.getChildren().add(tfDescricao);
		vbDi.getChildren().add(tfQtd);
		vbDi.getChildren().add(cbIdioma);
		vbDi.getChildren().add(tfPreco);

		painel.getChildren().add(vbEs);
		painel.getChildren().add(vbDi);

		return painel;
	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}
	
	//Cria uma ObservableList<String> a partir de uma lista de autores
	private ObservableList<String> criarListaStringAutores(ObservableList<Autor> autores){
		ObservableList<String> listaNomes = FXCollections.observableArrayList();
		for(Autor a:autores) {
			listaNomes.add(a.getNome());
		}
		
		return listaNomes;
	}

	/*
	 * else if (tfAutor.getText().equals("") || (!tfAutor.getText().
	 * matches("^[a-zA-Z]+(([\\'\\,\\.\\- ][a-zA-Z ])?[a-zA-Z]*)*$")) ||
	 * tfAutor.getText().length() > 150) {
	 * 
	 * Mensagens.erro("AUTOR ERRO", "Nome invalido", "Digite um Nome");
	 * 
	 * return false;
	 * 
	 * }
	 */

}