package space.gatt.GattBot.commands.Admin;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.entities.message.MessageDecoration;
import space.gatt.GattBot.Main;
import space.gatt.GattBot.Settings;
import space.gatt.GattBot.utils.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Zach G on 06-Apr-16.
 */

@Command("botinfo")
@Syntax("botinfo")
@Usage("botinfo")
@Permissions()
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = false)
public class BotInfo {
	@IMethod
	public static String command(DiscordAPI discordAPI, Message message, User user, String[] args) {
		MessageBuilder builder = new MessageBuilder();

		builder.append(Settings.getMsgStarter() + " My current name: `" + discordAPI.getYourself().getName() + "`").appendNewLine();
		builder.append(Settings.getMsgStarter() + " My current game: `" + discordAPI.getGame() + "`").appendNewLine();
		builder.append(Settings.getMsgStarter() + " My current profile picture: `" + discordAPI.getYourself().getAvatarUrl()+"`").appendNewLine();
		builder.append(Settings.getMsgStarter() + " Admin Rank Name: `Bot Commander`").appendNewLine();
		builder.append(Settings.getMsgStarter() + " Servers joined: `" + discordAPI.getServers().size()+"`").appendNewLine();

		builder.append( Settings.getMsgStarter() + " Users in Cache: `" + Main.userCache.keySet().size() + "`").appendNewLine();
		message.getAuthor().sendMessage(builder.build());
		builder = new MessageBuilder();
		builder.append(Settings.getMsgStarter() + "I've PM'd you my information, ").appendUser(message.getAuthor());
		return builder.build();
	}

}
