package algorithm.dac;

public class HanioTower {

	public static void main(String[] args) {
		HanioTower tower = new HanioTower();
		tower.move(3,'A','B','C');
	}

	/**
	 * ���Խ���ŵ�����ƶ���Ϊ������̵��ƶ��͵ײ�һ���̵��ƶ�
	 * 1. �����������̴� A -> B
	 * 2. ���ײ��̴� A -> C
	 * 3. ��������̴� B -> C
	 * @param num
	 * @param a
	 * @param b
	 * @param c
	 */
	public void move(int num,char a,char b,char c){
		if(num == 1){ // ��ֻ��һ�����ӵ�ʱ��ֱ�Ӵ� A -> C
			System.out.println("��1����:"+a+"->"+c);
		}else {
			// 1. ���ƶ����������
			move(num - 1,a,c,b);
			// 2. �ƶ��ײ���
			System.out.println("��"+num+"����:"+a+"->"+c);
			// 3. �ƶ��������
			move(num - 1,b,a,c);
		}
	}
}
