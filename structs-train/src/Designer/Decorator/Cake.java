package Designer.Decorator;

/**
 * ����Ĳ�Ʊ����
 */
public class Cake implements AbstractProduct {
	@Override
	public String getDesc() {
		return "����";
	}

	@Override
	public int getMoney() {
		return 30;
	}
}
