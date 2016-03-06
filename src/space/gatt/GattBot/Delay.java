package space.gatt.GattBot;

import com.google.common.util.concurrent.AbstractScheduledService;
import de.btobastian.javacord.entities.User;

import java.util.concurrent.TimeUnit;

/**
 * Created by Zach on 6/03/2016.
 */
public class Delay implements Runnable{

    private final TimeUnit unit;
    private final Integer time;
    private final User user;
    private final String msg;

    @Override
    public void run() {
        user.sendMessage("I will notify you in " + time + " " + unit.toString());
        try {
            unit.sleep(time);
            user.sendMessage("Timer is done!");
            user.sendMessage(msg);
        }catch (InterruptedException e){

        }
    }

    public Delay(TimeUnit unit, Integer time, User user, String msg) {
        this.unit = unit;
        this.time = time;
        this.user = user;
        this.msg = msg;

    }
}
