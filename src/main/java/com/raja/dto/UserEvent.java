package com.raja.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEvent {

    private String id;
    private String name;
    private String eventType;

}
