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

@Command("ranktest")
@Syntax("ranktest")
@Usage("ranktest")
@Permissions(ranks = {"Bot Commander", "This is a fake rank", "Seriously, if you make this rank; you're crazy."})
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = false)
public class RankTest {
	@IMethod
	public static String command(DiscordAPI api, Message message, User user, String[] args) {
		MessageBuilder builder = new MessageBuilder();
		builder.append(Settings.getMsgStarter()).appendUser(user).append(" you have the appropriate permission for the command! :smile:");
		return builder.build();
	}

}
