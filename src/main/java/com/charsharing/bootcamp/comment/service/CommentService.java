package com.charsharing.bootcamp.comment.service;

import com.charsharing.bootcamp.comment.domain.Comment;
import com.charsharing.bootcamp.comment.domain.FilterRequest;
import com.charsharing.bootcamp.comment.domain.FilterResponse;
import com.charsharing.bootcamp.comment.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private RestTemplateBuilder templateBuilder;
    @Value("${charSharing.filter.service.url}")
    private String filterServiceURL;
    @Autowired
    private CommentRepository commentRepository;

    public ResponseEntity<Comment> addComment(Comment comment) {
        final Comment repositoryComment  = commentRepository.findByCommentId(comment.getCommentId());
        if (repositoryComment != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }


        RestTemplate restTemplate = templateBuilder.build();
        FilterRequest body = new FilterRequest();
        body.setText(comment.getContent());
        HttpEntity<FilterRequest> request = new HttpEntity<>(body);
        ResponseEntity<FilterResponse> exchangeAnswer = restTemplate.exchange(filterServiceURL + "/filter", HttpMethod.POST, request, FilterResponse.class);



        comment.setContent(exchangeAnswer.getBody().getFilteredText());
        comment.setCreatedAt(new Date());
        final Comment savedComment = commentRepository.save(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    public List<Comment> findAllCommentsByBlockId(String id) {
        return commentRepository.findAllCommentsByBlockId(id);
    }
}
