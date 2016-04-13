package space.gatt.GattBot;

import com.google.common.util.concurrent.FutureCallback;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.MessageBuilder;
import space.gatt.GattBot.utils.Register;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
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
    public static Channel adminLogChannel;

    public static ArrayList<String> adminUsers = new ArrayList<>();
    public static ArrayList<String> senpais = new ArrayList<>();

    private static DiscordAPI api;

    public static long startupTime;

    private static String email, password;

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    private static boolean rebooted = false;

    public static DiscordAPI getApi() {
        return api;
    }

    private static DatabaseUpdater dbu;

    public static DatabaseUpdater getDbu() {
        return dbu;
    }

    public static void main(String[] args) {


        //new Thread(new SocketManager()).start();
        email = args[0];
        password = args[1];
        if (args.length > 2){
            if (args[2].equalsIgnoreCase("reboot")){
                rebooted = true;
            }
        }
        System.out.println("Loading settings...");
        //Settings.loadSettings();
        System.out.println("Attempting login with email " + email);
        senpais.add("80972065296887808");
        senpais.add("113462564217683968");
        senpais.add("117785797985435652");
        senpais.add("138481382794985472");
        adminUsers.add("113462564217683968");

        Register.enableSnooper();

        Date date = new Date();
        startupTime = date.getTime();
        api = Javacord.getApi(email, password);
        api.connect(new FutureCallback<DiscordAPI>() {
            @Override
            public void onSuccess(DiscordAPI api) {
                api.setGame("(∩｀-´)⊃━☆ﾟ.*･｡ﾟ with a wezurd");


                for (Server s : api.getServers()){
                    if (s.getName().equalsIgnoreCase("GattBotServer")){
                        for (Channel c : s.getChannels()){
                            if (c.getName().equalsIgnoreCase("logchannel")){
                                GattBotChannel = c;
                            }
                            if (c.getName().equalsIgnoreCase("admin_log")){
                                adminLogChannel = c;
                            }
                        }
                        break;
                    }
                }

               //ConsoleLogger logger = new ConsoleLogger();
               //new Thread(logger).start();
                dbu = new DatabaseUpdater();
                Timer time = new Timer();
                time.schedule(dbu, 0, 300000); // Create Repetitively task for every 1 secs
                api.setAutoReconnect(true);
                for (Server s : api.getServers()){
                    for (User m : s.getMembers()) {
                        userCache.put(m.getName(), m.getId());
                    }
                }
                commands.add("< help > - Seriously?");
                commands.add("< version > - Returns the version");

                commands.add("~ ~ ~ Fun ~ ~ ~");
                commands.add("< lolis > - Shows you one of the best lolis in the world");
                commands.add("< meme > - It has no memes...");
                commands.add("< ping > - Pong... Really?!");
                commands.add("< noticeme > - Hmph!...");
                commands.add("< falconpunch > [@User] - Fallconnnnnn Punchhhhh!");
                commands.add("< slap > [@User] - Ouch!");
                commands.add("< ddos > [@User] - DD0S th3m u l337 h4x0r");
                commands.add("< nope > - Nope.avi");
                commands.add("< lenny > - ( ͡° ͜ʖ ͡°)");
                commands.add("< hype > - Hypeeeeeeeeeeeee.");
                commands.add("< mindblown > - Boom.");
                commands.add("< kappa > - Kappa.");
                commands.add("< wat > - wat");
                commands.add("< awwyeah > - Awwwww Yeahhhh");
                commands.add("< ohwhale > - Oh Whale");
                commands.add("< facepalm > - Why?");
                commands.add("< triplefacepalm > - Why? Why? Why");
                commands.add("< wow > - Wow.");
                commands.add("< git > - Sends you the Git Repo");
                commands.add("< time > - Good Heavens! Look at the time!");
                commands.add("< uptime > - Sends the Uptime for GasaiBot.");

                commands.add("< sponsor > - Hmm...");

                commands.add("~ ~ ~ Misc ~ ~ ~");

                commands.add("< 1v1 > [@User1] [@User2] - 1v1 me, scrub");
                commands.add("< fightclub > [@User1] [@User2] [@User3] ... - Fight! Infinite number of fighters allowed.");
                commands.add("< lmgtfy >[@User] [Search] - Let me google that for you ;)");
                commands.add("< lmbtfy > [@User] [Search] - Let me bing that for you... for whatever reason?");
                commands.add("< timer > [seconds|minutes|hours] [amount] [message] - Will PM in [amount] of [seconds|minutes|hours] the [message] you specify privately.");
                commands.add("< roll > - Rolls a random number between 1 and 100");
                commands.add("< roll > [number] - Rolls a random number between 1 and [number]");
                commands.add("< 8ball > [question] - Ask the Magic-8-Ball something!");
                commands.add("< decide > [choice1] [choice2] [choice3] ... - Decide between given choices. Choices split by a space. Infinite amount of choices allowed. Use underscores as spaces.");


                commands.add("~ ~ ~ Useful ~ ~ ~");
                commands.add("< getinfo > [@User] - Gets data about the @User");
                commands.add("< botinfo > - Shows you information about GasaiBot like IP and random junk");
                commands.add("< log > - Gives you the link to join the log channel");
                commands.add("< setafk > [True/False] - Sets your AFK Status");
                commands.add("< isafk > [@User] - Is @User afk?");
                commands.add("< math > [Expression] - Does Math. May not work with all mathematical expressions [disabled for now]");

                // register listener
                api.registerListener(new PersonalMessageReplier());
                api.registerListener(new MessageReplier());
                api.registerListener(new AdminCommands());
                api.registerListener(new CommandListener());
                MiscCommands miscCommands = new MiscCommands();
                GattBotChannel.sendMessage("`__________________________________________________`");
                GattBotChannel.sendMessage("Caching meme images");
                miscCommands.cacheImages();
                GattBotChannel.sendMessage("Cached meme` images");
                GattBotChannel.sendMessage("Cached roughly " + userCache.keySet().size() + " users.");
                api.registerListener(miscCommands);
                GattBotChannel.sendMessage("GasaiBot loaded! There is " + commands.size() + " lines in the commands Array!");
                if (rebooted){
                    GattBotChannel.sendMessage("GasaiBot has successfully finished rebooting!");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();

            }
        });
        //api.getEventManager().registerListener(new GattBotListener(api));
    }

}
