package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class Mail {
    private String mailTo;
    @Nullable
    private String toCc;
    private String subject;
    private String message;
}
