package grammar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import java.util.function.Function;

/**
 * Created by Cawa on 20.07.2017.
 */
public class ProofGenerator {

    public Vector<TempProof> ans;
    public TempProof proof;

    class TempProof extends Proof {

        public HashMap<String, Boolean> suggestions;
        int bad = 0;// 1 - exists bad, 2 - any is bad

        public TempProof(Expression expr, Vector<Expression> g) {
            this.toProves = expr;
            if (g != null) {
                this.g = new Vector<Expression>(g);
            } else {
                this.g = new Vector<>();
            }
            this.prove = new Vector<>();
            suggestions = new HashMap<>();
            this.axioms = axiomsG;
        }

        public TempProof(Expression expr) {
            this.toProves = expr;
            this.g = new Vector<>();
            this.prove = new Vector<>();
            suggestions = new HashMap<>();
            this.axioms = axiomsG;
        }

        public TempProof(GrammarToken expr) {
            this.toProves = new Expression(expr);
            this.g = new Vector<>();
            this.prove = new Vector<>();
            suggestions = new HashMap<>();
            this.axioms = axiomsG;
        }

        public TempProof(TempProof other) {
            this.toProves = other.toProves;
            this.g = new Vector<>(other.g);
            this.prove = new Vector<>(other.prove);
            suggestions = new HashMap<>(other.suggestions);
            this.axioms = axiomsG;
        }


        void add(TempProof other) {
            prove.addAll(other.prove);
            for (String key: other.suggestions.keySet()) {
                suggestions.put(key, other.suggestions.get(key));
            }
            for (Expression expr : other.g) {
                if (!g.contains(expr)) {
                    g.add(expr);
                }
            }
        }

        String findDiff(TempProof other) {
            for (String key: other.suggestions.keySet()) {
                if (suggestions.keySet().contains(key)) {
                    if(suggestions.get(key) != other.suggestions.get(key)) {
                        return key;
                    }
                }
            }
            return null;
        }

        Vector<String> findDiffVec(TempProof other) {
            Vector<String> ans = new Vector<>();
            for (String key: other.suggestions.keySet()) {
                if (suggestions.keySet().contains(key)) {
                    if(suggestions.get(key) != other.suggestions.get(key)) {
                        ans.add(key);
                    }
                }
            }
            return ans;
        }
    }


    Expression isProoving;
    HashSet<String> var;
    Vector<Expression> axiomsG;



    public void setAxioms(Vector<Expression> axioms) {
        this.axiomsG = axioms;
    }



    public ProofGenerator(Expression expr) {
        isProoving = expr;
        var = new HashSet<>();
    }

    void findFreeVar() {
        findFreeVar(isProoving.head);
    }

    void findFreeVar(GrammarToken gt) {
        if (gt.isVar()) {
            var.add(gt.name);
        } else {
            findFreeVar(gt.first);
            if (gt.isBinary()) {
                findFreeVar(gt.second);
            }
        }
    }

    public HashMap<String, Boolean> cntrAns = null;

    public void solve() {
        findFreeVar();
        cntrAns = findBadAns(isProoving);
        if (cntrAns != null) {
            return;
        }
        TempProof init = new TempProof(isProoving, axiomsG);
        ans = find(init);
        ans = filter(ans);
        //proof = ans.get(2);
        //proof = colapse(ans.get(1), ans.get(2),"A");

        ans = colapse(ans);
        for (TempProof p : ans) {
            if (p.suggestions.size() == 0) {
                proof = p;
                return;
            }
        }
    }

    HashMap<String, Boolean> findBadAns(Expression expr) {
        HashMap<String, Boolean> vars = new HashMap<>();
        for (String str : var) {
            vars.put(str, false);
        }
        while (true) {
            if (!check(expr.head, vars)) {
                return vars;
            }
            boolean inc = true;
            for (String key : var) {
                if (vars.get(key)) {
                    vars.put(key, false);
                } else {
                    vars.put(key, true);
                    inc = false;
                    break;
                }
            }
            if (inc) {
                return null;
            }
        }

    }

