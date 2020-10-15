/**
 * this class represents a plus expression.
 */
public class Plus extends BinaryExpression {

    /**
     * constructor of Plus expression with two different expressions.
     * using super constructor
     *
     * @param leftExpression  the left expression of this expression
     * @param rightExpression the right expression of this expression
     */
    public Plus(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    /**
     * constructor of Plus expression with to variables.
     * using super constructor
     *
     * @param var1 the left variable
     * @param var2 the right variable
     */
    public Plus(String var1, String var2) {
        super(new Var(var1), new Var(var2));
    }

    /**
     * constructor of Plus expression with two doubles.
     * using super constructor
     *
     * @param num1 the left number
     * @param num2 the right number
     */
    public Plus(double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    /**
     * constructor of Plus expression with leftexpression and variable.
     * using super constructor
     *
     * @param leftExpression the left expression of this expression
     * @param var            the right variable of this expression
     */
    public Plus(Expression leftExpression, String var) {
        super(leftExpression, new Var(var));
    }

    /**
     * constructor of Plus expression with variable and rightexpression.
     * using super constructor
     *
     * @param var             the left variable of this expression
     * @param rightExpression the right expression of this expression
     */
    public Plus(String var, Expression rightExpression) {
        super(new Var(var), rightExpression);
    }

    /**
     * constructor of Plus expression with double and right expression.
     * using super constructor
     *
     * @param num             the left number of this expression
     * @param rightExpression the right expresstion of this expression
     */
    public Plus(double num, Expression rightExpression) {
        super(new Num(num), rightExpression);
    }

    /**
     * constructor of Plus expression with double and left expression.
     * using super constructor
     *
     * @param leftExpression left expression of this expression
     * @param num            right number of this expression
     */
    public Plus(Expression leftExpression, double num) {
        super(leftExpression, new Num(num));
    }

    /**
     * constructor of Plus expression with variable and double.
     * using super constructor
     *
     * @param var the left variable of this expression
     * @param num the right number of this expression
     */
    public Plus(String var, double num) {
        super(new Var(var), new Num(num));
    }

    /**
     * constructor of Plus expression with double and variable.
     * using super constructor
     *
     * @param num the left number of this expression
     * @param var the right variable of this expression
     */
    public Plus(double num, String var) {
        super(new Num(num), new Var(var));
    }

    @Override
    protected String operationString() {
        return " + ";
    }

    @Override
    protected double calculate(double left, double right) {
        return right + left;
    }

    @Override
    protected Expression specificChecks(Expression left, Expression right) {
        //if right expression is zero
        if (right.toString().equals(ZERO.toString())) {
            return left;
            //if left expression is zero
        } else if (left.toString().equals(ZERO.toString())) {
            return right;
        }
        return new Plus(left, right);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(getLeftExpression().assign(var, expression),
                getRightExpression().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // (f(x) + g(x))' = f'(x) + g'(x)
        return new Plus(getLeftExpression().differentiate(var),
                getRightExpression().differentiate(var));
    }
}
