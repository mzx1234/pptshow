package com.mzx.pptserver.utility;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 描 叙：对象序列化工具类
 * <p>
 * 
 * @author zison
 */

public class SerializeUtil {
	public static byte[] serialize(Object object) {
		if (object != null) {
			ObjectOutputStream oos = null;
			ByteArrayOutputStream baos = null;
			try {
				// 序列化
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				byte[] bytes = baos.toByteArray();
				return bytes;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Object unserialize(byte[] bytes) {
		if (bytes != null && bytes.length > 0) {
			ByteArrayInputStream bais = null;
			try {
				// 反序列化
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static byte[] serializeImg(BufferedImage img) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			javax.imageio.ImageIO.write(img, "jpg", out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out.toByteArray();
	}


	public static BufferedImage unserializeImg(byte[] bytes) {
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		BufferedImage image = null;
		try {
			image = javax.imageio.ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