    boolean check(GrammarToken expr, HashMap<String, Boolean> vars) {
        if(expr.isVar()) {
            return vars.get(expr.name);
        }
        switch(expr.name) {
            case "&":
                return check(expr.first, vars) & check(expr.second, vars);
            case "|":
                return check(expr.first, vars) | check(expr.second, vars);
            case "->":
                return !check(expr.first, vars) | check(expr.second, vars);
            case "!":
                return !check(expr.first, vars);
            default:
                return false;
        }
    }

    Vector<TempProof> filter(Vector<TempProof> vector) {
        Vector<TempProof> ans = new Vector<>();
        for (int i = 0; i < vector.size(); i++) {
            TempProof proof1 = vector.get(i);
            if (proof1.bad == 1) {
                continue;
            }
            for (int j = i + 1; j < vector.size(); j++) {
                TempProof proof2 = vector.get(j);
                if (in(proof2, proof1)) {
                    proof2.bad = 1;
                } else {
                    if (in(proof1, proof2)) {
                        proof1.bad = 1;
                    }
                }
            }
        }
        for (TempProof proof: vector) {
            if (proof.bad != 1) {
                ans.add(proof);
            }
        }
        return ans;
    }

    boolean in(TempProof A, TempProof B) {
        for (Expression expr: B.g) {
            if (!A.g.contains(expr)) {
                return false;
            }
        }
        return true;
    }

    Vector<TempProof> find(TempProof proof){
        GrammarToken gt = proof.toProves.head;
        Vector<TempProof> ans = null;
        if (gt.isVar()) {
            proof.suggestions.put(proof.toProves.head.name, true);
            proof.addProve(proof.toProves);
            proof.addG(proof.toProves);
            ans = new Vector<>();
            ans.add(proof);
        } else {
            switch (gt.name) {
                case "&":
                    ans = switchAnd(gt);
                    break;
                case "|":
                    ans = switchOr(gt);
                    break;
                case "->":
                    ans = switchImpl(gt);
                    break;
                case "!":
                    switch (gt.first.name) {
                        case "&":
                            ans = switchNotAnd(gt.first);
                            break;
                        case "|":
                            ans = switchNotOr(gt.first);
                            break;
                        case "->":
                            ans = switchNotImpl(gt.first);
                            break;
                        case "!":
                            ans = switchNegNeg(gt.first.first);
                            break;
                        default:
                            proof.addG(proof.toProves);
                            proof.addProve(proof.toProves);
                            proof.suggestions.put(proof.toProves.head.first.name, false);
                            ans = new Vector<>();
                            ans.add(proof);
                            break;
                    }
                    break;
            }
        }
        return (ans);
    }

    Vector<TempProof> switchNegNeg(GrammarToken gt) {
        TempProof first = new TempProof(new Expression(gt));
        Vector<TempProof> ans1 = find(first);
        Vector<TempProof> ans = new Vector<>();
        for (TempProof proof : ans1) {
                ans.add(addNegNeg(proof, gt));
        }
        return (ans);
    }

    TempProof addNegNeg(TempProof proof, GrammarToken gt) {
        proof.checkExpressions();
        Expression expr = proof.prove.lastElement();
        TempProof tmp = new TempProof(gt);
        Expression notA = new Expression(new Negate(gt));
        Expression A = new Expression(gt);
        tmp.addG(notA);
        tmp.addProve(notA);
        tmp.MPprove(notA);
        proof.add(tmp);
        Expression lineA2 = new Expression(new Implication(notA, A));
        proof.add(generateABAtoBA(A, notA));
        Expression line3 = new Expression(new Negate(notA.head));
        Expression line2 = new Expression(new Implication(new Implication(notA, notA), line3.head));
        Expression line1 = new Expression(new Implication(lineA2, line2));
        proof.addProve(line1);
        proof.addProve(line2);
        proof.addProve(line3);
        proof.toProves = line3;
        return compress(proof);
    }


