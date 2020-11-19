package boundary;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cadastro_Livro extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		HBox painel = new HBox();

		Scene cena = new Scene(painel, 600, 400);

		VBox vbEs = new VBox();
		vbEs.setPadding(new Insets(15, 12, 15, 12));
		vbEs.setSpacing(15);

		VBox vbDi = new VBox();
		vbDi.setPadding(new Insets(15, 12, 15, 12));
		vbDi.setSpacing(6.2);

		HBox hbBotao = new HBox();
		hbBotao.setSpacing(40);

		Button btOk = new Button("Cadastrar");
		Button btCancelar = new Button("Cancelar");

		hbBotao.getChildren().add(btOk);
		hbBotao.getChildren().add(btCancelar);

		vbEs.getChildren().add(new Label("T�tulo:"));
		vbEs.getChildren().add(new Label("ISBN:"));
		vbEs.getChildren().add(new Label("Autor:"));
		vbEs.getChildren().add(new Label("Edi��o:"));
		vbEs.getChildren().add(new Label("Ano:"));
		vbEs.getChildren().add(new Label("G�nero:"));
		vbEs.getChildren().add(new Label("Descri��o:"));
		vbEs.getChildren().add(new Label("Quantidade de livros no estoque:"));
		vbEs.getChildren().add(new Label("Idioma:"));
		vbEs.getChildren().add(new Label("Pre�o:"));

		vbEs.getChildren().add(hbBotao);

		TextField tfTitulo = new TextField();
		tfTitulo.setPrefWidth(330);
		TextField tfIsbn = new TextField();
		TextField tfAutor = new TextField();
		TextField tfEdicao = new TextField();
		TextField tfAno = new TextField();
		TextField tfGenero = new TextField();
		TextField tfDescricao = new TextField();
		TextField tfQtd = new TextField();
		TextField tfIdioma = new TextField();
		TextField tfPreco = new TextField();

		vbDi.getChildren().add(tfTitulo);
		vbDi.getChildren().add(tfIsbn);
		vbDi.getChildren().add(tfAutor);
		vbDi.getChildren().add(tfEdicao);
		vbDi.getChildren().add(tfAno);
		vbDi.getChildren().add(tfGenero);
		vbDi.getChildren().add(tfDescricao);
		vbDi.getChildren().add(tfQtd);
		vbDi.getChildren().add(tfIdioma);
		vbDi.getChildren().add(tfPreco);

		painel.getChildren().add(vbEs);
		painel.getChildren().add(vbDi);

		primaryStage.setTitle("Cadastro de Livros");
		primaryStage.setScene(cena);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(Cadastro_Livro.class, args);
	}

}
