package recursion;

public class Demo01 {

	public static void main(String[] args) {
		System.out.println(test01(3));
	}

	// ¼ÆËã½×³Ë
	public static int test01(int n){
		if(n == 1){
			return 1;
		}else {
			return test01(n-1)*n;
		}
	}
}
