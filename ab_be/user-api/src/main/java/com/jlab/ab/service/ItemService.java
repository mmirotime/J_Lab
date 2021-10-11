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

    // FIXME : 10월 11일 itemRepository 멤버 변수를 private final 및 생성자 주입 방식으로 변경하기 (Jelly)
    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public ItemReponse getItem(Long id){
        Item item = itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException("찾는 상품이 없습니다."));
        return new ItemReponse(item);
    }

    //새로 추가한 부분
    // FIXME : 10월 11일 메서드명 카멜 케이스로 수정하기 (Jelly)
    // FIXME : 10월 11일 반환 값 Entity → DTO로 변경하기 (Jelly)
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

    // FIXME : 10월 11일 서비스 클래스 내 DB 삽입, 수정, 삭제 로직에는 @Transactional 어노테이션 붙이기 (Jelly)
    public void deleteItem(Long id){
        Item item = itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException("찾는 상품이 없습니다."));
        itemRepository.delete(item);
    }
}
