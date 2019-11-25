#### 基本步骤
1. 分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题
2. 解决：若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题
3. 合并：将各个子问题的解合并为原问题的解。
#### 算法设计模式
```
if |P|≤n0
   then return(ADHOC(P))
//将P分解为较小的子问题 P1 ,P2 ,…,Pk
for i←1 to k
do yi ← Divide-and-Conquer(Pi)   递归解决Pi
T ← MERGE(y1,y2,…,yk)   合并子问题
return(T)
```
其中|P|表示问题P的规模；n0为一阈值，表示当问题P的规模不超过n0时，问题已容易直接解出，不必再继续分解。ADHOC(P)是该分治法中的基本子算法，用于直接解小规模的问题P。因此，当P的规模不超过n0时直接用算法ADHOC(P)求解。算法MERGE(y1,y2,…,yk)是该分治法中的合并子算法，用于将P的子问题P1 ,P2 ,…,Pk的相应的解y1,y2,…,yk合并为P的解。

#### 汉诺塔问题
思路
1. 如果是有一个盘， A->C
如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的盘 2. 上面的盘
2. 先把 最上面的盘 A->B
3. 把最下边的盘 A->C
4. 把B塔的所有盘 从 B->C

#### 代码实现
```java
public class HanioTower {

	public static void main(String[] args) {
		HanioTower tower = new HanioTower();
		tower.move(3,'A','B','C');
	}

	/**
	 * 可以将汉诺塔的移动分为上面的盘的移动和底部一个盘的移动
	 * 1. 将上面所有盘从 A -> B
	 * 2. 将底部盘从 A -> C
	 * 3. 将上面的盘从 B -> C
	 * @param num
	 * @param a
	 * @param b
	 * @param c
	 */
	public void move(int num,char a,char b,char c){
		if(num == 1){ // 当只有一个盘子的时候，直接从 A -> C
			System.out.println("第1个盘:"+a+"->"+c);
		}else {
			// 1. 先移动上面的盘子
			move(num - 1,a,c,b);
			// 2. 移动底部盘
			System.out.println("第"+num+"个盘:"+a+"->"+c);
			// 3. 移动上面的盘
			move(num - 1,b,a,c);
		}
	}
}
```