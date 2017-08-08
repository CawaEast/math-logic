package grammar;

/**
 * Created by Cawa on 17.07.2017.
 */
public class Disjunction extends BinaryOperator {
    public Disjunction(GrammarToken f, GrammarToken s) {
        super(f, s);
        name = "|";
    }
}
