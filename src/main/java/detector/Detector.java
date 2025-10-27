package src.main.java.detector;
// Interface for different attack detectors
public interface Detector {
    int evaluateLine(String line);
    int getTotalHits();
    String getName();
}
