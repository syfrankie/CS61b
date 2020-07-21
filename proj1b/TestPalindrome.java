import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset. */
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offbyone = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        boolean ans1 = palindrome.isPalindrome("apple");
        assertEquals(false, ans1);

        boolean ans2 = palindrome.isPalindrome("level");
        assertEquals(true, ans2);

        boolean off1 = palindrome.isPalindrome("face", offbyone);
        assertEquals(false, off1);

        boolean off2 = palindrome.isPalindrome("flake", offbyone);
        assertEquals(true, off2);
    }

}
