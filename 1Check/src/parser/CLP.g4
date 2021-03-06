grammar CLP;
@header {
    import grammar.*;
    import java.util.Vector;
}

@members {
    public Grammar grammar = new Grammar();
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
    :   header '\n' (a = expression{grammar.addProve(new Expression($a.rgt));} '\n')*
    ;

header
    :   (a = expression{grammar.addG(new Expression($a.rgt));} (',' a = expression{grammar.addG(new Expression($a.rgt));})*)? '|-' a = expression{grammar.setToProve(new Expression($a.rgt));}
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
    : [A-Z]([A-Z0-9])*
    ;

Whitespace
    :   [ \t\r]+ -> skip;