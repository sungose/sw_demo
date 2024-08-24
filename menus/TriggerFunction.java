package com.echo.menus;

import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JMenuItem;

import com.echo.services.Window_Menu_sun;

//触发器函数
public class TriggerFunction extends AbstractButtonFunction {
    public void ExecuteFunction(JMenuItem src) {

        try {
            System.out.println("调用触发器业务类");

            // 调用触发器业务类
            int quantity = ThreadLocalRandom.current().nextInt(10) + 1;
            Window_Menu_sun.triggerTestService.insertInBound("ss", quantity);

        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}