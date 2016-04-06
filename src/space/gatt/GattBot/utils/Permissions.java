package space.gatt.GattBot.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Zach G on 06-Apr-16.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //can use in method only.
public @interface Permissions {
	public String[] value() default "";
	public boolean adminOnly() default false;
	public String[] ranks() default "";
}
