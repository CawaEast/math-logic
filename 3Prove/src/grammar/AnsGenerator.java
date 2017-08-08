package grammar;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by Cawa on 17.07.2017.
 */
public class AnsGenerator {

    StringBuilder buffer;
    public Proof proof;

    public void generate(String name, Proof proof, boolean rules) {
        String s = name + "Ans.txt";
        try (Writer wr = Files.newBufferedWriter(Paths.get(s))) {
            buffer = new StringBuilder();
            generate(proof, rules);
            wr.write(buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generate(String name, HashMap<String, Boolean> proof) {
        String s = name + "Ans.txt";
        try (Writer wr = Files.newBufferedWriter(Paths.get(s))) {
            buffer = new StringBuilder();
            generate(proof);
            wr.write(buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void generate(HashMap<String, Boolean> contrAns) {
        write("Высказываеие ложно при ");
        boolean b = false;
        for (String str : contrAns.keySet()) {
            if (b) {
                write(", ");
            } else {
                b = true;
            }
            write(str + "=");
            if (contrAns.get(str)) {
                write("И");
            } else {
                write("Л");
            }
        }
    }


    void generate(Proof proof, boolean rules) {
        boolean b = true;
        for (Expression expr : proof.g) {
            if (b) {
                write(expr.head.toString());
                b = false;
            } else {
                write( ", " + expr.toString());
            }
        }
        write("|-");
        writeln(proof.toProves.toString());
        for (int i = 0; i < proof.prove.size(); i++) {
            Expression e = proof.prove.get(i);
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
