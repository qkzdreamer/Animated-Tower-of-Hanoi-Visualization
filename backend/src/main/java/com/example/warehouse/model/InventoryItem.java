package com.example.warehouse.model;

public record InventoryItem(
    String id,
    String name,
    String category,
    String location,
    int quantity,
    String status,
    String lastMove) {}
