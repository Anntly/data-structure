package search;

import java.util.Arrays;

/**
 * 쳲��������ҷ�
 * 1. 쳲��������У��������ֵ����������������ı�Խ�ӽ��ƽ����
 * 2. f[k] = f[k-1] + f[k-2]
 * 3. f[k] - 1 = f[k-1] - 1 + f[k-2] - 1 + 1 ��һ����Ϊ�����±��0��ʼ
 * 4. mid = low + f[k-1] - 1
 */
public class FibonacciSearch {

	private static int MAX = 20;

	public static void main(String[] args) {
		int[] arr = new int[]{1,2,3,5,8,9,12,16,17,19,22};
		System.out.println(search(arr,16));
	}

	// ��ȡ쳲���������,ֻ��Ҫ��֤Ҫ���ҵ����鳤��С����߽�ֵ����
	// ȡ20λ
	public static int[] fib(){
		int [] fib = new int[MAX];
		fib[0] = 1;
		fib[1] = 1;
		for (int i = 2; i < MAX; i++) {
			fib[i] = fib[i-1]+fib[i-2];
		}
		return fib;
	}

	/**
	 *
	 * @param arr ��ѯ������
	 * @param value ��ѯ��ֵ
	 * @return
	 */
	public static int search(int[] arr,int value){
		int low = 0;
		int high = arr.length-1;
		int k = 0; // ָ��쳲��������е�ָ��
		int [] fib = fib();
		// ��fib�������ҳ����ڵ������鳤�ȵĵ�һ����
		while (fib[k] - 1 < high){
			k++;
		}
		// ������鳤�Ȳ��㣬��high�±��ֵ�������
		int[] temp = Arrays.copyOf(arr,fib[k]);
		for (int i = high + 1; i < arr.length - 1; i++) {
			temp[i] = arr[high];
		}

		while (low < high){
			int mid = low + fib[k - 1] - 1; // �ȵ��ƽ�ָ���±�,fib[k]�������ұߵı߽�
			if(arr[mid] > value){ // ���Ҫ���ҵ�ֵ�����
				high = mid - 1;
				k--; // �����ѯ��high�����fib[k-1],k��Ҫ�����ƶ�һλ
			}else if(arr[mid] < value) {  // ����
				low = mid + 1;
				k -= 2;  // ���Ҳ�ѯ�������ұߵ�ֵΪfib[k-2],����kҪ�����ƶ���λ
			}else {
				if(mid > high){ // ����鵽high֮�⣬���ظ��Ĳ��֣�ֱ�ӷ���high�����ֵ
					return high;
				}else {
					return mid;
				}
			}
		}
		// δ�ҵ��ͷ���-1
		return -1;
	}
}
