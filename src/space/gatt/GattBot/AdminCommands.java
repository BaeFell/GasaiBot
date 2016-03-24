package space.gatt.GattBot;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.entities.message.MessageDecoration;
import de.btobastian.javacord.entities.message.MessageHistory;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Zach on 6/03/2016.
 */
public class AdminCommands implements MessageCreateListener {

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

            MessageBuilder builder = new MessageBuilder();
            args[0] = args[0].replaceFirst(Settings.getCommandStarter(), "");

            // Join Server Command
            if (args[0].equalsIgnoreCase("joinserver")) {
                message.delete();
                if (message.isPrivateMessage()) {
                    String msg = message.getContent();
                    String invite = msg.replace(Settings.getCommandStarter() + "joinserver ", "");
                    Server server = null;
                    try {
                        server = api.parseInvite(invite).get().acceptInvite().get();
                    } catch (InterruptedException | ExecutionException e) {
                        if (api.getServers().contains(server)) {
                            builder = new MessageBuilder();
                            builder.append(Settings.getMsgStarter() + "Joining server from invite " + invite);
                            message.reply(builder.build());
                            builder = new MessageBuilder();
                            builder.append(Settings.getMsgStarter() + "Found server from invite " + invite + "... " + server.getName());
                            message.reply(builder.build());
                        } else {
                            builder = new MessageBuilder();
                            builder.append(Settings.getMsgStarter() + "Could not join server from invite " + invite);
                            message.reply(builder.build());
                        }

                        builder = new MessageBuilder();
                        builder.append("(OH NO! SOMETHING HAPPENED. PRINTING STACK TRACE!)").append(e.getStackTrace().toString());
                        Main.GattBotChannel.sendMessage(builder.build());
                    }
                    if (api.getServers().contains(server)) {
                        builder = new MessageBuilder();
                        builder.append(Settings.getMsgStarter() + "Joining server from invite " + invite);
                        message.reply(builder.build());
                        builder = new MessageBuilder();
                        builder.append(Settings.getMsgStarter() + "Found server from invite " + invite + "... " + server.getName());
                        message.reply(builder.build());
                    } else {
                        builder = new MessageBuilder();
                        builder.append(Settings.getMsgStarter() + "Could not join server from invite " + invite);
                        message.reply(builder.build());
                    }
                    return;

                } else {
                    builder = new MessageBuilder();
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Personal Message me to make me join a server with the following syntax: " + Settings.getCommandStarter() + "joinserver <invite>");
                    message.reply(builder.build());
                }
                return;
            }

