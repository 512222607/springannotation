package mashibing;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author zhangzm
 * @date 2020/4/4 20:47
 */
public class JavaObjectLayout {


	public static void main(String[] args) {

		Object o = new Object();
		String s = ClassLayout.parseInstance(o).toPrintable();
		System.out.println(s);
		synchronized (o) {
			System.out.println(ClassLayout.parseInstance(o).toPrintable());
		}
	}
}
