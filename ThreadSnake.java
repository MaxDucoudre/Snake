import java.lang.*;
public class ThreadSnake implements Runnable {

	private Window view;
	private Model model;

	public ThreadSnake(Window view, Model model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void run() {
		System.out.println("Game Start!");

		while(this.model.checkCollision() == false) {
			try {
				Thread.sleep(250);
			} catch(InterruptedException e) {
				System.out.println(e);
			}
			this.model.move();
			this.view.setGridView(this.model.getGrid());

		} 

		System.out.println("Game Over!");

		
	}
}