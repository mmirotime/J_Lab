package com.jlab.ab.dto.response;


import com.jlab.ab.domain.Item;
import com.jlab.ab.repository.ItemRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ItemResponse {
    private String name;
    private Integer code;
    private String type;
    private Integer price;
    private Long id;


    public ItemResponse(Item item){
        this.id = item.getId();
        this.name = item.getName();
        this.code = item.getCode();
        this.type = item.getType();
        this.price = item.getPrice();
    }


}
