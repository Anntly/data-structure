package search;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ֲ���
 * Ҫ��֤�������Ǵ�С���
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = new int[]{1,2,3,4,5,6,7};
		List<Integer> search = search1(arr, 0, arr.length - 1, 9);
		System.out.println(search);
	}

	/**
	 * �ݹ�
	 * @param arr
	 * @param left ��ָ��
	 * @param right ��ָ��
	 * @param value Ҫ���ҵ�ֵ
	 * @return
	 */
	public static List<Integer> search(int[] arr,int left,int right,int value){
		if(left > right){ // Ϊ���ҵ�ֵ����һ���յ�list
			return new ArrayList<>();
		}
		int mid = (left+right)/2;
		if(arr[mid] > value){ // ˵��Ҫ�ҵ�ֵ�����
			return search(arr,left,mid-1,value);
		}else if(arr[mid] < value){ // ˵�����ұ�
			return search(arr,mid+1,right,value);
		} else {
			int temp = mid - 1;
			List<Integer> result = new ArrayList<>(); // Ҫ��֤˳��������������stack��ٱ���pop
			result.add(mid);
			// ��ȴ����ҵ�����Ҫ������ұ�����ȵ������ֵ
			while (temp >= 0 && arr[temp] == value){
				result.add(temp);
				temp--;
			}
			// ���Ҳ���
			temp = mid + 1;
			while (temp <= arr.length-1 && arr[temp] == value){
				result.add(temp);
				temp++;
			}
			return result;
		}
	}

	/**
	 * ѭ��ʵ��
	 * @param arr
	 * @param left
	 * @param right
	 * @param value
	 * @return
	 */
	public static List<Integer> search1(int[] arr,int left,int right,int value){
		List<Integer> result = new ArrayList<>();
		while (left < right){
			int mid = (left+right)/2;
			if(value > arr[mid]){ // ���Ҳ���
				left = mid + 1;
			}else if(value < arr[mid]){
				right = mid - 1;
			}else {
				// ���ҵ�
				int temp = mid - 1;
				result.add(mid);
				// ��ȴ����ҵ�����Ҫ������ұ�����ȵ������ֵ
				while (temp >= 0 && arr[temp] == value){
					result.add(temp);
					temp--;
				}
				// ���Ҳ���
				temp = mid + 1;
				while (temp <= arr.length-1 && arr[temp] == value){
					result.add(temp);
					temp++;
				}
				break;
			}
		}
		return result;
	}
}
