package com.charsharing.bootcamp.comment.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "comment")
public class Comment {


    private String blockId;
    @Id
    private String commentId;

    private String content;

    private String creator;

    private Date createdAt;
}
