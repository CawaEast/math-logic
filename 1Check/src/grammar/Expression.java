package grammar;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created by Cawa on 17.07.2017.
 */
public class Expression {
    GrammarToken head;
    public int key = -1, num1, num2;
    public Expression(GrammarToken gt) {
        head = gt;
    }

    boolean checkExpression(Expression mask) {
        HashMap<String, String> context = new HashMap<String, String>();
        return head.check(mask.head, context);
    }

    public String toString() {
        String ans = head.toString();
        switch (key) {
            case 0:
                ans += " (Сх. акс. " + (num1 + 1) + ")";
                break;
            case 2:
                ans += " (M.P. " + (num1 + 1) + ","  + (num2 + 1) + ")";
                break;
            case 1:
                ans += " (Предп. " + (num1 + 1) + ")";
                break;
            default:
                ans += " ( Не доказано)";

        }
        return ans;
    }
}
