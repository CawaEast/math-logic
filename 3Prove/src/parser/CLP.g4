proof CLP;
@header {
    import proof.*;
    import java.util.Vector;
}

@members {
    public Grammar proof = new Grammar();
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
    :   a = conjunction{$rgt = $a.rgt;} ('|' b = disjunction{$rgt = new Disjunction($a.rgt, $b.rgt);})?
    ;

conjunction  returns [GrammarToken rgt]
    :   a = negate{$rgt = $a.rgt;} ('&' b = conjunction{$rgt = new Conjunction($a.rgt, $b.rgt);})?
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