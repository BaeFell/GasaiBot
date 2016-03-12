package space.gatt.GattBot;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.entities.message.MessageDecoration;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/**
 * Created by Zach on 6/03/2016.
 */
public class UserCommands implements MessageCreateListener {

    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getContent().startsWith(Settings.getCommandStarter())) {
            String[] args = message.getContent().split(" ");
            // AFK Checker
            if (Main.afk.contains(message.getAuthor())) {
                Main.afk.remove(message.getAuthor());
                message.getAuthor().sendMessage("You're no longer AFK since you sent a message!");
            }

            if (message.getAuthor() != discordAPI.getYourself()) {
                for (User u : Main.afk) {
                    if (message.getMentions().contains(u)) {
                        MessageBuilder builder = new MessageBuilder();
                        builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Hey! ").appendUser(u).append(" is currently AFK!");
                        message.reply(builder.build());
                    }
                }
            }

            MessageBuilder builder = new MessageBuilder();
            // Help Command
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "help")) {
                message.delete();
                User u = message.getAuthor();
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter() + "I've PM'd you my command list, ").appendUser(message.getAuthor());
                message.reply(builder.build());
                builder = new MessageBuilder();
                for (String command : Main.commands) {
                    builder.append(Settings.getMsgStarter() + command).appendNewLine();
                }
                u.sendMessage(builder.build());
                return;
            }

            // Fun Shit

            // Is AFK?
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "isafk")) {
                message.delete();
                builder = new MessageBuilder();
                User u = message.getMentions().get(0);
                if (Main.afk.contains(u)) {
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" > " + u.getName() + " is AFK!");
                } else {
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" > " + u.getName() + " is not AFK!");
                }

                message.reply(builder.build());

                return;
            }
            // Help Command
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "version")) {
                message.delete();
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" GattBot Version " + Settings.getVersion());
                message.reply(builder.build());
                return;
            }


            // Set AFK
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "setafk")) {
                message.delete();
                if (args[1].equalsIgnoreCase("false")) {
                    Main.afk.remove(message.getAuthor());
                    builder = new MessageBuilder();
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" is no longer AFK.");
                    message.reply(builder.build());
                } else if (args[1].equalsIgnoreCase("true")) {
                    if (!Main.afk.contains(message.getAuthor())) {
                        Main.afk.add(message.getAuthor());
                        builder = new MessageBuilder();
                        builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" is now AFK.");
                        message.reply(builder.build());
                    }
                } else {
                    builder = new MessageBuilder();
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Unknown Argument!");
                    message.reply(builder.build());
                }

                return;
            }

            // Log
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "log")) {
                message.delete();
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendDecoration(MessageDecoration.BOLD, "https://discord.gg/0rtPRWdswScMri25").appendNewLine().append("There you go, for whatever reason you wanted it.");
                message.getAuthor().sendMessage(builder.build());
                return;
            }




            // Get Infomation
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "getinfo")) {
                message.delete();
                User u = message.getMentions().get(0);
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendDecoration(MessageDecoration.BOLD, " Info for " + u.getName()).appendNewLine()
                        .append(Settings.getMsgStarter() + "Discord ID: " + u.getId()).appendNewLine()
                        .append(Settings.getMsgStarter() + "Roles for this server: ").appendNewLine();
                for (Role r : u.getRoles(message.getChannelReceiver().getServer())) {
                    builder.appendDecoration(MessageDecoration.BOLD_ITALICS, Settings.getMsgStarter() + "Rank Name: " + r.getName()).appendNewLine();
                    builder.append(Settings.getMsgStarter() + " " + Settings.getMsgStarter() + " Rank ID: " + r.getId()).appendNewLine();
                    builder.append(Settings.getMsgStarter() + " " + Settings.getMsgStarter() + " Rank Color: " + r.getColor()).appendNewLine();
                    builder.append(Settings.getMsgStarter() + " " + Settings.getMsgStarter() + " Rank User Amount: " + r.getUsers().size()).appendNewLine().appendNewLine();
                }
                builder.append(Settings.getMsgStarter() + "Avatar URL: " + u.getAvatarUrl()).appendNewLine()
                        .append(Settings.getMsgStarter() + "Current Game: " + u.getGame()).appendNewLine();
                message.getAuthor().sendMessage(builder.build());
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter() + "I've PM'd you that user's information, ").appendUser(message.getAuthor());
                message.reply(builder.build());
                return;
            }

            // Meme Command
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "meme")) {
                message.delete();
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter() + "I has no memes, ").appendUser(message.getAuthor());
                message.reply(builder.build());
                return;
            }

            // Ping Command
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "ping")) {
                message.delete();
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter() + "Pong! ").appendUser(message.getAuthor());
                message.reply(builder.build());
                return;
            }

            //

            // Lolis
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "lolis")) {
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" You want a loli?").appendNewLine().append("https://i.gyazo.com/7049114840ae0333041f2138cccee63f.gif");
                message.reply(builder.build());
                return;
            }


            // Notice me Senpai
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "noticeme")) {
                message.delete();
                if (Main.adminUsers.contains(message.getAuthor().getId())) {
                    builder = new MessageBuilder();
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" S-s-s-s-s-senPAIII?!?!?!?! NOTICE ME SENPAI!").appendNewLine().append("http://pre14.deviantart.net/7397/th/pre/i/2013/153/5/5/yukki__i_ll_protect_you__by_saihina4ever-d67lec1.jpg");
                    message.reply(builder.build());
                    return;
                }
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" You're not senpai... ");
                message.reply(builder.build());

                return;
            }

            // Falcon Punch
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "falconpunch")) {
                message.delete();
                if (message.getMentions().get(0) != null) {
                    builder = new MessageBuilder();
                    User u = message.getMentions().get(0);
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" FALLLLLLLLCONNNNNNNNNN.... PUNCHHHHHHHHHHHH-ed ").appendUser(u).appendNewLine().append("http://atomix.vg/wp-content/uploads/2014/10/falcon-punch-anime.gif");
                    message.reply(builder.build());
                } else {
                    builder = new MessageBuilder();
                    User u = message.getMentions().get(0);
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Please supply an argument!");
                    message.reply(builder.build());
                }
                return;
            }

            // Slap
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "slap")) {
                message.delete();
                if (message.getMentions().get(0) != null) {
                    builder = new MessageBuilder();
                    User u = message.getMentions().get(0);
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" slapped you, ").appendUser(u);
                    message.reply(builder.build());
                } else {
                    builder = new MessageBuilder();
                    User u = message.getMentions().get(0);
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Please supply an argument!");
                    message.reply(builder.build());
                }
                return;
            }

            // 'DDOS'
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "ddos")) {
                message.delete();
                if (message.getMentions().get(0) != null) {
                    builder = new MessageBuilder();
                    User u = message.getMentions().get(0);
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" is DDOSing you, ").appendUser(u).append("! What a l337 h4x0r!");
                    message.reply(builder.build());
                } else {
                    builder = new MessageBuilder();
                    User u = message.getMentions().get(0);
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Please supply an argument!");
                    message.reply(builder.build());
                }
                return;
            }

            // Math Expression
            if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "math")) {
                message.delete();
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter() + "Disabled for now, ").appendUser(message.getAuthor());
                message.reply(builder.build());
                return;
                /*builder = new MessageBuilder();
                String mathExpression = "";
                args[0] = "";
                for (String s : args){
                    if (s != "") {
                        if (s.equalsIgnoreCase("pi")||s.equalsIgnoreCase("(pi)")){
                            s = ((Number)Math.PI).toString();
                        }
                        mathExpression = mathExpression + s + " ";
                    }
                }
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                String response = "N/A";
                try {
                    response = engine.eval(mathExpression).toString();
                }catch (ScriptException e){

                }
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" The answer to " + mathExpression + " is " + response);
                message.reply(builder.build());
                return;*/
            }
        }
    }
}
