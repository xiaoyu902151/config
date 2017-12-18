package net.framework.httpModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class GenerateImg {
	
	private static Logger logger = Logger.getLogger(GenerateImg.class);
	// 定义图片的高度和宽度
	private static int WIDTH = 60;
	private static int HEIGHT = 20;

	public byte [] getImg(HttpSession session) {
		// 在内存中创建一张图片
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		// 获取到图片
		Graphics g = image.getGraphics();
		// 生成随机码数据
		char[] rands = generateCheckCode();
		// 设置图片背景并生成干扰点
		drawBackground(g);
		// 在不同的高度上显示不同的字符数据
		drawRands(g, rands);
		// 绘制结束，释放资源
		g.dispose();

		// 将图像输出到客户端
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			// 将图片输出到字节数组输出流中
			ImageIO.write(image, "JPEG", bos);
			// 从字节数组输出流中取出数据
			//将当前验证码存入到Session中
	        session.setAttribute("check_code",new String(rands)); 
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("构造验证图失败", e);
		}
	}

	private char[] generateCheckCode() {
		// 定义验证码的字符表
		String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
		char[] rands = new char[4];
		for (int i = 0; i < 4; i++) {
			// 10个数字+26个字母
			int rand = (int) (Math.random() * 36);
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}

	private void drawRands(Graphics g, char[] rands) {
		// 设置字体颜色
		g.setColor(Color.BLACK);
		g.setFont(new Font(null,  Font.BOLD, 18));
		// 在不同的高度上输出验证码的每个字符
		g.drawString("" + rands[0], 1, 16);
		g.drawString("" + rands[1], 16, 16);
		g.drawString("" + rands[2], 32, 16);
		g.drawString("" + rands[3], 47, 16);
		//logger.info("本次验证码为：" + rands);
	}

	private void drawBackground(Graphics g) {
		// 画背景
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// 随机产生120个干扰点
		for (int i = 0; i < 120; i++) {
			int x = (int) (Math.random() * WIDTH);
			int y = (int) (Math.random() * HEIGHT);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(x, y, 0, 0);
		}
	}
}