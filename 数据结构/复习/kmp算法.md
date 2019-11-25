#### 介绍
1. KMP是一个解决模式串在文本串是否出现过，如果出现过，最早出现的位置的经典算法
2. Knuth-Morris-Pratt 字符串查找算法，简称为 “KMP算法”，常用于在一个文本串S内查找一个模式串P 的出现位置，这个算法由Donald Knuth、Vaughan Pratt、James H. Morris三人于1977年联合发表，故取这3人的姓氏命名此算法.
3. KMP方法算法就利用之前判断过信息，通过一个next数组，保存模式串中前后最长公共子序列的长度，每次回溯时，通过next数组找到，前面匹配过的位置，省去了大量的计算时间

#### 代码实现
```java
/**
 * 子串匹配
 */
public class KMPAlgorithm {

	public static void main(String[] args) {
		String str1 = "BBC ABCDAB ABCDABCDABDE";
		String str2 = "ABCDABD";

		int[] next = kmpNext(str2);
		System.out.println(Arrays.toString(next));
		int i = kmpSearch(str1, str2, next);
		System.out.println(i);
	}


	public static int kmpSearch(String str1,String str2,int[] next){
		for (int i = 0,j = 0; i < str1.length(); i++) {
			while (j > 0 && str1.charAt(i) != str2.charAt(j)){
				j = next[j - 1];
			}
			if(str1.charAt(i) == str2.charAt(j)){
				j++;
			}
			if(j == str2.length()){ // 长度相等即为找到
				return i - j + 1;
			}
		}
		return -1;
	}

	/**
	 * 获取字符串(查询的子串)的部分匹配值表
	 * 遍历字符串
	 * 遍历到的字母及之前的字符组成的字符串
	 * 除了最后一个元素的顺序子字符串为其前缀，反之为后缀
	 * 前缀的集合与后缀的集合的交集大小即为当前字母部分匹配值
	 * @param dest  ABCDABD
	 * @return
	 */
	public static int[] kmpNext(String dest){
		// 保存部分匹配值的数组
		int[] next = new int[dest.length()];
		next[0] = 0; // 字符串长度为1的时候，部分匹配值就是0
		for (int i = 1,j = 0; i < dest.length(); i++) {
			// 当dest.charAt[i] != dest.charAt[j],需要从next[j-1]中获取新的j
			// 直到发现有dest.charAt[i] == dest.charAt[j]成立，才退出
			while (j > 0 && dest.charAt(i) != dest.charAt(j)){
				j = next[j - 1];
			}
			if(dest.charAt(i) == dest.charAt(j)){
				j++;
			}
			next[i] = j;
		}
		return next;
	}
}
```