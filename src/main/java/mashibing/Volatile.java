package mashibing;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangzm
 * @date 2020/4/4 21:44
 */
public class Volatile {

	boolean runnint = true;

	void m() {
		System.out.println("m start");
		while (runnint) {

		}
		System.out.println("m end");
	}

	public static void main(String[] args) {
		Volatile t = new Volatile();
		new Thread(t::m, "t1").start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.runnint = false;
		System.out.println(t.runnint);
	}
}
