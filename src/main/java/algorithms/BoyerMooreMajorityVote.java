package algorithms;

import metrics.PerformanceTracker;

public class BoyerMooreMajorityVote {
    
    private PerformanceTracker tracker;
    
    public BoyerMooreMajorityVote() {
        this.tracker = new PerformanceTracker("Boyer-Moore Majority Vote");
    }
    
    public Integer findMajorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        tracker.reset();
        tracker.setInputCharacteristics(nums.length, "random");
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
    
    private Integer findCandidate(int[] nums) {
        Integer candidate = null;
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            tracker.incrementArrayAccess(); 
            
            if (count == 0) {
                candidate = nums[i];
                tracker.incrementMemoryAllocation(); 
                count = 1;
            } else {
                tracker.incrementComparison();
                if (nums[i] == candidate) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        
        return candidate;
    }
    
    private boolean isMajority(int[] nums, int candidate) {
        int count = 0;
        int majority = nums.length / 2;
        
        for (int i = 0; i < nums.length; i++) {
            tracker.incrementArrayAccess(); 
            tracker.incrementComparison(); 
            
            if (nums[i] == candidate) {
                count++;
                if (count > majority) {
                    return true;
                }
            }
        }
        
        return count > majority;
    }
    

 
    private boolean isProbablyMajority(int[] nums, int candidate) {
        int sampleSize = Math.min(50, nums.length / 10); // Sample 10% or max 50
        int matches = 0;
        
        for (int i = 0; i < sampleSize; i++) {
            int randomIndex = (int)(Math.random() * nums.length);
            tracker.incrementArrayAccess();
            tracker.incrementComparison();
            
            if (nums[randomIndex] == candidate) {
                matches++;
            }
        }
        
        return (double)matches / sampleSize > 0.6;
    }
    
    public Integer findMajorityElementOptimizedProbabilistic(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        tracker.reset();
        tracker.setInputCharacteristics(nums.length, "probabilistic-optimized");
        tracker.startTiming();
        
        Integer candidate = findCandidate(nums);
        
        if (candidate == null) {
            tracker.endTiming();
            tracker.storeResult();
            return null;
        }
        
        boolean isLikelyMajority = true;
        if (nums.length > 1000) {
            isLikelyMajority = isProbablyMajority(nums, candidate);
        }
        
        if (isLikelyMajority && isMajority(nums, candidate)) {
            tracker.endTiming();
            tracker.storeResult();
            return candidate;
        }
        
        tracker.endTiming();
        tracker.storeResult();
        return null;
    }
    
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
    
    public PerformanceTracker getPerformanceTracker() {
        return tracker;
    }
    
    public boolean hasMajorityElement(int[] nums) {
        return findMajorityElement(nums) != null;
    }
    

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