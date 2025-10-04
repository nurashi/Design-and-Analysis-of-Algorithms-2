package algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the complete Boyer-Moore implementation
 * Tests algorithm variants and optimizations
 */
public class BoyerMooreMajorityVoteIntegrationTest {
    
    @Test
    @DisplayName("Test optimized version without verification")
    void testOptimizedVersionNoVerification() {
        BoyerMooreMajorityVote algorithm = new BoyerMooreMajorityVote();
        
        // Create array where we guarantee majority exists
        int[] testArray = new int[1000];
        
        // Fill 60% with majority element
        for (int i = 0; i < 600; i++) {
            testArray[i] = 42;
        }
        
        // Fill rest with different values
        for (int i = 600; i < 1000; i++) {
            testArray[i] = i;
        }
        
        // Shuffle array
        java.util.Random random = new java.util.Random(42);
        for (int i = testArray.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = testArray[i];
            testArray[i] = testArray[j];
            testArray[j] = temp;
        }
        
        // Test optimized version (no verification)
        Integer result = algorithm.findMajorityElementOptimized(testArray);
        assertEquals(Integer.valueOf(42), result);
        
        // Verify performance metrics were collected
        var tracker = algorithm.getPerformanceTracker();
        assertTrue(tracker.getArrayAccesses() > 0);
        assertTrue(tracker.getExecutionTimeNs() > 0);
        assertEquals("guaranteed-majority", tracker.getInputType());
    }
    
    @Test
    @DisplayName("Comprehensive performance comparison")
    void testPerformanceComparison() {
        BoyerMooreMajorityVote algorithm = new BoyerMooreMajorityVote();
        
        int[] testArray = createTestArray(1000);
        
        // Test different algorithm variants
        long start1 = System.nanoTime();
        Integer result1 = algorithm.findMajorityElement(testArray.clone());
        long time1 = System.nanoTime() - start1;
        
        long start2 = System.nanoTime();
        Integer result2 = algorithm.findMajorityElementOptimized(testArray.clone());
        long time2 = System.nanoTime() - start2;
        
        long start3 = System.nanoTime();
        Integer result3 = algorithm.findMajorityElementNaive(testArray.clone());
        long time3 = System.nanoTime() - start3;
        
        // All should return same result
        assertEquals(result1, result2);
        assertEquals(result1, result3);
        
        // Boyer-Moore should be faster than naive for large inputs
        // (This might not always hold for small inputs due to overhead)
        System.out.printf("Boyer-Moore: %d ns, Optimized: %d ns, Naive: %d ns%n", 
                         time1, time2, time3);
    }
    
    private int[] createTestArray(int size) {
        int[] array = new int[size];
        int majorityCount = size / 2 + 1;
        
        // Fill with majority element
        for (int i = 0; i < majorityCount; i++) {
            array[i] = 999;
        }
        
        // Fill rest with different values
        for (int i = majorityCount; i < size; i++) {
            array[i] = i;
        }
        
        return array;
    }
}