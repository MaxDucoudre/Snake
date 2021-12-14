
public class Snake {
		public static void main(String[] args) {

			Window view = new Window();
			view.setVisible(true);

			Model model = new Model();
			view.setGridView(model.getGrid());


			Controller controller = new Controller(model, view);

			Runnable thread = new ThreadSnake(model, view);

			
			thread.start();

	}
}