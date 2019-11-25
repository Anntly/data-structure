package algorithm.dac;

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
