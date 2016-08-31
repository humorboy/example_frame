package com.example.rjh.exampleframe.ui;

/**
 * @author rjh
 * @Description:测试接口
 * @version:${MODULE_VERSION}
 * @FileName:InterfaceTest
 * @Package com.example.rjh.exampleframe.ui
 * @Date 2016/8/31 15:54
 */
public abstract class InterfaceTest {
    public InterfaceTest(){
        printLog("好好学习，天天向上");
    }

    abstract public void printLog(String msg);
}
