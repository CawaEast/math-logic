package grammar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

/**
 * Created by Cawa on 26.07.2017.
 */
public class Function extends GrammarToken {
    Vector<GrammarToken> vars;
    public Function(String name) {
        super(name);
        vars = new Vector<GrammarToken>();
        isFunc = true;
    }
    HashSet<String> getFree() {
        HashSet<String> ans = new HashSet<>();
        for (GrammarToken var : vars) {
            ans.addAll(var.getFree());
        }
        return ans;
    }

    void makeChange(GrammarToken from, GrammarToken to) {
        for(int i = 0; i < vars.size(); i++) {
            if (vars.get(i).equals(from)) {
                vars.set(i, to);
            }
        }

    }

    public void addParam(GrammarToken par) {
        vars.add(par);
        //free.addAll(par.free);
    }

    public String toString() {
        String ans = name + "(";
        boolean b = false;
        for (GrammarToken gt : vars) {
            if (b) {
                ans += ", ";
            } else {
                b = true;
            }
            ans += gt.toString();
        }
        return ans + ")";
    }

    Function copy() {
        Function ans = new Function(name);
        ans.name = name;
        ans.vars = new Vector<>(vars);
        return ans;
    }

    boolean check(Function mask, HashMap<String, String> context) {
        if (mask.name.equals(name)) {
            if (vars.size()!= mask.vars.size()) {
                return false;
            }
            for (int i = 0; i < vars.size(); i++) {
                if (!vars.get(i).equals(mask.vars.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
