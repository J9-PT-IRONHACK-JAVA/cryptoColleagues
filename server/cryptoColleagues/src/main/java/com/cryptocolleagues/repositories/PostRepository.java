package com.cryptocolleagues.repositories;

import com.cryptocolleagues.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post getById(Long id);
}
