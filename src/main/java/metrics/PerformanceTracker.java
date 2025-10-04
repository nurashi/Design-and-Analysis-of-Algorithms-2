package metrics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PerformanceTracker {
    private long arrayAccesses;
    private long comparisons;
    private long memoryAllocations;
    private long startTime;
    private long endTime;
    private String algorithmName;
    private int inputSize;
    private String inputType;
    
    private static List<PerformanceResult> results = new ArrayList<>();
    
    public PerformanceTracker(String algorithmName) {
        this.algorithmName = algorithmName;
        reset();
    }
    
    public void reset() {
        arrayAccesses = 0;
        comparisons = 0;
        memoryAllocations = 0;
        startTime = 0;
        endTime = 0;
    }
    
    public void startTiming() {
        startTime = System.nanoTime();
    }
    
    public void endTiming() {
        endTime = System.nanoTime();
    }
    
    public void incrementArrayAccess() {
        arrayAccesses++;
    }
    
    public void incrementComparison() {
        comparisons++;
    }
    
    public void incrementMemoryAllocation() {
        memoryAllocations++;
    }
    
    public void setInputCharacteristics(int size, String type) {
        this.inputSize = size;
        this.inputType = type;
    }
    
    public long getExecutionTimeNs() {
        return endTime - startTime;
    }
    
    public double getExecutionTimeMs() {
        return (endTime - startTime) / 1_000_000.0;
    }
    
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
    
    public static void exportToCSV(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Algorithm,InputSize,InputType,ArrayAccesses,Comparisons,MemoryAllocations,ExecutionTimeNs\n");
            
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
    
    public static void clearResults() {
        results.clear();
    }
    
    public static List<PerformanceResult> getResults() {
        return new ArrayList<>(results);
    }
    
    public long getArrayAccesses() { return arrayAccesses; }
    public long getComparisons() { return comparisons; }
    public long getMemoryAllocations() { return memoryAllocations; }
    public int getInputSize() { return inputSize; }
    public String getInputType() { return inputType; }
    
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