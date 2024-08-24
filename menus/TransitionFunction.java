package com.echo.menus;

import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JMenuItem;

import com.echo.services.Window_Menu_sun;

//触发事务
public class TransitionFunction extends AbstractButtonFunction {
    public void ExecuteFunction(JMenuItem src) {
        System.out.println("调用事务业务类");

        // 调用事务业务类
        int quantity = ThreadLocalRandom.current().nextInt(10) + 1;
        Window_Menu_sun.transactionTestService.insertOutBound("ss", quantity);
    }
}