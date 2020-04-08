/**
 * @author zhangzm
 * @date 2020/3/28 9:53
 */
public class MargeSort {

	public static void main(String[] args) {
		int[] a = new int[]{14, 17, 1, 8, 3, 31, 22, 28, 26, 5};
		mergeSort(a,0,a.length);
		System.out.println(a);
	}

	public static void mergeSort(int[] numbers,int left,int right) {
		int t = 1;
		int size  = right -left +1;
		while (t < size) {
			int s = t;
			t = 2 * s;
			int i = left;
			while (i + (t - 1) < size) {
				merge(numbers, i, i + (s - 1), i + (t - 1));
				i+=t;
			}
			if (i + (s - 1) < right) {
				merge(numbers, i, i + (s - 1), right);
			}
		}
	}

	private static void merge(int[] data, int p, int q, int r) {
		int[] b = new int[data.length];
		int s = p;
		int t = q+1;
		int k = p;
		while (s <= 1 && t <= r) {
			if (data[s] <= data[t]) {
				b[k] = data[s];
				s++;
			} else {
				b[k] = data[t];
				t++;
			}
			k++;
		}
		if (s == q + 1) {
			b[k++] = data[t++];
		} else {
			b[k++] = data[s++];
		}
		for (int i = p; i <= r; i++) {
			data[i] = b[i];
		}
	}
}
