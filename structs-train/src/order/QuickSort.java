package order;

import java.util.Arrays;

/**
 * ��������
 * ��������ȡ��һ����׼ֵpivot��ͨ��leftָ����rightָ����pivot���бȽϣ�����pivotС��ֵ������ߣ���ķ����ұ�
 * �ٵݹ�ӻ�׼ֵpivot�������ߵ�����ȡ�µĻ�׼ֵ��������������ֱ��left>right�������׼ֵ���Ҷ�������ˣ�����Ҫ�ڽ��н�����
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] arr = {1,7,-2,9,8,5,7,7,8};
		quickSort(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * ʹ����ӷ�
	 * 1. ����������ߵ�Ԫ��Ϊ��׼ֵ����temp�����ݴ�
	 * 2. ����ߵ�Ԫ�ؾ���һ�����ӡ�(temp�Ѿ��ݴ���ֵ�����Խ�����Ϊû��ֵ�Ŀ�)
	 * 3. ��left��right�ֱ�ָ���������������ҵ�Ԫ��
	 * 4. ����ʹ�õ������Ϊ��׼���ʹ�right--��ʼ���������ֱ���ҵ��Ȼ�׼ֵС�������������롰�ӡ��У�ͬʱleft++(���ڿ��Ѿ���ֵ��)
	 * 5. ��ʱ�ұ��Ǹ�������ߡ��ӡ���λ���γ���һ���µġ��ӡ�����left++���ҽ��б������ҵ�һ���Ȼ�׼ֵ���ֵ���������롰�ӡ���ͬʱright--
	 * 6. ��left=right��ʱ��ʹ�����һ���Ѿ���ɣ����ʱ�򡰿ӡ�Ҳ����left(right)��λ�ã���temp����
	 * 7. �������ӵ�λ�ã��������Ϊ���������֣�������ұ߰���������ʽ�ݹ���У���left>=right��ʱ��ʹ����������
	 */
	// �ָ�����
	public static int partition(int[] arr,int left,int right){
		int pivot = arr[left]; // ȥ����ߵ�Ϊ��׼ֵ
		while(left < right){
			// ��right��������ұ�pivotС��ֵ
			while (arr[right] >= pivot && left < right){ // ��Ҫ�жϵ��ڣ���Ȼ��������ظ���ֵ��ջ���
				right --;
			}
			if(left < right){ // ����ѭ����������һ����arr[right] < pivot,������Ҫ���ж�һ��
				// ����ߵĿ�
				arr[left] = arr[right];
				left ++; // ���ڿ��Լ���ֵ������һλ
			}
			// left��������ұ�pivot���ֵ
			while (arr[left] <= pivot && left < right){
				left ++;
			}

			if(left < right){
				arr[right] = arr[left];
				right --; // right����
			}
		}
		arr[left] = pivot; // ��󽫻�׼ֵ�������Ŀ���
		return left; // ����׼ֵ��λ�÷��أ�֮�����ݹ���
	}

	// ��ӷ���partition  ����ȡ�У�partition1
	public static void quickSort(int[] arr,int left,int right){
		if(arr.length <= 1 || left >= right){ // ��left�ƶ���right��λ�ô����Ѿ��������
			return;
		}
		int mid = partition1(arr,left,right); // ��ȡ��׼ֵ���±�
		// ����ݹ�
		quickSort(arr,left,mid);
		// ���ҵݹ�
		quickSort(arr,mid+1,right);
	}


	/**
	 * ʹ������ȡ�з�
	 * ���㷨�����Կ��Կ����������׼ֵ����ȡ������������м�ֵ��Ч������õ�
	 * �����ҵ���ֵ��Ҫ�ķѺܶ�����
	 * ÿ�εݹ��ʱ��ֱ��ȡleft��right��mid��ֵ���жԱȽ��м��ֵ��Ϊ��׼ֵ�����𵽽���Ч�������Ż�����Ĵ���
	 */
	public static int partition1(int[] arr,int left,int right){
		// ��׼ֵ������ȡ�����һ��
		// ��ȡ�м�λ�õ��±�
		int mid = left+(right-left)/2;
		// ���������С�м��ֵ������left(����Ϊ��׼ֵ)
		if(arr[left] > arr[right]){ // ���ϴ��ֵ�����ƶ�
			swap(arr,left,right);
		}
		if(arr[mid] > arr[right]){ // ���ϴ��ֵ������
			swap(arr,mid,right);
		}
		if(arr[mid] > arr[left]){ // �±�leftȡ�м��ֵ
			swap(arr,left,mid);
		}
		int pivot = arr[left];
		while (left < right){
			while (arr[right] >= pivot && left < right){ // ���ұ��ҵ�С�ڻ�׼ֵ���±�
				right --;
			}
			if(left < right){
				arr[left] = arr[right];
				left ++;
			}

			while (arr[left] <= pivot && left < right){
				left ++;
			}
			if(left < right){
				arr[right] = arr[left];
				right --;
			}
		}
		// ����׼ֵ�������
		arr[left] = pivot;
		return left; // ���ص��ǻ�׼ֵ���±꣬����֮��ĵݹ飬��������
	}

	public static void swap(int[] arr,int left,int right){
		arr[left] = arr[left]^arr[right];
		arr[right] = arr[left]^arr[right];
		arr[left] = arr[left]^arr[right];
	}
}
