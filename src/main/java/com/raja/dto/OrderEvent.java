package com.raja.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderEvent {
    private String id;
    private String name;
    private String orderType;
}
