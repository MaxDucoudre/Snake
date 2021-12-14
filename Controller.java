import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Controller implements KeyListener {

	private Window view;
	private Model model;

	public Controller(Window view, Model model) {
		this.view = view;
		this.model = model;
	}


	public void keyPressed(KeyEvent e){
	    int key = e.getKeyCode();
	    System.out.println("Direction changed!");
	    if (key == KeyEvent.VK_LEFT) {
	        this.model.setDirection(Movement.LEFT);
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	        this.model.setDirection(Movement.RIGHT);
	    }

	    if (key == KeyEvent.VK_UP) {
	        this.model.setDirection(Movement.UP);
	    }

	    if (key == KeyEvent.VK_DOWN) {
	        this.model.setDirection(Movement.DOWN);
	    
		}
	}

	public void keyReleased(KeyEvent e)  {
	}

	public void keyTyped(KeyEvent e){

	}

}