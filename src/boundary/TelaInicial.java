package boundary;

import java.io.InputStream;

import control.ControleTelas;
import control.GetenciadorPrincipal;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class TelaInicial implements ControleTelas, EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pane render() {
		Pane painel = new Pane();

		Label lblUser = new Label("Integrantes do Grupo: ");
		lblUser.relocate(750, 15);
		
		Label lblAdriano = new Label("Adriano Galan RA: ....");
		lblAdriano.relocate(750, 75);

		Label lblGustavo = new Label("Gustavo Narciso RA: ...");
		lblGustavo.relocate(750, 125);

		Label lblRoberto = new Label("Roberto Mitsunari RA: ...");
		lblRoberto.relocate(750, 175);


		painel.getChildren().addAll(lblUser, lblAdriano, lblGustavo, lblRoberto);

		
		InputStream is = getClass().getResourceAsStream("/images/Dino.jpg");
		Image imgSpace = new Image(is);
		

		Canvas canvas = new Canvas(imgSpace.getWidth(), imgSpace.getHeight());
		GraphicsContext ctx = canvas.getGraphicsContext2D();
		
		new AnimationTimer() {

			@Override
			public void handle(long time) {
				
				ctx.drawImage(imgSpace, 0, 0);
				
			} 
			
		}.start();
		
		
		painel.getChildren().add(canvas); 
		return painel;
		

	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

}
