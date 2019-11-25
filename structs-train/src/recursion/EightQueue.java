package recursion;

/**
 * 八皇后问题
 * 1. 使用一位数组来代替二维数组进行存储,下标代表第几行即第几个棋子-1，value代表第几列-即摆放的位置-1
 */
public class EightQueue {

	private int MAX; // max*max大的棋盘
	private int[] arr;
	private int count; // 共有多少种解法

	public static void main(String[] args) {
		EightQueue eightQueue = new EightQueue(8);
		eightQueue.play(0); // 从第一个棋子开始下
		System.out.println("共有:"+eightQueue.count+"种解法");
	}

	public EightQueue(int n){
		MAX = n;
		arr = new int[MAX];
	}

	// 下棋
	public void play(int n){
		if(n == MAX){ // 当n=8的时候，代表之前8个棋子已经下完
			print(); //打印结果
			count++;
			return;
		}
		// 对于第n个棋子，按列进行遍历
		for (int i = 0; i < MAX; i++) {
			arr[n] = i; // 摆放棋子，第一次就在第一列，之后累加
			if(isSuccess(n)){ // 判断摆放的棋子，位置是否冲突
				play(n+1); // 如果不冲突，就摆放下一个棋子
			}
			// 如果冲突，就继续遍历到不冲突的位置
			// 等栈弹出add(n+1)执行到此的时候，继续循环执行之后的循环节点判断其他位置，是否冲突
			// 如上所述，依次往前的节点进行回溯，就可以获取到所有不重复的解了
		}

	}

	// 判断第n个棋子摆放是否冲突(是否同一行/列/斜线)
	public boolean isSuccess(int n){
		// 由于各个棋子都不在一个下标，不需要判断是否同一行
		// arr[i] == arr[n] 代表与之前的棋子在同一列
		// Math.abs(n-i) == Math.abs(arr[n]-arr[i]) 代表与之前的棋子在同一斜线上(横坐标之差，等于纵坐标之差)
		for (int i = 0; i < n; i++) {
			if(arr[i] == arr[n] || Math.abs(n-i) == Math.abs(arr[n]-arr[i])){
				return false;
			}
		}
		return true;
	}

	// 打印结果
	public void print(){
		for (int i = 0; i < arr.length; i++) {
			System.out.print((arr[i]+1)+"  ");
		}
		System.out.println();
	}

	public int getCount() {
		return count;
	}
}
