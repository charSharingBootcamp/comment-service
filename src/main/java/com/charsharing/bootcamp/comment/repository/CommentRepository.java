package com.charsharing.bootcamp.comment.repository;

import com.charsharing.bootcamp.comment.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAllCommentsByBlockId(String id);

    //void deleteByTitleIn(List<String> titles);

    @Override
    @Query(value="{}", fields = "{content: 0}")
    List<Comment> findAll();

    @Query(value="{archived:false}", fields = "{content:0}")
    List<Comment> findAllExcludingArchived();


    Comment findByCommentId(String commentId);
}
