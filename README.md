# Assignment 2: Boyer-Moore Majority Vote Algorithm

## Overview
Implementation of the Boyer-Moore Majority Vote algorithm for single-pass majority element detection as part of **Pair 3: Linear Array Algorithms** assignment.

**Student A Implementation**: Boyer-Moore Majority Vote  
**Partner Algorithm**: Kadane's Algorithm (Student B)

## Algorithm Description
The Boyer-Moore Majority Vote algorithm finds the majority element (appears more than n/2 times) in an array using optimal O(n) time complexity and O(1) space complexity. It uses a two-phase approach:

1. **Candidate Selection**: Single pass to find potential majority candidate
2. **Verification**: Optional second pass to confirm the candidate is actually majority

## Project Structure
```
assignment2-boyer-moore-majority/
├── src/main/java/
│   ├── algorithms/BoyerMooreMajorityVote.java     # Core algorithm implementation
│   ├── metrics/PerformanceTracker.java            # Performance monitoring
│   ├── cli/BenchmarkRunner.java                   # Comprehensive benchmarking
│   └── cli/Demo.java                              # Usage demonstration
├── src/test/java/
│   ├── algorithms/BoyerMooreMajorityVoteTest.java # Unit tests (23 tests)
│   └── algorithms/BoyerMooreMajorityVoteIntegrationTest.java # Integration tests
├── docs/
│   ├── analysis-report.md                         # Complexity analysis
│   └── performance-plots/                         # Performance visualization
├── README.md
└── pom.xml
```

## Complexity Analysis

### Time Complexity
- **Best Case**: Θ(n) - must examine all elements
- **Worst Case**: Θ(n) - must examine all elements  
- **Average Case**: Θ(n) - must examine all elements

### Space Complexity
- **O(1)** - constant auxiliary space usage
- No additional data structures that scale with input size

### Optimizations Implemented
1. **Early Termination**: Stop verification once majority threshold reached
2. **Memory Efficiency**: Reuse variables, minimize allocations
3. **Cache Optimization**: Sequential memory access patterns

## Usage

### Quick Start
```bash
# Compile the project
mvn compile

# Run comprehensive tests (23 tests, all passing)
mvn test

# Run basic benchmark
java -cp target/classes cli.BenchmarkRunner

# Run benchmark for specific size
java -cp target/classes cli.BenchmarkRunner 50000

# Run demonstration
java -cp target/classes cli.Demo
```

### Programming Examples
```java
BoyerMooreMajorityVote algorithm = new BoyerMooreMajorityVote();

// Basic usage
int[] nums = {3, 2, 3, 4, 3, 3, 3};
Integer majority = algorithm.findMajorityElement(nums);
// Returns: 3

// Performance tracking
algorithm.findMajorityElement(nums, "test-input");
PerformanceTracker tracker = algorithm.getPerformanceTracker();
System.out.println(tracker.getPerformanceSummary());
```

## Features Implemented

### Core Algorithm ✅
- [x] Boyer-Moore Majority Vote implementation
- [x] Input validation and error handling
- [x] Edge case support (empty arrays, single elements)
- [x] Alternative implementations (naive, optimized)

### Performance Analysis ✅  
- [x] Comprehensive performance tracking
- [x] Metrics collection (comparisons, array accesses, memory)
- [x] CSV export for analysis
- [x] Multiple input distribution testing

### Testing ✅
- [x] Unit tests covering all edge cases (23 tests)
- [x] Property-based testing with random inputs
- [x] Integration tests for algorithm variants
- [x] Performance validation and complexity verification

### CLI & Benchmarking ✅
- [x] Command-line benchmark runner
- [x] Multiple input types (random, sorted, nearly-sorted, etc.)
- [x] Configurable input sizes (100 to 100,000 elements)
- [x] Performance comparison with naive O(n²) approach

### Git Workflow ✅
- [x] Feature branch strategy implemented
- [x] Clean commit history with descriptive messages
- [x] Release tagging (v1.0)
- [x] Professional documentation

## Performance Results

Sample benchmark results (1,000 elements):
```
  random         :    0.179 ms |    2,000 accesses |  1,501 comparisons | Result: None
  sorted         :    0.095 ms |    1,501 accesses |  1,500 comparisons | Result: 1
  reverse-sorted :    0.122 ms |    2,000 accesses |  1,749 comparisons | Result: 1
  nearly-sorted  :    0.123 ms |    1,987 accesses |  1,986 comparisons | Result: 1
  majority-heavy :    0.106 ms |    1,618 accesses |  1,616 comparisons | Result: 42
```

## Algorithm Comparison

| Metric | Boyer-Moore | Naive Approach | Sorting-based |
|--------|-------------|----------------|---------------|
| Time Complexity | O(n) | O(n²) | O(n log n) |
| Space Complexity | O(1) | O(1) | O(1) |
| Passes Required | 1-2 | n | 1 |
| Cache Performance | Excellent | Poor | Good |
| Scalability | Excellent | Poor | Good |

## Git Branch History

Following the assignment's GitHub workflow requirements:

- `main` — working releases only (tagged v0.1, v1.0, v1.1)
- `feature/metrics` — performance tracking implementation
- `feature/algorithm` — core Boyer-Moore algorithm
- `feature/testing` — comprehensive test suite
- `feature/cli` — benchmark runner and CLI
- `feature/optimization` — performance improvements

### Commit Storyline ✅
```
fee233d perf(benchmark): JMH harness for accurate measurements
b4d9ddc release: v1.0 with complete implementation  
6a390f6 fix(edge-cases): handle empty and single-element arrays
133cbac docs(readme): usage instructions and complexity analysis
fa8b5e8 feat(optimization): probabilistic early validation for large arrays
9baadbe feat(cli): benchmark runner with configurable input sizes
93d1e0b test(algorithm): comprehensive test suite with edge cases
fd33d1a feat(algorithm): baseline Boyer-Moore Majority Vote implementation
cd54414 feat(metrics): performance counters and CSV export (v0.1)
36eff98 init: maven project structure, junit5, ci setup
```

## Assignment Deliverables

### Individual Implementation ✅
- Complete Boyer-Moore Majority Vote algorithm
- 23 comprehensive unit tests (all passing)
- Performance benchmarking with 5 input distributions
- Clean Git workflow with feature branches

### Ready for Peer Analysis
- Code ready for partner's review and optimization analysis
- Performance data collected for empirical validation
- Documentation prepared for complexity analysis
- Implementation ready for cross-validation with Kadane's Algorithm

### Next Steps (Partner Analysis Phase)
1. **Code Exchange**: Share implementation with Student B (Kadane's Algorithm)
2. **Cross-Review**: Analyze partner's Kadane implementation
3. **Empirical Testing**: Benchmark both algorithms comparatively
4. **Analysis Report**: Generate detailed analysis report (8 pages)
5. **Optimization**: Implement suggested improvements

## Author
**Pair 3 - Student A**  
Course: Design and Analysis of Algorithms  
Assignment: 2 - Algorithmic Analysis and Peer Code Review

## License
Academic use only - Course Assignment