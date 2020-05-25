package lab5;

import java.io.IOException;

public class lab5 {

	public static void main(String[] args) {
		   try {
	            Car window = new Car();

	            window.setVisible(true);
	        } catch (IOException ex) {
	            System.out.println(ex.getMessage());
	        }

	}

}
