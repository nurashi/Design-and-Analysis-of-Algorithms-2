# Assignment 2: Boyer-Moore Majority Vote Algorithm

## Overview
Implementation of the Boyer-Moore Majority Vote algorithm for single-pass majority element detection as part of Pair 3: Linear Array Algorithms assignment.

## Algorithm Description
The Boyer-Moore Majority Vote algorithm finds the majority element (appears more than n/2 times) in an array using O(1) space and O(n) time complexity.

## Project Structure
```
assignment2-boyer-moore-majority/
├── src/main/java/
│   ├── algorithms/BoyerMooreMajorityVote.java
│   ├── metrics/PerformanceTracker.java
│   └── cli/BenchmarkRunner.java
├── src/test/java/
│   └── algorithms/BoyerMooreMajorityVoteTest.java
├── docs/
│   ├── analysis-report.pdf
│   └── performance-plots/
├── README.md
└── pom.xml
```

## Complexity Analysis
- **Time Complexity**: O(n) - single pass through the array
- **Space Complexity**: O(1) - constant auxiliary space
- **Best Case**: Θ(n) - must examine all elements
- **Worst Case**: Θ(n) - must examine all elements  
- **Average Case**: Θ(n) - must examine all elements

## Usage
```bash
# Compile
mvn compile

# Run tests
mvn test

# Run benchmark
java -cp target/classes cli.BenchmarkRunner [array_size]
```

## Branch Strategy
- `main` — working releases only
- `feature/algorithm` — main implementation
- `feature/metrics` — performance tracking
- `feature/testing` — unit tests
- `feature/cli` — command-line interface
- `feature/optimization` — performance improvements

## Performance Metrics
The implementation tracks:
- Array accesses
- Comparisons
- Memory allocations
- Execution time
- Space usage

## Author
Pair 3 - Student A