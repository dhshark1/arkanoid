import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

/**
 * sinus expression class.
 */
public class Sin extends UnaryExpression {

    /**
     * constructor - create new div expression using super constructor.
     *
     * @param expression the expression
     */
    public Sin(Expression expression) {
        super(expression);
    }

    /**
     * constructor shortcut - with a string.
     *
     * @param var  the string to create expression from
     */
    public Sin(String var) {
        super(new Var(var));
    }

    /**
     * constructor shortcut - with a double.
     *
     * @param num  the double to create expression from
     */
    public Sin(double num) {
        super(new Num(num));
    }

    @Override
    protected double calculate(double evaluate) {
        return sin(toRadians(evaluate));
    }

    @Override
    protected Expression check(Expression afterSimplify) {
        return new Sin(afterSimplify);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(getExpression().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // (sin(f(x)))' = cos(f(x)) * f'(x)
        return new Mult(new Cos(getExpression()),
                getExpression().differentiate(var));
    }

    @Override
    protected String operationString() {
        return "sin";
    }
}