package com.example.CROZ;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {

	@Query("Select distinct category From Joke")
	public List<String> findAllCategories();

	@Transactional
	@Modifying
	@Query("UPDATE Joke SET likes = likes + 1 WHERE id = :id ")
	void updateLikes(@Param("id") int id);

	@Transactional
	@Modifying
	@Query("UPDATE Joke SET dislikes = dislikes + 1 WHERE id = :id ")
	void updateDislikes(@Param("id") int id);

	@Transactional
	@Modifying
	@Query("UPDATE Joke SET difference=(SELECT likes - dislikes from Joke WHERE id=:id  ORDER BY likes - dislikes DESC ) WHERE id=:id")
	void updateSort(@Param("id") int id);
}
