
public class Snake {
		public static void main(String[] args) {

			int gridWidth = 25;
			int gridHeight = 25;
			int bonusNumber = 3;

			Window view = new Window(gridWidth,gridHeight);
			view.setVisible(true);

			Model model = new Model(gridWidth,gridHeight,bonusNumber);

			view.setGridView(model.getGrid());

			Controller controller = new Controller(view, model);
			view.addKeyListener(controller);

		
			Runnable runnable = new ThreadSnake(view, model);
	        Thread thread = new Thread(runnable);
	        thread.start();
	}
}