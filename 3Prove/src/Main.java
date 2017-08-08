import grammar.AnsGenerator;
import grammar.Proof;
import grammar.ProofGenerator;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.tool.Grammar;
import parser.CLPLexer;
import parser.CLPParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Proof g = getGrammar("axioms.txt", "input.txt");
        if (g == null) {
            return;
        }
        AnsGenerator ansGenerator = new AnsGenerator();
        ProofGenerator proofGenerator = new ProofGenerator(g.toProves);
        proofGenerator.setAxioms(g.axioms);
        proofGenerator.solve();
        if (proofGenerator.cntrAns != null) {
            ansGenerator.generate("input", proofGenerator.cntrAns);
            return;
        }
        g.checkExpressions();
        Proof gr = proofGenerator.proof;
        gr.checkExpressions();
        ansGenerator.generate("input", gr, false);
    }

    static Proof getGrammar(String axioms, String input) {
        try {
            CLPLexer lexer = new CLPLexer(CharStreams.fromStream(new FileInputStream(new File(input))));
            TokenStream ctk = new CommonTokenStream(lexer);
            CLPParser parser = new CLPParser(ctk);
            CLPParser.StartContext prc = parser.start();
            Proof proof = parser.proof;
            CLPLexer lexerAxioms = new CLPLexer(CharStreams.fromStream(new FileInputStream(new File(axioms))));
            TokenStream ctkAxioms = new CommonTokenStream(lexerAxioms);
            CLPParser parserAxioms = new CLPParser(ctkAxioms);
            parserAxioms.axioms();
            proof.setAxioms(parserAxioms.axioms);
            return proof;
        }catch (IOException e) {
            System.out.print("Error in file");
            e.printStackTrace();
            return null;
        }
    }
}
