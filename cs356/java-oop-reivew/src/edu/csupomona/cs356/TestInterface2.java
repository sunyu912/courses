package edu.csupomona.cs356;

//void aMethod(int aValue) {
//System.out.println("Hello World!");
//}

public interface TestInterface2 {

	void aMethodFix0(int aValue);
	
	protected aMethodFix1(int aValue);
	
	abstract void aMethodFix2(int aValue);
	
	static void aMethodFix3(int aValue) {
		System.out.println("Hello World!");
	}
	
	default void aMethodFix4(int aValue) {
		System.out.println("Hello World!");
	}
}
