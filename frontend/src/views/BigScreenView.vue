<script setup>
import { computed, onMounted, onUnmounted, ref } from 'vue'
import { getScreenAlerts, getScreenOverview } from '../api/warehouse'

const overview = ref(null)
const alerts = ref([])
const activeHighlight = ref(0)
const activeZone = ref('')
const now = ref('')
let timerId

const metrics = computed(() => overview.value?.metrics ?? [])
const zones = computed(() => overview.value?.zones ?? [])
const utilization = computed(() => overview.value?.utilization ?? [])
const highlights = computed(() => overview.value?.highlights ?? [])

const updateTime = () => {
  const time = new Date()
  const formatter = new Intl.DateTimeFormat('zh-CN', {
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
  })
  now.value = formatter.format(time)
}

const rotateHighlight = () => {
  if (!highlights.value.length) {
    return
  }
  activeHighlight.value = (activeHighlight.value + 1) % highlights.value.length
}

const loadData = async () => {
  const [overviewRes, alertRes] = await Promise.all([getScreenOverview(), getScreenAlerts()])
  if (overviewRes.success) {
    overview.value = overviewRes.data
    activeZone.value = overviewRes.data.zones?.[0]?.name ?? ''
  }
  if (alertRes.success) {
    alerts.value = alertRes.data
  }
}

onMounted(async () => {
  await loadData()
  updateTime()
  timerId = setInterval(() => {
    updateTime()
    rotateHighlight()
  }, 4000)
})

onUnmounted(() => {
  clearInterval(timerId)
})
</script>

<template>
  <div class="screen">
    <header class="screen-header">
      <div>
        <p class="screen-title">{{ overview?.warehouseName || '智慧仓储大屏' }}</p>
        <span class="screen-subtitle">最近同步：{{ overview?.lastSync || '---' }}</span>
      </div>
      <div class="screen-clock">{{ now }}</div>
    </header>

    <section class="screen-metrics">
      <div
        v-for="metric in metrics"
        :key="metric.label"
        class="metric-card"
        @click="activeZone = metric.label"
      >
        <p>{{ metric.label }}</p>
        <h3>{{ metric.value }}<span>{{ metric.unit }}</span></h3>
        <span class="metric-trend">{{ metric.trend }}</span>
      </div>
    </section>

    <section class="screen-main">
      <div class="screen-panel">
        <h2>库区利用率</h2>
        <div class="utilization">
          <div v-for="point in utilization" :key="point.time" class="utilization-bar">
            <div class="bar" :style="{ height: `${point.value}%` }"></div>
            <span>{{ point.time }}</span>
          </div>
        </div>
      </div>

      <div class="screen-panel">
        <h2>库区状态</h2>
        <div class="zones">
          <button
            v-for="zone in zones"
            :key="zone.name"
            class="zone"
            :class="{ active: zone.name === activeZone }"
            @click="activeZone = zone.name"
          >
            <div>
              <p>{{ zone.name }}</p>
              <span>{{ zone.status }}</span>
            </div>
            <div class="zone-progress">
              <div
                class="zone-bar"
                :style="{ width: `${Math.round((zone.used / zone.capacity) * 100)}%` }"
              ></div>
            </div>
            <small>{{ zone.used }} / {{ zone.capacity }}</small>
          </button>
        </div>
      </div>

      <div class="screen-panel highlight">
        <h2>运行提示</h2>
        <div class="highlight-card" v-if="highlights.length">
          <h3>{{ highlights[activeHighlight].title }}</h3>
          <p>{{ highlights[activeHighlight].description }}</p>
          <span>{{ highlights[activeHighlight].tag }}</span>
        </div>
        <div v-else class="highlight-card">
          <p>暂无提示</p>
        </div>
        <div class="highlight-dots">
          <button
            v-for="(item, index) in highlights"
            :key="item.title"
            :class="{ active: index === activeHighlight }"
            @click="activeHighlight = index"
          ></button>
        </div>
      </div>
    </section>

    <section class="screen-alerts">
      <h2>实时告警</h2>
      <div class="alert-list">
        <div v-for="alert in alerts" :key="alert.id" class="alert-item">
          <div>
            <span class="alert-level">{{ alert.level }}</span>
            <strong>{{ alert.message }}</strong>
          </div>
          <div class="alert-meta">
            <span>{{ alert.time }}</span>
            <span>{{ alert.status }}</span>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
