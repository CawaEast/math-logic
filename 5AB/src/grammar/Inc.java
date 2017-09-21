package grammar;

/**
 * Created by Cawa on 26.07.2017.
 */
public class Inc extends UnaryOperator {
    public Inc(GrammarToken f) {
        super(f);
        name = "'";
    }

    public String toString() {/*
        if (getNum() > 0) {
            return Integer.toString(getNum());
        }*/
        if (first.isVar() || first.isFunc || first.name.equals("'")) {
            return first.toString() + "'";
        } else {
            return "(" + first.toString() + ")'";

        }
    }
    int getNum() {
        int ans = first.getNum() + 1;
        if (ans != 0) {
            return ans;
        }
        return -1;
    }
}