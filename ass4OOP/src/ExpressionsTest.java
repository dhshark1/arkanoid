import java.util.Map;
import java.util.TreeMap;

/**
 * this is a test for ass4.
 */
public class ExpressionsTest {
    /**
     * test for the assignment.
     *
     * @param args input string. not used in this main.
     */
    public static void main(String[] args) {
        // Create the expression ((2 * x) + (sin(4 * y))) + (e^x).
        Expression e = new Plus(
                new Plus(
                        new Mult(2, "x"),
                        new Sin(new Mult(4, "y"))),
                new Pow("e", "x"));
        //Print the expression.
        System.out.println(e);
        //Print the value of the expression with (x=2,y=0.25,e=2.71).
        Map<String, Double> m = new TreeMap<>();
        m.put("x", 2.0);
        m.put("y", 0.25);
        m.put("e", 2.71);
        try {
            System.out.println(e.evaluate(m));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //Print the differentiated expression according to x.
        Expression eDifferentiate = e.differentiate("x");
        System.out.println(eDifferentiate);
        //Print the value of the differentiated expression according to x with the assignment above.
        try {
            System.out.println(eDifferentiate.evaluate(m));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //Print the simplified differentiated expression.
        System.out.println(eDifferentiate.simplify());
    }
}
