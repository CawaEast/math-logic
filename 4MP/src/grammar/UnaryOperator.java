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

    UnaryOperator copy() {
        if (name.equals("@")) {
            return new Any(second.copy(), first.copy());
        }
        if (name.equals("?")) {
            return new Exist(second.copy(), first.copy());
        }
        UnaryOperator ans = new UnaryOperator(first);
        ans.name = name;
        return ans;
    }
}
