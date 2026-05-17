<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import { getInventoryList } from '../api/warehouse'

const router = useRouter()
const items = ref([])

const loadList = async () => {
  const response = await getInventoryList()
  if (response.success) {
    items.value = response.data
  }
}

const goDetail = (item) => {
  router.push({ name: 'inventory-detail', params: { id: item.id } })
}

onMounted(loadList)
</script>

<template>
  <div class="inventory-list">
    <div class="list-header">
      <div>
        <h2>库存列表</h2>
        <p>点击行查看详情，列表数据来自本地 Mock。</p>
      </div>
      <button class="ghost-button">导出</button>
    </div>

    <div class="table">
      <div class="table-row table-head">
        <span>物料</span>
        <span>库位</span>
        <span>数量</span>
        <span>状态</span>
        <span>最近操作</span>
      </div>
      <button
        v-for="item in items"
        :key="item.id"
        class="table-row"
        @click="goDetail(item)"
      >
        <span>
          <strong>{{ item.name }}</strong>
          <small>{{ item.category }}</small>
        </span>
        <span>{{ item.location }}</span>
        <span>{{ item.quantity }}</span>
        <span class="status" :class="item.status">{{ item.status }}</span>
        <span>{{ item.lastMove }}</span>
      </button>
    </div>
  </div>
</template>

<style scoped>
.inventory-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 12px;
}

.list-header p {
  color: #6a7ba1;
  margin-top: 6px;
}

.table {
  display: grid;
  gap: 8px;
}

.table-row {
  display: grid;
  grid-template-columns: 2fr 1fr 1fr 1fr 1fr;
  gap: 12px;
  align-items: center;
  text-align: left;
  padding: 12px 16px;
  border-radius: 12px;
  background: #f8f9fc;
  border: none;
  cursor: pointer;
  transition: transform 0.2s ease;
}

.table-row:hover {
  transform: translateY(-1px);
}

.table-head {
  background: #eef2f9;
  font-weight: 600;
  cursor: default;
}

.table-row span {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.table-row small {
  color: #7a89ac;
  font-size: 12px;
}

.status {
  padding: 4px 10px;
  border-radius: 999px;
  font-size: 12px;
  width: fit-content;
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
}
</style>
