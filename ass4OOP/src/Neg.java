/**
 * this class represents a negative expression.
 */
public class Neg extends UnaryExpression {

    /**
     * constructor - create new div expression using super constructor.
     *
     * @param expression the expression
     */
    public Neg(Expression expression) {
        super(expression);
    }

    /**
     * constructor shortcut - with a string.
     *
     * @param var the string to create expression from
     */
    public Neg(String var) {
        super(new Var(var));
    }

    /**
     * constructor shortcut - with a double.
     *
     * @param num the double to create expression from
     */
    protected Neg(double num) {
        super(new Num(num));
    }

    @Override
    protected double calculate(double evaluate) {
        return -1 * evaluate;
    }

    @Override
    protected Expression check(Expression afterSimplify) {
        if (afterSimplify.toString().equals(ZERO.toString())) {
            return ZERO;
        }
        return new Neg(afterSimplify);
    }

    @Override
    protected String operationString() {
        return "-";
    }

    @Override
    public String toString() {
        return "(" + operationString() + getExpression() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return getExpression().assign(var, expression);
    }

    @Override
    public Expression differentiate(String var) {
        return (new Neg(getExpression().differentiate(var)));
    }
}
