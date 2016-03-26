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
		Main.adminLogChannel.sendMessage("```Starting Console Logger as of " + new Date().getTime() + "```");
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