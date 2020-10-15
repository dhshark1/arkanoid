/**
 * this class represents a division expression.
 */
public class Div extends BinaryExpression {
    /**
     * constructor of a div expression with two different expressions.
     * using super constructor
     *
     * @param leftExpression  the left expression of this div expression
     * @param rightExpression the right expression of this div expression
     */
    public Div(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    /**
     * constructor of a div expression with two Strings.
     * using super constructor
     *
     * @param var1 the left var of this div expression
     * @param var2 the right var of this div expression
     */
    public Div(String var1, String var2) {
        super(new Var(var1), new Var(var2));
    }

    /**
     * constructor of a div expression with two doubles.
     * using super constructor
     *
     * @param num1 the left number of this div expression
     * @param num2 the right number of this div expression
     */
    public Div(double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    /**
     * constructor of a div expression with one leftexpression and one String.
     * using super constructor
     *
     * @param leftExpression the left expression of this div expression
     * @param var            the right var of this div expression
     */
    public Div(Expression leftExpression, String var) {
        super(leftExpression, new Var(var));
    }

    /**
     * constructor of a div expression with one String and one rightexpression.
     * using a super constructor
     *
     * @param var             the left var of this div expression
     * @param rightExpression the right expression of this div expression
     */
    public Div(String var, Expression rightExpression) {
        super(new Var(var), rightExpression);
    }

    /**
     * constructor of a div expression with one double and one rightexpression.
     * using a super constructor
     *
     * @param num             the left number of this expression
     * @param rightExpression the right expression of this div expression
     */
    public Div(double num, Expression rightExpression) {
        super(new Num(num), rightExpression);
    }

    /**
     * constructor of a div expression with one rightexpression and one double.
     * using a super constructor
     *
     * @param leftExpression the left expression of this div expression
     * @param num            the right number of this expression
     */
    public Div(Expression leftExpression, double num) {
        super(leftExpression, new Num(num));
    }

    /**
     * constructor of a div expression with String and double.
     * using super constructor
     *
     * @param var right variable of div expression
     * @param num left number of div expression
     */
    public Div(String var, double num) {
        super(new Var(var), new Num(num));
    }

    /**
     * constructor of a div expression with double and String.
     * using super constructor
     *
     * @param num right number of div expression
     * @param var left variable of div expression
     */
    public Div(double num, String var) {
        super(new Num(num), new Var(var));
    }

    @Override
    protected String operationString() {
        return " / ";
    }

    @Override
    protected double calculate(double left, double right) throws Exception {
        //return max value if divisible by zero
        if (right != 0) {
            return left / right;
        }
        throw new Exception("Cannot divide by zero!");
    }

    @Override
    protected Expression specificChecks(Expression left, Expression right) {
        //if expression equal return 1
        if (left.toString().equals(right.toString())) {
            return new Num(1);
            //if right equals one then return left
        } else if (right.toString().equals(ONE.toString())) {
            return left;
        }
        return new Div(left, right);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(getLeftExpression().assign(var, expression),
                getRightExpression().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // (f(x)/g(x))' = (f'(x)*g(x) - f(x)*g'(x)) / (g(x))^2
        Expression numerator = new Minus(
                new Mult(getLeftExpression().differentiate(var),
                        getRightExpression()),
                new Mult(getLeftExpression(),
                        getRightExpression().differentiate(var)));
        Expression denominator = new Pow(getRightExpression(), 2);
        return new Div(numerator, denominator);
    }
}
