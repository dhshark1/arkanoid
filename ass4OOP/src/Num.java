import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 /**
  * this class represents a constant number.
  */
public class Num implements Expression {
    private double value;

    /**
     * constructor.
     *
     * @param value the num's value
     */
    public Num(double value) {
        this.value = value;
    }

    /**
     * return the number's value.
     *
     * @return the value of the number
     */
    public double getValue() {
        return value;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return value;
    }

    @Override
    public double evaluate() throws Exception {
        return value;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Num(value);
    }

    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return new Num(value);
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
