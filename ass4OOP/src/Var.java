import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * this class represents one variable.
 */
public class Var implements Expression {
    private String variable;

    /**
     * constructor.
     *
     * @param var is the expression's variable
     */
    public Var(String var) {
        this.variable = var;
    }

    /**
     * return the number's value.
     *
     * @return the value of the number
     */
    public String getVar() {
        return variable;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        Double value = assignment.get(variable);
        //if variable is in map
        if (value != null) {
            return value;
        }
        throw new Exception("variable is not in map: " + variable);
    }

    @Override
    public double evaluate() throws Exception {
        //if map is empty
        throw new Exception("variable is not in map: " + variable);
    }

    @Override
    public List<String> getVariables() {
        //return arrayList with var
        List<String> var = new ArrayList<>();
        var.add(variable);
        return var;
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals(variable)) {
            return expression;
        } else {
            return new Var(variable);
        }
    }

    @Override
    public Expression differentiate(String var) {
        //the derivative of simple variable relative to itself is exactly 1
        if (var.equals(variable)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    @Override
    public Expression simplify() {
        return new Var(variable);
    }
}
