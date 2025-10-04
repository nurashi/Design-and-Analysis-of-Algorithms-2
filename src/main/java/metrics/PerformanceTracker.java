package metrics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Performance tracking utility for Boyer-Moore Majority Vote algorithm
 * Tracks key operations and exports results to CSV for analysis
 */
public class PerformanceTracker {
    private long arrayAccesses;
    private long comparisons;
    private long memoryAllocations;
    private long startTime;
    private long endTime;
    private String algorithmName;
    private int inputSize;
    private String inputType;
    
    // Store results for multiple runs
    private static List<PerformanceResult> results = new ArrayList<>();
    
    public PerformanceTracker(String algorithmName) {
        this.algorithmName = algorithmName;
        reset();
    }
    
    /**
     * Reset all counters for a new measurement
     */
    public void reset() {
        arrayAccesses = 0;
        comparisons = 0;
        memoryAllocations = 0;
        startTime = 0;
        endTime = 0;
    }
    
    /**
     * Start timing measurement
     */
    public void startTiming() {
        startTime = System.nanoTime();
    }
    
    /**
     * End timing measurement
     */
    public void endTiming() {
        endTime = System.nanoTime();
    }
    
    /**
     * Increment array access counter
     */
    public void incrementArrayAccess() {
        arrayAccesses++;
    }
    
    /**
     * Increment comparison counter
     */
    public void incrementComparison() {
        comparisons++;
    }
    
    /**
     * Increment memory allocation counter
     */
    public void incrementMemoryAllocation() {
        memoryAllocations++;
    }
    
    /**
     * Set input characteristics for current run
     */
    public void setInputCharacteristics(int size, String type) {
        this.inputSize = size;
        this.inputType = type;
    }
    
    /**
     * Get execution time in nanoseconds
     */
    public long getExecutionTimeNs() {
        return endTime - startTime;
    }
    
    /**
     * Get execution time in milliseconds
     */
    public double getExecutionTimeMs() {
        return (endTime - startTime) / 1_000_000.0;
    }
    
    /**
     * Store current measurement as a result
     */
    public void storeResult() {
        results.add(new PerformanceResult(
            algorithmName,
            inputSize,
            inputType,
            arrayAccesses,
            comparisons,
            memoryAllocations,
            getExecutionTimeNs()
        ));
    }
    
    /**
     * Get current performance summary
     */
    public String getPerformanceSummary() {
        return String.format(
            "Algorithm: %s\n" +
            "Input Size: %d (%s)\n" +
            "Array Accesses: %d\n" +
            "Comparisons: %d\n" +
            "Memory Allocations: %d\n" +
            "Execution Time: %.3f ms\n" +
            "Time Complexity: O(n)\n" +
            "Space Complexity: O(1)",
            algorithmName, inputSize, inputType,
            arrayAccesses, comparisons, memoryAllocations,
            getExecutionTimeMs()
        );
    }
    
    /**
     * Export all results to CSV file
     */
    public static void exportToCSV(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // CSV header
            writer.write("Algorithm,InputSize,InputType,ArrayAccesses,Comparisons,MemoryAllocations,ExecutionTimeNs\n");
            
            // CSV data
            for (PerformanceResult result : results) {
                writer.write(String.format("%s,%d,%s,%d,%d,%d,%d\n",
                    result.algorithmName,
                    result.inputSize,
                    result.inputType,
                    result.arrayAccesses,
                    result.comparisons,
                    result.memoryAllocations,
                    result.executionTimeNs
                ));
            }
        }
    }
    
    /**
     * Clear all stored results
     */
    public static void clearResults() {
        results.clear();
    }
    
    /**
     * Get all stored results
     */
    public static List<PerformanceResult> getResults() {
        return new ArrayList<>(results);
    }
    
    // Getters for individual metrics
    public long getArrayAccesses() { return arrayAccesses; }
    public long getComparisons() { return comparisons; }
    public long getMemoryAllocations() { return memoryAllocations; }
    public int getInputSize() { return inputSize; }
    public String getInputType() { return inputType; }
    
    /**
     * Inner class to store performance results
     */
    public static class PerformanceResult {
        public final String algorithmName;
        public final int inputSize;
        public final String inputType;
        public final long arrayAccesses;
        public final long comparisons;
        public final long memoryAllocations;
        public final long executionTimeNs;
        
        public PerformanceResult(String algorithmName, int inputSize, String inputType,
                               long arrayAccesses, long comparisons, long memoryAllocations,
                               long executionTimeNs) {
            this.algorithmName = algorithmName;
            this.inputSize = inputSize;
            this.inputType = inputType;
            this.arrayAccesses = arrayAccesses;
            this.comparisons = comparisons;
            this.memoryAllocations = memoryAllocations;
            this.executionTimeNs = executionTimeNs;
        }
        
        @Override
        public String toString() {
            return String.format("PerformanceResult{algorithm='%s', size=%d, type='%s', " +
                               "accesses=%d, comparisons=%d, allocations=%d, timeNs=%d}",
                algorithmName, inputSize, inputType, arrayAccesses, comparisons, 
                memoryAllocations, executionTimeNs);
        }
    }
}