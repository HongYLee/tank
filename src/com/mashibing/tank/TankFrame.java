package com.mashibing.tank;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 功能:
 * 作者：黄焖鸡
 * 日期：2024-06-07 23:31
 */
public class TankFrame extends Frame {
    Tank myTank = new Tank(200,200,Dir.DOWN,this);  // 创建一个tank的类，大小为200，200的矩形方块，方向向下
    List<Bullet> bullets = new ArrayList<>();
    Bullet b = new Bullet(300,300,Dir.DOWN, this); // 创建一个子弹的类，大小为300，300的圆形，方向向下
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;  // 定义一个矩形画布的长和宽
    public TankFrame(){ // 构造器
        setSize(GAME_WIDTH, GAME_HEIGHT);  // 设置一个长为GAME_HEIGHT，宽为GAME_WIDTH的方框
        setResizable(false);
        setTitle("Tank War"); // 方框的标题为Tank War
        setVisible(true); // 显示方框
        this.addKeyListener(new MyKeyListener()); // 监听
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    Image offScreenImage = null; // 定义一张图片在缓存里
    @Override
    public void update(Graphics g){ // 更新
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        // 把背景重新画一遍
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        // 调用paint
        paint(gOffScreen);
        // 用屏幕画笔将缓存中的整张图片复制过来
        g.drawImage(offScreenImage,0,0,null);
    }
    @Override
    public void paint(Graphics g){ // 画坦克和画子弹
        Color c= g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("The number of bullets: " + bullets.size(),10,60);
        g.setColor(c);
        myTank.paint(g);
        for(int i = 0; i < bullets.size(); i++){
            // 迭代器迭代的时候中途不能增删
            // 消除子弹列表的内存泄露问题，小心处理迭代器中的删除问题
            // 1、使用普通方式迭代  2、在迭代过程中删除（iterator.remove）
            bullets.get(i).paint(g);
        }
    }
    class MyKeyListener extends KeyAdapter {  // 键盘监听
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire(); //打出一颗子弹
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        private void setMainTankDir(){
            if(!bL && !bU && !bR && !bD) myTank.setMoving(false);
            else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}
