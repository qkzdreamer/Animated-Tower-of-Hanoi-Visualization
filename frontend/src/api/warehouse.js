import {
  inventoryDetails,
  inventoryItems,
  screenAlerts,
  screenOverview,
  systemSettings,
  systemStats,
  systemTasks,
} from '../mock/data'

const DEFAULT_DELAY = 200

const createResponse = (success, data, message) => ({
  success,
  message,
  data,
})

const simulateRequest = (response, delay = DEFAULT_DELAY) =>
  new Promise((resolve) => {
    setTimeout(() => resolve(response), delay)
  })

export const getScreenOverview = async () => {
  try {
    return await simulateRequest(createResponse(true, screenOverview, 'mock screen overview'))
  } catch (error) {
    return createResponse(false, null, 'screen overview failed')
  }
}

export const getScreenAlerts = async () => {
  try {
    return await simulateRequest(createResponse(true, screenAlerts, 'mock screen alerts'))
  } catch (error) {
    return createResponse(false, [], 'screen alerts failed')
  }
}

export const getSystemDashboard = async () => {
  try {
    return await simulateRequest(
      createResponse(true, { stats: systemStats, tasks: systemTasks }, 'mock system dashboard')
    )
  } catch (error) {
    return createResponse(false, { stats: [], tasks: [] }, 'system dashboard failed')
  }
}

export const getInventoryList = async () => {
  try {
    return await simulateRequest(createResponse(true, inventoryItems, 'mock inventory list'))
  } catch (error) {
    return createResponse(false, [], 'inventory list failed')
  }
}

export const getInventoryDetail = async (id) => {
  try {
    const detail = inventoryDetails[id]
    if (!detail) {
      return createResponse(false, null, 'inventory item not found')
    }
    return await simulateRequest(createResponse(true, detail, 'mock inventory detail'))
  } catch (error) {
    return createResponse(false, null, 'inventory detail failed')
  }
}

export const getSystemSettings = async () => {
  try {
    return await simulateRequest(createResponse(true, systemSettings, 'mock system settings'))
  } catch (error) {
    return createResponse(false, [], 'system settings failed')
  }
}
