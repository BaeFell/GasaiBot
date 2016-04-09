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

@Command("notlog")
@Syntax("notlog")
@Usage("notlog")
@Permissions()
@Group("Admin")
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = true)
public class Log {
	@IMethod
	public static String command(DiscordAPI api, Message message, User user, String[] args) {
		MessageBuilder builder = builder = new MessageBuilder();
		builder.append(Settings.getMsgStarter()).appendDecoration(MessageDecoration.BOLD, "https://discord.gg/0rtPRWdswScMri25").appendNewLine().append("There you go, for whatever reason you wanted it.");

		return builder.build();
	}

}
