package grammar;

/**
 * Created by Cawa on 17.07.2017.
 */
public class Variable extends GrammarToken {
    public Variable(String name) {
        super(name);
    }

    public String toString() {
        return name;
    }
}
