<script setup>
import { onMounted, ref } from 'vue'
import { getSystemDashboard } from '../api/warehouse'

const stats = ref([])
const tasks = ref([])
const activeStat = ref('')

const loadDashboard = async () => {
  const response = await getSystemDashboard()
  if (response.success) {
    stats.value = response.data.stats
    tasks.value = response.data.tasks
  }
}

onMounted(loadDashboard)
</script>

<template>
  <div class="system-home">
    <section class="stats">
      <div
        v-for="stat in stats"
        :key="stat.label"
        class="stat-card"
        :class="{ active: activeStat === stat.label }"
        @click="activeStat = stat.label"
      >
        <p>{{ stat.label }}</p>
        <h3>{{ stat.value }}<span>{{ stat.unit }}</span></h3>
      </div>
    </section>

    <section class="tasks">
      <div class="tasks-header">
        <h2>任务进度</h2>
        <span>当前波次与巡检任务</span>
      </div>
      <div class="task-list">
        <div v-for="task in tasks" :key="task.id" class="task-item">
          <div>
            <strong>{{ task.title }}</strong>
            <p>{{ task.owner }}</p>
          </div>
          <div class="task-meta">
            <span>{{ task.status }}</span>
            <span>ETA {{ task.eta }}</span>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
.system-home {
  display: flex;
  flex-direction: column;
  gap: 28px;
}

.stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
  gap: 16px;
}

.stat-card {
  background: #f5f8ff;
  border-radius: 16px;
  padding: 16px;
  border: 1px solid transparent;
  cursor: pointer;
  transition: transform 0.2s ease, border 0.2s ease;
}

.stat-card.active {
  border-color: #4f9cfb;
  transform: translateY(-2px) scale(1.02);
}

.stat-card h3 {
  font-size: 22px;
  margin-top: 6px;
}

.stat-card h3 span {
  font-size: 12px;
  color: #7c8ab0;
  margin-left: 4px;
}

.tasks {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.tasks-header {
  display: flex;
  justify-content: space-between;
  color: #5b6b8a;
}

.task-list {
  display: grid;
  gap: 12px;
}

.task-item {
  padding: 14px 16px;
  border-radius: 12px;
  background: #f9fafc;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.task-item p {
  font-size: 12px;
  color: #7b8aa8;
}

.task-meta {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  font-size: 12px;
  color: #6c7aa1;
}
</style>
