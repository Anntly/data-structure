package Designer.Decorator;

/**
 * ¾ßÌåµÄ²ÍÆ±µ°¸â
 */
public class Cake implements AbstractProduct {
	@Override
	public String getDesc() {
		return "µ°¸â";
	}

	@Override
	public int getMoney() {
		return 30;
	}
}
