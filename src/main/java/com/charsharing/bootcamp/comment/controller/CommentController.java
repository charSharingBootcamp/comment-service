package com.charsharing.bootcamp.comment.controller;

import com.charsharing.bootcamp.comment.domain.Comment;
import com.charsharing.bootcamp.comment.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/comments")
@Slf4j
public class CommentController
{
    @Autowired
    private CommentService commentService;

    /**
     * Gets a list of comments for a certain block
     * @param id the od of the block
     * @return a http response with the list of comments in its body
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comment>> getAllCommentsByBlockId(@PathVariable(required = false) String id) {
        final List<Comment> comments = commentService.findAllCommentsByBlockId(id);
        if (isNull(comments)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comments);
    }

    /**
     * adds a comment to a block
     * @param comment the comment to add to the block
     * @return a http response with the added comment in its body
     */
    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

}
