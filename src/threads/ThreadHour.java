package threads;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ThreadHour extends Thread {
	
	private GraphicsContext hour;

	public ThreadHour(GraphicsContext hour) {
		this.hour = hour;
	}
	
	public void run() {
		while (true) {
			hour.setFont(new Font(35));
			hour.setFill(Color.DARKRED);
			try {
				Calendar fecha = new GregorianCalendar();
				int hora = fecha.get(Calendar.HOUR_OF_DAY);
				int minuto = fecha.get(Calendar.MINUTE);
				int segundo = fecha.get(Calendar.SECOND);
				String reloj = hora + ":" + minuto + ":" + segundo;
				hour.clearRect(0, 0, 200, 70);
				hour.fillText(reloj, 45, 45);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