            if (args[0].equalsIgnoreCase("startupmessage")) {
                message.delete();
                if (message.isPrivateMessage()) {
                    if (Main.adminUsers.contains(message.getAuthor().getId())) {
                        builder = new MessageBuilder();
                        builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" GattBot is initiating...");
                        for (Channel c : Settings.getJoinMsgChannels()) {
                            c.sendMessage(builder.build());
                        }
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            builder = new MessageBuilder();
                            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" GattBot has started!");
                            for (Channel c : Settings.getJoinMsgChannels()) {
                                c.sendMessage(builder.build());
                            }
                            TimeUnit.SECONDS.sleep(2);
                            builder = new MessageBuilder();
                            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Loading settings!");
                            for (Channel c : Settings.getJoinMsgChannels()) {
                                c.sendMessage(builder.build());
                            }
                            TimeUnit.SECONDS.sleep(3);
                            builder = new MessageBuilder();
                            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" GattBot has loaded!");
                            for (Channel c : Settings.getJoinMsgChannels()) {
                                c.sendMessage(builder.build());
                            }
                        } catch (InterruptedException e) {

                            builder = new MessageBuilder();
                            builder.append("(OH NO! SOMETHING HAPPENED. PRINTING STACK TRACE!)").append(e.getStackTrace().toString());
                            Main.GattBotChannel.sendMessage(builder.build());
                        }
                        return;

                    }
                }
            }

            if (args[0].equalsIgnoreCase("clearchat")) {
                message.delete();
                if (hasRole(message.getAuthor(), message.getChannelReceiver().getServer(), "Bot Commander")) {
                    try {
                        for (Message m : message.getChannelReceiver().getMessageHistory(1000).get().getMessagesSorted()) {
                            m.delete();
                        }
                    } catch (ExecutionException | InterruptedException e) {

                        builder = new MessageBuilder();
                        builder.append("(OH NO! SOMETHING HAPPENED. PRINTING STACK TRACE!)").append(e.getStackTrace().toString());
                        Main.GattBotChannel.sendMessage(builder.build());
                    }
                    if (args.length > 1) {
                        if (args[1].equalsIgnoreCase("-silent")) {
                        } else {
                            builder = new MessageBuilder();
                            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Attempted to clear up to 1000 messages in this channel! (That's all the API will allow at the one time)").appendNewLine().appendDecoration(MessageDecoration.BOLD, "If no messages were removed, that means I don't have the right permissions!");
                            message.reply(builder.build());
                        }
                    } else {
                        builder = new MessageBuilder();
                        builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Attempted to clear up to 1000 messages in this channel! (That's all the API will allow at the one time)").appendNewLine().appendDecoration(MessageDecoration.BOLD, "If no messages were removed, that means I don't have the right permissions!");
                        message.reply(builder.build());
                    }
                    return;
                } else {
                    builder = new MessageBuilder();
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" You do not have the ```Bot Commander``` rank!");
                    message.reply(builder.build());
                }
            }

            if (args[0].equalsIgnoreCase("listservers")) {
                message.delete();
                if (message.isPrivateMessage()) {
                    if (Main.adminUsers.contains(message.getAuthor().getId())) {
                        for (Server s : api.getServers()) {
                            builder = new MessageBuilder();
                            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" " + s.getName());
                            message.reply(builder.build());
                        }
                    } else {
                        builder = new MessageBuilder();
                        builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" You are not senpai!");
                        message.reply(builder.build());
                    }
                    return;
                }
            }

            // Join Server Command
            if (args[0].equalsIgnoreCase("leaveall")) {
                message.delete();
                if (message.isPrivateMessage()) {
                    if (Main.adminUsers.contains(message.getAuthor().getId())) {
                        for (Server s : api.getServers()) {
                            if (!s.getName().equalsIgnoreCase("GattBotServer")) {
                                s.leave();
                                builder = new MessageBuilder();
                                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Left server " + s.getName());
                                message.reply(builder.build());
                            } else {
                                builder = new MessageBuilder();
                                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Cannot leave server " + s.getName());
                                message.reply(builder.build());
                            }
                        }
                        try {
                            boolean hasServer = false;
                            for (Server s : api.getServers()) {
                                if (s.getName() == "GattBotServer") {
                                    hasServer = true;
                                    break;
                                }
                            }
                            if (!hasServer) {
                                BufferedImage icon = api.getYourself().getAvatar().get();
                                Server server = api.createServer("GattBotServer", icon).get();
                                Channel channel = server.createChannel("GattBotChannel").get();
                                channel.updateTopic("GattBotChannel");
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            builder = new MessageBuilder();
                            builder.append("(OH NO! SOMETHING HAPPENED. PRINTING STACK TRACE!)").append(e.getStackTrace().toString());
                            Main.GattBotChannel.sendMessage(builder.build());
                            return;
                        }
                    } else {
                        builder = new MessageBuilder();
                        builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" You are not senpai!");
                        message.reply(builder.build());
                    }
                    return;
                }
            }

            // Set Game
            if (args[0].equalsIgnoreCase("setsetting")) {
                message.delete();
                if (Main.adminUsers.contains(message.getAuthor().getId())) {
                    if (args[1].equalsIgnoreCase("game")) {
                        String game = "";
                        args[0] = "";
                        args[1] = "";
                        for (String s : args) {
                            if (s != "") {
                                game = game + s + " ";
                            }
                        }
                        System.out.println("setting game to " + game);
                        api.setGame(game);
                        Settings.setGame(game);
                        builder = new MessageBuilder();
                        builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Set setting 'Game' to " + api.getGame());
                        message.reply(builder.build());
                        return;
                    }

                    if (args[1].equalsIgnoreCase("profilepicture")) {
                        BufferedImage img = null;
                        System.out.println("Updating profile picture");
                        api.setGame("Updating Profile Picture");
                        builder = new MessageBuilder();
                        builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Updating profile picture.");
                        message.reply(builder.build());
                        String imgType = "url";
                        String xy = args[2];
                        Integer x, y;
                        if (xy.equalsIgnoreCase("null")) {
                            x = -1;
                            y = -1;
                        } else {
                            String[] xyA = xy.split("X");
                            x = Integer.valueOf(xyA[0]);
                            y = Integer.valueOf(xyA[1]);
                        }
                        imgType = args[3];
                        if (args[3].equalsIgnoreCase("url")) {
                            System.out.println("Image is URL");
                            try {
                                URL url = new URL(args[4]);
                                img = ImageIO.read(url);
                            } catch (IOException e) {
                                api.setGame(Settings.getGame());
                                e.printStackTrace();
                                builder = new MessageBuilder();
                                builder.append("(OH NO! SOMETHING HAPPENED. PRINTING STACK TRACE!)").append(e.getStackTrace().toString());
                                Main.GattBotChannel.sendMessage(builder.build());
                            }
                        } else if (args[3].equalsIgnoreCase("file")) {
                            System.out.println("Image is Attachment");
                            if (message.getAttachments().iterator().hasNext()) {
                                System.out.println("Getting image from attachment");
                                try {
                                    img = ImageIO.read(message.getAttachments().iterator().next().getUrl());
                                } catch (IOException e) {
                                    api.setGame(Settings.getGame());
                                    e.printStackTrace();
                                    builder = new MessageBuilder();
                                    builder.append("(OH NO! SOMETHING HAPPENED. PRINTING STACK TRACE!)").append(e.getStackTrace().toString());
                                    Main.GattBotChannel.sendMessage(builder.build());
                                }
                            } else {
                                System.out.println("Could not find image from attachement.");
                                builder = new MessageBuilder();
                                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Could not find supplied image.");
                                message.reply(builder.build());
                                api.setGame(Settings.getGame());
                                return;
                            }

                        } else if (args[3].equalsIgnoreCase("user")) {
                            System.out.println("Image is User's");
                            if (message.getMentions().get(0) != null) {
                                System.out.println("Getting image from user");
                                try {
                                    img = message.getMentions().get(0).getAvatar().get();
                                } catch (InterruptedException | ExecutionException e) {
                                    api.setGame(Settings.getGame());
                                    e.printStackTrace();
                                    builder = new MessageBuilder();
                                    builder.append("(OH NO! SOMETHING HAPPENED. PRINTING STACK TRACE!)").appendNewLine().append("```" + e.getLocalizedMessage() + "```");
                                    Main.GattBotChannel.sendMessage(builder.build());
                                }
                            }

                            //if (message.getAttachments().iterator().hasNext()){
                            //    System.out.println("Getting image from user");
                            //    try {
                            //        img = ImageIO.read(message.getAttachments().iterator().next().getUrl());
                            //    }catch (IOException e){
                            //        api.setGame(Settings.getGame());
                            //        e.printStackTrace();
                            //    }
                            //}
                        }
                        if (img != null) {
                            builder = new MessageBuilder();
                            if (x == -1 || y == -1) {
                                api.updateAvatar(img);
                            } else {
                                int heightMid = img.getHeight() / 2;
                                int widthMid = img.getWidth() / 2;
                                BufferedImage newImg = img.getSubimage(x, y, heightMid, widthMid);
                                api.updateAvatar(newImg);
                            }

                            if (imgType.equalsIgnoreCase("url")) {
                                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Set setting 'Profile Picture' to image from URL " + args[4]);
                            } else if (imgType.equalsIgnoreCase("file")) {
                                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Set setting 'Profile Picture' to image from supplied image");
                            } else if (imgType.equalsIgnoreCase("user")) {
                                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Set setting 'Profile Picture' to image from user ").appendUser(message.getMentions().get(0));
                            }

                            message.reply(builder.build());
                        } else {
                            builder = new MessageBuilder();
                            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Could not update image");
                            message.reply(builder.build());
                        }
                        api.setGame(Settings.getGame());

                        return;
                    }

                    if (args[1].equalsIgnoreCase("cloneuser")) {
                        User u = message.getMentions().get(0);
                        String name = u.getName();
                        String game = u.getGame();
                        builder = new MessageBuilder();
                        builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Cloning user ").appendUser(u);
                        message.reply(builder.build());
                        builder = new MessageBuilder();
                        try {
                            api.updateAvatar(message.getMentions().get(0).getAvatar().get());
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException | ExecutionException e) {
                            api.setGame(Settings.getGame());
                            e.printStackTrace();
                        }

                        try {
                            TimeUnit.SECONDS.sleep(1);
                            api.updateUsername(name + "*");
                        } catch (InterruptedException e) {
                            api.setGame(Settings.getGame());
                            e.printStackTrace();
                        }
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            api.setGame(game);
                        } catch (InterruptedException e) {
                            api.setGame(Settings.getGame());
                            e.printStackTrace();
                        }
                        try {
                            TimeUnit.SECONDS.sleep(2);
                            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Cloned user ").appendUser(u);
                            message.reply(builder.build());
                        } catch (InterruptedException e) {

                        }
                        return;
                    }

                    if (args[1].equalsIgnoreCase("name")) {
                        String name = "";
                        args[0] = "";
                        args[1] = "";
                        for (String s : args) {
                            if (s != "") {
                                name = name + s + " ";
                            }
                        }
                        System.out.println("setting name to " + name);

                        builder = new MessageBuilder();
                        api.updateUsername(name);
                        builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Set setting 'Name' to " + name);
                        message.reply(builder.build());
                        return;
                    }

                    if (args[1].equalsIgnoreCase("cmdstart")) {
                        String name = "";
                        args[0] = "";
                        args[1] = "";
                        for (String s : args) {
                            if (s != "") {
                                name = name + s + " ";
                            }
                        }
                        name = name.trim();
                        System.out.println("setting cmd starter to " + name);
                        builder = new MessageBuilder();
                        if (!name.equals("")) {
                            Settings.setCommandStarter(name);
                            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Set setting 'Command Starter' to " + name);
                            message.reply(builder.build());
                        } else {
                            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" No argument supplied!");
                            message.reply(builder.build());
                        }

                        return;
                    }
                } else {
                    builder = new MessageBuilder();
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" You are not senpai!");
                    message.reply(builder.build());
                }
                return;
            }

            // Add Channel

            if (args[0].equalsIgnoreCase("addchannel")) {
                message.delete();
                if (Main.adminUsers.contains(message.getAuthor().getId())) {
                    builder = new MessageBuilder();
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Added Channel " + message.getChannelReceiver().getName() + " on Server " + message.getChannelReceiver().getServer().getName() + " to Start Up Msg receivers...");
                    message.reply(builder.build());
                    Settings.addChannelToStartup(message.getChannelReceiver());

                } else {
                    builder = new MessageBuilder();
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" You are not senpai!");
                    message.reply(builder.build());
                }
                return;
            }

            // Shutdown Command
            if (args[0].equalsIgnoreCase("shutdown")) {

                if (Main.adminUsers.contains(message.getAuthor().getId())) {
                    if (!message.isPrivateMessage()) {
                        Settings.addChannelToStartup(message.getChannelReceiver());
                    }
                    builder = new MessageBuilder();
                    if (args.length > 0) {
                    }
                    if (args[1].equalsIgnoreCase("-shutdown")) {
                        builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Shutting down, Senpai");
                    } else {
                        builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Shutting down, Senpai (Unknown Argument!)");
                    }
                    for (Channel c : Settings.getJoinMsgChannels()) {
                        c.sendMessage(builder.build());
                    }
                    message.delete();
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        System.exit(0);
                    } catch (InterruptedException e) {

                    }
                } else {
                    builder = new MessageBuilder();
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" You are not senpai!");
                    message.reply(builder.build());
                }
                return;
            }
            builder = new MessageBuilder();
            if (!message.isPrivateMessage()) {
                builder.append(message.getAuthor().getName() + "(" + message.getChannelReceiver().getServer().getName() + ")[" + message.getChannelReceiver().getName() + "] > " + message.getContent());
                Main.GattBotChannel.sendMessage(builder.build());
            } else {
                builder.append(message.getAuthor().getName() + "(PM)> " + message.getContent());
                Main.GattBotChannel.sendMessage(builder.build());
            }
        }
    }
}


