package edu.csupomona.cs356;

public class InheritanceTest {

}


interface InterfaceA { }
interface InterfaceB { }
interface InterfaceC { }

class ClassA { }
class ClassB { }
class ClassC { }

class TestClass1 implements InterfaceA, InterfaceB { }
class TestClass2 extends InterfaceA { }
class TestClass3 extends Class B, ClassC { }
class TestClass4 extends ClassB implements InterfaceA, InterfaceB { }
class TestClass5 extends Class B, ClassC implements InterfaceA, InterfaceB { }
class TestClass6 implements InterfaceA, InterfaceB extends ClassB { }
interface TestInterface1 implements InterfaceA, InterfaceB { }
interface TestInterface2 extends InterfaceA, InterfaceB { }
interface TestInterface3 extends ClassB implements InterfaceA, InterfaceB { }
