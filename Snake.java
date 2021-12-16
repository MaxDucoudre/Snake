
public class Snake {
		public static void main(String[] args) {

			// Configuration de la partie
			boolean enable_bot = false;
			int gridWidth = 10;
			int gridHeight = 10	;
			int bonusNumber = 5;
			long int snakeSpeed = 100;
			//

			Window view = new Window(gridWidth,gridHeight);
			view.setVisible(true);

			Model model = new Model(gridWidth,gridHeight,bonusNumber);

			view.setGridView(model.getGrid());
			view.setHightScore(model.getHightScore());

			if(enable_bot == false) {
				Controller controller = new Controller(view, model);
				view.addKeyListener(controller);


			} else if (enable_bot == true) {

				Runnable bot = new Bot(model);
		        Thread botThread = new Thread(bot);
				botThread.start();

			}

			Runnable runnable = new ThreadSnake(view, model);
		    Thread thread = new Thread(runnable);
		    thread.start();



		

	}
}