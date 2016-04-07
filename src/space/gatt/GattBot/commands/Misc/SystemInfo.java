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

@Command("systeminfo")
@Aliases("sysinfo")
@Syntax("systeminfo")
@Usage("systeminfo")
@Permissions(adminOnly = true)
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = true)
public class SystemInfo{
	@IMethod
	public static String command(DiscordAPI api, Message message, User user, String[] args) {
		MessageBuilder builder = new MessageBuilder();
		String osname = System.getProperty("os.name");
		String osarch = System.getProperty("os.arch");
		String osversion = System.getProperty("os.version");
		String java_version = System.getProperty("java.version");

		builder.append(Settings.getMsgStarter() + " Below is my System Information" + "```xml").appendNewLine().append("<System OS> " + osname).appendNewLine()
				.append("<OS Arch> " + osarch).appendNewLine()
				.append("<OS Version> " + osversion).appendNewLine()
				.append("<Java Version> " + java_version).appendNewLine()
				.append("```");
		return builder.build();
	}

}
