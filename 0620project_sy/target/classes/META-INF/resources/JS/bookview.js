// bookview.js

function changeQuantity(amount) {
    const input = document.getElementById("quantityInput");
    let newValue = parseInt(input.value) + amount;
    if (newValue < 1) newValue = 1;
    input.value = newValue;
    document.getElementById("paymentQuantity").value = newValue;
    document.getElementById("cartQuantity").value = newValue;
}

document.getElementById("quantityInput").addEventListener("input", function () {
    let val = parseInt(this.value);
    if (isNaN(val) || val < 1) val = 1;
    this.value = val;
    document.getElementById("paymentQuantity").value = val;
    document.getElementById("cartQuantity").value = val;
});

function handleLikeSubmit(cid) {
    if (!isLogined()) {
        if (localStorage.getItem('liked_' + cid)) {
            alert("이미 좋아요를 누르셨습니다.");
            return false;
        } else {
            localStorage.setItem('liked_' + cid, 'true');
        }
    }
    return true;
}

function toggleReplies(cid) {
    const section = document.getElementById('reply-' + cid);
    const inputBox = document.getElementById('reply-input-' + cid);
    if (section) section.style.display = (section.style.display === 'none') ? 'block' : 'none';
    if (inputBox) inputBox.style.display = (section.style.display === 'none') ? 'none' : 'block';
}

function hideReplyInput(cid) {
    const inputBox = document.getElementById('reply-input-' + cid);
    if (inputBox) inputBox.style.display = 'none';
}

function handleReviewSubmit() {
    if (!isLogined()) {
        return false;
    }

    const score = document.getElementById('reviewScore').value;
    if (score === '0') {
        alert('점수를 선택해주세요.');
        return false;
    }

    const reviewText = document.querySelector('#reviewForm textarea[name="review_text"]').value;
    if (reviewText.trim() === '') {
        alert('리뷰 내용을 입력해주세요.');
        return false;
    }

    return true;
}

function updateFlowerImages(rating) {
    const flowerContainer = document.querySelector('.flower-rating-input');
    const flowerImages = flowerContainer.querySelectorAll('img');
    flowerImages.forEach((img, i) => {
        const fullIndex = i + 1;
        if (rating >= fullIndex) {
            img.src = filledFlowerPath;
        } else if (rating >= fullIndex - 0.5) {
            img.src = halfFlowerPath;
        } else {
            img.src = emptyFlowerPath;
        }
    });
}

// 점수 선택 스크립트
(function () {
    const flowerContainer = document.querySelector('.flower-rating-input');
    const flowerImages = flowerContainer.querySelectorAll('img');
    const scoreInput = document.getElementById('reviewScore');
    let currentRating = 0;

    flowerImages.forEach((img, i) => {
        img.addEventListener('click', (e) => {
            const index = i;
            const rect = img.getBoundingClientRect();
            const clickX = e.clientX - rect.left;
            const isHalf = clickX < rect.width / 2;

            currentRating = isHalf ? index + 0.5 : index + 1;
            scoreInput.value = currentRating;
            flowerContainer.dataset.rate = currentRating;
            updateFlowerImages(currentRating);
        });
    });
})();
