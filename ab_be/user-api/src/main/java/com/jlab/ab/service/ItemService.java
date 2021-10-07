package com.jlab.ab.service;

import com.jlab.ab.domain.Item;
import com.jlab.ab.domain.ItemRepository;
import com.jlab.ab.dto.request.ItemCreateForm;
import com.jlab.ab.dto.request.ItemUpdateForm;
import com.jlab.ab.dto.response.ItemReponse;
import com.jlab.ab.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public ItemReponse getItem(Long id){
        Item item = itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException("찾는 상품이 없습니다."));
        return new ItemReponse(item);
    }

    //새로 추가한 부분
    @Transactional(readOnly = true)
    public Iterable<Item> getItemlist(){
        Iterable<Item> itemList = itemRepository.findAll();
        return itemList;
    }


    @Transactional
    public Long createItem(ItemCreateForm createForm) {
        Item item = createForm.toEntity();

        Item newItem = itemRepository.save(item);
        return newItem.getId();
    }

    @Transactional
    public Long updateItem(Long id, ItemUpdateForm updateForm) {
        Item item= itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException("찾는 상품이 없습니다."));

        item.setCode(updateForm.getCode());
        item.setPrice(updateForm.getPrice());
        item.setName(updateForm.getName());
        item.setType(updateForm.getType());

        return item.getId();
    }

    public void deleteItem(Long id){
        Item item = itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException("찾는 상품이 없습니다."));
        itemRepository.delete(item);
    }
}
