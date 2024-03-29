package search;

/**
 * 插值排序
 * 1. 使用二分查找的时候，如果要查找的值越靠近数组的边界的时候，查找的次数就越多
 * 2. 二分查找: mid = left + (right-left)/2
 * 3.                         value - arr[left]
 *   插值查找: mid = left + ----------------------- * (right - left)
 *                         arr[right] - arr[left]
 *   当在边界的时候，只需要一次就可以找到要查找的值
 */
public class InsertValueSearch {

	public static int count = 0;

	public static void main(String[] args) {
		int[] arr = new int[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = i;
		}
		System.out.println(search(arr,0,arr.length-1,8));
		System.out.println("共查询"+count+"次");
	}

	/**
	 *	返回找到的下标
	 * @param arr
	 * @param left
	 * @param right
	 * @param value 查找的值
	 * @return
	 */
	public static int search(int[] arr,int left,int right,int value){
		if(left > right || value < arr[left] || value > arr[right]){
			return -1;
		}
		count++;
		int mid = left + ((value - arr[left])/(arr[right] - arr[left]))*(right - left);
		if(value > arr[mid]){ // 向右递归
			return search(arr,mid+1,right,value);
		}else if(value < arr[mid]){ // 向左递归
			return search(arr,left,mid-1,value);
		}else {
			return mid;
		}
	}
}
