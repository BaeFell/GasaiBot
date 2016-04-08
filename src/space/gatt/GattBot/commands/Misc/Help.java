package space.gatt.GattBot.commands.Misc;

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
@Command("help")
@Syntax("help")
@Usage("help")
@Permissions()
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = true)
public class Help {

	@IMethod
	public static String command(DiscordAPI api, Message message, User user, String[] args) {
		MessageBuilder builder = new MessageBuilder();

		builder.append(Settings.getMsgStarter() + "I've PM'd you my command list, ").appendUser(message.getAuthor());
		message.reply(builder.build());
		builder = new MessageBuilder();
		builder.append("```xml").appendNewLine();
		for (String command : Main.commands) {
			builder.append(Settings.getMsgStarter() + command).appendNewLine();
		}
		builder.append("```");
		return builder.build();
	}

}


