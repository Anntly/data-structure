package Designer.Decorator;

public class MilkDecorator extends AbstractDecorator{

	public MilkDecorator(AbstractProduct product) {
		super(product);
	}

	@Override
	public String getDesc() {
		return super.getDesc() + "ţ��";
	}

	@Override
	public int getMoney() {
		return super.getMoney() + 15;
	}
}
