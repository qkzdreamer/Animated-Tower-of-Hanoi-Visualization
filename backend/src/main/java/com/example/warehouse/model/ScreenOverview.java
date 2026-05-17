package com.example.warehouse.model;

import java.util.List;

public record ScreenOverview(
    String warehouseName,
    String lastSync,
    List<Metric> metrics,
    List<UtilizationPoint> utilization,
    List<ZoneStatus> zones,
    List<HighlightItem> highlights) {}
