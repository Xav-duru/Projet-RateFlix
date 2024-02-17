document.addEventListener('DOMContentLoaded', () => {
    const reviewsContainer = document.querySelector('.reviews-container');
    const addReviewForm = document.querySelector('.add-review');
    const averageRatingElement = document.createElement('div');
    averageRatingElement.classList.add('average-rating');
    reviewsContainer.parentNode.insertBefore(averageRatingElement, reviewsContainer);

    const reviews = [];

    const updateReviews = () => {
        reviewsContainer.innerHTML = '';
        let totalRating = 0;

        for (const review of reviews) {
            const newReview = document.createElement('div');
            newReview.classList.add('review');

            const reviewRating = document.createElement('div');
            reviewRating.classList.add('rating');
            reviewRating.innerHTML = `<span class="rating-value">${review.rating}</span>`;

            const userReview = document.createElement('div');
            userReview.classList.add('user-review');
            userReview.innerHTML = `
                <p class="username">${review.username}</p>
                <p class="review-text">${review.review}</p>
            `;

            newReview.appendChild(reviewRating);
            newReview.appendChild(userReview);
            reviewsContainer.appendChild(newReview);

            totalRating += parseInt(review.rating);
        }

        const averageRating = totalRating / reviews.length || 0;
        averageRatingElement.textContent = `${averageRating.toFixed(1)}`;

    };

    addReviewForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const username = document.getElementById('username').value;
        const rating = document.getElementById('rating').value;
        const reviewText = document.getElementById('review').value;

        const newReview = {
            username,
            rating,
            review: reviewText
        };

        reviews.push(newReview);
        updateReviews();
        addReviewForm.reset();
    });
});

