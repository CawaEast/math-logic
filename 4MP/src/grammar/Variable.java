package grammar;

import java.util.HashSet;

/**
 * Created by Cawa on 17.07.2017.
 */
public class Variable extends GrammarToken {
    public Variable(String name) {
        super(name);
    }

    public String toString() {
        return name;
    }
    HashSet<String> getFree() {
        HashSet<String> ans = new HashSet<String>();
        ans.add(name);
        return ans;
    }
}
