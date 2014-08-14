package edu.csupomona.cs356;



abstract class A {
	void test(A a) {
		System.out.println("You are in A");
	}
}

class B extends A {
	void test(B b) {
		System.out.println("You are in B");
	}
}

public class TrickyPoly {
	public static void main(String[] args) {
		A a1 = new B();
		A a2 = new B();
		B b1 = new B();
		a1.test(a2);
		b1.test(a2);
		a1.test(b1);
		b1.test(b1);
	}
}