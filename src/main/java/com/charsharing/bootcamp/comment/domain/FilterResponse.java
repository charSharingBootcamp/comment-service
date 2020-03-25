package com.charsharing.bootcamp.comment.domain;

import lombok.Data;

@Data
public final class FilterResponse {
    private boolean valid = false;
    private String filteredText;
}
