package TaskSheet113;

public class PrimitivesStringConcat {

    public static void main(String[] args) {

        boolean myBoo = true;
        byte myFirstBite = 0;
        byte mySecondBite = 1;
        char myD = 'd';
        char mySpace = ' ';
        short myShorty = 3;
        int myInt = 11;
        float myFloat = 2.0F;

        String outputString =
                        "H"
                        + myShorty
                        + myInt
                        + myFirstBite
                        + " w"
                        + myFirstBite
                        + "r"
                        + mySecondBite
                        + myD
                        + mySpace
                        + myFloat
                        + mySpace
                        + myBoo;

        System.out.println(outputString);

    }
}
