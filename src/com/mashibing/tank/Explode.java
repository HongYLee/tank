package com.mashibing.tank;

import java.awt.*;

/**
 * 功能:
 * 作者：黄焖鸡
 * 日期：2024-06-13 21:06
 */
public class Explode {
    private static final int SPEED = 10;
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x, y;
    TankFrame tf = null;
    private int step = 0;
    private boolean living = true;
    public Explode(int x, int y, TankFrame tf){
        this.x = x;
        this.y = y;
        this.tf = tf;
    }



    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step >= ResourceMgr.explodes.length)
            step = 0;
    }



}
