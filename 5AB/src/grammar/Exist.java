package grammar;

import java.util.HashSet;

/**
 * Created by Cawa on 26.07.2017.
 */
public class Exist extends UnaryOperator {

    public Exist(GrammarToken v, GrammarToken f) {
        super(f);
        second = v;
        name = "?";
        //free.remove(v);
        locks = true;
    }
    HashSet<String> getFree() {
        HashSet<String> ans =first.getFree();
        ans.remove(second.name);
        return ans;
    }

    public String toString() {
        return "?" + second.toString() + "(" + first.toString() + ")";
    }
}
