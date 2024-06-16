package com.mashibing.tank.test;

import org.junit.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * 功能:
 * 作者：黄焖鸡
 * 日期：2024-06-16 14:12
 */
public class ImageTest {
    @Test
    public void test(){
        try {
            BufferedImage image = ImageIO.read(new File("C:/Users/hp/Pictures/img13.png"));
            assertNotNull(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
