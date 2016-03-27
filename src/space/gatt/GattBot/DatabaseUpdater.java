package space.gatt.GattBot;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.ImplDiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.utils.ThreadPool;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.concurrent.ExecutionException;
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
		MessageBuilder builder = new MessageBuilder();
		try {
			User bot = Main.getApi().getYourself();
			String game = "Cannot get game";
			if (bot.getGame() != null) {
				game = bot.getGame();
			}
			builder.append("DEBUG: `Game:" + bot.getGame() + "`").appendNewLine().append("Attempting to update Database");
			Main.adminLogChannel.sendMessage(builder.build());
			String finalURL = url + Main.getPassword() + "&profile=" + bot.getAvatarUrl() + "&servers=" + Main.getApi().getServers().size() + "&users=" + Main.getApi().getUsers().size() + "&game=" + game.replaceAll(" ", "_space_") + "&name=" + bot.getName().replaceAll(" ", "_space_");
			URL u = new URL(finalURL);
			closeStream(u.openStream());
			builder = new MessageBuilder();
			builder.append("Using the following URL").appendNewLine().append(finalURL);
			Main.adminLogChannel.sendMessage(builder.build());

		}catch (IOException e) {
			builder = new MessageBuilder();
			builder.append("(OH NO! SOMETHING HAPPENED. PRINTING STACK TRACE!)").appendNewLine().append("```" + e.getMessage() + "```");
			Main.adminLogChannel.sendMessage(builder.build());
		}
	}
}
