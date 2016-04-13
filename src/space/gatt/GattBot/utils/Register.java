package space.gatt.GattBot.utils;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Zach G on 06-Apr-16.
 */
public class Register {

	private static List<Class> listeners = new ArrayList<>();
	private static List<Method> listeningMethods = new ArrayList<>();

	private static HashMap<String, Class> commandRegistrar = new HashMap<>();
	private static HashMap<String, Method> methodRegistrar = new HashMap<>();

	private static List<String> commandList = new ArrayList<>();

	private static HashMap<String, List<String>> helpLines = new HashMap<>();


	public static List<Class> getListeners() {
		return listeners;
	}

	public static List<Method> getListeningMethods() {
		return listeningMethods;
	}

	public static HashMap<String, Class> getCommandRegistrar() {
		return commandRegistrar;
	}

	public static HashMap<String, Method> getMethodRegistrar() {
		return methodRegistrar;
	}

	public static List<String> getCommandList() {
		return commandList;
	}



	/*
	@dir The folder where all your classes is located
	 */

	public static void enableSnooper(String dir){
		Reflections reflections = new Reflections(dir);
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Command.class);
		for (final Class c : classes){
			Annotation[] annotations = c.getAnnotations();
			for (Annotation a : annotations) {
				if (a instanceof Command) {
					String cmd = ((Command)a).value();
					if (!commandList.contains(cmd)) {
						commandList.add(cmd);
						System.out.println("Registered command " + cmd + " for class " + c.getName());
						Method[] methods = c.getDeclaredMethods();
						for (Method method : methods) {
							if (method.isAnnotationPresent(IMethod.class)) {
								if (Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers())) {
									listeners.add(method.getDeclaringClass());
									listeningMethods.add(method);
									commandRegistrar.put(cmd, method.getDeclaringClass());
									methodRegistrar.put(cmd, method);
									System.out.println("Registered method " + method.getName() + " for command " + cmd);
									loadData(c);
								} else {
									throw new IllegalArgumentException(method.getName() + " in " + c.getSimpleName()
											+ " is not public static!");
								}
							}
						}
					}else{
						throw new IllegalArgumentException("The class " + c.getName() + " tried to register the command " + cmd + " a second time!");
					}
				}
			}
		}
	}

	private static void loadData(Class c){
		Annotation[] annotations = c.getAnnotations();
		String group = "null";
		String description = "nul";
		String syntax = "null";
		String[] aliases = new String[]{"null"};
		for (Annotation a : annotations) {
			if (a instanceof Group){
				group = ((Group)a).value();
			}
			if (a instanceof Description){
				description = ((Description)a).value();
			}
			if (a instanceof Syntax){
				syntax = ((Syntax)a).value();
			}
			if (a instanceof Aliases){
				aliases = ((Aliases)a).value();
			}
		}
		if (group != "null" && !group.equalsIgnoreCase("hidden")){

		}

	}


}
