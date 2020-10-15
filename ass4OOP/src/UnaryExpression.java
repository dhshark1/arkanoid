import java.util.List;
import java.util.Map;

/**
 * this class represents an Unary expression.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression expression;

    /**
     * constructor.
     *
     * @param expression the expression
     */
    protected UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * this method returns the unary expression.
     *
     * @return Expression the unary expression
     */
    protected Expression getExpression() {
        Expression newExpression = this.expression;
        return newExpression;
    }

    @Override
    public final double evaluate(Map<String, Double> assignment) throws Exception {
        return calculate(expression.evaluate(assignment));
    }

    /**
     * this function calculates the expression according to the operator.
     *
     * @param evaluate the number to be evaluated
     * @return the evaluated number
     */
    protected abstract double calculate(double evaluate);

    @Override
    public String toString() {
        return operationString() + "(" + expression + ")";
    }

    @Override
    public List<String> getVariables() {
        List<String> variableList = expression.getVariables();
        return variableList;
    }

    @Override
    public Expression simplify() {
        Expression afterSimplify = expression.simplify();
        if (afterSimplify.getVariables().isEmpty()) {
            try {
                return new Num(afterSimplify.evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return check(afterSimplify);
    }

    /**
     * for each class, if there is not a "special" case in simplify -
     * return object from this class with new simplified expression.
     *
     * @param afterSimplify the expression after simplification
     * @return new object with simplified expression
     */
    protected abstract Expression check(Expression afterSimplify);
}
