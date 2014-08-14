package edu.csupomona.cs356;

class ClassParent {
	public void method1(int i) { }
	public void method2(int i) { }
	public static void method3(int i) { }
	public static void method4(int i) { }
	public void method5(int i) { }
	protected void method6(int i) { }
}

class ClassChild extends ClassParent {
	public static void method1(int i) { }
	public void method2(int i) { }
	public void method3(int i) { }
	public static void method4(int i) { }
	protected void method5(int i) { }
	public void method6(int i) { }
}
