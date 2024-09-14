package TaskSheet129;

public class Task129 {
  public static void main(String[] args) {
    Gorilla gorilla = new Gorilla();
    gorilla.groom();
    if (gorilla.feed(true)) {
      System.out.println("Gorilla has been fed.");
    }
    try {
      gorilla.pet();
    } catch (GorillaPunchException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }
}

interface Animal {
  boolean feed(boolean timeToEat);

  void groom();

  void pet();
}

class Gorilla implements Animal {

  public boolean feed(boolean timeToEat) {
    // put gorilla food into cage
    return true;
  }

  public void groom() {
    // lather, rinse, repeat
  }

  public void pet() throws GorillaPunchException {
    // pet at your own risk
    double gorillaPettingSkills = Math.random() * 100;

    if (gorillaPettingSkills > 75) return;

    throw new GorillaPunchException();
  }

}

class GorillaPunchException extends RuntimeException {
  GorillaPunchException() {
    super("You've been punched by the gorilla.");
  }
}