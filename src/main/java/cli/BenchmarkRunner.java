package cli;

import algorithms.BoyerMooreMajorityVote;
import metrics.PerformanceTracker;

import java.util.Random;
import java.io.IOException;

public class BenchmarkRunner {
    
    private static final int[] DEFAULT_SIZES = {100, 1000, 10000, 100000};
    private static final String[] INPUT_TYPES = {"random", "sorted", "reverse-sorted", "nearly-sorted", "majority-heavy"};
    
    public static void main(String[] args) {
        
        BenchmarkRunner runner = new BenchmarkRunner();
        
        if (args.length == 0) {
            runner.runComprehensiveBenchmark();
        } else if (args.length == 1) {
            try {
                int size = Integer.parseInt(args[0]);
                runner.runSingleSizeBenchmark(size);
            } catch (NumberFormatException e) {
                System.err.println("Invalid size: " + args[0]);
                printUsage();
            }
        } else {
            printUsage();
        }
    }
    
    public void runComprehensiveBenchmark() {
        
        BoyerMooreMajorityVote algorithm = new BoyerMooreMajorityVote();
        PerformanceTracker.clearResults();
        
        for (int size : DEFAULT_SIZES) {
            System.out.printf("Testing input size: %,d\n", size);
            System.out.println("-".repeat(30));
            
            for (String inputType : INPUT_TYPES) {
                runBenchmarkForType(algorithm, size, inputType);
            }
            System.out.println();
        }
        
        try {
            PerformanceTracker.exportToCSV("benchmark_results.csv");
            System.out.println("Results exported to benchmark_results.csv");
        } catch (IOException e) {
            System.err.println("Failed to export results: " + e.getMessage());
        }
        
        System.out.println("\n" + algorithm.getComplexityAnalysis());
    }
    
    public void runSingleSizeBenchmark(int size) {
        System.out.println("=".repeat(40));
        
        BoyerMooreMajorityVote algorithm = new BoyerMooreMajorityVote();
        PerformanceTracker.clearResults();
        
        for (String inputType : INPUT_TYPES) {
            runBenchmarkForType(algorithm, size, inputType);
        }
        
        try {
            String filename = String.format("benchmark_size_%d.csv", size);
            PerformanceTracker.exportToCSV(filename);
            System.out.println("\\nResults exported to " + filename);
        } catch (IOException e) {
            System.err.println("Failed to export results: " + e.getMessage());
        }
    }
    
    private void runBenchmarkForType(BoyerMooreMajorityVote algorithm, int size, String inputType) {
        int[] testData = generateTestData(size, inputType);
        
        for (int i = 0; i < 5; i++) {
            algorithm.findMajorityElement(testData.clone(), inputType);
        }
        
        Integer result = algorithm.findMajorityElement(testData, inputType);
        
        var tracker = algorithm.getPerformanceTracker();
        System.out.printf("  %-15s: %8.3f ms | %,8d accesses | %,6d comparisons | Result: %s\n",
            inputType,
            tracker.getExecutionTimeMs(),
            tracker.getArrayAccesses(),
            tracker.getComparisons(),
            result == null ? "None" : result.toString()
        );
        
        Integer naiveResult = algorithm.findMajorityElementNaive(testData);
        if (!java.util.Objects.equals(result, naiveResult)) {
            System.err.printf("    ERROR: Result mismatch! Boyer-Moore: %s, Naive: %s\n", 
                result, naiveResult);
        }
    }
    
    private int[] generateTestData(int size, String inputType) {
        Random random = new Random(42); 
        
        switch (inputType) {
            case "random":
                return generateRandomData(size, random);
            
            case "sorted":
                return generateSortedData(size, random);
            
            case "reverse-sorted":
                return generateReverseSortedData(size, random);
            
            case "nearly-sorted":
                return generateNearlySortedData(size, random);
            
            case "majority-heavy":
                return generateMajorityHeavyData(size, random);
            
            default:
                throw new IllegalArgumentException("Unknown input type: " + inputType);
        }
    }
    
    private int[] generateRandomData(int size, Random random) {
        int[] data = new int[size];
        
        if (random.nextDouble() < 0.7) {
            int majorityValue = random.nextInt(100);
            int majorityCount = size / 2 + 1 + random.nextInt(size / 4);
            
            for (int i = 0; i < majorityCount && i < size; i++) {
                data[i] = majorityValue;
            }
            
            for (int i = majorityCount; i < size; i++) {
                do {
                    data[i] = random.nextInt(100);
                } while (data[i] == majorityValue);
            }
            
            for (int i = size - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        } else {
            for (int i = 0; i < size; i++) {
                data[i] = random.nextInt(size);
            }
        }
        
        return data;
    }
    
    private int[] generateSortedData(int size, Random random) {
        int[] data = new int[size];
        int majorityValue = 1;
        int majorityCount = size / 2 + 1;
        
        for (int i = 0; i < majorityCount; i++) {
            data[i] = majorityValue;
        }
        
        for (int i = majorityCount; i < size; i++) {
            data[i] = i;
        }
        
        return data;
    }
    
    private int[] generateReverseSortedData(int size, Random random) {
        int[] data = new int[size];
        int majorityValue = 1;
        int majorityCount = size / 2 + 1;
        
        for (int i = 0; i < size - majorityCount; i++) {
            data[i] = size - i;
        }
        
        for (int i = size - majorityCount; i < size; i++) {
            data[i] = majorityValue;
        }
        
        return data;
    }
    
    private int[] generateNearlySortedData(int size, Random random) {
        int[] data = generateSortedData(size, random);
        
        int swaps = Math.max(1, size / 20);
        for (int i = 0; i < swaps; i++) {
            int pos1 = random.nextInt(size);
            int pos2 = random.nextInt(size);
            int temp = data[pos1];
            data[pos1] = data[pos2];
            data[pos2] = temp;
        }
        
        return data;
    }
    
    private int[] generateMajorityHeavyData(int size, Random random) {
        int[] data = new int[size];
        int majorityValue = 42;
        int majorityCount = (int)(size * 0.8); 
        
        for (int i = 0; i < majorityCount; i++) {
            data[i] = majorityValue;
        }
        
        for (int i = majorityCount; i < size; i++) {
            data[i] = random.nextInt(1000) + 1000;
        }
        
        for (int i = size - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
        
        return data;
    }
    
    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java cli.BenchmarkRunner           - Run comprehensive benchmark");
        System.out.println("  java cli.BenchmarkRunner <size>    - Run benchmark for specific size");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  java cli.BenchmarkRunner");
        System.out.println("  java cli.BenchmarkRunner 50000");
    }
}