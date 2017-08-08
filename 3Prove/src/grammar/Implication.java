package grammar;

/**
 * Created by Cawa on 17.07.2017.
 */
public class Implication extends BinaryOperator {
    public Implication(GrammarToken f, GrammarToken s) {
        super(f, s);
        name = "->";
    }
    public Implication(Expression f, Expression s) {
        super(f, s);
        name = "->";
    }
}
