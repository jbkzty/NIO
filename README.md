# NIO 入门

##联网与异步IO
    NIO中的联网也是需要依赖通道和缓冲区，通常使用InputStream和OutputStream来获取得到通 <br/>
    异步的IO是一种没有阻塞的读写数据的方法，通常代码在执行read()方法的时候，代码会阻塞直到有可供读取的数据，同样，write()方法也会阻塞直到数据可以写入 <br/>
    
    NIO不会阻塞的原因在于，我们在程序中会注册对特地IO感兴趣的事件（比如可读数据的到达，套接字的连接），发生这些事件之后，系统会通知 <br/>
    
    异步IO的一个优势是：可以同时执行大量的输入和输出IO.  `同步程序往往需要借助于轮训，或者创建许许多多的线程来处理大量的连接。使用异步的IO,可以监听任意数量通道上的事件，不用轮循，也不需要大量的线程` <br/>
    
    MultiPortEcho.java 是一个简单的Demo <br/>
    
    
##文件锁定
     
     文件锁就像常规的Java对象锁，他们是劝告式的锁，不阻止任何形式的数据访问，相反通过锁的共享和获取依赖允许系统的不同部分互相协调。<br/>
     
     您可以锁定整个文件或者文件的一部分。如果您获取一个排它锁，那么其他人就不能获得同一个文件或者文件的一部分上的锁。如果您获得一个共享锁，那么其他人可以获得同一个文件或者文件一部分上的共享锁，但是不能获得排它锁。文件锁定并不总是出于保护数据的目的。例如，您可能临时锁定一个文件以保证特定的写操作成为原子的，而不会有其他程序的干扰。 <br/>
     
     入门链接： http://www.ibm.com/developerworks/cn/education/java/j-nio/index.html
