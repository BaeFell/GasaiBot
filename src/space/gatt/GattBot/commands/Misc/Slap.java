package space.gatt.GattBot.commands.Misc;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import space.gatt.GattBot.Settings;
import space.gatt.GattBot.utils.*;

/**
 * Created by Zach G on 06-Apr-16.
 */

@Command("slap")
@Syntax("slap")
@Usage("slap")
@Permissions()
@Group("Fun")
@Description("Slap that fool, fool!")
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = false)
public class Slap {
	@IMethod
	public static String command(DiscordAPI api, Message message, User user, String[] args) {
		MessageBuilder builder;
		if (message.getMentions().get(0) != null) {
			builder = new MessageBuilder();
			User u = message.getMentions().get(0);
			builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" slapped you, ").appendUser(u);
		} else {
			builder = new MessageBuilder();
			builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Please supply an argument!");
		}
		return builder.build();
	}

}
