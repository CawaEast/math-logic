package grammar;

/**
 * Created by Cawa on 17.07.2017.
 */
public class Operator {
    int i = -1;
    boolean leftAssoc = true;
    String str;
    Operator(String s) {
        str = s;
        switch (s) {
            case "->":
                i = 1;
                leftAssoc = false;
                break;
            case "|":
                i = 2;
                break;
            case "&":
                i = 3;
                break;
            case "=":
                i = 4;
                break;
            case "+":
                i = 5;
                break;
            case "*":
                i = 6;
                break;
            case "!":
                i = 7;
                break;
            default:
                i = 8;
        }
    }

    public boolean less(Operator other) {
        return i < other.i;
    }

    public boolean equals(Operator other) {
        return i == other.i;
    }

    public boolean lessEquals(Operator other) {
        return i <= other.i;
    }
}
