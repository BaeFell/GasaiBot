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

@Command("version")
@Syntax("version")
@Usage("version")
@Permissions()
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = false)
public class Version {
	@IMethod
	public static String command(DiscordAPI api, Message message, User user, String[] args) {
		MessageBuilder builder = builder = new MessageBuilder();
		builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Gasai Bot Version " + Settings.getVersion());
		return builder.build();
	}

}
