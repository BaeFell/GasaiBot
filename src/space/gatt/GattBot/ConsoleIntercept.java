package space.gatt.GattBot;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.MessageBuilder;

import java.io.*;
import java.util.Locale;

/**
 * Created by Zach G on 13-Apr-16.
 */
public class ConsoleIntercept extends PrintStream {

	private DiscordAPI api = Main.getApi();

	public ConsoleIntercept(OutputStream out) {
		super(out);
	}

	public ConsoleIntercept(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
	}

	public ConsoleIntercept(OutputStream out, boolean autoFlush, String encoding) throws UnsupportedEncodingException {
		super(out, autoFlush, encoding);
	}

	public ConsoleIntercept(String fileName) throws FileNotFoundException {
		super(fileName);
	}

	public ConsoleIntercept(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
		super(fileName, csn);
	}

	public ConsoleIntercept(File file) throws FileNotFoundException {
		super(file);
	}

	public ConsoleIntercept(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
		super(file, csn);
	}

	@Override
	public void flush() {
		super.flush();
	}

	@Override
	public void close() {
		super.close();
	}

	@Override
	public boolean checkError() {
		return super.checkError();
	}

	@Override
	protected void setError() {
		super.setError();
	}

	@Override
	protected void clearError() {
		super.clearError();
	}

	@Override
	public void write(int b) {
		super.write(b);
	}

	@Override
	public void write(byte[] buf, int off, int len) {
		super.write(buf, off, len);
	}

	@Override
	public void print(boolean b) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + b + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.print(b);
	}

	@Override
	public void print(char c) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + c + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.print(c);
	}

	@Override
	public void print(int i) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + i + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.print(i);
	}

	@Override
	public void print(long l) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + l + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.print(l);
	}

	@Override
	public void print(float f) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + f + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.print(f);
	}

	@Override
	public void print(double d) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + d + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.print(d);
	}

	@Override
	public void print(char[] s) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System >").appendNewLine().append("```");
		for (char c : s){
			builder.append(String.valueOf(c)).appendNewLine();
		}
		builder.append("```");
		Main.adminLogChannel.sendMessage(builder.build());
		super.print(s);
	}

	@Override
	public void print(String s) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + s + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.print(s);
	}

	@Override
	public void print(Object obj) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + obj.toString() + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.print(obj);
	}

	@Override
	public void println() {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System >");
		Main.adminLogChannel.sendMessage(builder.build());
		super.println();
	}

	@Override
	public void println(boolean x) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + x + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.println(x);
	}

	@Override
	public void println(char x) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + x + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.println(x);
	}

	@Override
	public void println(int x) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + x + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.println(x);
	}

	@Override
	public void println(long x) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + x + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.println(x);
	}

	@Override
	public void println(float x) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + x + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.println(x);
	}

	@Override
	public void println(double x) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + x + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.println(x);
	}

	@Override
	public void println(char[] x) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System >").appendNewLine().append("```");
		for (char c : x){
			builder.append(String.valueOf(c)).appendNewLine();
		}
		builder.append("```");
		Main.adminLogChannel.sendMessage(builder.build());
		super.println(x);
	}

	@Override
	public void println(String x) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + x + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.println(x);
	}

	@Override
	public void println(Object x) {
		MessageBuilder builder = new MessageBuilder();
		builder.append("System > `" + x.toString() + "`");
		Main.adminLogChannel.sendMessage(builder.build());
		super.println(x);
	}

	@Override
	public PrintStream printf(String format, Object... args) {
		return super.printf(format, args);
	}

	@Override
	public PrintStream printf(Locale l, String format, Object... args) {
		return super.printf(l, format, args);
	}

	@Override
	public PrintStream format(String format, Object... args) {
		return super.format(format, args);
	}

	@Override
	public PrintStream format(Locale l, String format, Object... args) {
		return super.format(l, format, args);
	}

	@Override
	public PrintStream append(CharSequence csq) {
		return super.append(csq);
	}

	@Override
	public PrintStream append(CharSequence csq, int start, int end) {
		return super.append(csq, start, end);
	}

	@Override
	public PrintStream append(char c) {
		return super.append(c);
	}
}
