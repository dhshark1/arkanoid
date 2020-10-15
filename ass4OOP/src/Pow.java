import static java.lang.Math.pow;

/**
 * this class represents a power expression.
 */
public class Pow extends BinaryExpression {
    /**
     * constructor of pow expression with two different expressions.
     * using super constructor
     * @param leftExpression the left expression of this expression
     * @param rightExpression the right expression of this expression
     */
    public Pow(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    /**
     * constructor of pow expression with to variables.
     * using super constructor
     *
     * @param var1 the left variable
     * @param var2 the right variable
     */
    public Pow(String var1, String var2) {
        super(new Var(var1), new Var(var2));
    }

    /**
     * constructor of pow expression with two doubles.
     * using super constructor
     *
     * @param num1 the left number
     * @param num2 the right number
     */
    public Pow(double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    /**
     * constructor of Pow expression with leftexpression and variable.
     * using super constructor
     *
     * @param leftExpression the left expression of this expression
     * @param var the right variable of this expression
     */
    public Pow(Expression leftExpression, String var) {
        super(leftExpression, new Var(var));
    }

    /**
     * constructor of Pow expression with variable and rightexpression.
     * using super constructor
     *
     * @param var the left variable of this expression
     * @param rightExpression the right expression of this expression
     */
    public Pow(String var, Expression rightExpression) {
        super(new Var(var), rightExpression);
    }

    /**
     * constructor of Pow expression with double and right expression.
     * using super constructor
     *
     * @param num the left number of this expression
     * @param rightExpression the right expresstion of this expression
     */
    public Pow(double num, Expression rightExpression) {
        super(new Num(num), rightExpression);
    }

    /**
     * constructor of Pow expression with double and left expression.
     * using super constructor
     *
     * @param leftExpression left expression of this expression
     * @param num right number of this expression
     */
    public Pow(Expression leftExpression, double num) {
        super(leftExpression, new Num(num));
    }

    /**
     * constructor of Pow expression with variable and double.
     * using super constructor
     *
     * @param var the left variable of this expression
     * @param num the right number of this expression
     */
    public Pow(String var, double num) {
        super(new Var(var), new Num(num));
    }

    /**
     * constructor of Pow expression with double and variable.
     * using super constructor
     *
     * @param num the left number of this expression
     * @param var the right variable of this expression
     */
    public Pow(double num, String var) {
        super(new Num(num), new Var(var));
    }

    @Override
    protected double calculate(double left, double right) {
        return pow(left, right);
    }

    @Override
    protected String operationString() {
        return "^";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(getLeftExpression().assign(var, expression),
                getRightExpression().assign(var, expression));
    }

    @Override
    protected Expression specificChecks(Expression left, Expression right) {
        //if expression is to the power of 1
        if (right.toString().equals(ONE.toString())) {
            return left;
            //if expression is tot he power of zero
        } else if (right.toString().equals(ZERO.toString())) {
            return ONE;
        }
        return new Pow(left, right);
    }

    @Override
    public Expression differentiate(String var) {
        // (f(x)^g(x))' = (f(x)^g(x))*[f'(x)*(g(x))/f(x)) + g'(x)*log(e,f(x))]
        //  g'(x) * log(e,f(x)
        Expression rightMult = new Mult(getRightExpression().differentiate(var)
                , new Log("e", getLeftExpression()));
        Expression leftMultDiv = new Mult(getLeftExpression().differentiate(var),
                new Div(getRightExpression(), getLeftExpression()));
        Expression diff = new Mult(new Pow(getLeftExpression(),
                getRightExpression()), new Plus(leftMultDiv, rightMult));
        return diff;
    }
}
