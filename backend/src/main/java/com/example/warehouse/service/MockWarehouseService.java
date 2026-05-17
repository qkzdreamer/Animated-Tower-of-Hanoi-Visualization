package com.example.warehouse.service;

import com.example.warehouse.model.AlertItem;
import com.example.warehouse.model.DashboardResponse;
import com.example.warehouse.model.HighlightItem;
import com.example.warehouse.model.InventoryDetail;
import com.example.warehouse.model.InventoryHistory;
import com.example.warehouse.model.InventoryItem;
import com.example.warehouse.model.Metric;
import com.example.warehouse.model.ScreenOverview;
import com.example.warehouse.model.SettingItem;
import com.example.warehouse.model.SystemStat;
import com.example.warehouse.model.TaskItem;
import com.example.warehouse.model.UtilizationPoint;
import com.example.warehouse.model.ZoneStatus;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class MockWarehouseService {

  private final ScreenOverview screenOverview;
  private final List<AlertItem> alerts;
  private final DashboardResponse dashboard;
  private final List<InventoryItem> inventoryItems;
  private final Map<String, InventoryDetail> inventoryDetails;
  private final List<SettingItem> settings;

  public MockWarehouseService() {
    screenOverview =
        new ScreenOverview(
            "华北一号智慧仓",
            "2026-05-17 09:10",
            List.of(
                new Metric("在库托盘", 1280, "托", "+3%"),
                new Metric("空闲库位", 320, "位", "-2%"),
                new Metric("今日入库", 238, "托", "+8%"),
                new Metric("今日出库", 192, "托", "+5%")),
            List.of(
                new UtilizationPoint("06:00", 45),
                new UtilizationPoint("08:00", 58),
                new UtilizationPoint("10:00", 64),
                new UtilizationPoint("12:00", 71),
                new UtilizationPoint("14:00", 68),
                new UtilizationPoint("16:00", 74),
                new UtilizationPoint("18:00", 79)),
            List.of(
                new ZoneStatus("A区-冷链", 500, 420, "运行中"),
                new ZoneStatus("B区-零部件", 420, 310, "运行中"),
                new ZoneStatus("C区-成品", 380, 295, "补货中"),
                new ZoneStatus("D区-退货", 300, 170, "巡检中")),
            List.of(
                new HighlightItem("入库进度", "自动分拣完成率 98%，剩余 12 托待入库", "分拣线 2 号"),
                new HighlightItem("出库调度", "高优先级订单 6 单，预计 30 分钟内出库", "波次 15"),
                new HighlightItem("设备状态", "AGV 12 台在线，1 台待维护", "维护建议")));

    alerts =
        List.of(
            new AlertItem("AL-2026-001", "高", "A区温湿度偏离阈值，已启动除湿方案", "09:02", "处理中"),
            new AlertItem("AL-2026-002", "中", "B区补货任务延迟 15 分钟", "08:47", "待确认"),
            new AlertItem("AL-2026-003", "低", "C区叉车电量低于 30%", "08:15", "已通知"));

    dashboard =
        new DashboardResponse(
            List.of(
                new SystemStat("今日入库", 238, "托"),
                new SystemStat("今日出库", 192, "托"),
                new SystemStat("待处理任务", 18, "项"),
                new SystemStat("异常告警", 3, "条")),
            List.of(
                new TaskItem("TSK-1001", "C区补货波次 15", "调度中心", "进行中", "09:40"),
                new TaskItem("TSK-1002", "A区温控巡检", "设备维护", "待处理", "10:10"),
                new TaskItem("TSK-1003", "B区库存盘点", "仓储运营", "计划中", "13:00")));

    inventoryItems =
        List.of(
            new InventoryItem("INV-001", "伺服电机", "零部件", "B-03-12", 120, "充足", "2026-05-16"),
            new InventoryItem("INV-002", "工业传感器", "零部件", "B-02-08", 46, "关注", "2026-05-16"),
            new InventoryItem("INV-003", "冷链组件", "冷链", "A-01-05", 88, "充足", "2026-05-15"),
            new InventoryItem("INV-004", "装配成品箱", "成品", "C-07-02", 32, "紧张", "2026-05-15"));

    inventoryDetails =
        Map.of(
            "INV-001",
                new InventoryDetail(
                    "INV-001",
                    "伺服电机",
                    "零部件",
                    "B-03-12",
                    120,
                    "充足",
                    "制造中心",
                    "华南电机厂",
                    "2026-05-14 11:20",
                    "2026-05-16 16:35",
                    List.of(
                        new InventoryHistory("05-12", 40, 12),
                        new InventoryHistory("05-13", 20, 18),
                        new InventoryHistory("05-14", 60, 22),
                        new InventoryHistory("05-15", 0, 8))),
            "INV-002",
                new InventoryDetail(
                    "INV-002",
                    "工业传感器",
                    "零部件",
                    "B-02-08",
                    46,
                    "关注",
                    "智能车间",
                    "华北传感",
                    "2026-05-15 09:45",
                    "2026-05-16 18:10",
                    List.of(
                        new InventoryHistory("05-12", 10, 4),
                        new InventoryHistory("05-13", 12, 6),
                        new InventoryHistory("05-14", 20, 8),
                        new InventoryHistory("05-15", 6, 4))),
            "INV-003",
                new InventoryDetail(
                    "INV-003",
                    "冷链组件",
                    "冷链",
                    "A-01-05",
                    88,
                    "充足",
                    "冷链运营",
                    "华东制冷",
                    "2026-05-15 10:15",
                    "2026-05-16 14:05",
                    List.of(
                        new InventoryHistory("05-12", 22, 10),
                        new InventoryHistory("05-13", 18, 12),
                        new InventoryHistory("05-14", 24, 14),
                        new InventoryHistory("05-15", 16, 8))),
            "INV-004",
                new InventoryDetail(
                    "INV-004",
                    "装配成品箱",
                    "成品",
                    "C-07-02",
                    32,
                    "紧张",
                    "成品仓",
                    "总部工厂",
                    "2026-05-15 15:30",
                    "2026-05-16 17:50",
                    List.of(
                        new InventoryHistory("05-12", 8, 6),
                        new InventoryHistory("05-13", 6, 10),
                        new InventoryHistory("05-14", 4, 12),
                        new InventoryHistory("05-15", 14, 8))));

    settings =
        List.of(
            new SettingItem("autoDispatch", "自动波次调度", true),
            new SettingItem("tempAlert", "温控异常提醒", true),
            new SettingItem("nightShift", "夜班模式", false));
  }

  public ScreenOverview getScreenOverview() {
    return screenOverview;
  }

  public List<AlertItem> getAlerts() {
    return alerts;
  }

  public DashboardResponse getDashboard() {
    return dashboard;
  }

  public List<InventoryItem> getInventoryItems() {
    return inventoryItems;
  }

  public InventoryDetail getInventoryDetail(String id) {
    return inventoryDetails.get(id);
  }

  public List<SettingItem> getSettings() {
    return settings;
  }
}
