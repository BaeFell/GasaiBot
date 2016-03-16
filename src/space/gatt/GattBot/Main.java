package space.gatt.GattBot;

import com.google.common.util.concurrent.FutureCallback;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.MessageBuilder;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by Zach on 5/03/2016.
 */
public class Main {

    public static HashMap<String, String> userCache = new HashMap<>();

    public static ArrayList<User> afk = new ArrayList<>();

    public static ArrayList<String> commands = new ArrayList();
    public static Channel GattBotChannel;

    public static ArrayList<String> adminUsers = new ArrayList<>();
    public static ArrayList<String> senpais = new ArrayList<>();

    private static DiscordAPI api;

    public static DiscordAPI getApi() {
        return api;
    }

    public static long startupTime;
    private static String token;

    public static void main(String[] args) {


        //new Thread(new SocketManager()).start();
        final String email = args[0];
        final String password = args[1];
        System.out.println("Attempting login with email " + email);
        Date date = new Date();
        startupTime = date.getTime();
        //FutureCallback<DiscordAPI> futureAPI = new FutureCallback<DiscordAPI>() {
        //    @Override
        //    public void onSuccess(DiscordAPI result) {
        //        Main.token = result.getToken();
        //    }
//
        //    @Override
        //    public void onFailure(Throwable t) {
//
        //    }
        //};
        //Javacord.getApi(email, password).connect(futureAPI);
        api = Javacord.getApi(email, password);
        api.connect(new FutureCallback<DiscordAPI>() {
            @Override
            public void onSuccess(DiscordAPI api) {
                //api.convertToBotAccount(api.getToken());
                api.setGame("with Yuki.");
                //Settings.loadSettings();
                try{
                    boolean hasServer = false;
                    for (Server s : api.getServers()){
                        System.out.println("Server = " + s.getName());
                        if (s.getName().equalsIgnoreCase("GattBotServer")){
                            hasServer = true;
                            GattBotChannel = s.getChannels().iterator().next();
                            break;
                        }
                    }
                    if (!hasServer) {
                        BufferedImage icon = api.getYourself().getAvatar().get();
                        Server server = api.createServer("GattBotServer", icon).get();
                        Channel channel = server.createChannel("GattBotChannel").get();
                        channel.updateTopic("GattBotChannel");
                        GattBotChannel = channel;
                        System.out.println("Created Base Server");
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return;
                }
                api.setAutoReconnect(true);
                System.out.println("Updating user cache");
                for (Server s : api.getServers()){
                    for (User m : s.getMembers()) {
                        userCache.put(m.getName(), m.getId());
                    }
                }
                commands.add("help - Seriously?");
                commands.add("version - Returns the version");

                commands.add("**~ ~ ~ Fun ~ ~ ~**");
                commands.add("lolis - Shows you one of the best lolis in the world");
                commands.add("meme - It has no memes...");
                commands.add("ping - Pong... Really?!");
                commands.add("noticeme - Hmph!...");
                commands.add("falconpunch [@User] - Fallconnnnnn Punchhhhh!");
                commands.add("slap [@User] - Ouch!");
                commands.add("ddos [@User] - DD0S th3m u l337 h4x0r");
                commands.add("nope - Nope.avi");
                commands.add("lenny - ( ͡° ͜ʖ ͡°)");
                commands.add("hype - Hypeeeeeeeeeeeee.");
                commands.add("mindblown - Boom.");
                commands.add("kappa - Kappa.");
                commands.add("wat - wat");
                commands.add("awwyeah - Awwwww Yeahhhh");
                commands.add("git - Sends you the Git Repo");
                commands.add("time - Good Heavens! Look at the time!");
                commands.add("uptime - Sends the Uptime for GattBot.");

                commands.add("**~ ~ ~ Misc ~ ~ ~**");

                commands.add("1v1 [@User1] [@User2] - 1v1 me, scrub");
                commands.add("fightclub [@User1] [@User2] [@User3] ... - Fight! Infinite number of fighters allowed.");
                commands.add("lmgtfy [@User] [Search] - Let me google that for you ;)");
                commands.add("lmbtfy [@User] [Search] - Let me bing that for you... for whatever reason?");
                commands.add("timer [seconds|minutes|hours] [amount] [message] - Will PM in [amount] of [seconds|minutes|hours] the [message] you specify privately.");
                commands.add("roll - Rolls a random number between 1 and 100");
                commands.add("roll [number] - Rolls a random number between 1 and [number]");
                commands.add("8ball [question] - Ask the Magic-8-Ball something!");
                commands.add("decide [choice1] [choice2] [choice3] ... - Decide between given choices. Choices split by a space. Infinite amount of choices allowed. Use underscores as spaces.");


                commands.add("**~ ~ ~ Useful ~ ~ ~**");
                commands.add("getinfo [@User] - Gets data about the @User");
                commands.add("botinfo - Shows you information about GattBot like IP and random junk");
                commands.add("log - Gives you the link to join the log channel");
                commands.add("setafk [True/False] - Sets your AFK Status");
                commands.add("isafk [@User] - Is @User afk?");
                commands.add("math [Expression] - Does Math. May not work with all mathematical expressions [disabled for now]");

                // register listener
                api.registerListener(new PersonalMessageReplier());
                api.registerListener(new MessageReplier());
                api.registerListener(new AdminCommands());
                api.registerListener(new UserCommands());
                MiscCommands miscCommands = new MiscCommands();
                MessageBuilder builder = new MessageBuilder();
                builder.append("Caching meme images");
                GattBotChannel.sendMessage(builder.build());
                miscCommands.cacheImages();
                builder = new MessageBuilder();
                builder.append("Cached meme` images");
                GattBotChannel.sendMessage(builder.build());
                api.registerListener(miscCommands);
                builder = new MessageBuilder();
                builder.append("GattBot loaded! There is " + commands.size() + " lines in the commands Array!");
                GattBotChannel.sendMessage(builder.build());
                api.setGame(Settings.getGame());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
        //api.getEventManager().registerListener(new GattBotListener(api));
    }

}
