<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div class="system-layout">
    <aside class="system-sidebar">
      <div class="logo">
        <img src="@/assets/images/warehouse.svg" alt="logo" />
        <div>
          <strong>仓储系统</strong>
          <span>操作台</span>
        </div>
      </div>
      <nav>
        <RouterLink to="/system" end>首页总览</RouterLink>
        <RouterLink to="/system/inventory">库存列表</RouterLink>
        <RouterLink to="/system/settings">系统配置</RouterLink>
      </nav>
      <div class="sidebar-footer">
        <p>模拟登录：管理员</p>
        <span>权限：仓储运营</span>
      </div>
    </aside>

    <section class="system-main">
      <header class="system-header">
        <div>
          <h1>智慧仓储系统</h1>
          <p>本页面仅展示布局与交互，数据来自本地 Mock。</p>
        </div>
        <button class="primary-button">新建任务</button>
      </header>
      <main class="system-content">
        <RouterView />
      </main>
    </section>
  </div>
</template>

<style scoped>
.system-layout {
  min-height: 100vh;
  display: grid;
  grid-template-columns: 240px 1fr;
  background: #f5f7fb;
  color: #1f2a44;
}

.system-sidebar {
  background: #10172e;
  color: #dce4ff;
  padding: 24px 20px;
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.logo {
  display: flex;
  align-items: center;
  gap: 12px;
}

.logo img {
  width: 36px;
  height: 36px;
}

.logo span {
  font-size: 12px;
  color: #9fb1ff;
}

nav {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

nav a {
  padding: 10px 12px;
  border-radius: 12px;
  color: inherit;
  transition: background 0.2s ease;
}

nav a.router-link-exact-active {
  background: rgba(79, 156, 251, 0.2);
  color: #c8d6ff;
}

nav a:hover {
  background: rgba(255, 255, 255, 0.1);
}

.sidebar-footer {
  margin-top: auto;
  font-size: 12px;
  color: #8ea3e1;
}

.system-main {
  padding: 32px clamp(24px, 4vw, 48px);
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.system-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 24px;
}

.system-header p {
  color: #5b6b8a;
  margin-top: 6px;
}

.primary-button {
  border: none;
  border-radius: 12px;
  padding: 10px 18px;
  background: #4f9cfb;
  color: white;
  cursor: pointer;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.primary-button:hover {
  transform: translateY(-1px);
  box-shadow: 0 8px 16px rgba(79, 156, 251, 0.35);
}

.system-content {
  background: white;
  border-radius: 20px;
  padding: 24px;
  box-shadow: 0 20px 60px rgba(15, 28, 64, 0.08);
  min-height: 60vh;
}

@media (max-width: 900px) {
  .system-layout {
    grid-template-columns: 1fr;
  }

  .system-sidebar {
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
  }

  nav {
    flex-direction: row;
  }

  .sidebar-footer {
    display: none;
  }
}
</style>
