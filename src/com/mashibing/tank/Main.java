package com.mashibing.tank;

/**
 * 功能:
 * 作者：黄焖鸡
 * 日期：2024-06-13 16:14
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        while(true){
            Thread.sleep(50);
            tf.repaint(); // repaint首先会调用update，再调用paint
        }
    }
}
