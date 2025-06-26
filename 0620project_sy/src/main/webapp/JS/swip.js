const eventImgs = [
  "event1.PNG",
  "event2.PNG",
  "event3.PNG",
  "event4.PNG",
  "event5.PNG",
  "event6.PNG"
];

let currentIndex = 0;

function rotateImages() {
  const total = eventImgs.length;

  const idx1 = currentIndex % total;
  const idx2 = (currentIndex + 1) % total;
  const idx3 = (currentIndex + 2) % total;

  document.getElementById("eventImg1").src = `${contextPath}/IMG/EVENT/${eventImgs[idx1]}`;
  document.getElementById("eventImg2").src = `${contextPath}/IMG/EVENT/${eventImgs[idx2]}`;
  document.getElementById("eventImg3").src = `${contextPath}/IMG/EVENT/${eventImgs[idx3]}`;

  currentIndex = (currentIndex + 1) % total;
}

document.addEventListener("DOMContentLoaded", () => {
  rotateImages(); // 최초 1회 실행
  setInterval(rotateImages, 3000); // 3초마다 반복
});