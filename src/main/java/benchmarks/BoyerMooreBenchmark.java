package benchmarks;

import algorithms.BoyerMooreMajorityVote;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class BoyerMooreBenchmark {
    
    private BoyerMooreMajorityVote algorithm;
    private int[] smallArray;
    private int[] mediumArray;
    private int[] largeArray;
    
    @Setup
    public void setup() {
        algorithm = new BoyerMooreMajorityVote();
        Random random = new Random(42);
        
        smallArray = generateMajorityArray(100, random);
        
        mediumArray = generateMajorityArray(1000, random);
        
        largeArray = generateMajorityArray(10000, random);
    }
    
    private int[] generateMajorityArray(int size, Random random) {
        int[] array = new int[size];
        int majorityCount = size / 2 + 1;
        
        for (int i = 0; i < majorityCount; i++) {
            array[i] = 42;
        }
        
        for (int i = majorityCount; i < size; i++) {
            array[i] = random.nextInt(1000);
        }
        
        for (int i = size - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        
        return array;
    }
    
    @Benchmark
    public Integer benchmarkSmallArray() {
        return algorithm.findMajorityElement(smallArray.clone(), "jmh-small");
    }
    
    @Benchmark
    public Integer benchmarkMediumArray() {
        return algorithm.findMajorityElement(mediumArray.clone(), "jmh-medium");
    }
    
    @Benchmark
    public Integer benchmarkLargeArray() {
        return algorithm.findMajorityElement(largeArray.clone(), "jmh-large");
    }
    
    @Benchmark
    public Integer benchmarkOptimizedSmall() {
        return algorithm.findMajorityElementOptimized(smallArray.clone());
    }
    
    @Benchmark
    public Integer benchmarkOptimizedMedium() {
        return algorithm.findMajorityElementOptimized(mediumArray.clone());
    }
    
    @Benchmark
    public Integer benchmarkOptimizedLarge() {
        return algorithm.findMajorityElementOptimized(largeArray.clone());
    }
    
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(BoyerMooreBenchmark.class.getSimpleName())
                .build();
        
        new Runner(options).run();
    }
}