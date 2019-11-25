package order;

import java.util.Arrays;

/**
 * 基数排序(当要排的数量的时候，由于要创建9个等长的数组，可能会堆溢出)
 * 使用9个一位数组，按0-9排号
 * 将要排序的的数组的元素，从个位开始，个位与9个数组匹配就放入数组
 * 一轮结束后，按序遍历九个数组，将数放回原数组，个位排序完毕
 * 依次是十位，百位。。。直到最大的数字的位数为止，排序完毕
 */
public class RadixSort {

	public static void main(String[] args) {
		int[] arr = {1,2,9,8,5,7};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	// 只支持正数(从小打大)
	public static void sort(int[] arr){
		// 找出数组中最大的数字，求出其位数(即排序需要循环的次数)
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if(max < arr[i]){
				max = arr[i];
			}
		}
		int loopCount = (max+"").length();

		// 创建一个二维数组作为桶，0-9，存储的是要排序的元素，按照个/十/百。。。位排
		int [][] bucket = new int[10][arr.length];
		// 指向桶的长度的指针
		int [] bucketIndex = new int[10];
		//
		int bit = 0;
		for (int i = 0,n=1; i < loopCount; i++,n*=10) {
			for (int j = 0; j < arr.length; j++) { // 遍历一轮arr，将元素放在对应的桶中
				// 求出元素这一轮位数
				bit = (arr[j]/n)%10;
				// 将元素放在下标与该位的值相等的位置
				bucket[bit][bucketIndex[bit]] = arr[j];
				bucketIndex[bit]++; // 指针后移
			}

			// 将该轮放入桶的元素放回数组，即按当前位排好序
			int index = 0;
			for (int j = 0; j < bucket.length; j++) {
				// 当该桶中有值的时候将其放回数组
				if(bucketIndex[j] > 0){
					for (int k = 0; k < bucketIndex[j]; k++) {
						arr[index] = bucket[j][k];
						index++;
					}
				}
				bucketIndex[j] = 0; // 将指针清零，保证下一次按位排序
			}
		}
	}
}
