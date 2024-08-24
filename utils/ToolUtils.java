package com.echo.utils;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ToolUtils {

    public static int timestampSecond() {
        return Long.valueOf(System.currentTimeMillis() / 1000).intValue();
    }


    public static boolean empty(List<?> list) {
        return null == list || list.isEmpty();
    }

    public static boolean notEmpty(List<?> list) {
        return !empty(list);
    }

    public static List<Integer> getCenterLocation(int width, int height) {
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高

        return Arrays.asList((screenWidth - width) / 2, (screenHeight - height) / 2);
    }

    public static String randomnumber(){
        int num1 = (int)(Math.random()*100);
        if(num1 < 10){
            return "0"+num1;
        }
        else return ""+num1;
    }

    public  static void getCenter(Frame win, int w, int h){
        List<Integer> list= ToolUtils.getCenterLocation(w,h);
        win.setLocation(list.get(0),list.get(1));  // list集合的获取
        win.setSize(w,h);
        win.setVisible(true);
    }
}
