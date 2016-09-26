package com.jiibngkun.nio;

import java.nio.ByteBuffer;

/**
 * @Description: 缓存区分片
 * @author junjin4838
 * @version 1.0
 */
public class SliceBuffer {

	public static void main(String[] args) {

		//创建一个长度为10的ByteBuffer
		ByteBuffer buffer = ByteBuffer.allocate(10);

		//填充数据，第n个槽放第n个数字
		for (int i = 0; i < buffer.capacity(); ++i) {
			buffer.put((byte) i);
		}

		//创建一个包含槽 3 到槽 6 的子缓冲区
		buffer.position(3);
		buffer.limit(7);
		ByteBuffer slice = buffer.slice();

		//片段 和 缓冲区 共享同一个底层数据数组
		//遍历子缓冲区，将每一个元素乘以 11 来改变它 例如，5 会变成 55
		for (int i = 0; i < slice.capacity(); ++i) {
			byte b = slice.get(i);
			b *= 11;
			slice.put(i, b);
		}

		//查看一下原来buffer的数据
		buffer.position(0);
		buffer.limit(buffer.capacity());

		//结果表明只有在子缓冲区窗口中的元素被改变了
		while (buffer.remaining() > 0) {
			System.out.println(buffer.get());
		}

	}

}
