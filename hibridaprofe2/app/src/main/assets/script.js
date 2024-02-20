// Load existing activities from local storage
let activities = JSON.parse(localStorage.getItem('activities')) || [];

function displayActivities() {
  const activityList = document.getElementById('activityList');
  activityList.innerHTML = '';

  for (let i = 0; i < activities.length; i++) {
    const item = document.createElement('li');
    item.innerHTML = `
      <span>${activities[i].date} - ${activities[i].time}</span>
      <span class="${activities[i].completed ? 'tachado' : ''}">${activities[i].activity}</span>
      <select class="select" onchange="changeStatus(${i}, this)">
        <option value="inicializada" ${activities[i].status === 'inicializada' ? 'selected' : ''}>Inicializada</option>
        <option value="en-proceso" ${activities[i].status === 'en-proceso' ? 'selected' : ''}>En Proceso</option>
        <option value="terminada" ${activities[i].status === 'terminada' ? 'selected' : ''}>Terminada</option>
      </select>
    `;
    activityList.appendChild(item);
  }
}

function addActivity() {
  const activityInput = document.getElementById('activity');
  const activityText = activityInput.value.trim();
  
  if (activityText !== '') {
    const now = new Date();
    const activity = {
      date: now.toLocaleDateString(),
      time: now.toLocaleTimeString(),
      activity: activityText,
      status: 'inicializada',
      completed: false
    };

    activities.push(activity);
    localStorage.setItem('activities', JSON.stringify(activities));
    activityInput.value = '';
    displayActivities();
  }
}

function changeStatus(index, select) {
  const selectedStatus = select.value;
  activities[index].status = selectedStatus;

  if (selectedStatus === 'terminada') {
    activities[index].completed = true;
  } else {
    activities[index].completed = false;
  }

  localStorage.setItem('activities', JSON.stringify(activities));
  displayActivities();
}

// Display initial activities
displayActivities();
