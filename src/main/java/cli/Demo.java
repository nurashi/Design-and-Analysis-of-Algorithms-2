package cli;

import algorithms.BoyerMooreMajorityVote;

/**
 * Simple demonstration of Boyer-Moore Majority Vote algorithm
 * Shows basic usage and key features
 */
public class Demo {
    
    public static void main(String[] args) {
        System.out.println("Boyer-Moore Majority Vote Algorithm Demo");
        System.out.println("========================================\n");
        
        BoyerMooreMajorityVote algorithm = new BoyerMooreMajorityVote();
        
        // Example 1: Array with majority element
        System.out.println("Example 1: Array with majority element");
        int[] nums1 = {3, 2, 3, 4, 3, 3, 3};
        System.out.println("Input: " + java.util.Arrays.toString(nums1));
        Integer result1 = algorithm.findMajorityElement(nums1, "demo-with-majority");
        System.out.println("Majority element: " + result1);
        System.out.println("Performance: " + algorithm.getPerformanceTracker().getPerformanceSummary());
        System.out.println();
        
        // Example 2: Array without majority element
        System.out.println("Example 2: Array without majority element");
        int[] nums2 = {1, 2, 3, 4, 5};
        System.out.println("Input: " + java.util.Arrays.toString(nums2));
        Integer result2 = algorithm.findMajorityElement(nums2, "demo-no-majority");
        System.out.println("Majority element: " + (result2 == null ? "None" : result2));
        System.out.println("Performance: " + algorithm.getPerformanceTracker().getPerformanceSummary());
        System.out.println();
        
        // Example 3: Edge case - single element
        System.out.println("Example 3: Edge case - single element");
        int[] nums3 = {42};
        System.out.println("Input: " + java.util.Arrays.toString(nums3));
        Integer result3 = algorithm.findMajorityElement(nums3, "demo-single-element");
        System.out.println("Majority element: " + result3);
        System.out.println("Performance: " + algorithm.getPerformanceTracker().getPerformanceSummary());
        System.out.println();
        
        // Show complexity analysis
        System.out.println(algorithm.getComplexityAnalysis());
    }
}