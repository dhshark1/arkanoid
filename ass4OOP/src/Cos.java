import static java.lang.Math.cos;
import static java.lang.Math.toRadians;

/**
 * this class represents a cos expression.
 */
public class Cos extends UnaryExpression {

    /**
     * constructor - create new div expression using super constructor.
     *
     * @param expression the expression
     */
    public Cos(Expression expression) {
        super(expression);
    }

    /**
     * constructor with a string.
     *
     * @param var  the string to create expression from
     */
    public Cos(String var) {
        super(new Var(var));
    }

    /**
     * constructor with a double.
     *
     * @param num  the double to create expression from
     */
    public Cos(double num) {
        super(new Num(num));
    }

    @Override
    protected double calculate(double evaluate) {
        return cos(toRadians(evaluate));
    }

    @Override
    protected Expression check(Expression afterSimplify) {
        return new Cos(afterSimplify);
    }

    @Override
    protected String operationString() {
        return "cos";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(getExpression().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // (cos(f(x)))' = -sin(f(x)) * f'(x)
        return new Neg(new Mult(new Sin(getExpression()), getExpression().differentiate(var)));
    }
}
