package grammar;

/**
 * Created by Cawa on 27.07.2017.
 */
public class Zero extends GrammarToken {
    public Zero(String name) {
        super(name);
    }
    public Zero() {
        super("0");
    }

    public String toString() {
        return "0";
    }

    int getNum() {
        return 0;
    }
}