    Vector<TempProof> switchNotImpl(GrammarToken gt) {
        TempProof first = new TempProof(new Expression(gt.first));
        TempProof second = new TempProof(new Expression(new Negate(gt.second)));
        Vector<TempProof> ans1 = find(first);
        Vector<TempProof> ans2 = find(second);
        Vector<TempProof> ans = new Vector<>();
        for (TempProof proof1 : ans1) {
            for (TempProof proof2 : ans2) {
                if (proof1.findDiff(proof2) == null) {
                    ans.add(addNotImpl(proof1, proof2, gt));
                }
            }
        }
        return (ans);
    }


    TempProof addNotImpl(TempProof proof, TempProof second, GrammarToken gt) {
        proof.toProves = new Expression(new Negate(gt));
        Expression lineA1 = new Expression(gt);
        Expression lineA2 = new Expression(gt.first);
        Expression lineA3 = new Expression(gt.second);
        proof.addProve(lineA1);
        proof.addProve(lineA2);
        proof.addProve(lineA3);
        proof.addG(lineA1);
        proof.MPprove(lineA1);
        proof.add(second);
        Expression line2 = new Expression(new Implication(gt, new Negate(gt.second)));
        Expression line1 = new Expression(new Implication(new Negate(gt.second), line2.head));
        Expression line5 = new Expression(new Negate(gt));
        Expression line4 = new Expression(new Implication(new Implication(gt, new Negate(gt.second)), line5.head));
        Expression line3 = new Expression(new Implication(new Implication(gt, gt.second), line4.head));
        proof.addProve(line1);
        proof.addProve(line2);
        proof.addProve(line3);
        proof.addProve(line4);
        proof.addProve(line5);
        return compress(proof);
    }


    Vector<TempProof> switchNotOr(GrammarToken gt) {
        TempProof first = new TempProof(new Expression(new Negate(gt.first)));
        TempProof second = new TempProof(new Expression(new Negate(gt.second)));
        Vector<TempProof> ans1 = find(first);
        Vector<TempProof> ans2 = find(second);
        return (MullProofs(ans1, ans2, new Negate(gt), terribleThing));
    }


    Function<TempProof, TempProof> terribleThing = (TempProof first) -> {

        TempProof second = new TempProof(first);
        second.add(first);
        Expression A = new Expression(first.toProves.head.first.first);
        Expression notA = new Expression(new Negate(A.head));
        Expression B = new Expression(first.toProves.head.first.second);
        Expression notB = new Expression(new Negate(B.head));
        doTerrible(first, A, A, notA, notB);
        doTerrible(second, A, B, notA, notB);
        first.add(second);
        Expression AorB = new Expression(first.toProves.head.first);/*
        Expression line2 = new Expression(new Implication(A, notA));
        Expression line1 = new Expression(new Implication(notA, line2));
        Expression line4 = new Expression(new Implication(A, notB));
        Expression line3 = new Expression(new Implication(notB, line4));*/
        first.add(generateABAtoBA(notA, A));
        first.add(generateABAtoBA(notA, B));
        first.add(generate8Axiom(A, B, notA));
        first.add(generate8Axiom(A, B, A));
        first.add(generate9Axiom(AorB, A));/*
        Expression line7 = new Expression(new Implication(AorB, notA));
        Expression line6 = new Expression(new Implication(new Implication(A, notA), line7.head));
        Expression line5 = new Expression(new Implication(new Implication(B, notA), line6.head));
        Expression line10 = new Expression(new Implication(AorB, A));
        Expression line9 = new Expression(new Implication(new Implication(A, A), line10.head));
        Expression line8 = new Expression(new Implication(new Implication(B, A), line9.head));*/
        /*
        Expression line12 = new Expression(new Implication(line7.head, first.toProves.head));
        Expression line11 = new Expression(new Implication(line10, line12));*/
        /*
        first.addProve(line1);
        first.addProve(line2);
        first.addProve(line3);
        first.addProve(line4);
        first.addProve(line5);
        first.addProve(line6);
        first.addProve(line7);
        first.addProve(line8);
        first.addProve(line9);
        first.addProve(line10);
        first.addProve(line11);
        first.addProve(line12);
        first.addProve(new Expression(first.toProves.head));*/
        first.checkExpressions();
        Expression expr = first.prove.lastElement();
        return compress(first);
    };

