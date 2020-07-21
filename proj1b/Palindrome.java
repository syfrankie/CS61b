public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        int length = wordDeque.size();
        if (length <= 1) return true;

        int l = 0;
        int r = length - 1;
        while (l < r) {
            if (wordDeque.get(l) == wordDeque.get(r)) {
                l += 1;
                r -= 1;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        int length = wordDeque.size();
        if (length <= 1) return true;
        int l = 0;
        int r = length - 1;

        while (l < r) {
            char left = wordDeque.get(l);
            char right = wordDeque.get(r);
            if (cc.notLetter(left) || cc.notLetter(right)) return false;
            if (cc.equalChars(left, right)) {
                l += 1;
                r -= 1;
            } else {
                return false;
            }
        }
        return true;
    }


}
