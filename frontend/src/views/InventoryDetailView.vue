<script setup>
import { computed, onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getInventoryDetail } from '../api/warehouse'

const route = useRoute()
const router = useRouter()
const detail = ref(null)
const errorMessage = ref('')

const history = computed(() => detail.value?.history ?? [])

const loadDetail = async () => {
  const response = await getInventoryDetail(route.params.id)
  if (response.success) {
    detail.value = response.data
  } else {
    errorMessage.value = response.message
  }
}

onMounted(loadDetail)
</script>

<template>
  <div class="inventory-detail">
    <button class="ghost-button" @click="router.push('/system/inventory')">返回列表</button>

    <div v-if="detail" class="detail-card">
      <header>
        <div>
          <h2>{{ detail.name }}</h2>
          <p>{{ detail.category }} · {{ detail.location }}</p>
        </div>
        <span class="status" :class="detail.status">{{ detail.status }}</span>
      </header>

      <section class="detail-grid">
        <div>
          <h4>库存信息</h4>
          <p>数量：{{ detail.quantity }}</p>
          <p>负责人：{{ detail.owner }}</p>
          <p>供应商：{{ detail.supplier }}</p>
        </div>
        <div>
          <h4>出入库记录</h4>
          <p>最近入库：{{ detail.lastInbound }}</p>
          <p>最近出库：{{ detail.lastOutbound }}</p>
        </div>
      </section>

      <section class="history">
        <h4>近 4 日出入库</h4>
        <div class="history-grid">
          <div v-for="record in history" :key="record.date" class="history-item">
            <strong>{{ record.date }}</strong>
            <span>入库 {{ record.inbound }}</span>
            <span>出库 {{ record.outbound }}</span>
          </div>
        </div>
      </section>
    </div>

    <div v-else class="empty">{{ errorMessage || '加载中...' }}</div>
  </div>
</template>

<style scoped>
.inventory-detail {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.detail-card {
  background: #f8f9fd;
  border-radius: 16px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.detail-card header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.detail-card p {
  color: #6c7aa1;
  margin-top: 4px;
}

.detail-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 16px;
}

.history-grid {
  display: grid;
  gap: 10px;
  grid-template-columns: repeat(auto-fit, minmax(120px, 1fr));
}

.history-item {
  background: white;
  border-radius: 12px;
  padding: 12px;
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.status {
  padding: 6px 14px;
  border-radius: 999px;
  font-size: 12px;
}

.status.充足 {
  background: #e3f8ef;
  color: #1f8f5c;
}

.status.关注 {
  background: #fff1d6;
  color: #b06700;
}

.status.紧张 {
  background: #ffe0e6;
  color: #b81b3d;
}

.ghost-button {
  border: 1px solid #d1d9ee;
  background: transparent;
  padding: 8px 16px;
  border-radius: 10px;
  cursor: pointer;
  width: fit-content;
}

.empty {
  padding: 24px;
  text-align: center;
  color: #7b8aa8;
}
</style>
