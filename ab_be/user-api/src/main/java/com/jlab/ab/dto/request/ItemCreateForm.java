package com.jlab.ab.dto.request;

import com.jlab.ab.domain.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemCreateForm {

    private String name;
    private Integer code;
    private String type;
    private Integer price;
//    private String Option;
//    private String Size;
//    private Number Quantity;
//    private String Color;
//    private Number Sale;



    public Item toEntity() {
        return Item.builder().code(code).name(name).price(price).type(type).build();
    }
}
