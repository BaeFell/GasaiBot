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
    private static String version = "";
    private static String profilePicture = null;
    private static String commandStarter = "/";
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
        Settings.game = game;Settings.saveSettings();
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

    private static String buildString(){
        return "";
    }

    public static void saveSettings(){
        Properties properties = new Properties();
        properties.setProperty("game", getGame());
        properties.setProperty("msgstarter", getMsgStarter());
        properties.setProperty("version", getVersion());
        String sinpis = "";
        for (String s : Main.senpais){
            sinpis = sinpis + s + ",";
        }
        properties.setProperty("senpais", sinpis);
        String admoons = "";
        for (String s : Main.adminUsers){
            admoons = admoons + s + ",";
        }
        properties.setProperty("adminusers", sinpis);
        try {
            properties.store(System.out, "properties");
        }catch (IOException e){

        }
    }

    public static void loadSettings(){
        Properties properties = new Properties();
        InputStream inputStream = System.in;
        if (inputStream != null){
            try {
                properties.load(inputStream);
            }catch (IOException e){
            }
        }
        setGame(properties.getProperty("game"));
        setMsgStarter(properties.getProperty("msgStarter"));
        setVersion(properties.getProperty("version"));
        String[] senpais = properties.getProperty("senpais").split(",");
        for (String s : senpais){
            Main.senpais.add(s);
        }
        String[] admins = properties.getProperty("adminusers").split(",");
        for (String s : admins){
            Main.adminUsers.add(s);
        }
    }
}
