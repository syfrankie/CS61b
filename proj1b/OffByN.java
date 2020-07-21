public class OffByN implements CharacterComparator{

    private int off;
    public OffByN(int x) {
        off = x;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == off || diff == -off) return true;
        return false;
    }

    @Override
    public boolean notLetter(char x) {
        return (x<65 || (90<x && x<97) || x>122);
    }
}
