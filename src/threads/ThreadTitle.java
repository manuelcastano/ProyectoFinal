package threads;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ThreadTitle extends Thread {
	
	private Label title;

	public ThreadTitle(Label title) {
		this.title = title;
	}
	
	public void run() {
		title.setFont(new Font(40));
		while (true) {
			try {
				title.setTextFill(Color.GREEN);
				Thread.sleep(500);
				title.setTextFill(Color.RED);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
