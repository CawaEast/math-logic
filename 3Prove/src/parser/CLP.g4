grammar CLP;
@header {
    import grammar.*;
    import java.util.Vector;
}

@members {
    public Proof proof = new Proof();
    public Vector<Expression> axioms = new Vector<Expression>();
    static void wr(String x) {System.out.print(x);}
    static void tabs(int amount) {for(int i=0; i<amount; i++) System.out.print(' ');}
}

start
    : file EOF
    ;

axioms
    : (e = expression{axioms.add(new Expression($e.rgt));} '\n')+
         ;


file
    :   header '\n' (a = expression{proof.addProve(new Expression($a.rgt));} '\n')*
    ;

header
    :   (a = expression{proof.addG(new Expression($a.rgt));} (',' a = expression{proof.addG(new Expression($a.rgt));})*)? '|-' a = expression{proof.setToProve(new Expression($a.rgt));}
    ;

expression returns [GrammarToken rgt]
    :   a = disjunction{$rgt = $a.rgt;} ('->' b = expression{$rgt = new Implication($a.rgt, $b.rgt);})?
    ;

disjunction returns [GrammarToken rgt]
    :   a = conjunction{$rgt = $a.rgt;}
    |   aa = disjunction '|' b = conjunction{$rgt = new Disjunction($aa.rgt, $b.rgt);}
    ;

conjunction  returns [GrammarToken rgt]
    :   a = negate{$rgt = $a.rgt;}
    |   aa = conjunction '&' b = negate{$rgt = new Conjunction($aa.rgt, $b.rgt);}
    ;

negate returns [GrammarToken rgt]
    :   '!' a = negate{$rgt = new Negate($a.rgt);}
    |   n = Name{$rgt = new Variable($n.text);}
    |   '(' b = expression{$rgt = $b.rgt;} ')'
    ;

Name
    : [a-zA-Z]([a-zA-Z0-9])*
    ;

Whitespace
    :   [ \t\r]+ -> skip;