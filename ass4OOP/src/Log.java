import static java.lang.Math.log;

/**
 * this class represents a log expression.
 */
public class Log extends BinaryExpression {
    /**
     * constructor of log expression with two different expressions.
     * using super constructor
     * @param leftExpression the left expression of this expression
     * @param rightExpression the right expression of this expression
     */
    public Log(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    /**
     * constructor of log expression with to variables.
     * using super constructor
     *
     * @param var1 the left variable
     * @param var2 the right variable
     */
    public Log(String var1, String var2) {
        super(new Var(var1), new Var(var2));
    }

    /**
     * constructor of log expression with two doubles.
     * using super constructor
     *
     * @param num1 the left number
     * @param num2 the right number
     */
    public Log(double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    /**
     * constructor of log expression with leftexpression and variable.
     * using super constructor
     *
     * @param leftExpression the left expression of this expression
     * @param var the right variable of this expression
     */
    public Log(Expression leftExpression, String var) {
        super(leftExpression, new Var(var));
    }

    /**
     * constructor of log expression with variable and rightexpression.
     * using super constructor
     *
     * @param var the left variable of this expression
     * @param rightExpression the right expression of this expression
     */
    public Log(String var, Expression rightExpression) {
        super(new Var(var), rightExpression);
    }

    /**
     * constructor of log expression with double and right expression.
     * using super constructor
     *
     * @param num the left number of this expression
     * @param rightExpression the right expresstion of this expression
     */
    public Log(double num, Expression rightExpression) {
        super(new Num(num), rightExpression);
    }

    /**
     * constructor of log expression with double and left expression.
     * using super constructor
     *
     * @param leftExpression left expression of this expression
     * @param num right number of this expression
     */
    public Log(Expression leftExpression, double num) {
        super(leftExpression, new Num(num));
    }

    /**
     * constructor of log expression with variable and double.
     * using super constructor
     *
     * @param var the left variable of this expression
     * @param num the right number of this expression
     */
    public Log(String var, double num) {
        super(new Var(var), new Num(num));
    }

    /**
     * constructor of log expression with double and variable.
     * using super constructor
     *
     * @param num the left number of this expression
     * @param var the right variable of this expression
     */
    public Log(double num, String var) {
        super(new Num(num), new Var(var));
    }

    @Override
    protected double calculate(double left, double right) throws Exception {
        if (right > 0 || left > 0 || left != 1) {
            return log(right) / log(left);
        }
        throw new Exception("Out of bounds for log");
    }

    @Override
    protected Expression specificChecks(Expression left, Expression right) {
        //if left equals right return 1
        if (left.toString().equals(right.toString())) {
            return ONE;
        } else {
            return new Log(left, right);
        }
    }

    @Override
    protected String operationString() {
        return "log";
    }

    @Override
    public String toString() {
        return operationString() + "(" + getLeftExpression() + ", " + getRightExpression() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(getLeftExpression().assign(var, expression),
                getRightExpression().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        //(log(b, f(x)))' = f'(x) / (log(e,b) * f(x)
        return new Div(getRightExpression().differentiate(var), new Mult(getLeftExpression(),
                new Log("e", getLeftExpression())));
    }
}
