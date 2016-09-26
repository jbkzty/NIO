package com.jiibngkun.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description: 内部循环 - 使用缓存区将数据从 输入通道 拷贝到 输出通道
 *    clean()
 *    flip()  这两个方法用来切换在缓存区中的读与写操作
 * @author junjin4838
 * @version 1.0
 */
public class FastCopyFile {

	public static void main(String[] args) throws IOException {
		
		String ProjectPath=FastCopyFile.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		
		File file  = new File(ProjectPath + "newFile.txt");
		
		if(!file.exists()){
			file.createNewFile();    
		}
		
		String inFile = ProjectPath + "oldFile.txt";
		String outFile = ProjectPath + "newFile.txt";
		
		FileInputStream fin = new FileInputStream(inFile);
		FileOutputStream fout = new FileOutputStream(outFile);
		
		FileChannel fcin = fin.getChannel();
		FileChannel fcout = fout.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		
		while(true){
			buffer.clear();
			int r = fcin.read(buffer);
			if(r == -1){
				break;
			}
			buffer.flip();
			fcout.write(buffer);
		}
		
	}

}
