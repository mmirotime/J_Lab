package com.jlab.ab.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Integer code;

    @Column(nullable = false)
    private String name;

    // TODO : Enum 타입으로 변경하기 (Jelly)
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer price;

//    private String itemOption;
//
//    private String itemSize;
//
//    private Number itemQuantity;
//
//    private String itemColor;
//
//    private Number item_sale;

    @Builder
    public Item(Integer code, String name, String type, Integer price){
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    //item update

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
