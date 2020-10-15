import java.util.List;
import java.util.TreeMap;

/**
 * this is an abstract class for a base expression.
 */
public abstract class BaseExpression implements Expression {
    static final Num ZERO = new Num(0);
    static final Num ONE = new Num(1);
    /**
     * the string representation of the operation.
     *
     * @return String of the operation
     */
    protected abstract String operationString();

    @Override
    public double evaluate() throws Exception {
        return evaluate(new TreeMap<>());
    }

    /**
     * inserting variables to list and making sure there are no duplicates.
     *
     * @param variableList the final list
     * @param tempVarList  the list with maybe duplicates of expression
     * @return variableList a list of strings
     */
    protected List<String> insertVarsToList(List<String> variableList,
                                            List<String> tempVarList) {
        for (String var : tempVarList) {
            if (!variableList.contains(var)) {
                variableList.add(var);
            }
        }
        return variableList;
    }
}
