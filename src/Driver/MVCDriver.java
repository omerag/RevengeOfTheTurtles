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

		new Window(1024 + 6,768 + 18,"Revenge of the Turtles!");
		//new Game();

	}
}
