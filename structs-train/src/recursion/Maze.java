package recursion;

/**
 * �Թ�����
 */
public class Maze {

	public static void main(String[] args) {
		int[][]  map = new int[8][7];
		// �߿�����Ϊ1
		// ����
		for (int i = 0; i < 7; i++) {
			map[0][i] = 1;
			map[7][i] = 1;
		}
		// ����
		for (int i = 0; i < 8; i++) {
			map[i][0] = 1;
			map[i][6] = 1;
		}
		// ����ϰ�
		map[3][1] = 1;
		map[3][2] = 1;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}


		setWay(map,1,1);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

	/**
	 * 1. 0 ����δ�߹���1 ����ǽ��2 �������߹���3 �����߲�ͨ
	 * 2. ���Բ��� ��������(Ҳ��������������)
	 * 3. �����·��(���ݲ�ͬ�㷨��������)
	 * 4. [6,5] Ϊ�յ�
	 * @param map ��ͼ
	 * @param x ��ʼ������
	 * @param y ��ʼ������
	 * @return
	 */
	public static boolean setWay(int[][] map,int x,int y){
		if(map[6][5] == 2){ // ��ֹ����
			return true;
		} else {
			if(map[x][y] == 0){ // ���������
				// ��ȡ���Խ��еݹ�
				map[x][y] = 2; // ���϶��õ��ܹ���ͨ
				if (setWay(map,x+1,y)){ // ��
					return true;
				} else if(setWay(map,x,y+1)){ // ��
					return true;
				} else if(setWay(map,x-1,y)){ // ��
					return true;
				} else if(setWay(map,x,y-1)){ // ��
					return true;
				}else {
					map[x][y] = 3; // ������߲�ͨ������Ϊ3
					return false;
				}
			} else {
				// ���Ϊ1��2��3
				return false;
			}
		}
	}
}
