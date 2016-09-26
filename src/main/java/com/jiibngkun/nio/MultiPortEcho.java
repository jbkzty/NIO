package com.jiibngkun.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO有个非常重要的应用
 * NIO的联网与NIO中的其他运用没有什么不同的地方--依赖通道和缓存区，我们使用InputStream和OutputStream来获取通道
 * 
 * 
 * 在实际场景的运用中，会使用线程池来负责IO处理中耗时的部分
 * @author junjin4838
 * @version 1.0
 */
public class MultiPortEcho {

	private int[] ports;

	private ByteBuffer echoBuffer = ByteBuffer.allocate(1024);
	
	public MultiPortEcho(int posts[]) throws IOException{
		this.ports = posts;
		go();
	}

	public void go() throws IOException {

		/**
		 * 创建selector 然后我们可以将对不同的通道对象调用register()方法 以便注册我们对这些对象中发生的 I/O 事件的兴趣。
		 * register() 的第一个参数总是这个Selector。
		 */
		Selector selector = Selector.open();

		/**
		 * 打开一个ServerSocketChannel 监听的每一个端口都需要有一个ServerSocketChannel
		 */
		for (int i = 0; i < ports.length; ++i) {

			ServerSocketChannel ssc = ServerSocketChannel.open();
			ssc.configureBlocking(false);

			// socket - 绑定给定的端口
			ServerSocket ss = ssc.socket();
			InetSocketAddress address = new InetSocketAddress(ports[i]);
			ss.bind(address);

			// 将新打开的 ServerSocketChannels 注册到 Selector上,监听 accept
			// 事件，也就是在新的连接建立时所发生的事件。
			SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("Going to listen on " + ports[i]);
		}

		// 使用 Selectors 的几乎每个程序都像下面这样使用内部循环
		while (true) {
			int num = selector.select();
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator it = selectedKeys.iterator();
			while (it.hasNext()) {
				SelectionKey key = (SelectionKey) it.next();
				if((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
					//连接新的连接
					ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
					SocketChannel sc = ssc.accept();
					sc.configureBlocking( false );
					
					SelectionKey newKey = sc.register( selector, SelectionKey.OP_READ );
					it.remove();
					System.out.println( "Got connection from "+sc );
				}else if((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ){
					//读数据
					SocketChannel sc = (SocketChannel)key.channel();
					//回写数据
					int bytesEchoed = 0;
					while(true){
						echoBuffer.clear();
						int r = sc.read( echoBuffer );
						if(r < 0){
							break;
						}
						echoBuffer.flip();
						sc.write( echoBuffer );
						bytesEchoed += r;
					}
					System.out.println( "Echoed "+bytesEchoed+" from "+sc );
					it.remove();
				}
			}

		}

	}

	public static void main(String[] args) throws IOException {
		
		int ports[] = {8080,8082,8089,3036,5227};
		
		new MultiPortEcho(ports);

	}

}
