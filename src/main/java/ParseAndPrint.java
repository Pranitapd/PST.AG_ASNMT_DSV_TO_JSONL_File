import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;

/**
 * ParseAndPrint function has main function
 * @author Pranita Deshmukh
 */
public class ParseAndPrint {
    public static void main(String[] args) throws IOException, JSONException, ParseException {
        ReadFile r1 = new ReadFile();
        if(args.length == 1)
        {
            r1.read(args[0]);
        }
    }
}
