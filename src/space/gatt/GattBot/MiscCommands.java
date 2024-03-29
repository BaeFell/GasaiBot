package space.gatt.GattBot;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.entities.message.MessageDecoration;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
* Created by Zach on 6/03/2016.
*/
public class MiscCommands implements MessageCreateListener {


    private HashMap<String, File> imageCache = new HashMap<>();
    private HashMap<String, String> gifCache = new HashMap<>();

    private synchronized void cacheImage(String url, String extension, String name){
        try {
            if (extension.equalsIgnoreCase("gif")){
                gifCache.put(name, url);
                return;
            }
            File imgf = new File(name + "." + extension);
            BufferedImage img = ImageIO.read(new URL(url));
            ImageIO.write(img, extension, imgf);
            imageCache.put(name, imgf);
        }catch (IOException e){
            MessageBuilder builder = new MessageBuilder();
            builder.append("(OH NO! SOMETHING HAPPENED. PRINTING STACK TRACE!)").appendNewLine().append("```" + e.getLocalizedMessage() + "```");
            Main.adminLogChannel.sendMessage(builder.build());
        }
    }

    public synchronized void cacheImages(){
        cacheImage("http://orig09.deviantart.net/ac32/f/2011/356/f/1/nope_avi_high_resolution_by_wango911-d4jv1vx.png", "png", "nope");
        cacheImage("http://i0.kym-cdn.com/photos/images/newsfeed/000/173/576/Wat8.jpg", "jpg", "wat");
        cacheImage("http://res.cloudinary.com/urbandictionary/image/upload/a_exif,c_fit,h_200,w_200/v1395991705/gjn81wvxqsq6yzcwubok.png", "png", "kappa");
        cacheImage("http://pre14.deviantart.net/7397/th/pre/i/2013/153/5/5/yukki__i_ll_protect_you__by_saihina4ever-d67lec1.jpg", "jpg", "noticeme");
        cacheImage("https://i.ytimg.com/vi/fGl4LOAgW50/maxresdefault.jpg", "jpg", "awwyeah");
        cacheImage("http://i.imgur.com/Vu0WycI.png", "png", "internetgod");
        cacheImage("http://gasaibot.gatt.space/gifs/time.jpg", "jpg", "time");
        cacheImage("http://i.imgur.com/urSk0Ki.jpg", "jpg", "ohwhale");
        cacheImage("https://cdn.shopify.com/s/files/1/0358/2273/products/dogewow_grande.jpg", "jpg", "wow");
        cacheImage("http://i.imgur.com/i2fhjzB.jpg", "jpg", "sealofapproval");
        cacheImage("http://i.imgur.com/hUKGdpg.png", "png", "facepalm");
        cacheImage("http://i.imgur.com/7Qqz10b.png", "png", "triplefacepalm");
        cacheImage("http://i.imgur.com/vZRQvvC.jpg", "jpg", "badass");
        cacheImage("https://s-media-cache-ak0.pinimg.com/236x/f0/aa/82/f0aa8247510f29b5d66aa4c5ec78319d.jpg", "jpg", "illya");
        cacheImage("https://lh3.googleusercontent.com/-YPthLd7n9Uk/VGG1poxPbII/AAAAAAAAE0U/L2MfcQscBUQ/w600-h337-no/fsn5p1.png", "png", "waifu");
        cacheImage("http://gasaibot.gatt.space/gifs/mindblown.gif", "gif", "mindblown");
        cacheImage("http://gasaibot.gatt.space/gifs/lolis.gif", "gif", "lolis");
        cacheImage("http://gasaibot.gatt.space/gifs/falconpunch.gif", "gif", "falconpunch");
        cacheImage("https://media.giphy.com/media/amaS2ywWuJsXe/giphy.gif", "gif", "konga");
    }

    private long getTimeRunning(){
        Date date = new Date();
        return (date.getTime() - Main.startupTime);
    }

