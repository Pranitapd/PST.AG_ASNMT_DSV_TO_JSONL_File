import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvFormat;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile {

//    Map<String,String> mp = new LinkedHashMap<>();
    List<String> allKeys = new ArrayList<>();
    public void read(String inputFileName) throws IOException, JSONException, ParseException {

        File requestFile = new File(inputFileName);
        if(!Files.exists(Paths.get(String.valueOf(requestFile)))) {
            System.out.println("Input File does not exist: " + inputFileName);
            return;
        }

        FileWriter outputFile = new FileWriter("src/main/resources/resourcesoutputFile.jsonl");
        fillHashMap(requestFile,inputFileName, outputFile);
    }

    public String getTheDelimeter(String firstLine, String inputFileName){
        CsvParserSettings settings = new CsvParserSettings();
        settings.detectFormatAutomatically();

        CsvParser parser = new CsvParser(settings);
        List<String[]> rows = parser.parseAll(new File(inputFileName));

        CsvFormat format = parser.getDetectedFormat();
        System.out.println("format" + format.getDelimiterString());
        return format.getDelimiterString();
    }

    public void addValueInJSONObjAndPrintInFile(String line, String delimeter, FileWriter outputFile) throws IOException, JSONException, ParseException {

        ProcessDate processDate = new ProcessDate();
        //get all the values in list
        Stream<String> stream = Arrays.stream(line.split(Pattern.quote(delimeter)));
        List<String> allValues1 = stream.collect(Collectors.toList());

        LinkedList<String> allValues = new LinkedList<>(allValues1);

        for(String s : allValues)
            System.out.println("List" + s);

        allValues = checkAndMergeStrings(allValues);

        JSONObject jsonObject = new JSONObject();

        //iterate through both the lists and add in object
        Iterator<String> i1 = allKeys.iterator();
        Iterator<String> i2 = allValues.iterator();

        while(i1.hasNext() && i2.hasNext()){
            String key = i1.next().strip();
            String value = i2.next().strip();
            //check if value is a date
            if(ProcessDate.isValid(value))
            {
                value = ProcessDate.checkWhichDatePattern(value);
            }
            jsonObject.put(key,value);
            System.out.println(jsonObject);
        }
        outputFile.append(jsonObject.toString() + "\n");

        outputFile.flush();
    }

    public LinkedList<String> checkAndMergeStrings(LinkedList<String> allValues){
        int index1=-1,index2=0;
        //need to concatenate values in quotes
        for(int i=0; i<allValues.size(); i++){
            if((!allValues.get(i).isBlank()) && allValues.get(i).charAt(0) == '\"' && index1 == -1)
                index1=i;
            if((!allValues.get(i).isBlank()) && allValues.get(i).charAt(allValues.get(i).length()-1) =='\"' && index1!=-1) {
                index2 = i;
                multiconcat(index1,index2,allValues); //concat the elements between index1 and index2
                allValues = multidelete(index1+1, index2, allValues);//delete the elements that were copied (index1+1:index2)
                i-= index2-index1;
                index1=-1;
            }
        }
        return allValues;
    }
    public static void multiconcat(int index1, int index2, LinkedList<String> subList) {
        int lengthOfFirstElem = subList.get(index1).length();
        int lengthOfLastElem = subList.get(index2).length();

        StringBuffer sb = new StringBuffer(subList.get(index1).substring(1,lengthOfFirstElem));
        for(int i=index1+1; i<=index2; i++)
            sb.append(subList.get(i));
        subList.set(index1,sb.substring(0,sb.length()-1));
    }

    public static LinkedList<String> multidelete(int index1,int index2,LinkedList<String> subList){
        for (int i=index1; i<=index2;i++)
            subList.remove(i);
        return subList;
    }

    public void fillHashMap(File requestFile, String inputFileName, FileWriter outputFile) throws IOException, JSONException, ParseException {
        FileReader fr = new FileReader(requestFile);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String firstLine = br.readLine();
        //System.out.println(firstLine);

        //get the delimeter
        String delimeter = getTheDelimeter(firstLine, inputFileName);
        //Getting column names in a stream
//        Stream<String> stream = Arrays.stream(firstLine.split(delimeter));
//
//        //Get column names in a list
//        allKeys = stream.collect(Collectors.toList());
        String[] keys = firstLine.split(Pattern.quote(delimeter));
        for(String s:keys){
            allKeys.add(s);
        }

        String line;
        while((line = br.readLine())!=null){
            addValueInJSONObjAndPrintInFile(line,delimeter,outputFile);
            sb.append(line);
            sb.append("\n");
        }
        outputFile.flush();
        outputFile.close();
        fr.close();
    }

}
