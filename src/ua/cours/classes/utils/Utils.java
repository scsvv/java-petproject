package ua.cours.classes.utils;

import java.util.Random;

public class Utils {
    public static Random random = new Random();

    public static int getRandInt(int size) {
        return random.nextInt(size);
    }

    public static int getRandInt(int l, int r) {
        return l + random.nextInt(r - l + 1);
    }

    public static String getRandString(int size)
    {
        String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] _tmp = new char[size];
        for(int i = 0; i<_tmp.length;i++)
            _tmp[i]=LETTERS.charAt(getRandInt(LETTERS.length()-1));
        return new String(_tmp);
    }

    public static boolean getRandBool()
    {
        return random.nextBoolean();
    }

}
