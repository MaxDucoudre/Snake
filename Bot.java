
public class Bot implements Runnable {

	private Model model;
	private int iteration;

	public Bot(Model model) {
		this.model = model;
		this.iteration = 0;
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

		SnakePiece snakeHead = this.model.getSnakeHead();
		int gridWidth = model.getGridWidth();
		int gridHeight = model.getGridHeight();
		Movement direction = this.model.getDirection();
		iteration ++;

		if(iteration == 1) {
			return Movement.DOWN;
		}




		if(snakeHead.x == gridHeight-2 && direction == Movement.RIGHT) {
			return Movement.UP;
		}
		if(snakeHead.x == gridHeight-2 && direction == Movement.DOWN) {
			return Movement.RIGHT;
		}
		if(direction == Movement.UP && snakeHead.x != 0 && snakeHead.x != gridHeight-2) {
			return Movement.UP;
		}


		if(snakeHead.y == gridWidth-1 && snakeHead.x == gridHeight-2) {
			System.out.println("Bout");

			return Movement.DOWN;
		}
		if(snakeHead.x == gridHeight-1) {
			return Movement.LEFT;
		}
		if(snakeHead.x == gridHeight-1 && snakeHead.y == 0) {
			return Movement.UP;
		}
		if(snakeHead.x == 0 && direction == Movement.RIGHT) {
			return Movement.DOWN;
		}

		if(snakeHead.x == 0 && direction == Movement.UP) {
			return Movement.RIGHT;
		}
		if(direction == Movement.DOWN && snakeHead.x > 0 && snakeHead.x < gridHeight-2) {
			return Movement.DOWN;
		}





		return null;
	}		
}