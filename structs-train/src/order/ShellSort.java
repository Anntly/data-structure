package order;

import java.util.Arrays;

/**
 * 希尔排序
 * 1. 由于插入排序，当有序列表的数量增多的时候，后面的无序列表的项的值又很小，
 * 	  需要进行移位的元素增多，就会进行许多不必要的移位操作
 * 2. 按照步长进行分组，多次分组来避免这种情况
 */
public class ShellSort {

	public static void main(String[] args) {
		int[] arr = {1,-2,9,8,5,7,32,-10};
		sort2(arr);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 1. 交换法实现(效率低,比冒泡还低)
	 * 循环对length/2得到gap,gap为步长
	 * 按照步长对数组进行分组，步长之间的元素进行交换
	 * 当length循环除2到gap为1的时候，会进行最后一次排序
	 */
	public static void sort1(int[] arr){
		for (int gap = arr.length/2; gap > 0; gap/=2) { // 获取步长
			for (int i = gap; i < arr.length; i++) { // 从下标为(下标0+gap步长)的元素开始遍历比较
				for (int j = i - gap; j >= 0; j -= gap) { // 与之前等步长的元素进行比较
					if(arr[j] > arr[j+gap]){
						arr[j] = arr[j]^arr[j+gap];
						arr[j+gap] = arr[j]^arr[j+gap];
						arr[j] = arr[j]^arr[j+gap];
					}
				}
			}
		}
	}

	/**
	 * 2. 移位法实现(性能优)
	 *    不使用交换，而是将其插入要交换的位置，后面元素后移，步长越小的时候移动越少
	 *  比如进行到   1 3 4 5 6 7 8 9 2 0  要将2排到3前面
	 *  使用交换的方式 每交换一次有三个语句执行，就要执行24次
	 *  而使用移位的方式 就只需要 8+1 = 9次
	 */
	 public static void sort2(int[] arr){
	 	 int index; // 存放被比较的有序部分的下标
	 	 int value; // 存放要比较的无序部分的值
		 for (int gap = arr.length/2; gap > 0 ; gap/=2) {  // 步长
			 for (int i = gap; i < arr.length; i+=gap) {
				value = arr[i];
				index = i - gap;
				while(index >= 0 && arr[index] > value){
					// 有序部分，index下标之后的元素后移
					arr[index+gap] = arr[index];
					index -= gap;
				}
				if(index != i-gap){ // 给要插入的位置赋值
					arr[index+gap] = value;
				}
			 }
		 }
	 }
}
