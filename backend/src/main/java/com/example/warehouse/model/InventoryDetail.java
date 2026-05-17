package com.example.warehouse.model;

import java.util.List;

public record InventoryDetail(
    String id,
    String name,
    String category,
    String location,
    int quantity,
    String status,
    String owner,
    String supplier,
    String lastInbound,
    String lastOutbound,
    List<InventoryHistory> history) {}
