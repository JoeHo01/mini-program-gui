export {
  formatDate,
  formatTime
}

function formatDate(timeStamp) {
  return getDate(new Date(timeStamp));
}

function formatTime(timeStamp) {
  let date = new Date(timeStamp);
  return getDate(date) + " " + getTime(date);
}

function getDate(date) {
  let year = date.getFullYear();
  let month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
  let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
  return year + "-" + month + "-" + day;
}

function getTime(date) {
  let hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
  let minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
  let seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
  return hours + ":" + minutes + ":" + seconds;
}