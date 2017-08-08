package grammar;

/**
 * Created by Cawa on 26.07.2017.
 */
public class Add extends BinaryOperator {
    public Add(GrammarToken f, GrammarToken s) {
        super(f, s);
        name = "+";
    }
}
