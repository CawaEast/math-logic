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

    GrammarToken(GrammarToken other, boolean copy) {
        name = other.name;
        if (copy) {
            if (other.first != null) {
                first = new GrammarToken(other.first, true);
            }
            if (other.second != null) {
                second = new GrammarToken(other.second, true);
            }
        } else {
            first = other.first;
            second = other.second;
        }
    }

    GrammarToken(Expression first, Expression second) {
        this.first = first.head.copy();
        this.second = second.head.copy();
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

    public String toString() {
        if (isBinary()) {
            return first.toString() + name + second.toString();
        }
        if (isUnary()) {
            return name + first.toString();
        }
        return name;
    }

    GrammarToken copy() {
        GrammarToken ans = new GrammarToken(first, second);
        ans.name = name;
        return ans;
    }
}
