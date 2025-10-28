package src.main.java.detector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// Reads a File line by line and passes each line through all detectors
public class Main {
    public static void main(String[] args) {
        // TODO: make file specification easier
        String fileName = (args.length > 0) ? args[0] : "example.txt";
        // Storing detectors in Array
        sqlDetector sql = new sqlDetector("SQL");
        xssDetector xss = new xssDetector("XSS");
        xss.readList("src/main/signatures/xss_signatures.txt");
        sql.readList("src/main/signatures/sql_signatures.txt");
        Detector[] detectors = {sql,xss};
        int lineNo = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNo++;
                for (Detector d: detectors){
                    int score = d.evaluateLine(line);
                    if (score > 0) {
                        System.out.printf("Hit (score=%d) at line %d: %s%n", score, lineNo, line);
                    }
                }
            }
            for (Detector d: detectors){
                System.out.println(d.getName() + "Detector had " + d.getTotalHits() + " hits!");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
