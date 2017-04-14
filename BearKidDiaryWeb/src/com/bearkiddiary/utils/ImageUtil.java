package com.bearkiddiary.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;

public class ImageUtil {

	/**
	 * 从本地磁盘路径获取图片
	 * @param imgName
	 * @return
	 */
	public static String getImage(String imgName, String imagePath){
		String img = imagePath + "/" + imgName + ".jpg";
		String str_img = null;
		File image = new File(img);
		BufferedImage bi;
		try {
			bi = ImageIO.read(image);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			byte[] bytes = baos.toByteArray();
			
			str_img = Base64.getEncoder().encodeToString(bytes);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str_img;
	}
	
	/**
	 * 保存图片
	 * @param img_str
	 * @param img_name
	 * @return
	 */
	public static boolean saveImage(String img_str, String img_name, String img_path){
		
		String imgFilePath = img_path;
		
		if(img_str == null)
			return false;
		
		try {
			byte[] b = new BASE64Decoder().decodeBuffer(img_str);
			for(int i = 0; i < b.length; ++i){
				if(b[i] < 0)
					b[i] += 256;
			}
			File file = new File(imgFilePath, img_name);
			OutputStream out = new FileOutputStream(file);
			out.flush();
			out.write(b);
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
