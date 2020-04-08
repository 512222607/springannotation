package mashibing.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.Key;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Handler;

/**
 * @author zhangzm
 * @date 2020/4/8 16:20
 */
public class nio {

	public static void main(String[] args) throws IOException {
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.bind(new InetSocketAddress("127.0.0.1",8080));
		channel.configureBlocking(false);

		System.out.println("server started ...");
		Selector selector = Selector.open();
		channel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			int select = selector.select();
			if (select > 0) {
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey next = iterator.next();
					iterator.remove();
					handle(next);
				}
			}
		}
	}


	public static void handle(SelectionKey selectorkey) {
		if (selectorkey.isAcceptable()) {
			try {
				ServerSocketChannel channel = (ServerSocketChannel) selectorkey.channel();
				SocketChannel socketChannel = channel.accept();
				socketChannel.configureBlocking(false);

				socketChannel.register(selectorkey.selector(), SelectionKey.OP_READ);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		} else if (selectorkey.isReadable()) {
			SocketChannel sc = null;
			try {
				sc = (SocketChannel) selectorkey.channel();
				ByteBuffer buffer = ByteBuffer.allocate(512);
				buffer.clear();
				int read = sc.read(buffer);
				if (read != -1) {
					System.out.println(new String(buffer.array(),0,read));
				}
				ByteBuffer wrap = ByteBuffer.wrap("Hello".getBytes());
				sc.write(wrap);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
