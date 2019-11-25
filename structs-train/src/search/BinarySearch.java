package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 要保证有序且是从小大大
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = new int[]{1,2,3,4,5,6,7};
		List<Integer> search = search1(arr, 0, arr.length - 1, 9);
		System.out.println(search);
	}

	/**
	 * 递归
	 * @param arr
	 * @param left 左指针
	 * @param right 右指针
	 * @param value 要查找的值
	 * @return
	 */
	public static List<Integer> search(int[] arr,int left,int right,int value){
		if(left > right){ // 为查找到值返回一个空的list
			return new ArrayList<>();
		}
		int mid = (left+right)/2;
		if(arr[mid] > value){ // 说明要找的值在左边
			return search(arr,left,mid-1,value);
		}else if(arr[mid] < value){ // 说明在右边
			return search(arr,mid+1,right,value);
		} else {
			int temp = mid - 1;
			List<Integer> result = new ArrayList<>(); // 要保证顺序可以向左遍历存stack里，再遍历pop
			result.add(mid);
			// 相等代表找到，需要向左和右边找相等的其余的值
			while (temp >= 0 && arr[temp] == value){
				result.add(temp);
				temp--;
			}
			// 向右查找
			temp = mid + 1;
			while (temp <= arr.length-1 && arr[temp] == value){
				result.add(temp);
				temp++;
			}
			return result;
		}
	}

	/**
	 * 循环实现
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
			if(value > arr[mid]){ // 向右查找
				left = mid + 1;
			}else if(value < arr[mid]){
				right = mid - 1;
			}else {
				// 即找到
				int temp = mid - 1;
				result.add(mid);
				// 相等代表找到，需要向左和右边找相等的其余的值
				while (temp >= 0 && arr[temp] == value){
					result.add(temp);
					temp--;
				}
				// 向右查找
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
