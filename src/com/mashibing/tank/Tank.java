package com.mashibing.tank;

import java.awt.*;

/**
 * 功能:
 * 作者：黄焖鸡
 * 日期：2024-06-13 20:29
 */
public class Tank {
    // 成员变量
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

    private boolean moving = false;
    private TankFrame tf = null;


    public Tank(int x, int y, Dir dir, TankFrame tf) { // 构造器
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }
    public boolean isMoving() {
        return moving;
    }
    public void setMoving(boolean moving){
        this.moving = moving;
    }
    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g){  // 画坦克，颜色，设置坦克的移动方向和速度
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
        }

        move();
    }

    private void move(){  // 设置移动的方向和移动的速度
        if(!moving) return;
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+= SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;

        }
    }


    public void fire() {
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.tf));
    }
}
