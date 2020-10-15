import java.util.Map;
import java.util.List;
/**
 * this is an interface that represents an expression.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided.
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment map that keeps all the variables
     * @return double value of the evaluated expression
     * @throws Exception if the variable in the expression is not in the map.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return double value of the evaluated expression
     * @throws Exception if the variable in the expression is not in the map.
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return List of Strings with all the variables
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return String of the expression
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        variable to be replaced
     * @param expression expression to replace variable
     * @return Expression the new expression after the assignment
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var the variable relative tot he differentiation
     * @return a differentiated expression
     */
    Expression differentiate(String var);

    /**
     * Returned a simplified version of the current expression.
     *
     * @return the simplified expression
     */
    Expression simplify();
}