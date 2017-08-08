package grammar;

import grammar.Expression;
import grammar.Grammar;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Cawa on 17.07.2017.
 */
public class AnsGenerator {

    StringBuilder buffer;
    public Grammar grammar;

    public void generate(String name, Grammar grammar, boolean rules) {
        String s = name + "Ans.txt";
        try (Writer wr = Files.newBufferedWriter(Paths.get(s))) {
            buffer = new StringBuilder();
            generate(grammar, rules);
            wr.write(buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void generate(Grammar grammar, boolean rules) {
        boolean b = true;
        for (Expression expr : grammar.g) {
            if (b) {
                write(expr.head.toString());
                b = false;
            } else {
                write( ", " + expr.toString());
            }
        }
        write("|-");
        writeln(grammar.toProves.toString());
        for (int i = 0; i < grammar.prove.size(); i++) {
            Expression e = grammar.prove.get(i);
            if (rules) {
                writeln("(" + (i + 1) + ") " + e.toString(true));
            } else {
                writeln(e.toString());
            }
        }
    }

    void write(String str) {
        buffer.append(str);
    }

    void writeln(String str) {
        write(str + System.lineSeparator());
    }

    void writeln() {
        writeln("");
    }
}
