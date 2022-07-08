import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ProcessDateTest {

    @Test
    void testIfDateisValid() throws ParseException {
        String date = "2022/01/01";
        assertTrue(ProcessDate.isValid(date));
    }

    @Test
    void testIfDateisNotValid() throws ParseException {
        String date = "2022.01/01";
        assertFalse(ProcessDate.isValid(date));
    }

    @Test
    void testIfADatePattern() throws ParseException {
        String givenDate = "2022/01/01";
        String expectedDate = "2022-01-01";
        assertEquals(expectedDate,ProcessDate.checkWhichDatePattern(givenDate));
    }

    @Test
    void testIfNotADatePattern() throws ParseException {
        String givenDate = "2022/01/01";
        String expectedDate = "2022/01-01";
        assertNotEquals(expectedDate,ProcessDate.checkWhichDatePattern(givenDate));
    }
}