package search;

/**
 * ��ֵ����
 * 1. ʹ�ö��ֲ��ҵ�ʱ�����Ҫ���ҵ�ֵԽ��������ı߽��ʱ�򣬲��ҵĴ�����Խ��
 * 2. ���ֲ���: mid = left + (right-left)/2
 * 3.                         value - arr[left]
 *   ��ֵ����: mid = left + ----------------------- * (right - left)
 *                         arr[right] - arr[left]
 *   ���ڱ߽��ʱ��ֻ��Ҫһ�ξͿ����ҵ�Ҫ���ҵ�ֵ
 */
public class InsertValueSearch {

	public static int count = 0;

	public static void main(String[] args) {
		int[] arr = new int[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = i;
		}
		System.out.println(search(arr,0,arr.length-1,8));
		System.out.println("����ѯ"+count+"��");
	}

	/**
	 *	�����ҵ����±�
	 * @param arr
	 * @param left
	 * @param right
	 * @param value ���ҵ�ֵ
	 * @return
	 */
	public static int search(int[] arr,int left,int right,int value){
		if(left > right || value < arr[left] || value > arr[right]){
			return -1;
		}
		count++;
		int mid = left + ((value - arr[left])/(arr[right] - arr[left]))*(right - left);
		if(value > arr[mid]){ // ���ҵݹ�
			return search(arr,mid+1,right,value);
		}else if(value < arr[mid]){ // ����ݹ�
			return search(arr,left,mid-1,value);
		}else {
			return mid;
		}
	}
}
