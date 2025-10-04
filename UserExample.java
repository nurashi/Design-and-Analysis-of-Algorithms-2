import algorithms.BoyerMooreMajorityVote;
import metrics.PerformanceTracker;

public class UserExample {
    public static void main(String[] args) {
        BoyerMooreMajorityVote algorithm = new BoyerMooreMajorityVote();
        
        int[] myData = {7, 7, 5, 7, 5, 1, 5, 7, 5, 5, 7, 7, 7, 7, 7, 7};
        Integer majority = algorithm.findMajorityElement(myData);
        System.out.println("Majority element: " + majority);
        
        int[] largeData = new int[10000];
        for (int i = 0; i < 6000; i++) {
            largeData[i] = 999;
        }
        for (int i = 6000; i < 10000; i++) {
            largeData[i] = i;
        }
        
        Integer result = algorithm.findMajorityElement(largeData, "my-test");
        System.out.println("Result: " + result);
        
        PerformanceTracker tracker = algorithm.getPerformanceTracker();
        System.out.println("Execution time: " + tracker.getExecutionTimeMs() + " ms");
        System.out.println("Array accesses: " + tracker.getArrayAccesses());
        System.out.println("Comparisons: " + tracker.getComparisons());
        
        int[] empty = {};
        int[] single = {42};
        int[] noMajority = {1, 2, 3, 4, 5};
        
        System.out.println("Empty array: " + algorithm.findMajorityElement(empty));       
        System.out.println("Single element: " + algorithm.findMajorityElement(single));   
        System.out.println("No majority: " + algorithm.findMajorityElement(noMajority));  
    }
}

