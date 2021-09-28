package com.jlab.ab.service;

import com.jlab.ab.domain.Item;
import com.jlab.ab.domain.ItemRepository;
import com.jlab.ab.dto.request.CreateForm;
import com.jlab.ab.dto.request.UpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item getItem(Long id){
        Item item = itemRepository.findById(id).orElseThrow(()->new IllegalArgumentException("찾는 상품이 없습니다."));
        return item;
    }

    @Transactional
    public Long createItem(CreateForm createForm) {
        Item item = createForm.toEntity();

        Item newItem = itemRepository.save(item);
        return newItem.getId();
    }

    @Transactional
    public Long updateItem(Long id, UpdateForm updateForm) {
        Item item= itemRepository.findById(id).orElseThrow(()->new IllegalArgumentException("찾는 상품이 없습니다."));

        item.setCode(updateForm.getCode());
        item.setPrice(updateForm.getPrice());
        item.setName(updateForm.getName());
        item.setType(updateForm.getType());

        return item.getId();
    }

    public void deleteItem(Long id){
        Item item = itemRepository.findById(id).orElseThrow(()->new IllegalArgumentException("찾는 상품이 없습니다."));
        itemRepository.delete(item);
    }
}
