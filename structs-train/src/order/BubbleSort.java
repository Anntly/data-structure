package order;

import java.util.Arrays;

/**
 * 冒泡排序
 * 每次将最大(最小)的数字排到最后
 * 时间复杂度为O(n^2)
 */
public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = {1,-2,9,8,5,7};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void sort(int[] arr){
		boolean flag = false; // 用于判断是否已经排好序
		// 只需要排序arr.length - 1轮就可以排完
		// 因为arr.length - 1个数排好后，剩下的那个数字就是最小(最大)的，就不用再排了
		for (int i = 0; i < arr.length - 1; i++) { // 有小到大
			for (int j = 0; j < arr.length - 1 - i; j++) {
				// 两两对比，第一次只需要对比arr.length -1 -0 次
				// 第二次由于最大的数字已经排在最后就字需要对比 arr.length -1 - 1次，以此类推
				if(arr[j] > arr[j+1]){
					arr[j] = arr[j]^arr[j+1];
					arr[j+1] = arr[j]^arr[j+1];
					arr[j] = arr[j]^arr[j+1];
					flag = true; // 若交换就置为 true
				}
			}
			if(!flag){ // 若一轮排序未进行过交换，就代表已经排好序
				break;
			}else {
				flag = false;
			}
		}
	}
}
