package com.jlab.ab.dto.response;


import com.jlab.ab.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemReponse {
    private String name;
    private Integer code;
    private String type;
    private Integer price;
    private Long id;


    @Builder
    public ItemReponse(Item item){
        this.id = item.getId();
        this.name = item.getName();
        this.code = item.getCode();
        this.type = item.getType();
        this.price = item.getPrice();
    }

}
