import grammar.AnsGenerator;
import grammar.Grammar;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import parser.CLPLexer;
import parser.CLPParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Grammar g = getGrammar("axioms.txt", "input.txt");
        if (g == null) {
            return;
        }
        AnsGenerator generator = new AnsGenerator();
        g.checkExpressions();
        g.mpOneThing();
        generator.generate("input", g, false);
        /*g.checkExpressions();
        generator.generate("input1", g, true);*/
    }

    static Grammar getGrammar(String axioms, String input) {
        try {
            CLPLexer lexer = new CLPLexer(CharStreams.fromStream(new FileInputStream(new File(input))));
            TokenStream ctk = new CommonTokenStream(lexer);
            CLPParser parser = new CLPParser(ctk);
            CLPParser.StartContext prc = parser.start();
            Grammar grammar = parser.grammar;
            CLPLexer lexerAxioms = new CLPLexer(CharStreams.fromStream(new FileInputStream(new File(axioms))));
            TokenStream ctkAxioms = new CommonTokenStream(lexerAxioms);
            CLPParser parserAxioms = new CLPParser(ctkAxioms);
            parserAxioms.axioms();
            grammar.setAxioms(parserAxioms.axioms);
            return grammar;
        }catch (IOException e) {
            System.out.print("Error in file");
            e.printStackTrace();
            return null;
        }
    }
}
