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

@Command("setafk")
@Syntax("setafk [true/false]")
@Usage("setafk [true/false]")
@Permissions()
@Group("Moderation")
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = false)
public class SetAFK {
	@IMethod
	public static String command(DiscordAPI api, Message message, User user, String[] args) {
		MessageBuilder builder = new MessageBuilder();
		boolean afk = false;
		afk = Boolean.parseBoolean(args[0]);
		if (!afk) {
			Main.afk.remove(message.getAuthor());
			builder = new MessageBuilder();
			builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" is no longer AFK.");
			message.reply(builder.build());
		} else if (afk) {
			if (!Main.afk.contains(message.getAuthor())) {
				Main.afk.add(message.getAuthor());
				builder = new MessageBuilder();
				builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" is now AFK.");
				message.reply(builder.build());
			}
		}
		return builder.build();
	}

}