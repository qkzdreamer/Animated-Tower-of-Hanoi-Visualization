<script setup>
import { onMounted, ref } from 'vue'
import { getSystemSettings } from '../api/warehouse'

const settings = ref([])

const loadSettings = async () => {
  const response = await getSystemSettings()
  if (response.success) {
    settings.value = response.data
  }
}

const toggleSetting = (setting) => {
  setting.value = !setting.value
}

onMounted(loadSettings)
</script>

<template>
  <div class="settings">
    <h2>系统配置</h2>
    <p class="subtitle">点击开关触发轻交互，配置暂为前端 Mock。</p>

    <div class="settings-list">
      <div v-for="setting in settings" :key="setting.key" class="setting-item">
        <div>
          <strong>{{ setting.label }}</strong>
          <span>当前：{{ setting.value ? '开启' : '关闭' }}</span>
        </div>
        <button
          class="toggle"
          :class="{ active: setting.value }"
          @click="toggleSetting(setting)"
        >
          <span></span>
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.settings {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.subtitle {
  color: #6c7aa1;
}

.settings-list {
  display: grid;
  gap: 12px;
}

.setting-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f5f7fc;
  padding: 14px 16px;
  border-radius: 12px;
}

.setting-item span {
  font-size: 12px;
  color: #7b8aa8;
}

.toggle {
  width: 46px;
  height: 24px;
  border-radius: 999px;
  border: none;
  background: #d6deef;
  cursor: pointer;
  position: relative;
  transition: background 0.2s ease;
}

.toggle span {
  position: absolute;
  top: 4px;
  left: 4px;
  width: 16px;
  height: 16px;
  background: white;
  border-radius: 50%;
  transition: transform 0.2s ease;
}

.toggle.active {
  background: #4f9cfb;
}

.toggle.active span {
  transform: translateX(22px);
}
</style>
