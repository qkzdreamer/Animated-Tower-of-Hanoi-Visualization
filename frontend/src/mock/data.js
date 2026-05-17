export const screenOverview = {
  warehouseName: '华北一号智慧仓',
  lastSync: '2026-05-17 09:10',
  metrics: [
    { label: '在库托盘', value: 1280, unit: '托', trend: '+3%' },
    { label: '空闲库位', value: 320, unit: '位', trend: '-2%' },
    { label: '今日入库', value: 238, unit: '托', trend: '+8%' },
    { label: '今日出库', value: 192, unit: '托', trend: '+5%' },
  ],
  utilization: [
    { time: '06:00', value: 45 },
    { time: '08:00', value: 58 },
    { time: '10:00', value: 64 },
    { time: '12:00', value: 71 },
    { time: '14:00', value: 68 },
    { time: '16:00', value: 74 },
    { time: '18:00', value: 79 },
  ],
  zones: [
    { name: 'A区-冷链', capacity: 500, used: 420, status: '运行中' },
    { name: 'B区-零部件', capacity: 420, used: 310, status: '运行中' },
    { name: 'C区-成品', capacity: 380, used: 295, status: '补货中' },
    { name: 'D区-退货', capacity: 300, used: 170, status: '巡检中' },
  ],
  highlights: [
    {
      title: '入库进度',
      description: '自动分拣完成率 98%，剩余 12 托待入库',
      tag: '分拣线 2 号',
    },
    {
      title: '出库调度',
      description: '高优先级订单 6 单，预计 30 分钟内出库',
      tag: '波次 15',
    },
    {
      title: '设备状态',
      description: 'AGV 12 台在线，1 台待维护',
      tag: '维护建议',
    },
  ],
}

export const screenAlerts = [
  {
    id: 'AL-2026-001',
    level: '高',
    message: 'A区温湿度偏离阈值，已启动除湿方案',
    time: '09:02',
    status: '处理中',
  },
  {
    id: 'AL-2026-002',
    level: '中',
    message: 'B区补货任务延迟 15 分钟',
    time: '08:47',
    status: '待确认',
  },
  {
    id: 'AL-2026-003',
    level: '低',
    message: 'C区叉车电量低于 30%',
    time: '08:15',
    status: '已通知',
  },
]

export const systemStats = [
  { label: '今日入库', value: 238, unit: '托' },
  { label: '今日出库', value: 192, unit: '托' },
  { label: '待处理任务', value: 18, unit: '项' },
  { label: '异常告警', value: 3, unit: '条' },
]

export const systemTasks = [
  {
    id: 'TSK-1001',
    title: 'C区补货波次 15',
    owner: '调度中心',
    status: '进行中',
    eta: '09:40',
  },
  {
    id: 'TSK-1002',
    title: 'A区温控巡检',
    owner: '设备维护',
    status: '待处理',
    eta: '10:10',
  },
  {
    id: 'TSK-1003',
    title: 'B区库存盘点',
    owner: '仓储运营',
    status: '计划中',
    eta: '13:00',
  },
]

export const inventoryItems = [
  {
    id: 'INV-001',
    name: '伺服电机',
    category: '零部件',
    location: 'B-03-12',
    quantity: 120,
    status: '充足',
    lastMove: '2026-05-16',
  },
  {
    id: 'INV-002',
    name: '工业传感器',
    category: '零部件',
    location: 'B-02-08',
    quantity: 46,
    status: '关注',
    lastMove: '2026-05-16',
  },
  {
    id: 'INV-003',
    name: '冷链组件',
    category: '冷链',
    location: 'A-01-05',
    quantity: 88,
    status: '充足',
    lastMove: '2026-05-15',
  },
  {
    id: 'INV-004',
    name: '装配成品箱',
    category: '成品',
    location: 'C-07-02',
    quantity: 32,
    status: '紧张',
    lastMove: '2026-05-15',
  },
]

export const inventoryDetails = {
  'INV-001': {
    id: 'INV-001',
    name: '伺服电机',
    category: '零部件',
    location: 'B-03-12',
    quantity: 120,
    status: '充足',
    owner: '制造中心',
    supplier: '华南电机厂',
    lastInbound: '2026-05-14 11:20',
    lastOutbound: '2026-05-16 16:35',
    history: [
      { date: '05-12', inbound: 40, outbound: 12 },
      { date: '05-13', inbound: 20, outbound: 18 },
      { date: '05-14', inbound: 60, outbound: 22 },
      { date: '05-15', inbound: 0, outbound: 8 },
    ],
  },
  'INV-002': {
    id: 'INV-002',
    name: '工业传感器',
    category: '零部件',
    location: 'B-02-08',
    quantity: 46,
    status: '关注',
    owner: '智能车间',
    supplier: '华北传感',
    lastInbound: '2026-05-15 09:45',
    lastOutbound: '2026-05-16 18:10',
    history: [
      { date: '05-12', inbound: 10, outbound: 4 },
      { date: '05-13', inbound: 12, outbound: 6 },
      { date: '05-14', inbound: 20, outbound: 8 },
      { date: '05-15', inbound: 6, outbound: 4 },
    ],
  },
  'INV-003': {
    id: 'INV-003',
    name: '冷链组件',
    category: '冷链',
    location: 'A-01-05',
    quantity: 88,
    status: '充足',
    owner: '冷链运营',
    supplier: '华东制冷',
    lastInbound: '2026-05-15 10:15',
    lastOutbound: '2026-05-16 14:05',
    history: [
      { date: '05-12', inbound: 22, outbound: 10 },
      { date: '05-13', inbound: 18, outbound: 12 },
      { date: '05-14', inbound: 24, outbound: 14 },
      { date: '05-15', inbound: 16, outbound: 8 },
    ],
  },
  'INV-004': {
    id: 'INV-004',
    name: '装配成品箱',
    category: '成品',
    location: 'C-07-02',
    quantity: 32,
    status: '紧张',
    owner: '成品仓',
    supplier: '总部工厂',
    lastInbound: '2026-05-15 15:30',
    lastOutbound: '2026-05-16 17:50',
    history: [
      { date: '05-12', inbound: 8, outbound: 6 },
      { date: '05-13', inbound: 6, outbound: 10 },
      { date: '05-14', inbound: 4, outbound: 12 },
      { date: '05-15', inbound: 14, outbound: 8 },
    ],
  },
}

export const systemSettings = [
  { key: 'autoDispatch', label: '自动波次调度', value: true },
  { key: 'tempAlert', label: '温控异常提醒', value: true },
  { key: 'nightShift', label: '夜班模式', value: false },
]
