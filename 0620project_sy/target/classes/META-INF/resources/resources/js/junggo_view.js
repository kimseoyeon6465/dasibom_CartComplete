/**
 * 
 */
document.addEventListener("DOMContentLoaded", function () {
    const slides = document.querySelectorAll(".slide");
    let current = 0;

    if (slides.length > 0) {
        slides[current].style.display = "block";

        document.getElementById("prevBtn").addEventListener("click", () => {
            slides[current].style.display = "none";
            current = (current - 1 + slides.length) % slides.length;
            slides[current].style.display = "block";
        });

        document.getElementById("nextBtn").addEventListener("click", () => {
            slides[current].style.display = "none";
            current = (current + 1) % slides.length;
            slides[current].style.display = "block";
        });
    }
});
