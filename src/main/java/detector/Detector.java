package src.main.java.detector;
import java.util.ArrayList;
// Interface for different attack detectors
public interface Detector {
    int evaluateLine(String line);
    int getTotalHits();
    String getName();
    ArrayList<String> readList(String filepath);
}
