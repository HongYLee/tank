package com.mashibing.tank;

import java.awt.*;
import java.util.Random;

/**
 * 功能:
 * 作者：黄焖鸡
 * 日期：2024-06-13 20:29
 */
public class Tank {
    // 成员变量
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 2;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
    Rectangle rect = new Rectangle();
    private Random random = new Random();
    private Group group = Group.BAD; // 我方坦克 or 敌军坦克
    private boolean moving = true;
    private TankFrame tf = null;
    private boolean living = true;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) { // 构造器
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    private void randomDir(){ // 设置敌军坦克可以随意调整方向（判断我方坦克不要随意调整方向）
        if(this.group == Group.GOOD) return;
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void paint(Graphics g){  // 画坦克，颜色，设置坦克的移动方向和速度
        if(!living) tf.tanks.remove(this);

        switch (dir) {
            case LEFT:
                g.drawImage(this.group ==  Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group ==  Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null);
                break;
            case UP:
                g.drawImage(this.group ==  Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group ==  Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null);
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
        // 如果是敌军，随机发射一些炮弹
        if(this.group ==  Group.BAD && random.nextInt(100) > 95){
            this.fire();
            randomDir();}
        // 做一个边界检测
        boundsCheck();
        // update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if(this.x < 2) x = 2;
        if(this.y < 28)   y = 28;
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH)  x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT)  y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
    }


    public void fire() {
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tf.bullets.add(new Bullet(bX, bY, this.dir,this.group, this.tf));
    }

    public void die() {
        this.living = false;
    }
}
