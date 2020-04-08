package mashibing;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * @author zhangzm
 * @date 2020/4/5 21:09
 */
public class LockSupport {

	private static Thread t1;

	private static Thread t2;

	public static void main(String[] args) throws FileNotFoundException {

		RandomAccessFile randomAccessFile = new RandomAccessFile("", "");
		char[] a = "123456".toCharArray();
		char[] b = "ABCDEF".toCharArray();

		t1 = new Thread(() -> {
			for (char i : a) {
				System.out.print(i);
				java.util.concurrent.locks.LockSupport.unpark(t2);
				java.util.concurrent.locks.LockSupport.park();
			}
		},"t1");
		t1.start();


		t2 = new Thread(() -> {
			for (char i : b) {
				java.util.concurrent.locks.LockSupport.park();
				System.out.print(i);
				java.util.concurrent.locks.LockSupport.unpark(t1);
			}
		}, "t2");
		t2.start();
	}
}
