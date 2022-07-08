import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;

public class ParseAndPrint {
    public static void main(String[] args) throws IOException, JSONException, ParseException {
        System.out.println("In main");
        //System.out.println(args[0]);
        ReadFile r1 = new ReadFile();
        if(args.length == 1)
        {
            //System.out.println(args[0]);
            r1.read(args[0]);
        }
    }
}
