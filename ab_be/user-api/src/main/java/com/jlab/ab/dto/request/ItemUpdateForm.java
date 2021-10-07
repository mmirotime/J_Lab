package com.jlab.ab.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemUpdateForm {
    private String name;
    private Integer code;
    private String type;
    private Integer price;

}
