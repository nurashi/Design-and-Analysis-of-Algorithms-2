package algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.Arrays;

public class BoyerMooreMajorityVoteTest {
    
    private BoyerMooreMajorityVote algorithm;
    
    @BeforeEach
    void setUp() {
        algorithm = new BoyerMooreMajorityVote();
    }
    
    @Nested
    @DisplayName("Edge Cases")
    class EdgeCases {
        
        @Test
        @DisplayName("Null input should return null")
        void testNullInput() {
            assertNull(algorithm.findMajorityElement(null));
        }
        
        @Test
        @DisplayName("Empty array should return null")
        void testEmptyArray() {
            int[] nums = {};
            assertNull(algorithm.findMajorityElement(nums));
        }
        
        @Test
        @DisplayName("Single element should return that element")
        void testSingleElement() {
            int[] nums = {42};
            assertEquals(Integer.valueOf(42), algorithm.findMajorityElement(nums));
        }
        
        @Test
        @DisplayName("Two identical elements should return that element")
        void testTwoIdenticalElements() {
            int[] nums = {5, 5};
            assertEquals(Integer.valueOf(5), algorithm.findMajorityElement(nums));
        }
        
        @Test
        @DisplayName("Two different elements should return null")
        void testTwoDifferentElements() {
            int[] nums = {1, 2};
            assertNull(algorithm.findMajorityElement(nums));
        }
    }
    
    @Nested
    @DisplayName("Basic Functionality")
    class BasicFunctionality {
        
        @Test
        @DisplayName("Clear majority element")
        void testClearMajority() {
            int[] nums = {3, 2, 3};
            assertEquals(Integer.valueOf(3), algorithm.findMajorityElement(nums));
        }
        
        @Test
        @DisplayName("Majority at the beginning")
        void testMajorityAtBeginning() {
            int[] nums = {2, 2, 1, 1, 1, 2, 2};
            assertEquals(Integer.valueOf(2), algorithm.findMajorityElement(nums));
        }
        
        @Test
        @DisplayName("Majority at the end")
        void testMajorityAtEnd() {
            int[] nums = {1, 2, 1, 2, 2, 2, 2};
            assertEquals(Integer.valueOf(2), algorithm.findMajorityElement(nums));
        }
        
        @Test
        @DisplayName("Majority scattered throughout")
        void testMajorityScattered() {
            int[] nums = {1, 2, 1, 3, 1, 4, 1};
            assertEquals(Integer.valueOf(1), algorithm.findMajorityElement(nums));
        }
        
        @Test
        @DisplayName("No majority element")
        void testNoMajority() {
            int[] nums = {1, 2, 3, 4};
            assertNull(algorithm.findMajorityElement(nums));
        }
        
        @Test
        @DisplayName("Almost majority (exactly n/2 occurrences)")
        void testAlmostMajority() {
            int[] nums = {1, 1, 2, 2}; 
            assertNull(algorithm.findMajorityElement(nums));
        }
    }
    
    @Nested
    @DisplayName("Complex Scenarios")
    class ComplexScenarios {
        
        @Test
        @DisplayName("Large array with majority")
        void testLargeArrayWithMajority() {
            int[] nums = new int[1001];
            for (int i = 0; i < 501; i++) {
                nums[i] = i;
            }
            for (int i = 501; i < 1001; i++) {
                nums[i] = 999;
            }
            nums[0] = 999; 
            
            assertEquals(Integer.valueOf(999), algorithm.findMajorityElement(nums));
        }
        
        @Test
        @DisplayName("Array with duplicates but no majority")
        void testDuplicatesNoMajority() {
            int[] nums = {1, 1, 2, 2, 3, 3};
            assertNull(algorithm.findMajorityElement(nums));
        }
        
        @Test
        @DisplayName("Negative numbers")
        void testNegativeNumbers() {
            int[] nums = {-1, -1, -1, 2, 2};
            assertEquals(Integer.valueOf(-1), algorithm.findMajorityElement(nums));
        }
        
        @Test
        @DisplayName("Mixed positive and negative with majority")
        void testMixedNumbers() {
            int[] nums = {-1, 2, -1, 3, -1};
            assertEquals(Integer.valueOf(-1), algorithm.findMajorityElement(nums));
        }
    }
    
    @Nested
    @DisplayName("Algorithm Variants")
    class AlgorithmVariants {
        
        @Test
        @DisplayName("Optimized version (assumes majority exists)")
        void testOptimizedVersion() {
            int[] nums = {1, 2, 1, 3, 1, 4, 1};
            assertEquals(Integer.valueOf(1), algorithm.findMajorityElementOptimized(nums));
        }
        
