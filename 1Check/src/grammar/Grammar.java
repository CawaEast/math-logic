package grammar;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by Cawa on 17.07.2017.
 */
public class Grammar {
    public Vector<Expression> g, axioms, prove;
    public Expression toProves;

    public Grammar() {
        g = new Vector<Expression>();
        prove = new Vector<Expression>();
    }

    public void addG(Expression a) {
        g.add(a);
    }

    public void addProve(Expression a) {
        prove.add(a);
    }

    public void setToProve(Expression e) {
        toProves = e;
    }

    public void setAxioms(Vector<Expression> a) {
        axioms = new Vector<Expression>(a);
    }

    public void checkExpressions() {
        for (int i = 0; i < prove.size(); i++) {
        Expression e = prove.get(i);
        checkExpression(e, i);
        }
    }

    HashMap<String, Vector<Integer>> findMP = new HashMap<>();
    HashMap<String, Integer> find = new HashMap<>();

    void addBoost(Expression e, Integer ind) {
        String s = e.head.toString();
        //if (!find.containsKey(s)) {
            find.put(s, ind);
            if (e.head.name.equals("->")) {
                String str = e.head.second.toString();
                if (findMP.containsKey(str)) {
                    findMP.get(str).add(ind);
                } else {
                    Vector<Integer> vector = new Vector<>();
                    vector.add(ind);
                    findMP.put(str, vector);
                }
            }
        //}
    }

    boolean checkExpression(Expression e, int ind) {
        for(int i = 0; i < axioms.size(); i++) {
            Expression axiom = axioms.get(i);
            if (e.checkExpression(axiom)) {
                addBoost(e, ind);
                e.key = 0;
                e.num1 = i;
                return true;
            }
        }
        for(int i = 0; i < g.size(); i++) {
            Expression expr = g.get(i);
            if (e.checkExpression(expr)) {
                addBoost(e, ind);
                e.key = 1;
                e.num1 = i;
                return true;
            }
        }
        String str = e.head.toString();
        if (!findMP.containsKey(str)) {
            return false;
        }
        for (int i : findMP.get(str)) {
            Expression expr1 = prove.get(i);


            String str2 = expr1.head.first.toString();
            if (!find.containsKey(str2)) {
                continue;
            }
            int j = find.get(str2);
            if (prove.get(j).key >= 0) {
                addBoost(e, ind);
                e.key = 2;
                e.num1 = i;
                e.num2 = j;
                return true;
            }
        }
        return false;
    }



}
