package com.java.reflect.merbers.field;

import java.lang.reflect.Field;
        import java.util.Arrays;
        import static java.lang.System.out;

enum Tweedle {DEE, DUM}

public class GetSetFiled {
    public long chapters = 0;
    public String[] characters = {"Alice", "White Rabbit"};
    public Tweedle twin = Tweedle.DEE;

    public static void main(String... args) {
        GetSetFiled book = new GetSetFiled();
        String fmt = "%6S:  %-12s = %s%n";

        try {
            Class<?> c = book.getClass();
            //普通字段设置和访问
            Field chap = c.getDeclaredField("chapters");
            out.format(fmt, "before", "chapters", book.chapters);
            chap.setLong(book, 12);
            out.format(fmt, "after", "chapters", chap.getLong(book));

            //数组字段的设置和访问，改变的是这个数组对象
            Field chars = c.getDeclaredField("characters");
            out.format(fmt, "before", "characters",
                    Arrays.asList(book.characters));
            String[] newChars = {"Queen", "King"};
            chars.set(book, newChars);
            out.format(fmt, "after", "characters",
                    Arrays.asList(book.characters));

            //枚举字段的设置和访问
            Field t = c.getDeclaredField("twin");
            out.format(fmt, "before", "twin", book.twin);
            t.set(book, Tweedle.DUM);
            out.format(fmt, "after", "twin", t.get(book));

            // production code should handle these exceptions more gracefully
        } catch (NoSuchFieldException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        }
    }
}