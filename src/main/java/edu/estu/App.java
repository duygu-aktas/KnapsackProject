package edu.estu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // If no file paths are provided as arguments
        if (args.length == 0) {
            System.out.println("Please provide file paths as arguments.");
            return;
        }

        // Loop through each file path provided in command-line arguments
        for (String testFilePath : args) {
            Path path = Paths.get(testFilePath);

            // Check if the path exists and is a regular file
            if (Files.exists(path) && Files.isRegularFile(path)) {
                try {
                    // Read knapsack instance from the provided file
                    Knapsack knapsack = readKnapsackFromFile(testFilePath);

                    // Compute greedy solution
                    long greedyProfit = knapsack.greedySolution();

                    // Compute random solutions
                    int randomBetterCount = 0;
                    List<Long> randomProfits = new ArrayList<>();
                    int iterations = 1_000_000;

                    for (int i = 0; i < iterations; i++) {
                        long randProfit = knapsack.randomSolution();
                        randomProfits.add(randProfit);
                        if (randProfit > greedyProfit) {
                            randomBetterCount++;
                        }
                    }

                    // Gather stats from random profits
                    long minProfit = Collections.min(randomProfits);
                    long maxProfit = Collections.max(randomProfits);
                    double averageProfit = randomProfits.stream().mapToDouble(p -> p).average().orElse(0);

                    // Print results for this file
                    System.out.println("Results for file: " + testFilePath);
                    System.out.println("Profit of the greedy algorithm: " + greedyProfit);
                    System.out.println("Stats of the random solutions (min, avg, max): " + minProfit + ", " + averageProfit + ", " + maxProfit);
                    System.out.printf("%d/1,000,000 random solutions were better than the greedy solution.%n", randomBetterCount);
                } catch (IOException e) {
                    System.err.println("Error reading file: " + testFilePath);
                    e.printStackTrace();
                }
            } else {
                System.out.println("The path " + testFilePath + " does not exist or is not a regular file.");
            }
        }
    }

    public static Knapsack readKnapsackFromFile(String inputFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            long capacity = Long.parseLong(br.readLine().trim());
            Knapsack knapsack = new Knapsack(capacity);

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                long profit = Long.parseLong(parts[0]);
                long weight = Long.parseLong(parts[1]);
                knapsack.addItem(new Item(profit, weight));
            }
            return knapsack;
        }
    }
}