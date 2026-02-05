# Comparative Analysis of Matrix Chain Multiplication Algorithms

This repository contains a Java-based implementation and technical analysis of the **Matrix Chain Multiplication (MCM)** problem. Developed as part of **CMSC 123: Data Structures and Algorithms II**, this project evaluates three distinct algorithmic design paradigms—**Dynamic Programming (Tabulation)**, **Divide and Conquer**, and **Backtracking**—to determine the most efficient method for minimizing scalar multiplications.

## Overview

Matrix Chain Multiplication is a classic optimization problem where the goal is to find the optimal parenthesization of a sequence of matrices. Because matrix multiplication is associative, the order in which operations are performed significantly impacts the total computational cost, even though the final product remains the same.

## Implementation Details

The project includes a graphical user interface (GUI) built with **Java Swing** that allows users to input matrix dimensions and select an algorithmic approach to compute the minimum multiplication cost.

### Algorithmic Paradigms

1. **Dynamic Programming (Tabulation):** Iteratively builds a 2D cost table ( space) to solve subproblems and avoid redundant calculations.


2. **Divide and Conquer:** Recursively partitions the matrix chain into smaller segments. This implementation utilizes **memoization** to store results and prevent recomputation.


3. **Backtracking:** Explores all possible parenthesization sequences recursively to guarantee an optimal solution, though it is the most computationally expensive approach.



---

## Complexity Analysis

The following table summarizes the theoretical and observed efficiency of each implemented solution:

| Algorithm | Time Complexity | Space Complexity | Recommendation |
| --- | --- | --- | --- |
| **Dynamic Programming** |  |  | <br>**Best for large datasets** 

 |
| **Divide and Conquer** |  |  | Practical for small chains 

 |
| **Backtracking** |  |  | Useful as a conceptual tool 

 |

### Key Findings

* **Efficiency:** Dynamic Programming is the most optimal approach due to its polynomial time complexity (), which grows significantly slower than the exponential growth () of the other methods.


* **Redundancy:** DP successfully eliminates redundant computations by storing intermediate results in a tabulation matrix, whereas basic recursion or backtracking without effective pruning results in wasted computational cycles.



---

## Technical Stack

* **Language:** Java 


* **Environment:** Visual Studio Code 


* **GUI Framework:** Java Swing

## How to Run

1. Ensure you have the **Java Development Kit (JDK)** installed.
2. Compile the source file:
```bash
javac MatrixChainMultiplication.java

```


3. Execute the application:
```bash
java MatrixChainMultiplication

```


4. Input matrix dimensions as a comma-separated list (e.g., `10, 20, 30` for two matrices of  and ).
