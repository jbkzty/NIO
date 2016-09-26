package com.jiibngkun.nio;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @Description: 
 * @author junjin4838
 * @version 1.0
 */
public class UseCharsets {

	public static void main(String[] args) {
		
		//创建"ISO-8859-1"字符集的一个实例
		Charset latin1 = Charset.forName("ISO-8859-1");
		
		//创建解码器 编码器
		CharsetDecoder decoder = latin1.newDecoder();
		CharsetEncoder encoder = latin1.newEncoder();

	}

}
