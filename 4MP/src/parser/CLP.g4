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
    :   a = conjunction{$rgt = $a.rgt;}
    |   aa = disjunction '|' b = conjunction{$rgt = new Disjunction($aa.rgt, $b.rgt);}
    ;

conjunction  returns [GrammarToken rgt]
    :   a = unary{$rgt = $a.rgt;}
    |   aa = conjunction '&' b = unary{$rgt = new Conjunction($aa.rgt, $b.rgt);}
    ;

unary returns [GrammarToken rgt]
    :   '!' a = unary{$rgt = new Negate($a.rgt);}
    |   n = predicat{$rgt = $n.rgt;}
    |   '(' b = expression{$rgt = $b.rgt;} ')'
    |   '@' v2 = Varrable a = unary{$rgt = new Any(new Variable($v2.text), $a.rgt);}
    |   '?' v3 = Varrable a = unary{$rgt = new Exist(new Variable($v3.text), $a.rgt);}
    |   va = NamePred{$rgt = new Variable($va.text);}
    ;

predicat returns [GrammarToken rgt]
    :  a = NamePred{ Function tmp = new Function($a.text); }  '(' r = term{ tmp.addParam($r.rgt); } (',' r = term{ tmp.addParam($r.rgt); })*')' { $rgt = tmp; }
    |   t1 = term '=' t2 = term{$rgt = new Equals($t1.rgt, $t2.rgt);}
    ;

term returns [GrammarToken rgt]
    :   a1 = add{$rgt = $a1.rgt;}
    |   a = term '+' b = add{$rgt = new Add($a.rgt, $b.rgt);}
    ;

add returns [GrammarToken rgt]
    :   q = mull{$rgt = $q.rgt;}
    |   a = add '*' b = mull{$rgt = new Mull($a.rgt, $b.rgt);}
    ;

mull returns [GrammarToken rgt]
    :   a = Varrable{ Function tmp = new Function($a.text); }  '(' r = term{ tmp.addParam($r.rgt); } (',' r = term{ tmp.addParam($r.rgt); })*')'{ $rgt = tmp; }
    |   q = Varrable{ $rgt = new Variable($q.text); }
    |   '('a3 = term{ $rgt = $a3.rgt; }')'
    |   '0'{ $rgt = new Zero(); }
    |   a5 = mull'\''{ $rgt = new Inc($a5.rgt); }
    ;

NamePred
    :   [A-Z]([A-Z0-9])*
    ;

Varrable
    :   [a-z]([a-z0-9])*
    ;

Whitespace
    :   [ \t\r]+ -> skip;