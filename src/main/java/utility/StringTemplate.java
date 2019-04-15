package utility;

import org.stringtemplate.v4.ST;

public class StringTemplate {
    public static String Hello(String name){
        ST hello = new ST("Hello, <name>!");
        hello.add("name", name);
        return hello.render();
    }
    public static String Meeting(String name){
        ST meeting = new ST("Hello, <name>! Don't forget about tomorrow's meeting.");
        meeting.add("name", name);
        return meeting.render();
    }
}
