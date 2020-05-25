package lab1;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.*;

public class lab1 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		Scene scene = new Scene(root, 800, 475);
		
		Polygon basisShip = new Polygon(110, 250, 350, 330, 550, 330, 700, 260, 575, 430, 330, 430, 110, 250);
		root.getChildren().add(basisShip);
		basisShip.setFill(Color.rgb(128, 64, 0));
		
		Rectangle mastShip = new Rectangle(410, 200, 13, 130);
		root.getChildren().add(mastShip);
		mastShip.setFill(Color.rgb(0, 0, 0));
		
		Polygon sailShip = new Polygon(300, 130, 350, 30, 540, 30, 500, 150, 540, 240, 340, 230, 300, 130);
		root.getChildren().add(sailShip);
		sailShip.setFill(Color.rgb(192, 192, 192));
	
		scene.setFill(Color.rgb(255, 255, 128));
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
