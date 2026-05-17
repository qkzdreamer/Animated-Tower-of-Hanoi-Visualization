package com.example.warehouse.controller;

import com.example.warehouse.api.ApiResponse;
import com.example.warehouse.model.AlertItem;
import com.example.warehouse.model.ScreenOverview;
import com.example.warehouse.service.MockWarehouseService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/screen")
public class ScreenController {

  private final MockWarehouseService service;

  public ScreenController(MockWarehouseService service) {
    this.service = service;
  }

  @GetMapping("/overview")
  public ApiResponse<ScreenOverview> getOverview() {
    return ApiResponse.success(service.getScreenOverview(), "mock screen overview");
  }

  @GetMapping("/alerts")
  public ApiResponse<List<AlertItem>> getAlerts() {
    return ApiResponse.success(service.getAlerts(), "mock screen alerts");
  }
}
