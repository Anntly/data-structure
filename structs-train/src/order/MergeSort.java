package order;

import java.util.Arrays;

/**
 * 归并排序(分治法)
 * 1. 与快速排序类似，都使用了分治法的思想
 * 2. 将数组递归分组，直到最终两两一组
 * 3. 再排序->合并，直到全部合并完毕
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] arr = {1,-2,9,8,5,7};
		int[] temp = new int[arr.length];
		mergeSort(arr,0,arr.length-1,temp);
		System.out.println(Arrays.toString(arr));
	}


	public static void mergeSort(int[] arr,int left,int right,int[] temp){
		if(left >= right){ // 当left大于等于right的时候代表
			return;
		}
		int mid = (left+right)/2;
		// 向左递归
		mergeSort(arr,left,mid,temp);
		// 向右递归
		mergeSort(arr,mid+1,right,temp);
		// 合并
		merge(arr,left,right,mid,temp);
	}

	/**
	 * 划分的两个子数组进行有序的合并
	 * 两两比较，小的放入temp
	 * @param arr 要排序的数组
	 * @param left 左边数组:左起点 -> mid
	 * @param right 右边数组:mid+1 -> 右终点
	 * @param mid 划分数组的下标
	 * @param temp 用于存放排序数据的临时数组
	 */
	public static void merge(int[] arr,int left,int right,int mid,int[] temp){
		int i = left; // 左边数组的游标
		int j = mid+1; // 右边数组的游标
		int t = 0; // temp数组的游标
		while (i <= mid && j <= right){
			if(arr[i] < arr[j]){ // 将小的放入temp
				temp[t] = arr[i];
				t++;
				i++;
			}else {
				temp[t] = arr[j];
				t++;
				j++;
			}
		}
		// 将剩余的未比较的加入temp
		while (i <= mid){
			temp[t] = arr[i];
			i++;
			t++;
		}

		while (j <= right){
			temp[t] = arr[j];
			j++;
			t++;
		}

		t = 0;
		int tempLeft = left; // 遍历arr的游标
		// 将temp赋值给arr
		while (tempLeft <= right){
			arr[tempLeft] = temp[t];
			tempLeft++;
			t++;
		}
	}
}
