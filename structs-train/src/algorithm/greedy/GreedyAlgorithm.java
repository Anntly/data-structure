package algorithm.greedy;

import java.util.*;

/**
 * ̰���㷨������ϸ�������
 * 1.
 */
public class GreedyAlgorithm {

	public static void main(String[] args) {
		// ʹ��Map<String,HashSet>�洢�㲥̨�븲�ǵ���
		Map<String,HashSet<String>> ks = new HashMap<>();
		HashSet<String> set1 = new HashSet<>();
		set1.add("����");
		set1.add("�Ϻ�");
		set1.add("���");
		ks.put("k1",set1);
		HashSet<String> set2 = new HashSet<>();
		set2.add("����");
		set2.add("����");
		set2.add("����");
		ks.put("k2",set2);
		HashSet<String> set3 = new HashSet<>();
		set3.add("�ɶ�");
		set3.add("�Ϻ�");
		set3.add("����");
		ks.put("k3",set3);
		HashSet<String> set4 = new HashSet<>();
		set4.add("�Ϻ�");
		set4.add("���");
		ks.put("k4",set4);
		HashSet<String> set5 = new HashSet<>();
		set5.add("����");
		set5.add("����");
		ks.put("k5",set5);

		// ʹ��һ��HashSet������е���
		HashSet<String> areas = new HashSet<>();
		areas.addAll(set1);
		areas.addAll(set2);
		areas.addAll(set3);
		areas.addAll(set4);
		areas.addAll(set5);

		// ����ks��ÿһ�ֽ���areas�������ļ��ϵ�key����result,�ٽ�areas�еĶ�Ӧ����ȥ��
		List<String> result = new ArrayList<>();
		// �ݴ�ÿһ��ÿ��key��areas�Ľ������������ֵ
		String maxKey = null;
		Integer maxNum = 0;
		// �����ݴ渲�ǵ����Ľ���
		HashSet<String> temp = new HashSet<>();

		while (areas.size() > 0){ // ���������ϻ��е���δ�����Ǿͼ�������
			// ����maxKey��maxNum
			maxKey = null;
			maxNum = 0;
			for (String key : ks.keySet()){
				// ��� temp
				temp.clear();
				HashSet<String> set = ks.get(key);
				temp.addAll(set);
				temp.retainAll(areas); // �󽻼���������������temp��
				if(temp.size() > 0 &&  temp.size() > maxNum){ // ���������ڣ��Ҵ�С������󽻼���ʱ������maxKey
					maxKey = key;
					maxNum = temp.size();
				}
			}
			// �ҵ�maxKey֮��maxKey����result��
			if(maxKey != null){
				result.add(maxKey);
				// ��areas�е��Ѹ��ǵ���ȡ��
				areas.removeAll(ks.get(maxKey));
			}
		}

		System.out.println(result);
	}
}
