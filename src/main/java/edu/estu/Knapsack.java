package edu.estu;

import java.util.*;

public class Knapsack {
    private final long capacity;
    private final List<Item> items;

    public Knapsack(long capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public long greedySolution() {
        Collections.sort(items, Comparator.comparingDouble(Item::unitPrice).reversed());

        long totalProfit = 0;
        long totalWeight = 0;

        for (Item item : items) {
            if (totalWeight + item.weight() <= capacity) {
                totalWeight += item.weight();
                totalProfit += item.profit();
                System.out.println("Selecting item with profit: " + item.profit() + " and weight: " + item.weight());
            } else {
                System.out.println("Skipping item with profit: " + item.profit() + " and weight: " + item.weight());
            }
        }

        return totalProfit;
    }

    public long randomSolution() {
        Random random = new Random();
        List<Item> randomItems = new ArrayList<>(items);
        Collections.shuffle(randomItems, random);

        long totalProfit = 0;
        long totalWeight = 0;

        for (Item item : randomItems) {
            if (totalWeight + item.weight() <= capacity) {
                totalWeight += item.weight();
                totalProfit += item.profit();
                System.out.println("Selecting item with profit: " + item.profit() + " and weight: " + item.weight());
            } else {
                System.out.println("Skipping item with profit: " + item.profit() + " and weight: " + item.weight());
            }
        }

        return totalProfit;
    }

    public long getCapacity() {
        return capacity;
    }

    public List<Item> getItems() {
        return items;
    }
}