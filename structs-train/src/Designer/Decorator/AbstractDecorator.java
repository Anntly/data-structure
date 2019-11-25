package Designer.Decorator;

public class AbstractDecorator implements AbstractProduct{

	private AbstractProduct product; // ˽�еĲ�Ʒ,ͨ��������ע��

	public AbstractDecorator(AbstractProduct product){
		this.product = product;
	}

	@Override
	public String getDesc() {
		return this.product.getDesc();
	}

	@Override
	public int getMoney() {
		return this.product.getMoney();
	}
}
