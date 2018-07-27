package com.aia.ui.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

public class DrawImage {

	public static void drawImage(int width, int height)
	{
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g=image.getGraphics();
		g.setColor(new Color(61,157,2));
		g.fillRect(0, 0, width, height);
		
		g.dispose();
		try
		{
			ImageIO.write(image, "JPEG", new FileOutputStream("C:/jason.jpeg"));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		drawImage(60,50);
	}

}
