
public class Bot implements Runnable {

	private Model model;

	public Bot(Model model) {
		this.model = model;
	}


	@Override
	public void run() {
		System.out.println("Bot started!");
		try {

			while(this.model.isOver() == false) {

				Thread.sleep(200-this.model.getScore());
				this.model.setDirection(this.selectMovement());
			} 


		} catch(InterruptedException e) {
			System.out.println(e);

		}
		System.out.println("End of Bot");

	}

	public Movement selectMovement() {

		return Movement.LEFT;
	}
}