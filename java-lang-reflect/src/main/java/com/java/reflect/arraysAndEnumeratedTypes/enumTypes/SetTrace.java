package com.java.reflect.arraysAndEnumeratedTypes.enumTypes;



import java.lang.reflect.Field;
        import static java.lang.System.out;

/**
 * 枚举的设置  跟字段设置一样,
 * Field.get(object)
 * Filed.set(object,enum)
 *
 *
 */

public class SetTrace {
    public static void main(String... args) {
        args = new String []{"LOW"};
        TraceLevel newLevel = TraceLevel.valueOf(args[0]);
        try {
            MyServer svr = new MyServer();
            Class<?> c = svr.getClass();
            Field f = c.getDeclaredField("level");
            f.setAccessible(true);
            TraceLevel oldLevel = (TraceLevel) f.get(svr);
            out.format("Original trace level:  %s%n", oldLevel);
            if (oldLevel != newLevel) {
                f.set(svr, newLevel);
                out.format("    New  trace level:  %s%n", f.get(svr));
            }
            // production code should handle these exceptions more gracefully
        } catch (IllegalArgumentException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (NoSuchFieldException x) {
            x.printStackTrace();
        }
    }
}
enum TraceLevel {OFF, LOW, MEDIUM, HIGH, DEBUG}
class MyServer {
    private TraceLevel level = TraceLevel.OFF;
}