package algorithms;

import metrics.PerformanceTracker;

/**
 * Boyer-Moore Majority Vote Algorithm Implementation
 * 
 * Finds the majority element (appears more than n/2 times) in an array
 * using O(1) space and O(n) time complexity.
 * 
 * Algorithm Description:
 * 1. Maintain a candidate and count
 * 2. If count is 0, set current element as candidate
 * 3. If current element equals candidate, increment count
 * 4. Otherwise, decrement count
 * 5. The final candidate is the potential majority element
 * 6. Verify by counting occurrences (optional second pass)
 * 
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - constant auxiliary space
 */
public class BoyerMooreMajorityVote {
    
    private PerformanceTracker tracker;
    
    public BoyerMooreMajorityVote() {
        this.tracker = new PerformanceTracker("Boyer-Moore Majority Vote");
    }
    
    /**
     * Find majority element using Boyer-Moore algorithm
     * @param nums input array
     * @return majority element if exists, null otherwise
     */
    public Integer findMajorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        tracker.reset();
        tracker.setInputCharacteristics(nums.length, "random");
        tracker.startTiming();
        
        // Phase 1: Find potential candidate
        Integer candidate = findCandidate(nums);
        
        // Phase 2: Verify candidate is actually majority
        if (candidate != null && isMajority(nums, candidate)) {
            tracker.endTiming();
            tracker.storeResult();
            return candidate;
        }
        
        tracker.endTiming();
        tracker.storeResult();
        return null; // No majority element exists
    }
    
    /**
     * Find majority element with input type specification for testing
     */
    public Integer findMajorityElement(int[] nums, String inputType) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        tracker.reset();
        tracker.setInputCharacteristics(nums.length, inputType);
        tracker.startTiming();
        
        Integer candidate = findCandidate(nums);
        
        if (candidate != null && isMajority(nums, candidate)) {
            tracker.endTiming();
            tracker.storeResult();
            return candidate;
        }
        
        tracker.endTiming();
        tracker.storeResult();
        return null;
    }
    
    /**
     * Phase 1: Find potential majority candidate using Boyer-Moore algorithm
     * Core algorithm that maintains candidate and count
     */
    private Integer findCandidate(int[] nums) {
        Integer candidate = null;
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            tracker.incrementArrayAccess(); // Access nums[i]
            
            if (count == 0) {
                candidate = nums[i];
                tracker.incrementMemoryAllocation(); // Candidate assignment
                count = 1;
            } else {
                tracker.incrementComparison(); // Compare with candidate
                if (nums[i] == candidate) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        
        return candidate;
    }
    
    /**
     * Phase 2: Verify that candidate is actually the majority element
     * Counts occurrences to ensure it appears more than n/2 times
     */
    private boolean isMajority(int[] nums, int candidate) {
        int count = 0;
        int majority = nums.length / 2;
        
        for (int i = 0; i < nums.length; i++) {
            tracker.incrementArrayAccess(); // Access nums[i]
            tracker.incrementComparison(); // Compare with candidate
            
            if (nums[i] == candidate) {
                count++;
                // Early termination optimization
                if (count > majority) {
                    return true;
                }
            }
        }
        
        return count > majority;
    }
    
    /**
     * Alternative implementation: Find majority without verification
     * (assumes majority element always exists)
     * More efficient when guarantee exists
     */
    public Integer findMajorityElementOptimized(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        tracker.reset();
        tracker.setInputCharacteristics(nums.length, "guaranteed-majority");
        tracker.startTiming();
        
        Integer candidate = findCandidate(nums);
        
        tracker.endTiming();
        tracker.storeResult();
        return candidate;
    }
    
    /**
     * Naive approach for comparison - O(n²) time complexity
     * Used for correctness verification and performance comparison
     */
    public Integer findMajorityElementNaive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        int majority = nums.length / 2;
        
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
            if (count > majority) {
                return nums[i];
            }
        }
        
        return null;
    }
    
    /**
     * Get performance tracker for metrics collection
     */
    public PerformanceTracker getPerformanceTracker() {
        return tracker;
    }
    
    /**
     * Utility method to check if array has majority element
     * without finding the element itself
     */
    public boolean hasMajorityElement(int[] nums) {
        return findMajorityElement(nums) != null;
    }
    
    /**
     * Get detailed algorithm analysis
     */
    public String getComplexityAnalysis() {
        return "Boyer-Moore Majority Vote Algorithm Analysis:\n" +
               "==========================================\n" +
               "Time Complexity:\n" +
               "  - Best Case: Θ(n) - must examine all elements\n" +
               "  - Worst Case: Θ(n) - must examine all elements\n" +
               "  - Average Case: Θ(n) - must examine all elements\n" +
               "\n" +
               "Space Complexity: O(1) - constant auxiliary space\n" +
               "\n" +
               "Algorithm Properties:\n" +
               "  - Single pass through array (with optional verification)\n" +
               "  - Constant extra memory usage\n" +
               "  - Early termination in verification phase\n" +
               "  - Optimal for majority element detection\n" +
               "\n" +
               "Comparison with naive O(n²) approach:\n" +
               "  - Space: O(1) vs O(1)\n" +
               "  - Time: O(n) vs O(n²)\n" +
               "  - Scalability: Excellent vs Poor";
    }
}