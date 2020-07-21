public class OffByOne implements CharacterComparator{

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == 1 || diff == -1) return true;
        return false;
    }

    @Override
    public boolean notLetter(char x) {
        return (x<65 || (90<x && x<97) || x>122);
    }
}