    void doTerrible(TempProof proof, Expression A, Expression B, Expression notA, Expression notB1) {
        proof.addG(B);
        proof.addProve(B);
        Expression notB = new Expression(new Negate(B.head));
        proof.add(generateABAtoBA(notB, notA));
        proof.add(generateABAtoBA(B, notA));
        proof.add(generate9Axiom(notA, B));
        proof.add(generateNotNotA(A));/*
        Expression line2 = new Expression(new Implication(notA, B));
        Expression line1 = new Expression(new Implication(B, line2));
        Expression line4 = new Expression(new Implication(notA, notB));
        Expression line3 = new Expression(new Implication(notB, line4));
        Expression line7 = new Expression(new Negate(notA.head));
        Expression line6 = new Expression(new Implication(line4, line7));
        Expression line5 = new Expression(new Implication(line2, line6));
        Expression line8 = new Expression(new Implication(line7, A));
        proof.addProve(line1);
        proof.addProve(line2);
        proof.addProve(line3);
        proof.addProve(line4);
        proof.addProve(line5);
        proof.addProve(line6);
        proof.addProve(line7);
        proof.addProve(line8);
        proof.addProve(A);*/
        proof.checkExpressions();
        proof.MPprove(B);
    }

    Vector<TempProof> switchNotAnd(GrammarToken gt) {
        TempProof first = new TempProof(new Expression(new Negate(gt.first)));
        TempProof second = new TempProof(new Expression(new Negate(gt.second)));
        Vector<TempProof> ans1 = find(first);
        Vector<TempProof> ans2 = find(second);
        Vector<TempProof> ans = new Vector<>();
        for (TempProof proof1 : ans1) {
            ans.add(addNotAnd(proof1, gt));
        }
        for (TempProof proof2 : ans2) {
            ans.add(addNotAnd(proof2, gt));
        }
        return (ans);
    }

    TempProof addNotAnd(TempProof proof, GrammarToken gt) {
        Expression notA = proof.toProves;
        Expression line6 = new Expression(new Negate(gt));
        Expression line1 = new Expression(new Implication(gt, notA.head.first));
        Expression line3 = new Expression(new Implication(gt, notA.head));
        Expression line2 = new Expression(new Implication(notA, line3));
        Expression line5 = new Expression(new Implication(line3, line6));
        Expression line4 = new Expression(new Implication(line1, line5));
        proof.addProve(line1);
        proof.addProve(line2);
        proof.addProve(line3);
        proof.addProve(line4);
        proof.addProve(line5);
        proof.addProve(line6);
        proof.toProves = line6;
        //proof.toProves = new Expression(new Negate(gt));
        return compress(proof);
    }

    Vector<TempProof> switchAnd(GrammarToken gt) {
        TempProof first = new TempProof(new Expression(gt.first));
        TempProof second = new TempProof(new Expression(gt.second));
        Vector<TempProof> ans1 = find(first);
        Vector<TempProof> ans2 = find(second);
        return (MullProofs(ans1, ans2, gt, addAnd));
    }

    Function<TempProof, TempProof> addAnd = (TempProof proof) -> {
        Expression line3 = proof.toProves;
        Expression line2 = new Expression(new Implication(line3.head.second, line3.head));
        Expression line1 = new Expression(new Implication(line3.head.first, line2.head));
        proof.addProve(line1);
        proof.addProve(line2);
        proof.addProve(line3);
        return compress(proof);
    };

