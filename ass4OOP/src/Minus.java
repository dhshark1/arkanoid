/**
 * this class represents a minus expression.
 */
public class Minus extends BinaryExpression {
    /**
     * constructor of minus expression with two expressions.
     * using super constructor
     *
     * @param leftExpression  left expression of this expression
     * @param rightExpression right expression of this expression
     */
    public Minus(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    /**
     * constructor of minus expression with two variables.
     * using super constructor
     *
     * @param var1 left variable of this expression
     * @param var2 right variable of this expression
     */
    public Minus(String var1, String var2) {
        super(new Var(var1), new Var(var2));
    }

    /**
     * constructor of minus expression with two doubles.
     * using super constructor
     *
     * @param num1 left number of this expression
     * @param num2 right number of this expression
     */
    public Minus(double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    /**
     * constructor of minus expression with one leftexpression and a variable.
     * using super constructor
     *
     * @param leftExpression left expression of this expression
     * @param var right variable of this expression
     */
    public Minus(Expression leftExpression, String var) {
        super(leftExpression, new Var(var));
    }

    /**
     * constructor of minus expression with one rightexpresson and a variable.
     * using super constructor
     *
     * @param var the left variable of this expression
     * @param rightExpression the right expression of this expression
     */
    public Minus(String var, Expression rightExpression) {
        super(new Var(var), rightExpression);
    }

    /**
     * constructor of minus expression with one double and a right expression.
     * using super constructor
     *
     * @param num the left number of this expression
     * @param rightExpression the right expression of this expression
     */
    public Minus(double num, Expression rightExpression) {
        super(new Num(num), rightExpression);
    }

    /**
     * constructor of minus expression of one leftexpression and a double.
     * using super constructor
     *
     * @param leftExpression the left expression of this expression
     * @param num the right number of this expression
     */
    public Minus(Expression leftExpression, double num) {
        super(leftExpression, new Num(num));
    }

    /**
     * constructor of minus expression of one variable and a double.
     * using super constructor
     *
     * @param var the left variable of this expression
     * @param num the right number of this expression
     */
    public Minus(String var, double num) {
        super(new Var(var), new Num(num));
    }

    /**
     * constructor of minus expression of one double and a variable.
     * using super constructor
     *
     * @param num the left number of this expression
     * @param var the right variable of this expression
     */
    public Minus(double num, String var) {
        super(new Num(num), new Var(var));
    }

    @Override
    protected double calculate(double left, double right) {
        return left - right;
    }

    @Override
    protected Expression specificChecks(Expression left, Expression right) {
        //if left equals right return zero
        if (left.toString().equals(right.toString())) {
            return ZERO;
            //if left equals zero return negative of right
        } else if (left.toString().equals(ZERO.toString())) {
            return new Neg(right);
            //if right equals zero return left
        } else if (right.toString().equals(ZERO.toString())) {
            return left;
        }
        return new Minus(left, right);
    }

    @Override
    protected String operationString() {
        return " - ";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(getLeftExpression().assign(var, expression),
                getRightExpression().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // (f(x) - g(x))' = f'(x) - g'(x)
        return new Minus(getLeftExpression().differentiate(var),
                getRightExpression().differentiate(var));
    }
}
