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
    int size = 100000;
    Writer writer;

    public void generate(String name, Grammar grammar, boolean rules) {
        String s = name + "Ans.txt";
        try {
            writer = Files.newBufferedWriter(Paths.get(s));
            buffer = new StringBuilder();
            generate(grammar, rules);
            writer.write(buffer.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateBad(String name, Grammar grammar) {
        String s = name + "Ans.txt";
        try {
            writer = Files.newBufferedWriter(Paths.get(s));
            buffer = new StringBuilder();
            generateBad(grammar);
            writer.write(buffer.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void generateBad(Grammar grammar) throws IOException{
        write("Вывод некорректен начиная с формулы номер " + (grammar.badString + 1));
    }

    void generate(Grammar grammar, boolean rules) throws IOException{
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

    void write(String str) throws IOException{
        buffer.append(str);
        if (buffer.length() > size) {
            writer.write(buffer.toString());
            buffer = new StringBuilder();
        }
    }

    void writeln(String str) throws IOException{
        write(str + System.lineSeparator());
    }

    void writeln() throws IOException {
        writeln("");
    }
}