    Vector<TempProof> switchOr(GrammarToken gt) {
        TempProof first = new TempProof(new Expression(gt.first));
        TempProof second = new TempProof(new Expression(gt.second));
        Vector<TempProof> ans1 = find(first);
        Vector<TempProof> ans2 = find(second);
        Vector<TempProof> ans = new Vector<>();
        Expression pr = new Expression(gt);
        for (TempProof proof1 : ans1) {
            proof1.toProves = pr;
            ans.add(addOr(proof1, gt.first));
        }
        for (TempProof proof2 : ans2) {
            proof2.toProves = pr;
            ans.add(addOr(proof2, gt.second));
        }
        return (ans);
    }

    TempProof addOr(TempProof proof, GrammarToken gt) {
        Expression line2 = proof.toProves;
        Expression line1 = new Expression(new Implication(gt, line2.head));
        proof.addProve(line1);
        proof.addProve(line2);
        return compress(proof);
    };

    Vector<TempProof> switchImpl(GrammarToken gt) {
        Vector<TempProof> ans = new Vector<>();
        TempProof second = new TempProof(new Expression(gt.second));
        TempProof NegFirst = new TempProof(new Expression(new Negate(gt.first)));
        Vector<TempProof> ans2 = find(second);
        Expression pr = new Expression(gt);
        for (TempProof proof2 : ans2) {
            proof2.toProves = pr;
            ans.add(addOr(proof2, gt.second));
        }
        Vector<TempProof> ans1N = find(NegFirst);
        for (TempProof proof1 : ans1N) {
            proof1.toProves = pr;
            ans.add(addImpl(proof1, gt));
        }
        return ans;
    }

    TempProof addImpl(TempProof proofO, GrammarToken gt) {
        proofO.checkExpressions();
        Expression expr = proofO.prove.lastElement();
        TempProof proof = new TempProof(proofO);
        Expression notB = new Expression(new Negate(gt.second));
        Expression A = new Expression(gt.first);
        Expression notA = new Expression(new Negate(gt.first));
        Expression B = new Expression(gt.second);
        TempProof nBimplA = generateABAtoBA(A, notB);
        TempProof nBimplNA = generateABAtoBA(notA, notB);
        TempProof nnB = generate9Axiom(notB, A);
        TempProof proofB = generateNotNotA(B);
        proof.checkExpressions();
        expr = proof.prove.lastElement();
        proof.addG(A);
        proof.addProve(A);
        proof.addProve(notA);
        proof.add(nBimplA);
        proof.add(nBimplNA);
        proof.add(nnB);
        proof.add(proofB);
        proof.checkExpressions();
        expr = proof.prove.lastElement();
        proof.MPprove(A);
        return compress(proof);
    };

    Vector<TempProof> MullProofs(Vector<TempProof> vector1, Vector<TempProof> vector2, GrammarToken gt,
                              Function<TempProof, TempProof> func) {
        Vector<TempProof> ans = new Vector<>();
        for (TempProof proof1 : vector1) {
            for (TempProof proof2 : vector2) {
                TempProof tmp = combine(proof1, proof2, gt);
                if (tmp != null) {
                    ans.add(func.apply(tmp));
                }
            }
        }
        return ans;
    }

    TempProof combine(TempProof first, TempProof second, GrammarToken gt) {
        String var = first.findDiff(second);
        if (var != null) {
            return null;
        }
        TempProof proof = new TempProof(gt);
        proof.add(first);
        proof.add(second);
        return compress(proof);
    }

    TempProof generate8Axiom(Expression A, Expression B, Expression C) {
        TempProof ans = new TempProof(new Negate(A.head));
        Expression or = new Expression(new Disjunction(A.head, B.head));
        Expression line3 = new Expression(new Implication(or, C));
        Expression line2 = new Expression(new Implication(new Implication(B, C), line3.head));
        Expression line1 = new Expression(new Implication(new Implication(A, C), line2.head));
        ans.addProve(line1);
        ans.addProve(line2);
        ans.addProve(line3);
        return ans;
    }

