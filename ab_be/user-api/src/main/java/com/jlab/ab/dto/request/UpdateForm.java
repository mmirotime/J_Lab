package com.jlab.ab.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateForm {

    private String Name;
    private Integer Code;
    private String Type;
    private Integer Price;

}
