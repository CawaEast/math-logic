package grammar;

/**
 * Created by Cawa on 27.07.2017.
 */
public class Mull extends BinaryOperator {
    public Mull(GrammarToken f, GrammarToken s) {
        super(f, s);
        name = "*";
    }
}