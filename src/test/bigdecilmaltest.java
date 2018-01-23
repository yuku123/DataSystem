package test;

import java.math.BigDecimal;

public class bigdecilmaltest {
	public static void main(String []agrs){
		BigDecimal b1=new BigDecimal("0.00");
		System.out.println("b1£º"+b1);
		BigDecimal b=new BigDecimal("0.00");
		b=b1.add(new BigDecimal("3.0000"));
		System.out.println(b.toString());
	}

}
