package com.jiibngkun.nio;

import java.nio.ByteBuffer;

/**
 * 类型化的get() 与 set() 方法
 * ByteBuffer 可以写不同类型的值
 * @author junjin4838
 * @version 1.0
 */
public class TypesInByteBuffer {

	public static void main(String[] args) {
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		
		byteBuffer.putInt(1);
		byteBuffer.putFloat(1000L);
		byteBuffer.putDouble(Math.PI);
		
		byteBuffer.flip();
		
		System.out.println(byteBuffer.getInt());
		System.out.println(byteBuffer.getFloat());
		System.out.println(byteBuffer.getDouble());
		
	}

}
