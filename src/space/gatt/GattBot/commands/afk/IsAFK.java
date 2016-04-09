package space.gatt.GattBot.commands.afk;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import space.gatt.GattBot.Main;
import space.gatt.GattBot.Settings;
import space.gatt.GattBot.utils.*;

/**
 * Created by Zach G on 07-Apr-16.
 */

@Command("isafk")
@Syntax("isafk @user")
@Usage("isafk @user")
@Permissions()
@Group("Moderation")
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = false)
public class IsAFK {
	@IMethod
	public static String command(DiscordAPI api, Message message, User user, String[] args) {
		MessageBuilder builder = new MessageBuilder();
		User u = message.getMentions().get(0);
		if (Main.afk.contains(u)) {
			builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" > " + u.getName() + " is AFK!");
		} else {
			builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" > " + u.getName() + " is not AFK!");
		}
		return builder.build();
	}

}