    TempProof generate9Axiom(Expression A, Expression B) {
        TempProof ans = new TempProof(new Negate(A.head));
        Expression line3 = new Expression(new Negate(A.head));
        Expression line2 = new Expression(new Implication(new Implication(A.head, new Negate(B.head)), new Negate(A.head)));
        Expression line1 = new Expression(new Implication(new Implication(A, B), line2.head));
        ans.addProve(line1);
        ans.addProve(line2);
        ans.addProve(line3);
        return ans;
    }

    TempProof generateAA(Expression A) {
        TempProof ans = new TempProof(new Implication(A, A));
        ans.addProve(A);
        ans.addG(A);
        ans.MPprove(A);
        return ans;
    }

    TempProof generateABAtoBA(Expression A, Expression B) {
        Expression line2 = new Expression(new Implication(B, A));
        Expression line1 = new Expression(new Implication(A, line2));
        TempProof ans = new TempProof(line2);
        ans.addProve(line1);
        ans.addProve(line2);
        return ans;
    }

    TempProof generateNotNotA(Expression A) {
        TempProof ans = new TempProof(A.head);
        Expression line1 = new Expression(new Implication(new Negate(new Negate(A.head)), A.head));
        ans.addProve(line1);
        ans.addProve(A);
        return ans;
    }

    TempProof generateAiBinBinA(Expression A, Expression B) {
        Expression AB = new Expression(new Implication(A, B));
        Expression notA = new Expression(new Negate(A.head));
        Expression notB = new Expression(new Negate(B.head));
        TempProof ans = new TempProof(new Implication(AB.head, new Implication(notB, notA)));
        ans.addG(AB);
        ans.addG(notB);
        Expression line6 = new Expression(new Implication(A, notB));
        Expression line4 = new Expression(new Implication(notB, line6));
        Expression line3 = new Expression(new Implication(line6, notA));
        Expression line1 = new Expression(new Implication(AB, line3));
        ans.addProve(line1);
        ans.addProve(AB);
        ans.addProve(line3);
        ans.addProve(line4);
        ans.addProve(notB);
        ans.addProve(line6);
        ans.addProve(notA);
        ans.checkExpressions();
        ans.MPprove(notB);
        ans.checkExpressions();
        ans.MPprove(AB);
        return compress(ans);
    }

    TempProof generateAOrA(Expression A) {
        Expression notA = new Expression(new Negate(A.head));
        Expression AornA = new Expression(new Disjunction(A.head, notA.head));
        Expression nAornA = new Expression(new Negate(AornA.head));
        TempProof ans = new TempProof(A.head);
        Expression line1 = new Expression(new Implication(A, AornA));
        TempProof proof1 = generateAiBinBinA(A, AornA);
        Expression line2 = new Expression(new Implication(notA, AornA));
        TempProof proof2 = generateAiBinBinA(notA, AornA);
        TempProof proof3 = generateAiBinBinA(notA, nAornA);
        TempProof proof4 = generate9Axiom(nAornA, notA);
        TempProof proof5 = generateNotNotA(AornA);
        ans.addProve(line1);
        ans.add(proof1);
        ans.addProve(new Expression(new Implication(nAornA, notA)));
        ans.addProve(line2);
        ans.add(proof2);
        ans.addProve(new Expression(new Implication(nAornA, new Expression(new Negate(notA.head)))));
        ans.add(proof3);
        ans.add(proof4);
        ans.add(proof5);
        return compress(ans);
    }


/*
    Vector<TempProof> colapse(Vector<TempProof> vector) {
        int size = vector.size();
        Vector<TempProof> ans = new Vector<>();
        while (ans.size() < vector.size()){
            if (ans.size() > 0) {
                vector = ans;
            }
            ans = new Vector<>();
            for (int i = 0; i < vector.size(); i++) {
                TempProof proof1 = vector.get(i);
                for (int j = i + 1; j < vector.size(); j++) {
                    TempProof proof2 = vector.get(j);
                    Vector<String> vec = proof1.findDiffVec(proof2);
                    if ((vec.size() == 1)) {
                        proof1.bad = 1;
                        proof2.bad = 1;
                        TempProof newProof = colapse(proof1, proof2, vec.get(0));
                        ans.add(newProof);
                        break;
                    }
                }
                if (proof1.bad == 0) {
                    ans.add(proof1);
                }
            }
        }
        return ans;
    }*/

