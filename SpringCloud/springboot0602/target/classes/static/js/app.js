// API基础URL
const API_BASE_URL = '/api/v1/users';

// DOM元素
const userForm = document.getElementById('userForm');
const userTableBody = document.getElementById('userTableBody');
const deleteModal = document.getElementById('deleteModal');
const alertSuccess = document.getElementById('alertSuccess');
const alertError = document.getElementById('alertError');

// 当前选中的用户ID（用于删除操作）
let selectedUserId = null;

// 页面加载完成后执行
document.addEventListener('DOMContentLoaded', () => {
  // 加载用户列表
  loadUsers();

  // 表单提交事件
  userForm.addEventListener('submit', handleFormSubmit);

  // 关闭模态框
  document.querySelectorAll('.close, .close-modal').forEach(element => {
    element.addEventListener('click', () => {
      deleteModal.style.display = 'none';
    });
  });

  // 确认删除
  document.getElementById('confirmDelete').addEventListener('click', handleDelete);
});

// 加载用户列表
async function loadUsers() {
  try {
    const response = await fetch(API_BASE_URL);
    const result = await response.json();

    if (result.code === 200) {
      renderUserTable(result.data);
    } else {
      showError(result.message);
    }
  } catch (error) {
    showError('加载用户列表失败');
    console.error('Error:', error);
  }
}

// 渲染用户表格
function renderUserTable(users) {
  userTableBody.innerHTML = users.map(user => `
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${formatDateTime(user.createdAt)}</td>
            <td>
                <button class="btn btn-secondary btn-sm" onclick="handleEdit(${user.id})">
                    <i class="fas fa-edit"></i> 编辑
                </button>
                <button class="btn btn-danger btn-sm" onclick="showDeleteModal(${user.id})">
                    <i class="fas fa-trash"></i> 删除
                </button>
            </td>
        </tr>
    `).join('');
}

// 处理表单提交
async function handleFormSubmit(event) {
  event.preventDefault();

  const userId = document.getElementById('userId').value;
  const userData = {
    username: document.getElementById('username').value,
    password: document.getElementById('password').value,
    email: document.getElementById('email').value
  };

  try {
    const url = userId ? `${API_BASE_URL}/${userId}` : API_BASE_URL;
    const method = userId ? 'PUT' : 'POST';

    const response = await fetch(url, {
      method: method,
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(userData)
    });

    const result = await response.json();

    if (response.ok) {
      showSuccess(userId ? '更新成功' : '创建成功');
      userForm.reset();
      document.getElementById('userId').value = '';
      loadUsers();
    } else {
      showError(result.message || '操作失败');
    }
  } catch (error) {
    showError('操作失败');
    console.error('Error:', error);
  }
}

// 处理编辑操作
async function handleEdit(userId) {
  try {
    const response = await fetch(`${API_BASE_URL}/${userId}`);
    const result = await response.json();

    if (result.code === 200) {
      const user = result.data;
      document.getElementById('userId').value = user.id;
      document.getElementById('username').value = user.username;
      document.getElementById('email').value = user.email;
      document.getElementById('password').value = ''; // 出于安全考虑，不回显密码

      // 滚动到表单
      document.querySelector('.form-container').scrollIntoView({ behavior: 'smooth' });
    } else {
      showError(result.message);
    }
  } catch (error) {
    showError('加载用户信息失败');
    console.error('Error:', error);
  }
}

// 显示删除确认框
function showDeleteModal(userId) {
  selectedUserId = userId;
  deleteModal.style.display = 'block';
}

// 处理删除操作
async function handleDelete() {
  if (!selectedUserId) return;

  try {
    const response = await fetch(`${API_BASE_URL}/${selectedUserId}`, {
      method: 'DELETE'
    });

    if (response.ok) {
      showSuccess('删除成功');
      deleteModal.style.display = 'none';
      loadUsers();
    } else {
      const result = await response.json();
      showError(result.message || '删除失败');
    }
  } catch (error) {
    showError('删除失败');
    console.error('Error:', error);
  }
}

// 显示成功消息
function showSuccess(message) {
  alertSuccess.textContent = message;
  alertSuccess.style.display = 'block';
  setTimeout(() => {
    alertSuccess.style.display = 'none';
  }, 3000);
}

// 显示错误消息
function showError(message) {
  alertError.textContent = message;
  alertError.style.display = 'block';
  setTimeout(() => {
    alertError.style.display = 'none';
  }, 3000);
}

// 格式化日期时间
function formatDateTime(timestamp) {
  if (!timestamp) return '';
  const date = new Date(timestamp);
  return date.toLocaleString('zh-CN', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit'
  });
} 