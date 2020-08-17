package byow.Core;

public class InputString {
    private String input;
    private int idx;
    private int size;

    public InputString(String s) {
        input = s;
        idx = 0;
        size = s.length();
    }

    public char getChar() {
        char output = Character.toUpperCase(input.charAt(idx));
        idx += 1;
        return output;
    }

    public boolean completeInput() {
        return idx == size;
    }

}
