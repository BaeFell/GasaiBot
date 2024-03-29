package space.gatt.GattBot.commands.Admin;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.entities.message.MessageDecoration;
import space.gatt.GattBot.Settings;
import space.gatt.GattBot.utils.*;

/**
 * Created by Zach G on 06-Apr-16.
 */

@Command("log")
@Syntax("log")
@Usage("log")
@Permissions()
@Group("Admin")
@Description("Returns the invite link to Gasai Bots' Log Channel")
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = true)
public class Log {
	@IMethod
	public static String command(DiscordAPI api, Message message, User user, String[] args) {
		MessageBuilder builder = builder = new MessageBuilder();
		builder.append(Settings.getMsgStarter()).appendDecoration(MessageDecoration.BOLD, "https://discord.gg/0rtPRWdswScMri25").appendNewLine().append("There you go, for whatever reason you wanted it.");

		return builder.build();
	}

}
