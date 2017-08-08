package grammar;

/**
 * Created by Cawa on 17.07.2017.
 */
public class Conjunction extends BinaryOperator {
    public Conjunction(GrammarToken f, GrammarToken s) {
        super(f, s);
        name = "&";
    }
}
