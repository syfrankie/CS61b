import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOffByOne {
    static CharacterComparator offbyone = new OffByOne();

    @Test
    public void testOffByOne() {
        boolean ans1 = offbyone.equalChars('y', 's');
        assertEquals(false, ans1);

        boolean ans2 = offbyone.equalChars('m', 'n');
        assertEquals(true, ans2);
    }
}
