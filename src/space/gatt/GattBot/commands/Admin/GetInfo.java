package space.gatt.GattBot.commands.Admin;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.entities.message.MessageDecoration;
import de.btobastian.javacord.entities.permissions.Role;
import space.gatt.GattBot.Main;
import space.gatt.GattBot.Settings;
import space.gatt.GattBot.utils.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Zach G on 06-Apr-16.
 */

@Command("getinfo")
@Syntax("getinfo @user")
@Usage("getinfo @user")
@Permissions()
@Group("Admin")
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = true)
public class GetInfo {
	@IMethod
	public static String command(DiscordAPI discordAPI, Message message, User user, String[] args) {
		MessageBuilder builder = new MessageBuilder();
		User u = message.getMentions().get(0);
		builder.append(Settings.getMsgStarter()).appendDecoration(MessageDecoration.BOLD, " Info for `" + u.getName() +"`").appendNewLine()
				.append(Settings.getMsgStarter() + "Discord ID: `" + u.getId() + "`").appendNewLine()
				.append(Settings.getMsgStarter() + "Discord Discriminator: `" + u.getDiscriminator() + "`").appendNewLine()
				.append(Settings.getMsgStarter() + "Roles for this server: ").appendNewLine();

		for (Role r : u.getRoles(message.getChannelReceiver().getServer())) {
			builder.appendDecoration(MessageDecoration.BOLD, Settings.getMsgStarter() + "Rank Name: `" + r.getName() + "`").appendNewLine();
			builder.append(Settings.getMsgStarter() + " " + Settings.getMsgStarter() + " Rank ID: `" + r.getId() + "`").appendNewLine();
			builder.append(Settings.getMsgStarter() + " " + Settings.getMsgStarter() + " Rank Color: `" + r.getColor() + "`").appendNewLine();
			builder.append(Settings.getMsgStarter() + " " + Settings.getMsgStarter() + " Rank User Amount: `" + r.getUsers().size() + "`").appendNewLine().appendNewLine();
		}
		builder.append(Settings.getMsgStarter() + "Avatar URL: " + u.getAvatarUrl() + "").appendNewLine()
				.append(Settings.getMsgStarter() + "Current Game: `" + u.getGame() + "`").appendNewLine();
		message.getAuthor().sendMessage(builder.build());
		builder = new MessageBuilder();
		builder.append(Settings.getMsgStarter() + "I've PM'd you that user's information, ").appendUser(message.getAuthor());
		return builder.build();
	}

}
