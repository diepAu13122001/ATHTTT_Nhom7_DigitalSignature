package timer;

import javax.servlet.ServletContext;

public class TimerExample implements Runnable {
	ServletContext servletContext;
	public TimerExample(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	@Override
	public void run() {
		System.out.println("Happy New Year");
		
	}

}
