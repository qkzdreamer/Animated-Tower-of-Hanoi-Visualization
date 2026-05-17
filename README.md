# Animated Tower of Hanoi Visualization

- 原始内容：基于 C++ 的汉诺塔可视化演示。
- 新增内容：仓储大屏 + 系统操作台雏形（Vue + Java），仅做静态展示与点击动效。

## 目录
- `frontend/`：Vue3 前端雏形（大屏 + 系统页面，Mock 数据）
- `backend/`：Spring Boot 后端占位（静态 API）
- `docs/IMPLEMENTATION_GUIDE.md`：实施说明与后续对接指引

## 快速开始
### 前端
```bash
cd frontend
npm install
npm run dev
```

### 后端
```bash
cd backend
mvn spring-boot:run
```

> 后端仅为接口占位，默认端口 8080。
