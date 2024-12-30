package edu.estu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private String testFilePath;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary test file with knapsack data
        testFilePath = "test_knapsack.txt";
        // Format: Capacity\nProfit1 Weight1\nProfit2 Weight2\n...
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilePath))) {
            writer.write("50\n"); // capacity
            writer.write("10 5\n"); // item 1: profit 10, weight 5
            writer.write("20 10\n"); // item 2: profit 20, weight 10
            writer.write("30 15\n"); // item 3: profit 30, weight 15
        }
    }

    @Test
    void testReadKnapsackFromFile() throws IOException {
        Knapsack knapsack = App.readKnapsackFromFile(testFilePath);
        assertNotNull(knapsack);
        assertEquals(50, knapsack.getCapacity()); // Assuming a getCapacity method exists
        assertEquals(3, knapsack.getItems().size()); // Assuming a getItems method exists
    }

    @Test
    void testGreedySolution() throws IOException {
        Knapsack knapsack = App.readKnapsackFromFile(testFilePath);
        long greedyProfit = knapsack.greedySolution(); // Assuming you have defined this method
        assertEquals(40, greedyProfit); // Expected profit will depend on your greedy algorithm
    }

    @Test
    void testRandomSolution() throws IOException {
        Knapsack knapsack = App.readKnapsackFromFile(testFilePath);
        long randomProfit = knapsack.randomSolution(); // Assuming you have defined this method
        assertTrue(randomProfit >= 0); // Random profit should be non-negative
    }

    @org.junit.Test
    public void testFileDoesNotExist() {
        assertThrows(IOException.class, () -> {
            App.readKnapsackFromFile("non_existing_file.txt");
        });
    }

    @AfterEach
    void tearDown() throws IOException {
        // Delete the test file
        Files.deleteIfExists(Paths.get(testFilePath));
    }
}