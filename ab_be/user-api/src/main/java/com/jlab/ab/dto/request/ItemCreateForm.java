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
//    private String itemOption;
//    private String itemSize;
//    private Number itemQuantity;
//    private String itemColor;
//    private Number itemSale;



    public Item toEntity() {
        return Item.builder().code(code).name(name).price(price).type(type).build();
    }
}
