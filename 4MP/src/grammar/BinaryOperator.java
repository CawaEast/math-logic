package grammar;

import java.util.HashSet;

/**
 * Created by Cawa on 17.07.2017.
 */
public class BinaryOperator extends GrammarToken{
    BinaryOperator(GrammarToken f, GrammarToken s) {
        super(f, s);
    }
    BinaryOperator(Expression f, Expression s) {
        super(f.head, s.head);
    }

    HashSet<String> getFree() {
        HashSet<String> ans = first.getFree();
        ans.addAll(second.getFree());
        return ans;
    }

    public String toString() {
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
