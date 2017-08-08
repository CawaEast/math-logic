package grammar;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

/**
 * Created by Cawa on 17.07.2017.
 */
public class Grammar {
    Vector<Expression> g, axioms, prove;
    public Expression toProves;
    public HashSet<String> freeInG = new HashSet<>();
    public int badString = -1;

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

    public void checkExpressions(Boolean b) {
        freeInG.clear();
        if (b) {
            freeInG.addAll(g.lastElement().head.getFree());
        }
        for (int i = 0; i < prove.size(); i++) {
            Expression e = prove.get(i);
            checkExpression(e, i);
            if (e.key == -1) {
                badString = i;
            }
        }
    }

    public void checkExpressions() {
        freeInG.clear();
        for (int i = 0; i < prove.size(); i++) {
            Expression e = prove.get(i);
            checkExpression(e, i);
            if (e.key == -1) {
                badString = i;
            }
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
        if (e.head.name.equals("->")) {
            if (checkIndAxiom(e.head)) {
                addBoost(e, ind);
                e.key = 0;
                e.num1 = axioms.size();
                return true;
            }
            if (check11Axiom(e.head)) {
                addBoost(e, ind);
                e.key = 0;
                e.num1 = axioms.size()+ 1;
                return true;
            }
            if (check12Axiom(e.head)) {
                addBoost(e, ind);
                e.key = 0;
                e.num1 = axioms.size() + 2;
                return true;
            }
        }
        for(int i = 0; i < g.size(); i++) {
            Expression expr = g.get(i);
            if (e.toString().equals(expr.toString())) {
                addBoost(e, ind);
                e.key = 1;
                e.num1 = i;
                return true;
            }
        }
        String str = e.head.toString();
        if (findMP.containsKey(str)) {
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
        }
        if (e.head.name.equals("->")) {
            if (e.head.second.name.equals("@")) {
                GrammarToken psi = e.head.second.first;
                GrammarToken fi = e.head.first;
                String var = e.head.second.second.toString();
                if (!hasFree(fi, var)) {
                    GrammarToken newGt = new Implication(fi, psi);
                    if (find.containsKey(newGt.toString())) {
                        if (!freeInG.contains(var)) {
                            addBoost(e, ind);
                            e.key = 3;
                            e.num1 = 0;
                            e.num2 = find.get(newGt.toString());
                            return true;
                        }
                    }
                }
            }
            if (e.head.first.name.equals("?")) {
                GrammarToken psi = e.head.first.first;
                GrammarToken fi = e.head.second;
                String var = e.head.first.second.toString();
                if (!hasFree(fi, var)) {
                    GrammarToken newGt = new Implication(psi, fi);
                    if (find.containsKey(newGt.toString())) {
                        addBoost(e, ind);
                        e.key = 3;
                        e.num1 = 1;
                        e.num2 = find.get(newGt.toString());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean check11Axiom(GrammarToken gt) {
        if (gt.name.equals("->")) {
            if (gt.first.name.equals("@")) {
                GrammarToken first = gt.first.first;
                GrammarToken second = gt.second;
                String var = gt.first.second.toString();
                GrammarToken ans = getVar(first, second, var);
                if (ans == null) {
                    return first.toString().equals(second.toString());
                }
                if (ans.equals(bad)) {
                    return false;
                }
                HashSet<String> locked = new HashSet<String>();
                return checkFree(first, ans, var, locked);
            }
        }
        return false;
    }

    boolean check12Axiom(GrammarToken gt) {
        if (gt.name.equals("->")) {
            if (gt.second.name.equals("?")) {
                GrammarToken first = gt.second.first;
                GrammarToken second = gt.first;
                String var = gt.second.second.toString();
                GrammarToken ans = getVar(first, second, var);
                if (ans == null) {
                    return true;
                }
                if (ans.equals(bad)) {
                    return false;
                }
                HashSet<String> locked = new HashSet<String>();
                return checkFree(second, ans, var, locked);
            }
        }
        return false;
    }

    boolean checkIndAxiom(GrammarToken gt) {
        if (gt.name.equals("->")) {
            GrammarToken psi = gt.second;
            if (gt.first.name.equals("&")) {
                GrammarToken psi0 = gt.first.first;
                GrammarToken any = gt.first.second;
                if (any.name.equals("@")) {
                    GrammarToken x = any.second;
                    if (any.first.name.equals("->")) {
                        GrammarToken psiFirst = any.first.first;
                        GrammarToken psiSecond = any.first.second;
                        if (!psiFirst.toString().equals(psi.toString())) {
                            return false;
                        }
                        if (!checkFree(psi, x, x.name, new HashSet<>())) {
                            return false;
                        }
                        GrammarToken z = getVar(psi, psi0, x.name);
                        if (z == null) {
                            return false;
                        }
                        if (!z.name.equals("0")) {
                            return false;
                        }
                        GrammarToken x1 = getVar(psi, psiSecond, x.name);
                        if (x1 == null) {
                            return false;
                        }
                        GrammarToken xInc = new Inc(x);
                        if (!x1.toString().equals(xInc.toString())) {
                            return false;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean checkRule1(GrammarToken gt) {
        if (gt.name.equals("->")) {
            if (gt.second.name.equals("@")) {
                GrammarToken first = gt.second.first;
                GrammarToken second = gt.first;
                String var = gt.second.second.toString();
                HashSet<String> locked = new HashSet<String>();
                if (checkFree(first, new Variable(var), var, locked)) {
                    GrammarToken newGt = new Implication(second, first);
                    return find.containsValue(newGt.toString());
                }
                return false;
            }
        }
        return false;
    }

    boolean hasFree(GrammarToken where, String var){
        if (where.isBinary()) {
            boolean ans1 = hasFree(where.first, var);
            boolean ans2 = hasFree(where.second, var);

            return hasFree(where.first, var) || hasFree(where.second, var);
        }
        if (where.name.equals("@") || where.name.equals("?")) {
            if (where.second.name.equals(var)) {
                return false;
            } else {
                return hasFree(where.first, var);
            }
        }
        if (where.isUnary()) {
            return hasFree(where.first, var);
        }
        if (where.isVar()) {
            return where.name.equals(var);
        }
        if (where.isFunc) {
            Function f = (Function) where;
            for (GrammarToken gt : f.vars) {
                if (hasFree(gt, var)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }


    boolean checkFree(GrammarToken where, GrammarToken what, String var, HashSet<String> locked) {
        if (where.isBinary()) {
            HashSet<String> second = new HashSet<String>(locked);
            return checkFree(where.first, what, var, locked) && checkFree(where.second, what, var, locked);
        }
        if (where.name.equals("@") || where.name.equals("?")) {
            if (where.second.name.equals(var)) {
                return true;
            }
            HashSet<String> locked2 = new HashSet<String>(locked);
            locked2.add(where.second.name);
            return checkFree(where.first, what, var, locked2);
        }
        if (where.isUnary()) {
            return checkFree(where.first, what, var, locked);
        }
        if (where.isVar() && where.name.equals(var)) {
            HashSet<String> freeh = what.getFree();
            for (String free : freeh) {
                if (locked.contains(free)) {
                    return false;
                }
            }
            return true;
        }
        if (where.isFunc) {
            Function f = (Function) where;
            for (GrammarToken gt : f.vars) {
                if (!checkFree(gt, what, var, locked)) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    GrammarToken bad = new Variable("-0");

    GrammarToken getVar(GrammarToken mask, GrammarToken where, String Var) {
        if (mask.name.equals("@") || mask.name.equals("?")) {
            if (mask.second.name.equals(Var)) {
                return null;
            }
        }
        if (mask.isVar()){
            if (mask.name.equals(Var)) {
                return where;
            } else {
                if (!mask.name.equals(where.name)) {
                    return bad;
                }
                return null;
            }
        }
        if (!mask.name.equals(where.name)) {
            return bad;
        }
        if (mask.isBinary()) {
            GrammarToken first = getVar(mask.first, where.first, Var);
            GrammarToken second = getVar(mask.second, where.second, Var);
            if ((first == null) || (second == null)) {
                if (first != null) {
                    return first;
                } else {
                    return second;
                }
            }
            if (bad.equals(first) || bad.equals(second)) {
                return bad;
            }
            if (first.toString().equals(second.toString())) {
                return first;
            }
            return bad;
        }
        if (mask.isUnary()) {
            GrammarToken first = getVar(mask.first, where.first, Var);
            return first;
        }
        Function func1 = (Function) mask;
        Function func2 = (Function) where;
        GrammarToken ans = null;
        for (int i = 0; i < func1.vars.size(); i++) {
            GrammarToken tmp = getVar(func1.vars.get(i),func2.vars.get(i),Var);
            if (tmp == null) {
                continue;
            }
            if (bad.equals(tmp)) {
                return bad;
            }
            if (ans == null) {
                ans = tmp;
            } else {
                if (!ans.equals(tmp)) {
                    return bad;
                }
            }
        }
        return ans;
    }

    void MPprove(int ind) {
        Expression alpha = g.get(ind);
        g.remove(ind);
        Vector<Expression> newProve =  new Vector<Expression>();
        for (Expression sigmaI: prove) {
            switch (sigmaI.key) {
                case 1:
                    if (sigmaI.toString().equals(alpha.toString())) {
                        Expression line5 = new Expression(new Implication(alpha, alpha));
                        Expression line1 = new Expression(new Implication(alpha, line5));
                        Expression line4 = new Expression(new Implication(alpha, new Expression(new Implication(line5, alpha))));
                        Expression line3 = new Expression(new Implication(line4, line5));
                        Expression line2 = new Expression(new Implication(line1, line3));
                        newProve.add(line1);
                        newProve.add(line2);
                        newProve.add(line3);
                        newProve.add(line4);
                        newProve.add(line5);
                        break;
                    }
                case 0:
                    Expression mpAS = new Expression(new Implication(alpha, sigmaI));
                    Expression axiomSAS = new Expression(new Implication(sigmaI, mpAS));
                    newProve.add(sigmaI);
                    newProve.add(axiomSAS);
                    newProve.add(mpAS);
                    break;
                case 2:
                    Expression sigmaJ = prove.get(sigmaI.num2);
                    Expression sigmaK = prove.get(sigmaI.num1);
                    Expression line2_3 = new Expression(new Implication(alpha, sigmaI));
                    Expression line2_2 = new Expression(new Implication(new Implication(alpha, sigmaK), new Implication(alpha, sigmaI)));
                    Expression line2_1 = new Expression(new Implication(new Implication(alpha, sigmaJ), line2_2.head.copy()));
                    newProve.add(line2_1);
                    newProve.add(line2_2);
                    newProve.add(line2_3);
                    break;
                case 3:
                    if (sigmaI.num1 == 0) {
                        Expression fi = new Expression(sigmaI.head.first);
                        Expression psi = new Expression(prove.get(sigmaI.num2).head.second);
                        Expression anyPsi = new Expression(sigmaI.head.second);
                        newProve.addAll(generateAny(fi, psi, alpha, anyPsi));
                    } else {
                        Expression fi = new Expression(sigmaI.head.second);
                        Expression existsPsi = new Expression(sigmaI.head.first);
                        Expression psi = new Expression(prove.get(sigmaI.num2).head.first);
                        newProve.addAll(generateExist(fi, psi, alpha, existsPsi));
                    }

            }
        }
        prove = newProve;
        toProves = new Expression(new Implication(alpha, toProves));
        findMP = new HashMap<>();
        find = new HashMap<>();
    }

    Vector<Expression> generateExist(Expression fi, Expression psi, Expression alpha, Expression existsPsi) {
        Vector<Expression> newProve = new Vector<Expression>();

        Expression AF = new Expression(new Implication(alpha, fi));
        Expression AP = new Expression(new Implication(alpha, psi));
        Expression PAP = new Expression(new Implication(psi, AP));
        Expression ePF = new Expression(new Implication(existsPsi, fi));
        Expression ePA = new Expression(new Implication(existsPsi, alpha));
        Expression AePA = new Expression(new Implication(alpha, ePA));
        Expression ePAF = new Expression(new Implication(existsPsi , AF));
        Expression ePAFePF = new Expression(new Implication(ePAF, ePF));
        Expression APF = new Expression(new Implication(alpha.head, new Implication(psi, fi)));
        Expression line5 = ABC(alpha, psi, fi);
        Expression APFAF = new Expression(new Implication(APF, AF));
        Expression line19 = ABC(existsPsi, alpha, fi);


        newProve.add(PAP);
        newProve.add(APF);
        newProve.addAll(generateABAtoBA(APF, psi));
        newProve.add(line5);
        newProve.addAll(generateABAtoBA(line5, psi));
        newProve.addAll(generateABC(psi, AP, APFAF));
        newProve.addAll(generateABC(psi, APF, AF));
        newProve.add(AePA);
        newProve.add(ePAF);
        newProve.addAll(generateABAtoBA(ePAF, alpha));
        newProve.add(line19);
        newProve.addAll(generateABAtoBA(line19, alpha));
        newProve.addAll(generateABC(alpha, ePA, ePAFePF));
        newProve.addAll(generateABC(alpha, ePAF, ePF));

        return newProve;
    }


    Vector<Expression> generateAny(Expression fi, Expression psi, Expression alpha, Expression anyPsi) {
        Vector<Expression> newProve = new Vector<Expression>();

        Expression fPsi = new Expression(new Implication(fi, psi));
        Expression AFP = new Expression(new Implication(alpha, fPsi));
        Expression fAnyPsi = new Expression(new Implication(fi, anyPsi));
        Expression AiF = new Expression(new Conjunction(alpha.head, fi.head));
        Expression AiFF = new Expression(new Implication(AiF, fi));
        Expression AFAiF = new Expression(new Implication(alpha.head, new Implication(fi, AiF)));
        Expression AiFA = new Expression(new Implication(AiF, alpha));
        Expression AiFAnyPsi = new Expression(new Implication(AiF, anyPsi));
        Expression line17 = new Expression(new Implication(AiFAnyPsi.head, new Implication(fi, AiFAnyPsi)));
        Expression FAiFAnyPsi = new Expression(new Implication(fi, AiFAnyPsi));
        Expression line23 = ABC(fi, AiF, anyPsi);
        Expression line13 = new Expression(new Implication(alpha.head, new Implication(fi.head, new Conjunction(alpha.head, fi.head))));

        newProve.addAll(generateABAtoBA(AFP, AiF));
        newProve.add(AiFA);
        newProve.addAll(generateABC(AiF, alpha, fPsi));
        newProve.add(AiFF);
        newProve.addAll(generateABC(AiF, fi, psi));
        newProve.add(AiFAnyPsi);
        newProve.addAll(generateABAtoBA(AiFAnyPsi, alpha));
        newProve.add(line17);
        newProve.addAll(generateABAtoBA(line17, alpha));
        newProve.addAll(generateABC(alpha, AiFAnyPsi, FAiFAnyPsi));
        newProve.add(line23);
        newProve.addAll(generateABAtoBA(line23, alpha));
        newProve.add(AFAiF);
        newProve.addAll(generateABC(alpha, new Expression(line13.head.second) , new Expression(new Implication(FAiFAnyPsi, fAnyPsi))));
        newProve.addAll(generateABC(alpha, new Expression(new Implication(fi, AiFAnyPsi)) ,fAnyPsi));
        return newProve;
    }

    Vector<Expression> generateABAtoBA(Expression A, Expression B) {
        Expression line2 = new Expression(new Implication(B, A));
        Expression line1 = new Expression(new Implication(A, line2));
        Vector<Expression> proof = new Vector<Expression>();
        proof.add(line1);
        proof.add(line2);
        return proof;
    }

    Vector<Expression> generateABC(Expression A, Expression B, Expression C) {
        Expression AC = new Expression(new Implication(A, C));
        Expression AB = new Expression(new Implication(A, B));
        Expression ABC = new Expression(new Implication(A.head, new Implication(B, C)));
        Expression line2 = new Expression(new Implication(ABC, AC));
        Expression line1 = new Expression(new Implication(AB, line2));
        Vector<Expression> proof = new Vector<Expression>();
        proof.add(line1);
        proof.add(line2);
        proof.add(AC);
        return proof;
    }

    Expression ABC(Expression A, Expression B, Expression C) {
        Expression AC = new Expression(new Implication(A, C));
        Expression AB = new Expression(new Implication(A, B));
        Expression ABC = new Expression(new Implication(A.head, new Implication(B, C)));
        Expression line2 = new Expression(new Implication(ABC, AC));
        return new Expression(new Implication(AB, line2));
    }


    public void mpOneThing() {
        MPprove(g.size() - 1);
    }

}
