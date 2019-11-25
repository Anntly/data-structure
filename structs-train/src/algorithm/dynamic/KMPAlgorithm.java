package algorithm.dynamic;

import java.util.Arrays;

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
