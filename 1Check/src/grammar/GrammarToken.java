package grammar;

import java.util.HashMap;

/**
 * Created by Cawa on 17.07.2017.
 */
public class GrammarToken {
    String name;
    GrammarToken first = null, second = null;

    GrammarToken(String name) {
        this.name = name;
    }

    GrammarToken(GrammarToken first, GrammarToken second) {
        this.first = first;
        this.second = second;
    }

    GrammarToken(GrammarToken first) {
        this.first = first;
        this.second = null;
    }

    boolean check(GrammarToken mask, HashMap<String, String> context) {
        if (mask.isBinary() || mask.isUnary()) {
            if (!mask.name.equals(name)) {
                return false;
            }
            boolean ans = first.check(mask.first, context);
            if (mask.isBinary()) {
                ans &= second.check(mask.second, context);
            }
            return ans;
        } else {
            if (context.keySet().contains(mask.name)) {
                return toString().equals(context.get(mask.name));
            } else {
                context.put(mask.name, toString());
                return true;
            }
        }
    }

    boolean isBinary() {
        String n = name;
        return n.equals("|") || n.equals("->") || n.equals("&");
    }

    boolean isUnary() {
        String n = name;
        return n.equals("!");
    }
}