    @Override
    public synchronized void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getAuthor().getId().equalsIgnoreCase("166190165214232576")){
            message.delete();
            return;
        }
        String[] args = message.getContent().split(" ");
        MessageBuilder builder;
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "konga")){
            message.delete();
            message.reply(gifCache.get("konga"));
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "illya")){
            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Illya :heart:").appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("illya"));
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "waifu")){
            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Your waifu has arrived. :heart:").appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("waifu"));
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "sealofapproval")){
            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" gives his Seal of Approval").appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("sealofapproval"));
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "badass")){
            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" steps back.").appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("badass"));
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "ohwhale")){
            message.delete();
            builder = new MessageBuilder();

            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Oh, Whale!").appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("ohwhale"));
        }

        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "wow")){
            message.delete();
            builder = new MessageBuilder();

            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("wow"));
        }

        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "triplefacepalm")){
            message.delete();
            builder = new MessageBuilder();

            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("triplefacepalm"));
        }

        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "facepalm")){
            message.delete();
            builder = new MessageBuilder();

            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("facepalm"));
        }

        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "awwyeah")){
            message.delete();
            builder = new MessageBuilder();

            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).appendDecoration(MessageDecoration.BOLD, " Awwww, Yeahhhh!").appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("awwyeah"));
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "internetgod")){
            message.delete();
            builder = new MessageBuilder();

            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("internetgod"));
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "wat")){
            message.delete();
            builder = new MessageBuilder();

            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).appendDecoration(MessageDecoration.BOLD, " wat?").appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("wat"));
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "kappa")){
            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).appendDecoration(MessageDecoration.BOLD, " Kappa!").appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("kappa"));
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "mindblown")){

            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).appendDecoration(MessageDecoration.BOLD, "'s mind blew up!").appendNewLine();
            builder.append(gifCache.get("mindblown"));
            message.reply(builder.build());
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "hype")){
            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Let the hype train begin!");
            message.reply(builder.build());
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "lenny")){
            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" ( ͡° ͜ʖ ͡°)");
            message.reply(builder.build());
        }

        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "sponsor")){
            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Gasai Bot is sponsored and hosted thanks to Shockbyte!").appendNewLine().append("https://www.shockbyte.com/").appendNewLine().append("Use the coupon code 'DISCORD' to get a Dedicated IP for 3$ a month (instead of the usual 5$ a month!)");
            message.reply(builder.build());
        }

        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "1v1")){
            message.delete();
            if (message.getMentions().size() > 1) {
                User u1 = message.getMentions().get(0);
                User u2 = message.getMentions().get(1);
                ArrayList<User> fiteme = new ArrayList<>();
                fiteme.add(u1);
                fiteme.add(u2);
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" ").appendUser(u1).append(" VERSUS ").appendUser(u2).append("!").appendNewLine().append("And the winner is... ").appendUser(fiteme.get(new Random().nextInt(fiteme.size())));
                message.reply(builder.build());
            }else{
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Specify two users, please...");
                message.getAuthor().sendMessage(builder.build());
            }
        }

        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "lmgtfy")){
            message.delete();
            if (message.getMentions().size() > 0) {
                User u = message.getMentions().get(0);
                String question = "";
                args[0] = "";
                args[1] = "";
                for (String s : args) {
                    if (s != "") {
                        question = question + s + "+";
                    }
                }
                String url = "http://lmgtfy.com/?q=" + question;
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(u).append("! ").appendUser(message.getAuthor()).append(" thinks this might help!").appendNewLine().appendDecoration(MessageDecoration.BOLD, url);
                message.reply(builder.build());
            }else{
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Specify a user please.");
                message.getAuthor().sendMessage(builder.build());
            }
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "lmbtfy")){
            message.delete();
            if (message.getMentions().size() > 0) {
                User u = message.getMentions().get(0);
                String question = "";
                args[0] = "";
                args[1] = "";
                for (String s : args) {
                    if (s != "") {
                        question = question + s + "+";
                    }
                }
                String url = "http://lmbtfy.com/?q=" + question;
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(u).append("! ").appendUser(message.getAuthor()).append(" thinks this might help! (But it's Bing, so ya know)").appendNewLine().appendDecoration(MessageDecoration.BOLD, url);
                message.reply(builder.build());
            }else{
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Specify a user please.");
                message.getAuthor().sendMessage(builder.build());
            }
        }

        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "fightclub")){
            message.delete();
            if (message.getMentions().size() > 1) {
                ArrayList<User> fiteme = new ArrayList<User>();
                for (User u : message.getMentions()){
                    fiteme.add(u);
                }

                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" A fight between " + fiteme.size() + " people has started!!").appendNewLine().append("And the winner is... ").appendUser(fiteme.get(new Random().nextInt(fiteme.size())));
                message.reply(builder.build());
            }else{
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Specify more than two users, please...");
                message.getAuthor().sendMessage(builder.build());
            }
        }

        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "timer")){
            message.delete();
            if (args.length >= 4){
                Integer delay = Integer.valueOf(args[2]);
                if (delay == null){
                    builder = new MessageBuilder();
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Arg 2 must be an integer!");
                    message.getAuthor().sendMessage(builder.build());
                    return;
                }
                TimeUnit unit = null;
                if (args[1].equalsIgnoreCase("seconds")){
                    unit = TimeUnit.SECONDS;
                }
                if (args[1].equalsIgnoreCase("minutes")){
                    unit = TimeUnit.MINUTES;
                }
                if (args[1].equalsIgnoreCase("hours")){
                    unit = TimeUnit.HOURS;
                }

                if (unit == null){
                    builder = new MessageBuilder();
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Arg 1 must be either MINUTES HOURS or SECONDS");
                    message.getAuthor().sendMessage(builder.build());
                    return;
                }
                String msg = "";
                args[0] = "";
                args[1] = "";
                args[2] = "";
                for (String s : args) {
                    if (s != "") {
                        msg = msg + s + " ";
                    }
                }
                new Thread(new Delay(unit, delay, message.getAuthor(), msg)).start();
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" I'll remind you when you requested me to :)");
                message.getAuthor().sendMessage(builder.build());
                return;
            }else{
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Not enough arguments!");
                message.getAuthor().sendMessage(builder.build());
                return;
            }
        }

        // Lolis
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "lolis")) {
            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" You want a loli?").appendNewLine();
            builder.append(gifCache.get("lolis"));
            message.reply(builder.build());
            return;
        }


        // Notice me Senpai
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "noticeme")) {
            message.delete();
            if (Main.senpais.contains(message.getAuthor().getId())) {
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" S-s-s-s-s-senPAIII?!?!?!?! NOTICE ME SENPAI!").appendNewLine();
                message.reply(builder.build());
                message.replyFile(imageCache.get("noticeme"));
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
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" FALLLLLLLLCONNNNNNNNNN.... PUNCHHHHHHHHHHHH-ed ").appendUser(u).appendNewLine();
                builder.append(gifCache.get("falconpunch"));
                message.reply(builder.build());
            } else {
                builder = new MessageBuilder();
                User u = message.getMentions().get(0);
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Please supply an argument!");
                message.reply(builder.build());
            }
            return;
        }

        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "uptime")) {
            message.delete();
            long seconds = getTimeRunning() / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            String time = days + " days, " + hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds";
            builder = new MessageBuilder();
            builder.append("`" + Settings.getMsgStarter() +" I've been online for " + time +"`");
            message.reply(builder.build());
            return;
        }

        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "time")) {
            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).appendDecoration(MessageDecoration.BOLD, " Good heavens, would you look at the time!").appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("time"));
            return;
        }
        // Nope

        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "nope")) {
            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).appendDecoration(MessageDecoration.BOLD, " Nope").appendNewLine();
            message.reply(builder.build());
            message.replyFile(imageCache.get("nope"));
            return;
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "site")){
            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Here you go!").appendNewLine().append("http://gasaibot.gatt.space/");
            message.getAuthor().sendMessage(builder.build());
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "git")){
            message.delete();
            builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Here you go!").appendNewLine().append("https://github.com/Funnygatt/GasaiBot");
            message.getAuthor().sendMessage(builder.build());
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "roll")){
            message.delete();
            if (args.length == 1){
                Integer maxNo = 100;
                Random random = new Random();
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" You rolled a " + (random.nextInt(maxNo) + 1) + "! (1-100)");
                message.reply(builder.build());
            }else{
                Integer maxNo = Integer.valueOf(args[1]);
                if (maxNo == null){
                    builder = new MessageBuilder();
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" You did not supply a valid integer!");
                    message.reply(builder.build());
                    return;
                }
                Random random = new Random();
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" You rolled a " + (random.nextInt(maxNo) + 1) + "! (1-" + maxNo + ")");
                message.reply(builder.build());
            }
        }

        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "8ball")){
            message.delete();
            if (args.length == 1){
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" :8ball: You did not supply a question!");
                message.reply(builder.build());
            }else{
                String question = "";
                args[0] = "";
                for (String s : args) {
                    if (s != "") {
                        question = question + s + " ";
                    }
                }
                ArrayList<String> responses = new ArrayList<>();
                responses.add("It is certain.");
                responses.add("It is decidedly so.");
                responses.add("Without a doubt.");
                responses.add("Yes, definitely.");
                responses.add("You may rely on it.");
                responses.add("As I see it, yes.");
                responses.add("Most likely.");
                responses.add("Outlook good.");
                responses.add("Yes.");
                responses.add("The stars point to Yes.");
                responses.add("Reply is hazy... try again.");
                responses.add("Ask again later.");
                responses.add("Better not tell you now.");
                responses.add("Cannot predict now.");
                responses.add("Concentrate and ask again.");
                responses.add("Don't count on it.");
                responses.add("My reply is no.");
                responses.add("My sources say no.");
                responses.add("Outlook not so good.");
                responses.add("Very doubtful.");
                Random random = new Random();
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" :8ball: Hmm... " + responses.get(random.nextInt(responses.size())));
                message.reply(builder.build());
            }
        }
        if (args[0].equalsIgnoreCase(Settings.getCommandStarter() + "decide")){
            message.delete();
            if (args.length == 1){
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" :8ball: You did not supply decisions!");
                message.reply(builder.build());
            }else{
                ArrayList<String> responses = new ArrayList<>();
                args[0] = "";
                for (String s : args) {
                    if (s != "") {
                        responses.add(s.replace("_", " "));
                    }
                }
                Random random = new Random();
                builder = new MessageBuilder();
                builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" :8ball: Hmm... I'm going with " + responses.get(random.nextInt(responses.size())));
                message.reply(builder.build());
            }
        }
    }
}
