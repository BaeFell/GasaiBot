package space.gatt.GattBot.commands.Misc;

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

@Command("ping")
@Syntax("ping")
@Usage("ping")
@Permissions()
@Group("Fun")
@Description("Pong!")
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = false)
public class Ping {
	@IMethod
	public static String command(DiscordAPI api, Message message, User user, String[] args) {
		MessageBuilder builder = new MessageBuilder();
		builder.append(Settings.getMsgStarter() + "Pong! ").appendUser(message.getAuthor());
		return builder.build();
	}

}
