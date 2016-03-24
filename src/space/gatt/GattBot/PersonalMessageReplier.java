package space.gatt.GattBot;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.entities.message.MessageDecoration;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.util.*;

/**
 * Created by Zach G on 12-Mar-16.
 */
public class PersonalMessageReplier implements MessageCreateListener {

	private long getTimeRunning(){
		Date date = new Date();
		return (date.getTime() - Main.startupTime);
	}

	private void reply(Message message, String s){
		s = s.replaceAll("%msg", message.getContent());
		s = s.replaceAll("%timerunning", Objects.toString(getTimeRunning(), null));
		s = s.replaceAll("%user", message.getAuthor().getMentionTag());
		MessageBuilder builder = new MessageBuilder();
		Integer id = -1;
		String[] args = s.split("");
		String currentAction = "scanning";
		String addMsg = "";
		Boolean doAdd;
		builder.append(Settings.getMsgStarter() + " ");
		for (String s1 : args){
			id++;
			// Italics Scanner
			doAdd = true;
			if (s1.equalsIgnoreCase("%")){
				if (args[id+1].equalsIgnoreCase("i") && args[id+2].equalsIgnoreCase("(")){
					 currentAction = "addingToItalics";
					args[id] = "";
					args[id+2] = "";
					args[id+1] = "";
					doAdd = false;
				}
			}
			if (currentAction.equalsIgnoreCase("addingtoitalics")){
				if (!s1.equalsIgnoreCase(")")){
					addMsg = addMsg + s1;
					doAdd = false;
				}else{
					builder.appendDecoration(MessageDecoration.ITALICS, addMsg);
					addMsg = "";
					currentAction = "scanning";
					doAdd = false;
				}
			}
			if (s1.equalsIgnoreCase("%")){
				if (args[id+1].equalsIgnoreCase("b") && args[id+2].equalsIgnoreCase("(")){
					currentAction = "addingToBold";
					args[id] = "";
					args[id+2] = "";
					args[id+1] = "";
					doAdd = false;
				}
			}
			if (currentAction.equalsIgnoreCase("addingtobold")){
				if (!s1.equalsIgnoreCase(")")){
					addMsg = addMsg + s1;
					doAdd = false;
				}else{
					builder.appendDecoration(MessageDecoration.BOLD, addMsg);
					addMsg = "";
					currentAction = "scanning";
					doAdd = false;
				}
			}
			if (doAdd){
				builder.append(s1);
			}
		}
		message.reply(builder.build());
	}

	@Override
	public void onMessageCreate(DiscordAPI discordAPI, Message message) {

		if (message.isPrivateMessage() && (message.getAuthor() != discordAPI.getYourself())){

			List<String> replies = new ArrayList<>();
			replies.add("I'm GattBot!");
			replies.add("I'm GattBot! Use " + Settings.getCommandStarter() + "help for my command list!");
			replies.add("Hmmmm... I don't know...");
			replies.add("Maybe?");
			replies.add("わっと?");
			replies.add("は?");
			replies.add("ばか!!");
			replies.add("I've been running for %b(%timerunning) milliseconds!");
			replies.add("%i(%msg) to you too!");
			replies.add("Hmmm... Do I know you, %user?");
			replies.add("Humph!");
			replies.add("Do you need something?");
			replies.add("Yes. I'm a Bot. Yes - this is a predefined message. No you cannot add more");

			String reply = "";
			Random random = new Random();
			reply = replies.get(random.nextInt(replies.size()));

			reply(message, reply);

		}
	}
}
