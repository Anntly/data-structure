package order;

import java.util.Arrays;

/**
 * 插入排序
 * 将要排序的数组分为已排序和未排序两部分
 * 每次从未排序的第一个数，与排序的最后一个数(依次往前)开始比较，如果未排序的数较小就交换位置
 * 效果就是将未排序的数插入到排序好的部分里面，插入之后的元素后移
 */
public class InsertSort {

	public static void main(String[] args) {
		int[] arr = {1,-2,9,8,5,7};
		sort1(arr);
		System.out.println(Arrays.toString(arr));
	}

	// 交换的方式，性能较差
	public static void sort(int[] arr){
		for (int i = 1; i < arr.length; i++) { // 第一次假定下标为0的已排好序,length-i均为未排序
			// 将未排序的第一个元素，依次与排序好的进行比较，如果较小就交换
			for (int j = i; j > 0 && arr[j] < arr[j-1] ; j--) {
				arr[j] = arr[j]^arr[j-1];
				arr[j-1] = arr[j]^arr[j-1];
				arr[j] = arr[j]^arr[j-1];
			}
		}
	}

	// 移位的方式
	public static void sort1(int[] arr){
		int index; // 需要与无序列表第一个元素进行对比的有序列表的下标，从有序列表的最后一个元素开始递减
		int value; // 无序列表第一个元素的值
		for (int i = 1; i < arr.length; i++) {
			index = i - 1;
			value = arr[i];
			while (index >= 0 && value < arr[index]){
				// 如果无序列表第一个值小于有序的index下标的元素，就将index之后的有序元素都后移
				arr[index+1] = arr[index];
				index --;
			}
			if(index != i-1){ // 如果index的值改变了就将值插入到该位置
				arr[index + 1] = value; // 由于退出while的时候会多减1，需要补上
			}
		}
	}
}
