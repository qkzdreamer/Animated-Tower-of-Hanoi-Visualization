# 前端/后端雏形实施说明

## 目标范围
- **库的大屏**：全屏展示、自动轮播、轻交互。
- **系统界面**：登录后操作台风格，包含首页、列表、详情、配置。
- **不做**：硬件连接、云数据库、真实通信。

## 基础准备（零基础）
1. 学习顺序：HTML/CSS → JavaScript → Vue3 基础（组件、路由、状态）。
2. 安装工具：Node.js、npm/pnpm、JDK 17、Maven、VS Code、IntelliJ。

## 目录结构
- `frontend/`：Vue3 前端雏形（静态 Mock 数据）。
- `backend/`：Spring Boot 后端占位（静态 Mock 接口）。
- `docs/`：说明文档与对接指引。

## 前端运行
```bash
cd frontend
npm install
npm run dev
```
- 入口页面：`/`（选择大屏或系统）。
- 大屏页面：`/screen`。
- 系统页面：`/system`。

## 后端运行
```bash
cd backend
mvn spring-boot:run
```
- 默认端口：`8080`。
- 仅返回静态 Mock 数据。

## Mock API 列表（后续对接真实后端）
| 模块 | 方法 | 路径 | 说明 |
| --- | --- | --- | --- |
| 大屏 | GET | `/api/screen/overview` | 大屏概览（指标、库区、利用率、提示） |
| 大屏 | GET | `/api/screen/alerts` | 告警列表 |
| 系统 | GET | `/api/system/dashboard` | 系统首页（统计、任务） |
| 系统 | GET | `/api/system/items` | 库存列表 |
| 系统 | GET | `/api/system/items/{id}` | 库存详情 |
| 系统 | GET | `/api/system/settings` | 系统配置 |

## 字段示例（前后端统一结构）
```json
{
  "success": true,
  "message": "mock",
  "data": {
    "warehouseName": "华北一号智慧仓",
    "metrics": [{"label": "在库托盘", "value": 1280, "unit": "托"}],
    "zones": [{"name": "A区-冷链", "capacity": 500, "used": 420}]
  }
}
```

## 后续接入真实 API 的步骤
1. **确认字段**：与后端约定字段名称、分页、筛选参数。
2. **替换前端请求**：在 `frontend/src/api/warehouse.js` 中替换为真实 `fetch/axios`。
3. **处理异常**：保持 `success/message/data` 结构，统一错误提示。
4. **跨域与鉴权**：配置后端 CORS、Token/Session 机制。
5. **联调验证**：使用 Postman/Apifox 先验证接口，再接前端。

## 验收清单
- 大屏与系统页面可打开，布局完整。
- 点击动画、轮播切换可见。
- Mock 数据可渲染。
- 前后端 API 结构已预留。