        @Test
        @DisplayName("Compare with naive implementation")
        void testCompareWithNaive() {
            int[] nums = {3, 3, 4, 2, 4, 4, 2, 4, 4};
            Integer boyerMooreResult = algorithm.findMajorityElement(nums);
            Integer naiveResult = algorithm.findMajorityElementNaive(nums);
            assertEquals(naiveResult, boyerMooreResult);
        }
        
        @Test
        @DisplayName("Has majority element utility")
        void testHasMajorityElement() {
            int[] withMajority = {1, 2, 1, 3, 1};
            int[] withoutMajority = {1, 2, 3, 4};
            
            assertTrue(algorithm.hasMajorityElement(withMajority));
            assertFalse(algorithm.hasMajorityElement(withoutMajority));
        }
    }
    
    @Nested
    @DisplayName("Performance Validation")
    class PerformanceValidation {
        
        @Test
        @DisplayName("Performance tracking functionality")
        void testPerformanceTracking() {
            int[] nums = {1, 2, 1, 3, 1};
            algorithm.findMajorityElement(nums, "test-input");
            
            var tracker = algorithm.getPerformanceTracker();
            assertTrue(tracker.getArrayAccesses() > 0);
            assertTrue(tracker.getComparisons() > 0);
            assertTrue(tracker.getExecutionTimeNs() >= 0);
            assertEquals(5, tracker.getInputSize());
            assertEquals("test-input", tracker.getInputType());
        }
        
        @Test
        @DisplayName("Linear time complexity validation")
        void testLinearTimeComplexity() {
            int[] sizes = {100, 200, 400};
            long[] times = new long[sizes.length];
            
            for (int i = 0; i < sizes.length; i++) {
                int[] nums = createMajorityArray(sizes[i]);
                
                long startTime = System.nanoTime();
                algorithm.findMajorityElement(nums);
                long endTime = System.nanoTime();
                
                times[i] = endTime - startTime;
            }
            
            assertTrue(times[2] < times[0] * 5, 
                "Time complexity appears to be worse than linear");
        }
        
        @Test
        @DisplayName("Constant space complexity")
        void testConstantSpace() {
            
            int[] small = createMajorityArray(100);
            int[] large = createMajorityArray(10000);
            
            assertNotNull(algorithm.findMajorityElement(small));
            assertNotNull(algorithm.findMajorityElement(large));
        }
    }
    
    @Nested
    @DisplayName("Property-Based Testing")
    class PropertyBasedTesting {
        
        @Test
        @DisplayName("Random input correctness verification")
        void testRandomInputs() {
            Random random = new Random(42);
            
            for (int test = 0; test < 100; test++) {
                int size = 10 + random.nextInt(90); 
                int[] nums = new int[size];
                
                for (int i = 0; i < size; i++) {
                    nums[i] = random.nextInt(10);
                }
                
                Integer boyerMooreResult = algorithm.findMajorityElement(nums);
                Integer naiveResult = algorithm.findMajorityElementNaive(nums);
                
                assertEquals(naiveResult, boyerMooreResult, 
                    "Results differ for input: " + Arrays.toString(nums));
            }
        }
        
        @Test
        @DisplayName("Guaranteed majority scenarios")
        void testGuaranteedMajority() {
            Random random = new Random(123);
            
            for (int test = 0; test < 50; test++) {
                int size = 10 + random.nextInt(40);
                int majorityValue = random.nextInt(100);
                int[] nums = createArrayWithGuaranteedMajority(size, majorityValue);
                
                assertEquals(Integer.valueOf(majorityValue), 
                    algorithm.findMajorityElement(nums));
            }
        }
    }
    
    private int[] createMajorityArray(int size) {
        int[] nums = new int[size];
        int majorityCount = size / 2 + 1;
        
        for (int i = 0; i < majorityCount; i++) {
            nums[i] = 1;
        }
        
        for (int i = majorityCount; i < size; i++) {
            nums[i] = i; 
        }
        
        return nums;
    }
    
    private int[] createArrayWithGuaranteedMajority(int size, int majorityValue) {
        int[] nums = new int[size];
        int majorityCount = size / 2 + 1;
        Random random = new Random();
        
        boolean[] placed = new boolean[size];
        for (int i = 0; i < majorityCount; i++) {
            int pos;
            do {
                pos = random.nextInt(size);
            } while (placed[pos]);
            
            nums[pos] = majorityValue;
            placed[pos] = true;
        }
        
        for (int i = 0; i < size; i++) {
            if (!placed[i]) {
                do {
                    nums[i] = random.nextInt(1000);
                } while (nums[i] == majorityValue);
            }
        }
        
        return nums;
    }
}