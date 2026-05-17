package com.example.warehouse.model;

import java.util.List;

public record DashboardResponse(List<SystemStat> stats, List<TaskItem> tasks) {}
