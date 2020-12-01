package boundary;

import java.sql.SQLException;
import control.ControleAutor;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import entity.Autor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.Mensagens;

/**
 * Classe tela que cadastra autor e implementa a interface ControleTelas do package control
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class TelaCadastroAutor implements ControleTelas, EventHandler<ActionEvent> {

	/** Propriedade btOK */
	private Button btOk;
	
	/** Propriedade btCancelar */
	private Button btCancelar;
	
	/** Propriedade btAlterar */
	private Button btAlterar;
	
	/** Propriedade tfNome */
	private TextField tfNome;
	
	/** Propriedade tfNa */
	private TextField tfNa;
	
	/** Propriedade Autor a */
	private Autor a = null;
	
	/** Propriedade TelaPesquisaAutor tela */
	private TelaPesquisaAutor tela;
	
	/** Propriedade ControleAutor control */
	private ControleAutor control = new ControleAutor();
	
	TelaCadastroAutor(){
	}
	
	/**
	 * TelaCadastroAutor
	 * 
	 * @param a
	 * @param telaA
	 */
	TelaCadastroAutor(Autor a,TelaPesquisaAutor telaA){
		this.a = a;
		tela = telaA;
	}

	/**
	 * Metodo handle - acao aos botoes - adicionar e alterar autor
	 */
	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btOk && verificaCampos()) {
			
			try {
				
				if(!control.verificaDuplicata(tfNome.getText())) {
					addAutor();
					limpaCampos();
					Mensagens.informacao("Autor cadastro", "Autor cadastrado com sucesso", "");
				}else {
					Mensagens.erro("Erro nome autor", "Por favor insira um nome diferente", "Nome de autor j� cadastrado");
				}
				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			

		}else if(e.getSource() == btAlterar) {
			DadosParaEntidades();
			try {
				control.alterarAutor(a);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			Mensagens.informacao("Autor cadastro", "Autor alterado com sucesso", "");
			tela.carregarTabela();
			tela.stage.close();
			
		}
	}

	/**
	 * Metodo que adiciona dados do autor.
	 */
	private void addAutor() {

		ControleAutor aa = new ControleAutor();

		Autor a = new Autor();

		a.setNome(tfNome.getText());
		a.setNacionalidade(tfNa.getText());
		

		try {
			aa.addAutor(a);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Metodo que verifica se os campos foram preenchidos corretamente.
	 * 
	 * @return true or false
	 */
	private boolean verificaCampos() {
		// TODO Auto-generated method stub

		if (tfNome.getText().equals("")) {

			Mensagens.erro("Nome erro", "Nome invalido", "Digite um nome");

			return false;

		} else if (tfNa.getText().equals("")) {

			Mensagens.erro("Nacionalidade erro", "Nacionalidade invalida", "Digite uma Nacionalidade");

			return false;

		}else {
			return true;
		}

		
	}

	/**
	 * Painel Render
	 */
	@Override
	public Pane render() {

		HBox painel = new HBox();

		VBox vbEs = new VBox();
		vbEs.setPadding(new Insets(15, 12, 15, 12));
		vbEs.setSpacing(15);

		VBox vbDi = new VBox();
		vbDi.setPadding(new Insets(15, 12, 15, 12));
		vbDi.setSpacing(5);

		HBox hbBotao = new HBox();
		hbBotao.setSpacing(40);

		



		vbEs.getChildren().add(new Label("Nome:"));
		vbEs.getChildren().add(new Label("Nacionalidade:"));
		
		tfNome = new TextField();
		tfNome.setPrefWidth(330);
		tfNa = new TextField();

		vbDi.getChildren().add(tfNome);
		vbDi.getChildren().add(tfNa);
		
		
		if(a == null) {
			btOk = new Button("Cadastrar");
			btOk.addEventHandler(ActionEvent.ACTION, this);
			hbBotao.getChildren().add(btOk);
		}else {
			btAlterar = new Button("Alterar");
			btAlterar.addEventHandler(ActionEvent.ACTION, this);
			hbBotao.getChildren().add(btAlterar);
			carregaDadosCampos();
		}
		

		btCancelar = new Button("Cancelar");
		btCancelar.addEventHandler(ActionEvent.ACTION, this);
		
		
		hbBotao.getChildren().add(btCancelar);
		vbEs.getChildren().add(hbBotao);

		painel.getChildren().add(vbEs);
		painel.getChildren().add(vbDi);

		return painel;
	}
	
	/**
	 * Metodo CarregaDadosCampos
	 */
	private void carregaDadosCampos(){
		tfNome.setText(a.getNome());
		tfNa.setText(a.getNacionalidade());
	}
	
	/**
	 * Metodo DadosParaEntidades
	 */
	private void DadosParaEntidades() {
		a.setNome(tfNome.getText());
		a.setNacionalidade(tfNa.getText());
	}

	/**
	 * Metodo limpaCampos
	 */
	private void limpaCampos() {
		tfNome.setText("");
		tfNa.setText("");
	}
	
	/**
	 * Gerenciador Principal
	 */
	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

}
