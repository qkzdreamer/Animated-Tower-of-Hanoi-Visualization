package com.example.warehouse.controller;

import com.example.warehouse.api.ApiResponse;
import com.example.warehouse.model.DashboardResponse;
import com.example.warehouse.model.InventoryDetail;
import com.example.warehouse.model.InventoryItem;
import com.example.warehouse.model.SettingItem;
import com.example.warehouse.service.MockWarehouseService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system")
public class SystemController {

  private final MockWarehouseService service;

  public SystemController(MockWarehouseService service) {
    this.service = service;
  }

  @GetMapping("/dashboard")
  public ApiResponse<DashboardResponse> getDashboard() {
    return ApiResponse.success(service.getDashboard(), "mock system dashboard");
  }

  @GetMapping("/items")
  public ApiResponse<List<InventoryItem>> getInventoryItems() {
    return ApiResponse.success(service.getInventoryItems(), "mock inventory list");
  }

  @GetMapping("/items/{id}")
  public ApiResponse<InventoryDetail> getInventoryDetail(@PathVariable String id) {
    InventoryDetail detail = service.getInventoryDetail(id);
    if (detail == null) {
      return ApiResponse.failure("inventory item not found");
    }
    return ApiResponse.success(detail, "mock inventory detail");
  }

  @GetMapping("/settings")
  public ApiResponse<List<SettingItem>> getSettings() {
    return ApiResponse.success(service.getSettings(), "mock system settings");
  }
}
