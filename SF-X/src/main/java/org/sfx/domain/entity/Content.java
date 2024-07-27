package org.sfx.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Content {
    private Integer id;
    private String name;
    private String desc;
    private String creator;
    private LocalDateTime createdTime;
}
