// Example of how to use the Boyer-Moore Majority Vote algorithm in your own code

import algorithms.BoyerMooreMajorityVote;
import metrics.PerformanceTracker;

public class UserExample {
    public static void main(String[] args) {
        // Create the algorithm instance
        BoyerMooreMajorityVote algorithm = new BoyerMooreMajorityVote();
        
        // Example 1: Simple usage
        int[] myData = {7, 7, 5, 7, 5, 1, 5, 7, 5, 5, 7, 7, 7, 7, 7, 7};
        Integer majority = algorithm.findMajorityElement(myData);
        System.out.println("Majority element: " + majority); // Output: 7
        
        // Example 2: With performance tracking
        int[] largeData = new int[10000];
        // Fill with some majority element (60% of array)
        for (int i = 0; i < 6000; i++) {
            largeData[i] = 999;
        }
        for (int i = 6000; i < 10000; i++) {
            largeData[i] = i;
        }
        
        Integer result = algorithm.findMajorityElement(largeData, "my-test");
        System.out.println("Result: " + result);
        
        // Get performance metrics
        PerformanceTracker tracker = algorithm.getPerformanceTracker();
        System.out.println("Execution time: " + tracker.getExecutionTimeMs() + " ms");
        System.out.println("Array accesses: " + tracker.getArrayAccesses());
        System.out.println("Comparisons: " + tracker.getComparisons());
        
        // Example 3: Handle edge cases
        int[] empty = {};
        int[] single = {42};
        int[] noMajority = {1, 2, 3, 4, 5};
        
        System.out.println("Empty array: " + algorithm.findMajorityElement(empty));       // null
        System.out.println("Single element: " + algorithm.findMajorityElement(single));   // 42
        System.out.println("No majority: " + algorithm.findMajorityElement(noMajority));  // null
    }
}

/*
To compile and run this example:
1. javac -cp target/classes UserExample.java
2. java -cp target/classes:. UserExample
*/