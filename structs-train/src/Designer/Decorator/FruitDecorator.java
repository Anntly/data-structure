package Designer.Decorator;

public class FruitDecorator extends AbstractDecorator{

	public FruitDecorator(AbstractProduct product) {
		super(product);
	}

	@Override
	public String getDesc() {
		return super.getDesc() + "ˮ��";
	}

	@Override
	public int getMoney() {
		return super.getMoney() + 12;
	}
}
