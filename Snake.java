
public class Snake {
		public static void main(String[] args) {

			Window view = new Window();
			view.setVisible(true);

			Model model = new Model();
			view.setGridView(model.getGrid());

			Controller controller = new Controller(view, model);
			view.addKeyListener(controller);

		
			Runnable runnable = new ThreadSnake(view, model);
	        Thread thread = new Thread(runnable);
	        thread.start();
	}
}