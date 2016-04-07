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
@Command("ranktest")
@Syntax("ranktest")
@Usage("ranktest")
@Permissions(ranks = {"Bot Commander", "This is a fake rank", "Seriously, if you make this rank; you're crazy."})
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = false)
public class Help {

	@IMethod
	public static String command(DiscordAPI api, Message message, User user, String[] args) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("```xml");
		for (String command : Main.commands) {
			builder.append(Settings.getMsgStarter() + command).appendNewLine();
		}
		builder.append("```");
		user.sendMessage(builder.build());

		builder = new MessageBuilder();
		builder.append(Settings.getMsgStarter() + "I've PM'd you my command list, ").appendUser(message.getAuthor());
		message.reply(builder.build());

		return builder.build();
	}

}


