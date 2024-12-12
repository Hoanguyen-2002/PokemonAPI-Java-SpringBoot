package com.pokemonreview.api.Repository;

import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.models.Review;
import com.pokemonreview.api.repository.ReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase( connection = EmbeddedDatabaseConnection.H2)
public class ReviewRepositoryTests {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewRepositoryTests(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Test
    public void ReviewRepository_SaveAll_ReturnSaveReview(){
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        Review savedReview = reviewRepository.save(review);

        Assertions.assertNotNull(savedReview);
        Assertions.assertTrue(savedReview.getId() > 0);
    }

    @Test
    public void ReviewRepository_GetAll_ReturnMoreThanOneReview() {
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        Review review2 = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewRepository.save(review);
        reviewRepository.save(review2);

        List<Review> reviews = reviewRepository.findAll();

        Assertions.assertNotNull(reviews);
        Assertions.assertEquals(2, reviews.size());
    }

    @Test
    public void ReviewRepository_FindById_ReturnSaveReview(){
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        Review savedReview = reviewRepository.save(review);

        Review reviewReturn = reviewRepository.findById(savedReview.getId()).get();

        Assertions.assertNotNull(savedReview);
    }

    @Test
    public void ReviewRepository_UpdateReview_ReturnReviewNotNull() {
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewRepository.save(review);
        Review reviewSave = reviewRepository.findById(review.getId()).get();
        reviewSave.setTitle("new title");
        reviewSave.setContent("new content");
        reviewSave.setStars(5);

        Review updatedReview = reviewRepository.save(reviewSave);
        Assertions.assertNotNull(updatedReview.getTitle());
        Assertions.assertNotNull(updatedReview.getContent());

    }

    @Test
    public void ReviewRepository_ReviewDelete_ReturnReviewNotNull() {
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewRepository.save(review);

        reviewRepository.deleteById(review.getId());
        Optional<Review> reviewReturn = reviewRepository.findById(review.getId());

        Assertions.assertTrue(reviewReturn.isEmpty());
    }
}
