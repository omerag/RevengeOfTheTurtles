package Driver;

import Controller.Controller;
import Model.Model;
import Model.Game;
import View.View;
import View.Window;

public class MVCDriver {

	/*
	public static void main(String[] args) {
		Model model = new MyModel();
		View view = new MyView();
		Controller controller = new MyController(model, view);
		((MyModel)model).addObserver(controller);
		((MyView)view).addObserver(controller);
		view.start();
	}
*/

	public static void main(String[] args) {

		/*
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainController mainController = new MainController();
				mainController.setVisible(true);
			}
		});
		*/
		int width = 1024 + 6;
		int height = 768 + 18;

		new Window(width,height,"Revenge of the Turtles!");
	}
}
