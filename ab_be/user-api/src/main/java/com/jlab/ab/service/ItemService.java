package com.jlab.ab.service;

import com.jlab.ab.domain.Item;
import com.jlab.ab.repository.ItemRepository;
import com.jlab.ab.dto.request.ItemCreateForm;
import com.jlab.ab.dto.request.ItemUpdateForm;
import com.jlab.ab.dto.response.ItemResponse;
import com.jlab.ab.exception.ItemNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }


    @Transactional
    public ItemResponse getItem(Long id){
        Item item = itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException("찾는 상품이 없습니다."));
        return new ItemResponse(item);
    }


    @Transactional(readOnly = true)
    public List<ItemResponse> getItemList(){
        return itemRepository.findAllDesc().stream()
                .map(ItemResponse::new).collect(Collectors.toList());
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

    @Transactional
    public void deleteItem(Long id){
        Item item = itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException("찾는 상품이 없습니다."));
        itemRepository.delete(item);
    }
}
