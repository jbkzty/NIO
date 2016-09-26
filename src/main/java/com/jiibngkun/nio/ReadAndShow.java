package com.jiibngkun.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description: nio 读取文件
 * @author junjin4838
 * @version 1.0
 */
public class ReadAndShow {

	public static void main(String[] args) throws IOException {
		
		String ProjectPath=ReadAndShow.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		
		/**
		 * Step1: 获取通道
		 */
		FileInputStream fin = new FileInputStream(ProjectPath + "/readandshow.txt");
		FileChannel fc = fin.getChannel();
		
		/**
		 * Step2: 创建缓存
		 */
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		/**
		 * Step3: 将数据从通道中读取到缓存
		 */
		fc.read(buffer);
		
		buffer.flip();
		
		int i = 0;
		while(buffer.remaining()>0){
			Byte b = buffer.get();
			System.out.println("Character : " + i + b);
			i++;
		}
		
		fin.close();
		
		

	}

}
