import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * this class represents a basic binary expression.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression leftExpression;
    private Expression rightExpression;

    /**
     * constructor.
     *
     * @param leftExpression  the left expression
     * @param rightExpression the right expression
     */
    protected BinaryExpression(Expression leftExpression,
                               Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    /**
     * this method returns the left expression.
     *
     * @return Expression leftExpression
     */
    protected Expression getLeftExpression() {
        return this.leftExpression;
    }

    /**
     * this method returns the right expression.
     *
     * @return Expression rightExpression
     */
    protected Expression getRightExpression() {
        return this.rightExpression;
    }

    @Override
    public final double evaluate(Map<String, Double> assignment) throws Exception {
        return calculate(leftExpression.evaluate(assignment),
                rightExpression.evaluate(assignment));
    }

    /**
     * this function calculates the expression according to the operator.
     *
     * @param left left expression
     * @param right right expression
     * @return double- the result
     * @throws Exception in case we are trying to calculate something illegal
     */
    protected abstract double calculate(double left, double right) throws Exception;

    /**
     * this method evaluates expressions without variables.
     *
     * @param exp1 the first expression
     * @param exp2 the second expression
     * @return an expression that has been evaluated
     */
    private Expression evaluateWithoutVariables(Expression exp1,
                                                  Expression exp2) {
        // if both expressions are only numbers - calculate the result and return
        if (exp1.getVariables().size() == 0
                && exp2.getVariables().size() == 0) {
            try {
                return new Num(calculate(exp1.evaluate(), exp2.evaluate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Expression simplify() {
        Expression left = leftExpression.simplify();
        Expression right = rightExpression.simplify();
        Expression result = evaluateWithoutVariables(leftExpression, rightExpression);
        if (result != null) {
            return result;
        }
        return specificChecks(left, right);
    }

    /**
     * this method checks for simplifications individually for each operator.
     *
     * @param left the left expression
     * @param right the right expression
     * @return the expression simplified
     */
    protected abstract Expression specificChecks(Expression left,
                                                    Expression right);

    @Override
    public String toString() {
        return "(" + leftExpression + operationString() + rightExpression + ")";
    }

    @Override
    public List<String> getVariables() {
        List<String> tempLeft = leftExpression.getVariables();
        List<String> tempRight = rightExpression.getVariables();
        List<String> variableList = new ArrayList<>();
        variableList = insertVarsToList(variableList, tempLeft);
        variableList = insertVarsToList(variableList, tempRight);
        return variableList;
    }
}
