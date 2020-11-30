package boundary;

import java.io.InputStream;

import control.ControleTelas;
import control.GetenciadorPrincipal;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


public class TelaInicial implements ControleTelas, EventHandler<ActionEvent> {

	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pane render() {
		Pane painel = new Pane();

		Label lblGrupo = new Label("Integrantes do Grupo: ");
		lblGrupo.relocate(750, 15);
		lblGrupo.setFont(new Font("Chilanka", 20));
		
		Label lblAdriano = new Label("Adriano Galan RA: ....");
		lblAdriano.relocate(750, 75);
		lblAdriano.setFont(new Font("Chilanka", 15));

		Label lblGustavo = new Label("Gustavo Narciso RA: ...");
		lblGustavo.relocate(750, 125);
		lblGustavo.setFont(new Font("Chilanka", 15));

		Label lblRoberto = new Label("Roberto Mitsunari RA: ...");
		lblRoberto.relocate(750, 175);
		lblRoberto.setFont(new Font("Chilanka", 15));


		painel.getChildren().addAll(lblGrupo, lblAdriano, lblGustavo, lblRoberto);

		
		InputStream is = getClass().getResourceAsStream("/images/Dino.jpg");
		Image imgSpace = new Image(is);
		

		Canvas canvas = new Canvas(imgSpace.getWidth(), imgSpace.getHeight());
		GraphicsContext ctx = canvas.getGraphicsContext2D();
		
	
		
		new AnimationTimer() {

			@Override
			public void handle(long time) {
				
				ctx.drawImage(imgSpace, 0, 0);
				
			
		}}.start();
		
		
		painel.getChildren().add(canvas); 
		return painel;
		

	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

}
