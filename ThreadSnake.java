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

		while(this.model.isOver() == false) {
			try {

				Thread.sleep(250 - this.model.getScore());
			} catch(InterruptedException e) {
				System.out.println(e);
			}
			this.model.move();
			this.view.setScoreLabel(this.model.getScore());
			this.view.setGridView(this.model.getGrid());
		} 
		System.out.println("End of game!");
		JFrame gameOverFrame = new JFrame("Game Over");
		gameOverFrame.setLayout(new BorderLayout());
		gameOverFrame.add(new JLabel("Game Over"),BorderLayout.CENTER);
		JButton tryAgain = new JButton("Try again");
		gameOverFrame.add(tryAgain,BorderLayout.SOUTH);


		
	}
}