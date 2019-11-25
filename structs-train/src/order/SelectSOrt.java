package order;

import java.util.Arrays;

/**
 * 选择排序
 * 每轮排序将最小值与每轮排序第一个元素进行交换
 * 第一轮最小值交换到下标0，第二轮存储到下标1...
 */
public class SelectSOrt {
	public static void main(String[] args) {
		int[] arr = {1,-2,9,8,5,7};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void sort(int[] arr){
		int index = 0; // 用于存储最小值的下标
		// 由于最后一轮只有最大的数字，就不用进行排序，只需要排序arr.length - 1次
		for (int i = 0; i < arr.length - 1; i++) {
			index = i; // 假定最小值为第一个
			for (int j = i + 1; j < arr.length; j++) { // 要与最后一个数进行比较，需要小于arr.length
				if (arr[j] < arr[index]){
					index = j; // 当有比最小值小的，就修改最小值的下标
				}
			}
			if(index != i){ // 如果最小值的下标改变，表示有比该轮第一个值还小的数，就交换下标
				arr[i] = arr[i]^arr[index];
				arr[index] = arr[i]^arr[index];
				arr[i] = arr[i]^arr[index];
			}
		}
	}
}
