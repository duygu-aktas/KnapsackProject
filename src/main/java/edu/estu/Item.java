package edu.estu;

public record Item(long profit, long weight) {
    public double unitPrice() {
        return (weight > 0) ? (double) profit / weight : 0;
    }
}