package decideTest;
import decideProgram.LIC;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LICTests {

    @Test
    public void testCondition0() {
        LIC lic = new LIC();
        assertTrue(lic.condition0(), "condition0 should return true");
    }
}