.screen {
  min-height: 100vh;
  padding: 32px;
  color: #e9eef7;
  background: linear-gradient(135deg, #0b1020, #121c36 40%, #0b1020);
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.screen-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.screen-title {
  font-size: 28px;
  font-weight: 600;
}

.screen-subtitle {
  font-size: 13px;
  color: #a5b4d9;
}

.screen-clock {
  font-size: 24px;
  font-weight: 600;
  padding: 12px 20px;
  border-radius: 16px;
  background: rgba(86, 126, 255, 0.15);
  border: 1px solid rgba(86, 126, 255, 0.4);
}

.screen-metrics {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
  gap: 16px;
}

.metric-card {
  background: rgba(255, 255, 255, 0.06);
  border-radius: 18px;
  padding: 16px 20px;
  display: flex;
  flex-direction: column;
  gap: 8px;
  cursor: pointer;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.metric-card:hover {
  transform: translateY(-2px) scale(1.02);
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.3);
}

.metric-card h3 {
  font-size: 22px;
}

.metric-card h3 span {
  font-size: 12px;
  margin-left: 4px;
  color: #a9b7de;
}

.metric-trend {
  font-size: 12px;
  color: #79f5c7;
}

.screen-main {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 20px;
}

.screen-panel {
  background: rgba(255, 255, 255, 0.05);
  border-radius: 20px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.screen-panel h2 {
  font-size: 16px;
}

.utilization {
  display: flex;
  align-items: flex-end;
  gap: 12px;
  height: 160px;
}

.utilization-bar {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 8px;
  font-size: 11px;
  color: #9fb3e0;
}

.utilization-bar .bar {
  width: 100%;
  border-radius: 12px 12px 6px 6px;
  background: linear-gradient(180deg, #4f9cfb, #62f5d3);
}

.zones {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.zone {
  background: rgba(8, 14, 30, 0.6);
  border: 1px solid transparent;
  border-radius: 14px;
  padding: 12px 14px;
  text-align: left;
  color: inherit;
  cursor: pointer;
  transition: transform 0.2s ease, border 0.2s ease;
}

.zone.active {
  border-color: rgba(82, 152, 255, 0.8);
  transform: translateY(-2px);
}

.zone-progress {
  height: 6px;
  background: rgba(255, 255, 255, 0.08);
  border-radius: 999px;
  overflow: hidden;
  margin: 8px 0;
}

.zone-bar {
  height: 100%;
  background: linear-gradient(90deg, #4f9cfb, #7e8bff);
}

.highlight {
  position: relative;
}

.highlight-card {
  background: rgba(80, 126, 255, 0.15);
  border-radius: 14px;
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  min-height: 140px;
}

.highlight-card span {
  font-size: 12px;
  color: #b4c6ff;
}

.highlight-dots {
  display: flex;
  gap: 6px;
}

.highlight-dots button {
  width: 10px;
  height: 10px;
  border-radius: 999px;
  border: none;
  background: rgba(255, 255, 255, 0.2);
  cursor: pointer;
}

.highlight-dots button.active {
  background: #7ea2ff;
}

.screen-alerts {
  background: rgba(255, 255, 255, 0.05);
  border-radius: 20px;
  padding: 20px;
}

.alert-list {
  display: grid;
  gap: 12px;
}

.alert-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: rgba(10, 15, 30, 0.7);
  border-radius: 12px;
  padding: 12px 16px;
  gap: 12px;
}

.alert-level {
  padding: 2px 8px;
  border-radius: 999px;
  font-size: 12px;
  background: rgba(255, 99, 132, 0.2);
  color: #ff9fb4;
  margin-right: 8px;
}

.alert-meta {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  font-size: 12px;
  color: #a1b2d8;
}

@media (max-width: 768px) {
  .screen {
    padding: 20px;
  }

  .screen-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 12px;
  }
}
</style>
