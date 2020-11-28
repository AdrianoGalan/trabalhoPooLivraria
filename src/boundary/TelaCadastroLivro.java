package boundary;

import java.sql.SQLException;

import DAO.LivroDao;
import control.ControleAutor;
import control.ControleLivro;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import entity.Autor;
import entity.Livro;
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
import util.Mensagens;

public class TelaCadastroLivro implements ControleTelas, EventHandler<ActionEvent> {

	private Button btOk;
	private Button btCancelar;
	private TextField tfTitulo; 
	private TextField tfIsbn;                                                                  
	private TextField tfAutor;                                                                   
	private TextField tfEdicao;                                                                    
	private TextField tfAno;                                                                   
	private ComboBox<String> cbGenero;                                                                    
	private TextField tfDescricao;                                                                   
	private TextField tfQtd;                                                                  
	private ComboBox<String> cbIdioma;                                                                    
	private TextField tfPreco;                                                                  
	
	
	

	@Override
	public void handle(ActionEvent e) {

		if (e.getTarget() == btOk && verificaCampos()) {
			
			if(verificaDuplicata()) {
				
				addLivro();
				
			}
			
		}
		if (e.getTarget() == btCancelar) {
			
		}

	}

	private void addLivro() {
		// TODO Auto-generated method stub
		
		ControleLivro cl = new ControleLivro();
	
		
		Livro l = new Livro();
		Autor a = new Autor();
		
		l.setAno(tfAno.getText());
		l.setDescricao(tfDescricao.getText());
		l.setEdicao(tfEdicao.getText());
		l.setIsbn(tfIsbn.getText());
		l.setTitulo(tfTitulo.getText());
		l.setQtsEstoque(Integer.parseInt(tfQtd.getText()));
	//	l.setPreco(Double.parseDouble(tfPreco.getText()));
		
		
		l.setGenero(cbGenero.getSelectionModel().getSelectedItem());
		l.setIdioma(cbIdioma.getSelectionModel().getSelectedItem());
		
		a.setNome(tfAutor.getText());
		
		try {
			cl.addLivro(l, a);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		
	}
	
	private boolean verificaDuplicata() {
		try {
			LivroDao lDao = new LivroDao();
			if(lDao.verificaDuplicIsbn(tfIsbn.getText())) {
				Mensagens.erro("ISBN ERRO", "ISBN inválido", "ISBN inválido ou já cadastrado");
				return false;
			}else {
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

		} else if (tfEdicao.getText().equals("") || tfEdicao.getText().length() > 3) {

			Mensagens.erro("EDICAO ERRO", "Edicao invalida", "Digite uma Edicao");

			return false;

		} else if (tfDescricao.getText().equals("")) {

			Mensagens.erro("DESCRICAO ERRO", "Descricao invalida", "Digite uma Descricao");

			return false;

		} else if (tfIsbn.getText().equals("") || tfIsbn.getText().length() > 13 || (!tfIsbn.getText().matches("\\d+"))) {

			Mensagens.erro("ISBN ERRO", "Isbn invalido", "Digite um Isbn: 13 Digitos");

			return false;

		} else if (tfAno.getText().equals("") || (!tfAno.getText().matches("\\d+")) || tfAno.getText().length() != 4) {

			Mensagens.erro("ANO ERRO", "Ano invalido", "Digite um Ano");

			return false;

		} else if (tfQtd.getText().equals("") || (!tfQtd.getText().matches("\\d+"))) {

			Mensagens.erro("ESTOQUE ERRO", "Estoque invalido", "Digite uma Quantidade");

			return false;

		} else if (tfPreco.getText().equals("") || (!tfPreco.getText().matches("^\\$?\\d+(\\.(\\d{2}))?$"))) {

			Mensagens.erro("PRECO ERRO", "Preco invalido", "Digite um valor");

			return false;

		} else if (tfAutor.getText().equals("") || (!tfAutor.getText().matches("^[a-zA-Z]+(([\\'\\,\\.\\- ][a-zA-Z ])?[a-zA-Z]*)*$")) || tfAutor.getText().length() > 150) {

			Mensagens.erro("AUTOR ERRO", "Nome invalido", "Digite um Nome");

			return false;

		}

		
		return true;
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

		TextField tfTitulo = new TextField();
		tfTitulo.setPrefWidth(330);
		tfIsbn = new TextField();
		tfAutor = new TextField();
		tfEdicao = new TextField();
		tfAno = new TextField();
		tfDescricao = new TextField();
		tfQtd = new TextField();
		tfPreco = new TextField();
		
		cbGenero = new ComboBox<String>();
		cbGenero.getItems().addAll("Terror", "Aventura", "Romance", "Suspense","Ficcao");
		cbGenero.setPrefWidth(80);
		cbGenero.getSelectionModel().select(0);
		
		cbIdioma = new ComboBox<String>();
		cbIdioma.getItems().addAll("Portugues", "Ingles", "Espanhol", "Frances");
		cbIdioma.setPrefWidth(80);
		cbIdioma.getSelectionModel().select(0);

		vbDi.getChildren().add(tfTitulo);
		vbDi.getChildren().add(tfIsbn);
		vbDi.getChildren().add(tfAutor);
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

}