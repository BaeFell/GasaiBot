package space.gatt.GattBot;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Region;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import space.gatt.GattBot.utils.CommandSettings;
import space.gatt.GattBot.utils.Permissions;
import space.gatt.GattBot.utils.Register;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Zach G on 06-Apr-16.
 */
public class CommandListener implements MessageCreateListener {

	private boolean hasRole(User user, Server server, String roleName, boolean caseSensitive){
		if (caseSensitive) {
			for (Role r : user.getRoles(server)) {
				if (r.getName().equalsIgnoreCase(roleName)) {
					return true;
				}
			}
			return false;
		}else{
			return hasRole(user, server, roleName);
		}
	}

	private boolean hasRole(User user, Server server, String roleName){
		for (Role r : user.getRoles(server)){
			if (r.getName().equals(roleName)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {
		if (message.getContent().startsWith(Settings.getCommandStarter())) {
			String[] args = message.getContent().split(" ");
			args[0] = args[0].replaceFirst(Settings.getCommandStarter(), "");
			if (Register.getCommandList().contains(args[0])) {
				String msg = "Error. No response given by command.";
				Class<?> enclosingClass = Register.getCommandRegistrar().get(args[0]);
				String cmd = args[0];
				args[0] = "";
				if (enclosingClass != null) {

					boolean gattOnly = false;
					boolean deleteMsg = false;
					boolean sendPM = false;
					boolean requiresPM = false;

					for (Annotation a : enclosingClass.getAnnotations()){
						if (a instanceof Permissions){
							gattOnly = ((Permissions)a).gattOnly();
						}
						if (a instanceof CommandSettings){
							deleteMsg = ((CommandSettings)a).deleteInitatingMsg();
							sendPM = ((CommandSettings)a).sendResponseViaPM();
							requiresPM = ((CommandSettings)a).requiresPM();
						}
					}

					if (requiresPM){
						if (!message.isPrivateMessage()){
							return;
						}
					}

					if (deleteMsg){
						message.delete();
					}

					if (gattOnly){
						if (!(Main.adminUsers.contains(message.getAuthor().getId()))){
							return;
						}
					}
					Method method;
					Class<?> clz = Register.getCommandRegistrar().get(cmd);
					String methodName = Register.getMethodRegistrar().get(cmd).getName();
					try {
						method = clz.getDeclaredMethod(methodName, DiscordAPI.class, Message.class, User.class, String[].class);
						Object value = method.invoke(this, api, message, message.getAuthor(), args);
						msg = (String) value;
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (sendPM){
						message.getAuthor().sendMessage(msg);
					}else{
						message.reply(msg);
					}
				}
			}
		}
	}
}