    Vector<TempProof> colapse(Vector<TempProof> vector) {
        boolean cont = true;
        while (cont) {
            cont = false;
            int size = vector.size();
            Vector<TempProof> ans = new Vector<>();
            for (String str : var) {
                for (int i = 0; i < vector.size(); i++) {
                    TempProof proof1 = vector.get(i);
                    for (int j = i + 1; j < vector.size(); j++) {
                        TempProof proof2 = vector.get(j);
                        Vector<String> vec = proof1.findDiffVec(proof2);
                        if ((vec.size() == 1)) {
                            if (vec.get(0).equals(str)) {
                                TempProof newProof = colapse(proof1, proof2, vec.get(0));
                                ans.add(newProof);
                            }
                        }
                    }
                }
                if (ans.size() > 0) {
                    vector.addAll(ans);
                    ans = new Vector<>();
                    vector = filter(vector);
                    cont = true;

                }
            }/*
            if (ans.size() > 0) {
                vector.addAll(ans);
                vector = filter(vector);
            } else {
                break;
            }*/
        }
        return vector;
    }



    TempProof colapse(TempProof proof1O, TempProof proof2O, String var) {
        TempProof proof1 = new TempProof(proof1O);
        TempProof proof2 = new TempProof(proof2O);
        TempProof ans = new TempProof(proof1O.toProves);
        proof1.suggestions.remove(var);
        proof2.suggestions.remove(var);
        Expression A = new Expression(new Variable(var));
        Expression notA = new Expression(new Negate(new Variable(var)));
        proof1.checkExpressions();
        proof2.checkExpressions();
        Expression last1 = proof1.prove.lastElement();
        Expression last2 = proof2.prove.lastElement();
        if (proof1.g.contains(A)) {
            proof1.MPprove(A);
            proof2.MPprove(notA);
        } else {
            proof2.MPprove(A);
            proof1.MPprove(notA);
        }
        proof1.checkExpressions();
        proof2.checkExpressions();
        last1 = proof1.prove.lastElement();
        last2 = proof2.prove.lastElement();
        ans.add(proof1);
        ans.add(proof2);
        ans.add(generateAOrA(A));
        ans.checkExpressions();
        Expression last = ans.prove.lastElement();
        ans.add(generate8Axiom(A, notA, proof1O.toProves));
        last = ans.prove.lastElement();
        ans.addProve(ans.toProves);
        last = ans.prove.lastElement();
        ans.checkExpressions();
        //ans = compress(ans);
        ans.checkExpressions();
        last = ans.prove.lastElement();
        //return proof2;
        return ans;
    }

    TempProof compress(TempProof proof) {
        HashSet<String> strings = new HashSet<>();
        TempProof ans = new TempProof(proof.toProves);
        String pr = ans.toProves.toString();
        ans.g = proof.g;
        ans.axioms = proof.axioms;
        ans.suggestions = proof.suggestions;
        for (Expression expr : proof.prove) {
            String str = expr.toString();
            if (!strings.contains(str)) {
                strings.add(str);
                ans.addProve(expr);
                if (str.equals(pr)) {
                    return ans;
                }
            }
        }
        return ans;
        //return proof;
    }
}
