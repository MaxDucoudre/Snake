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
	    	if(this.model.getDirection() != Movement.RIGHT || this.model.getSnakeSize() == 1) {
	        	this.model.setDirection(Movement.LEFT);
	    	}
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	    	if(this.model.getDirection() != Movement.LEFT || this.model.getSnakeSize() == 1) {
	        	this.model.setDirection(Movement.RIGHT);
	    	}
	    }

	    if (key == KeyEvent.VK_UP) {
	    	if(this.model.getDirection() != Movement.DOWN || this.model.getSnakeSize() == 1) {
	        	this.model.setDirection(Movement.UP);
	        }
	    }

	    if (key == KeyEvent.VK_DOWN) {
	    	if(this.model.getDirection() != Movement.UP || this.model.getSnakeSize() == 1) {
	     	   this.model.setDirection(Movement.DOWN);
	    	}
	    
		}
	}

	public void keyReleased(KeyEvent e)  {
	}

	public void keyTyped(KeyEvent e){

	}

}