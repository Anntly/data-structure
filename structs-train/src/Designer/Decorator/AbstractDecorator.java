package Designer.Decorator;

public class AbstractDecorator implements AbstractProduct{

	private AbstractProduct product; // 私有的产品,通过构造器注入

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
