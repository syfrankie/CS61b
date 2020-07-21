import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOffByN {
    static CharacterComparator offbyn = new OffByN(11);

    @Test
    public void testOffByN() {
        boolean ans1 = offbyn.equalChars('y', 's');
        assertEquals(false, ans1);

        boolean ans2 = offbyn.equalChars('a', 'l');
        assertEquals(true, ans2);
    }
}
