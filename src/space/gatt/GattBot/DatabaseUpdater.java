package space.gatt.GattBot;

import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.MessageBuilder;

import java.io.IOException;
import java.io.InputStream;
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

	private synchronized void closeStream(final InputStream stream){
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					stream.close();
				}catch (IOException e){

				}
			}
		}, 5, 5, TimeUnit.SECONDS);
	}

	public void update(){
		String url = "http://gasaibot.gatt.space/?updatedata=true&pw=";
		User bot = Main.getApi().getYourself();
		MessageBuilder builder = new MessageBuilder();
		builder.append("`Attempting to update Database`").appendNewLine().append("Attempting to create the following URL: `" + url + "<password>" + "&profile=" + bot.getAvatarUrl() + "&servers=" + Main.getApi().getServers().size() + "&users=" + Main.userCache.keySet().size() + "&game=Couldnt_space_get_space_game!&name=" + bot.getName().replaceAll(" ", "_space_") + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		try {
			URL u = new URL(url + Main.getPassword() + "&profile=" + bot.getAvatarUrl() + "&servers=" + Main.getApi().getServers().size() + "&users=" + Main.getApi().getUsers().size() + "&game=Couldnt_space_get_space_game!&name=" + bot.getName().replaceAll(" ", "_space_"));
			builder = new MessageBuilder();
			builder.append("Using the following URL `" + u + "`");
			Main.adminLogChannel.sendMessage(builder.build());
			closeStream(u.openStream());
		}catch (IOException e){
			builder = new MessageBuilder();
			builder.append("(OH NO! SOMETHING HAPPENED. PRINTING STACK TRACE!)").appendNewLine().append("```" + e.getMessage() + "```");
			Main.adminLogChannel.sendMessage(builder.build());
		}
	}
}