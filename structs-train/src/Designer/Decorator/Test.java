package Designer.Decorator;

public class Test {

	public static void main(String[] args) {
		AbstractDecorator decoreator = new MilkDecorator(new FruitDecorator(new Cake()));

		System.out.println(decoreator.getDesc());
		System.out.println(decoreator.getMoney());
	}
}
