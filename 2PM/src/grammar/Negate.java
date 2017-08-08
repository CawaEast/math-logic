package grammar;

/**
 * Created by Cawa on 17.07.2017.
 */
public class Negate extends UnaryOperator {
    public Negate(GrammarToken f) {
        super(f);
        name = "!";
    }
}
