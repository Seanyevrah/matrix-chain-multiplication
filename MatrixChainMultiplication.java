import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MatrixChainMultiplication {
    
    private JFrame frame;
    private JTextField inputField;
    private JComboBox<String> methodSelector;
    private JTextArea resultArea;
    
    public MatrixChainMultiplication() {
        frame = new JFrame("Matrix Chain Multiplication");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        
        inputField = new JTextField(20);
        methodSelector = new JComboBox<>(new String[]{"DP Tabulation", "Backtracking", "Divide & Conquer"});
        JButton computeButton = new JButton("Compute");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        
        frame.add(new JLabel("Matrix Dimensions:"));
        frame.add(inputField);
        frame.add(new JLabel("Method:"));
        frame.add(methodSelector);
        frame.add(computeButton);
        frame.add(new JScrollPane(resultArea));
        
        computeButton.addActionListener(new ComputeListener());
        
        frame.setVisible(true);
    }
    
    private class ComputeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String[] input = inputField.getText().split(",");
                long[] dims = Arrays.stream(input).mapToLong(Long::parseLong).toArray();
                long result = 0;
                
                String method = (String) methodSelector.getSelectedItem();
                if (method.equals("DP Tabulation")) {
                    result = matrixChainDP(dims);
                } else if (method.equals("Backtracking")) {
                    result = matrixChainBacktracking(dims, 1, dims.length - 1);
                } else if (method.equals("Divide & Conquer")) {
                    result = matrixChainDivideAndConquer(dims, 1, dims.length - 1, new Long[dims.length][dims.length]);
                }
                
                resultArea.setText("Minimum Multiplications: " + result);
            } catch (Exception ex) {
                resultArea.setText("Invalid input!");
            }
        }
    }
    
    // Dynamic Programming (Tabulation) Approach
    // Builds a table of solutions for subproblems to avoid redundant calculations
    private long matrixChainDP(long[] p) {
        int n = p.length;
        long[][] dp = new long[n][n];
        
        for (int L = 2; L < n; L++) { // L is the chain length
            for (int i = 1; i < n - L + 1; i++) {
                int j = i + L - 1;
                dp[i][j] = Long.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    if (dp[i][k] != Long.MAX_VALUE && dp[k + 1][j] != Long.MAX_VALUE) {
                        long cost = dp[i][k] + dp[k + 1][j] + Math.multiplyExact(Math.multiplyExact(p[i - 1], p[k]), p[j]);
                        dp[i][j] = Math.min(dp[i][j], cost);
                    }
                }
            }
        }
        return dp[1][n - 1];
    }
    
    // Recursive Backtracking Approach
    // Tries all possible ways to parenthesize the matrices recursively
    private long matrixChainBacktracking(long[] dims, int i, int j) {
        if (i == j) return 0; // Base case: single matrix
        long minCost = Long.MAX_VALUE;
        for (int k = i; k < j; k++) {
            long leftCost = matrixChainBacktracking(dims, i, k);
            long rightCost = matrixChainBacktracking(dims, k + 1, j);
            long cost = leftCost + rightCost + Math.multiplyExact(Math.multiplyExact(dims[i - 1], dims[k]), dims[j]);
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }
    
    // Divide and Conquer with Memoization
    // Recursively solves subproblems and stores results to avoid recomputation
    private long matrixChainDivideAndConquer(long[] dims, int i, int j, Long[][] opt) {
        if (i == j) return 0; // Base case: single matrix
        if (opt[i][j] != null) return opt[i][j]; // Use stored result if available
        
        long minCost = Long.MAX_VALUE;
        for (int k = i; k < j; k++) {
            long leftCost = matrixChainDivideAndConquer(dims, i, k, opt);
            long rightCost = matrixChainDivideAndConquer(dims, k + 1, j, opt);
            long cost = leftCost + rightCost + Math.multiplyExact(Math.multiplyExact(dims[i - 1], dims[k]), dims[j]);
            if (cost < minCost) {
                minCost = cost;
                opt[i][j] = minCost; // Store the result for future use
            }
        }
        return minCost;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MatrixChainMultiplication::new);
    }
}