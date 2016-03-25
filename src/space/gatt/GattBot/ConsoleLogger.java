package space.gatt.GattBot;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Zach G on 25-Mar-16.
 */
public class ConsoleLogger implements Runnable {

	@Override
	public void run() {
		long seconds =  new Date().getTime() / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 24;
		String time = days + " days, " + hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds";
		Main.adminLogChannel.sendMessage("```Starting Console Logger as of " + time + "```");
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(new Runnable() {
			String prevLine = "THIS IS THE STARTING LINE OR SOMETHING";
			@Override
			public void run() {
				String line = System.console().readLine();
				if (prevLine != line){
					Main.adminLogChannel.sendMessage("```" + line + "```");
					prevLine = line;
				}
			}
		}, 5, 5, TimeUnit.MILLISECONDS);
	}
}
