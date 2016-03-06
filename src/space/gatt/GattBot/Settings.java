package space.gatt.GattBot;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.btobastian.javacord.entities.Channel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * Created by Zach on 5/03/2016.
 */
public class Settings {

    private static String defaultGroup = "";
    private static String version = "Alpha 0.8";
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
        Settings.game = game;
    }

    public static String getMsgStarter() {
        return msgStarter;
    }

    public static void setMsgStarter(String msgStarter) {
        Settings.msgStarter = msgStarter;
    }

    public static String getCommandStarter() {
        return commandStarter;
    }

    public static void setCommandStarter(String str) {
        commandStarter = str;
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String str) {
        version = str;
    }

    public static String getProfilePicture() {
        return profilePicture;
    }

    public static void setProfilePicture(String str) {
        profilePicture = str;
    }

    public static String getDefaultGroup() {

        return defaultGroup;
    }

    public static void setDefaultGroup(String str) {
        defaultGroup = str;
    }

    private static String buildString(){
        return "";
    }

    private static void saveSettings(){
        /*File settings = new File(System.getProperty("user.dir") + "/gattbotsettings.json");
        if (!settings.exists()) {
            try {
                settings.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Saving settings to " + settings);

        try (Writer writer = new FileWriter(System.getProperty("user.dir") + "/gattbotsettings.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(getJoinMsgChannels(), writer);
            String json = new Gson().toJson(getJoinMsgChannels());
            System.out.println(json);
        }catch (IOException e){
            e.printStackTrace();
        }*/

    }
    public static void loadSettings(){
        /*File settings = new File(System.getProperty("user.dir") + "/gattbotsettings.json");
        if (!settings.exists()){
            try {
                settings.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        boolean fileExists = true;
        try {
            BufferedReader br = new BufferedReader(new FileReader(settings));
            if (br.readLine() == null) {
                fileExists = false;
            }
        }catch (IOException e){

        }
        if (fileExists) {
            try (Reader reader = new FileReader(System.getProperty("user.dir") + "/gattbotsettings.json")) {
                Gson gson = new GsonBuilder().create();
                Type type = new TypeToken<List<Channel>>(){}.getType();
                Settings.joinMsgChannels = gson.fromJson(reader, type);
                System.out.println(getJoinMsgChannels().size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}
