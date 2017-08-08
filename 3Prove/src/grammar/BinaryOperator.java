package grammar;

/**
 * Created by Cawa on 17.07.2017.
 */
public class BinaryOperator extends GrammarToken{
    BinaryOperator(GrammarToken f, GrammarToken s) {
        super(f, s);
    }
    BinaryOperator(Expression f, Expression s) {
        super(f, s);
    }

    public String toString() {
/*
        System.err.println(first.toString() + " " + name);
        if (second == null) {
            System.err.print("hm");
        }*/

        Operator op = new Operator(name);
        Operator fop = new Operator(first.name);
        Operator sop = new Operator(second.name);
        String ans = "";
        if (op.leftAssoc) {
            if (fop.less(op)) {
                ans += "(" + first.toString() + ")";
            } else {
                ans += first.toString();
            }
            ans += name;
            if (sop.lessEquals(op)) {
                ans += "(" + second.toString() + ")";
            } else {
                ans += second.toString();
            }
        } else {
            if (fop.lessEquals(op)) {
                ans += "(" + first.toString() + ")";
            } else {
                ans += first.toString();
            }
            ans += name;
            if (sop.less(op)) {
                ans += "(" + second.toString() + ")";
            } else {
                ans += second.toString();
            }
        }
        return ans;
    }

    BinaryOperator copy() {
        BinaryOperator ans = new BinaryOperator(first, second);
        ans.name = name;
        return ans;
    }
}
