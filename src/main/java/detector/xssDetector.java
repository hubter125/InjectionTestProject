package src.main.java.detector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class xssDetector implements Detector {
    protected int hits;
    private String name;
    private ArrayList<String> signatures;
    BufferedReader reader;

    public xssDetector(String name) {
        this.name = name;
        this.signatures = new ArrayList<>();
    }
    
    /**
     * This method evaluates the liklihood of injection attacks being present based on static analysis
     * 
     * Reads a line of parsed file and matches expressions common in injection attacks
     * 
     * @param line The line of the file that we are currently reading
     * 
     * @return the total score, either 0 or 1 currently until further implementation is added
     */

    @Override
    public int evaluateLine(String line) {
        if (line == null) {
            return 0;
        }
        int score = 0;

        for (String l:signatures){
            if (line.contains(l)){
                score +=1;
            }
        }
        hits += score;
        return score;
    }

    /**
     * Getter for total amount of hits
     * 
     * @return The number of signatures that were found in the log file for a given Detector
     */

    public int getTotalHits() {
        return hits;
    }

    /**
     * Getter for Name of Detector
     * 
     * This method gets the name of the Detector to be used in output formatting
     * 
     * @return String name of Detector
     */

    public String getName() {
        return name;
    }

    /**
     * Reads a log file and stores it in an Array List
     * 
     * This method stores Strings in an ArrayList to be passed to evaluateLine()
     * 
     * @param String filepath is the filepath of the file you want to parse of injection attacks
     * @return ArrayList<String> called signatures to store each line of the file for parsing in evaluateLine()
     * @throws IOException if their is an error reading signatures file
     */
    
    public ArrayList<String> readList(String filepath) {
        signatures.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    signatures.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading signatures: " + e.getMessage());
        }
        return signatures;

    }

}