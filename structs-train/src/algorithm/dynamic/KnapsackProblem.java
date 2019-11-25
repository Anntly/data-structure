package algorithm.dynamic;

import java.util.Arrays;

/**
 * 简单背包问题
 * 1. 多个物品 有重量以及价值
 * 2. 背包固定重量，求放入背包的物品的最高价值，物品不能重复
 */
public class KnapsackProblem {
	public static void main(String[] args) {
		int[] val = {1500,3000,2000}; // 物品价值，下标第几个物品
		int[] w = {1,4,3}; // 物品重量
		int m = 4; // 背包容量
		int n = val.length;
		// v[i][j]代表的是 j容量的时候能存放的最大的价值
		// i 第几个物品  j 递增的容量,不超过背包最大容量
		int[][] v = new int[n + 1][m + 1];
		// 二维数组的第一行和第一列不存放价值，默认为0，为了保证算法不越界
		for (int i = 1; i < v.length; i++) {
			for (int j = 1; j < v[0].length; j++) {
				if(w[i-1] > j){ // 如果当前物品重量比当前容量大，直接取上一个物品的时候的最大价值
					v[i][j] = v[i - 1][j];
				}else { // 小于的时候，需要将当前最大值与之前的最大价值进行比较
					v[i][j] = Math.max(v[i-1][j],val[i - 1] + v[i-1][j - w[i - 1]]);
				}
			}
		}

		for (int[] ints : v) {
			System.out.println(Arrays.toString(ints));
		}
	}
}
