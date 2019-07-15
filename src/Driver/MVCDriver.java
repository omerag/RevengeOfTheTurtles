package Driver;

import View.Window;

public class MVCDriver {

	public static void main(String[] args) {

		int width = 1024 + 6;
		int height = 768  -8 - 32;

		new Window(width,height,"Revenge of the Turtles!");
	}
}
