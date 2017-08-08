package grammar;

/**
 * Created by Cawa on 03.08.2017.
 */
public class Equals extends BinaryOperator {
    public Equals(GrammarToken f, GrammarToken s) {
        super(f, s);
        name = "=";
    }
}
