package grammar;

/**
 * Created by Cawa on 17.07.2017.
 */
public class UnaryOperator extends GrammarToken{
    UnaryOperator(GrammarToken f) {
        super(f);
    }

    public String toString() {
        if (first.isBinary()) {
            return name + "(" + first.toString() + ")";
        }
        return name + first.toString();
    }
}
