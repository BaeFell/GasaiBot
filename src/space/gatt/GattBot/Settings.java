package space.gatt.GattBot;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.btobastian.javacord.entities.Channel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Properties;

/**
 * Created by Zach on 5/03/2016.
 */
public class Settings {

    private static Settings sts = new Settings();

    private static String defaultGroup = "";
    private static String version = "Alpha 1.2";
    private static String profilePicture = null;
    private static String commandStarter = "gasai";
    private static String msgStarter = "» ";
    private static String game = "Being an awesome Bot";

    private static List<Channel> joinMsgChannels = new ArrayList<>();

    public static List<Channel> getJoinMsgChannels() {
        return joinMsgChannels;
    }

    public static void addChannelToStartup(Channel channel){
        if (!joinMsgChannels.contains(channel)){
            joinMsgChannels.add(joinMsgChannels.size(), channel);
            saveSettings();
        }
    }

    public static void removeChannelToStartup(Channel channel){
        if (joinMsgChannels.contains(channel)){
            joinMsgChannels.remove(channel);
            saveSettings();
        }
    }

    public static String getGame() {
        return game;
    }

    public static void setGame(String game) {
        Settings.game = game;
        Settings.saveSettings();
    }

    public static String getMsgStarter() {
        return msgStarter;
    }

    public static void setMsgStarter(String msgStarter) {
        Settings.msgStarter = msgStarter;
        Settings.saveSettings();
    }

    public static String getCommandStarter() {
        return commandStarter;
    }

    public static void setCommandStarter(String str) {
        commandStarter = str;
        Settings.saveSettings();
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String str) {
        version = str;
        Settings.saveSettings();
    }

    public static String getDefaultGroup() {
        return defaultGroup;
    }

    public static void setDefaultGroup(String str) {
        defaultGroup = str;
        Settings.saveSettings();
    }

    private static Boolean loadedSettings = false;

    private static String buildString(){
        return "";
    }

    public static void saveSettings(){
        if (loadedSettings) {
            Properties properties = new Properties();
            properties.setProperty("game", getGame());
            properties.setProperty("msgstarter", getMsgStarter());
            properties.setProperty("version", getVersion());
            String sinpis = "";
            for (String s : Main.senpais) {
                sinpis = sinpis + s + ",";
            }
            properties.setProperty("senpais", sinpis);
            String admoons = "";
            for (String s : Main.adminUsers) {
                admoons = admoons + s + ",";
            }
            properties.setProperty("adminusers", sinpis);
            try {
                File propertiesFile = new File("settings.properties");
                OutputStream is = new FileOutputStream(propertiesFile);
                properties.store(is, "properties");
            } catch (IOException e) {

            }
        }
    }

    public static void loadSettings(){
        Properties properties = new Properties();
        System.out.println("new Properties()");
        InputStream inputStream = Settings.sts.getClass().getResourceAsStream("settings.properties");
        System.out.println("Got InputStream");
        File propertiesFile = new File("settings.properties");

        if (inputStream != null){
            try {
                Boolean createDefaults = false;
                if (!propertiesFile.exists()){
                    propertiesFile.createNewFile();
                    createDefaults = true;
                }
                System.out.println("Loading Properties via inputStream");
                InputStream is = new FileInputStream(propertiesFile);
                properties.load(is);
                if (!properties.containsKey("version")){
                    createDefaults = true;
                }
                if (createDefaults){
                    System.out.println("Setting default values!");
                    properties.setProperty("game", "with Yuki.");
                    properties.setProperty("msgStarter", "»");
                    properties.setProperty("adminusers", "113462564217683968,117785797985435652");
                    properties.setProperty("senpais", "80972065296887808,113462564217683968,117785797985435652,138481382794985472");
                    properties.setProperty("version", "Alpha 1.2");
                }
                System.out.println("Done!");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        System.out.println("Saving Properties to Cache.");
        setGame(properties.getProperty("game"));
        setMsgStarter(properties.getProperty("msgStarter"));
        String[] senpais = properties.getProperty("senpais").split(",");
        for (String s : senpais){
            Main.senpais.add(s);
        }
        System.out.println("Saved Senpais");
        String[] admins = properties.getProperty("adminusers").split(",");
        for (String s : admins){
            Main.adminUsers.add(s);
        }
        System.out.println("Saved Admins");
        System.out.println("Done!");
        loadedSettings = true;
    }
}
