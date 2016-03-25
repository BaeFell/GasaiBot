package space.gatt.GattBot;

import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.MessageBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Zach G on 25-Mar-16.
 */
public class DatabaseUpdater implements Runnable {



	@Override
	public void run() {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				update();
			}
		}, 10, 10, TimeUnit.MINUTES);
	}

	public void update(){

		String url = "http://gattbot.gatt.space/?updatedata=true&pw=";
		User bot = Main.getApi().getYourself();
		try {
			URL u = new URL(url + Main.getPassword() + "&profile=" + bot.getAvatarUrl() + "&servers=" + Main.getApi().getServers().size() + "&users=" + Main.getApi().getUsers().size() + "&game=" + bot.getGame().replaceAll(" ", "_space_") + "&name=" + bot.getName().replaceAll(" ", "_space_"));
			u.openStream().close();
		}catch (Exception e){
			MessageBuilder builder = new MessageBuilder();
			builder.append("(OH NO! SOMETHING HAPPENED. PRINTING STACK TRACE!)").appendNewLine().append("```" + e.getMessage() + "```");
			Main.adminLogChannel.sendMessage(builder.build());
		}
	}
}
