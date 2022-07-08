import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ProcessDateTest class
 * @author Pranita Deshmukh
 */
class ProcessDateTest {

    /**
     * testIfDateisValid tests if Date is valid
     * @throws ParseException
     */
    @Test
    void testIfDateisValid() throws ParseException {
        String date = "2022/01/01";
        assertTrue(ProcessDate.isValid(date));
    }

    /**
     * testIfDateisNotValid tests if Date is invalid
     * @throws ParseException
     */
    @Test
    void testIfDateisNotValid() throws ParseException {
        String date = "2022.01/01";
        assertFalse(ProcessDate.isValid(date));
    }

    /**
     * testIfADatePattern tests if we get Date pattern as expected
     * @throws ParseException
     */
    @Test
    void testIfADatePattern() throws ParseException {
        String givenDate = "2022/01/01";
        String expectedDate = "2022-01-01";
        assertEquals(expectedDate,ProcessDate.checkWhichDatePattern(givenDate));
    }

    /**
     * testIfNotADatePattern tests if we get Date pattern not as expected
     * @throws ParseException
     */
    @Test
    void testIfNotADatePattern() throws ParseException {
        String givenDate = "2022/01/01";
        String expectedDate = "2022/01-01";
        assertNotEquals(expectedDate,ProcessDate.checkWhichDatePattern(givenDate));
    }
}