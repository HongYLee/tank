package com.mashibing.tank;

import java.awt.*;

/**
 * 功能:
 * 作者：黄焖鸡
 * 日期：2024-06-13 20:29
 */
public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;

    private boolean moving = false;

    public boolean isMoving() {
        return moving;
    }
    public void setMoving(boolean moving){
        this.moving = moving;
    }

    public Tank(int x, int y, Dir dir) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,50,50);
        g.setColor(c);
        move();
    }

    private void move(){
        if(!moving) return;
        switch (dir) {
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


}
