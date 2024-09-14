package TaskSheet127;

public class Task127 {

  public static void main(String[] args) {
    B b = new B();
    b.abstractMethod();
    b.concreteMethod();
    C c = new C();
    c.abstractMethod();
    c.concreteMethod();
  }

}

abstract class AbstractClass {
  abstract void abstractMethod();

  void concreteMethod() { // concrete methods are still allowed in abstract classes
    System.out.println("This is a concrete method.");
  }
}

class B extends AbstractClass {
  void abstractMethod() {
    System.out.println("B's implementation of abstractMethod.");
  }
}

class C extends AbstractClass {
  void abstractMethod() {
    System.out.println("C's implementation of abstractMethod.");
  }
}