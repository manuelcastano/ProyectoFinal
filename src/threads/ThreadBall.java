package threads;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ThreadBall extends Thread{
	
	private GraphicsContext gc;
	

	public ThreadBall(GraphicsContext gc) {
		this.gc = gc;

	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}
	
	public void run() {
		Image i = new Image("file:///C:\\Users\\mafes\\Documents\\LaboratoriosAPO\\ProyectoFinal\\balon.png");
		String direction = "DOWN";
		int y = 0;
		while(true) {
			gc.clearRect(0, 0, 70, 440);
			gc.drawImage(i, 0, y);
			if(direction.equals("DOWN")) {
				y++;
				if(y == 370) {
					direction = "UP";
				}
			}
			else {
				y--;
				if(y == 0) {
					direction = "DOWN";
				}
			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
	}

	
}
