package com.jlab.ab.controller;


import com.jlab.ab.domain.Item;
import com.jlab.ab.dto.request.CreateForm;
import com.jlab.ab.dto.request.UpdateForm;
import com.jlab.ab.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/items")
@RequiredArgsConstructor    //자동으로 생성자 만들어줌! itemService
public class ItemController {

    private final ItemService itemService;  //final 불변성=> 안전/

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id){
        Item item = itemService.getItem(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Long> createItem(@RequestBody CreateForm createForm){
        Long itemId = itemService.createItem(createForm);

        return ResponseEntity.ok(itemId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateItem(@PathVariable Long id, @RequestBody UpdateForm updateForm ){
        Long itemId = itemService.updateItem(id, updateForm);

        return ResponseEntity.ok(itemId);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
        return ResponseEntity.ok(id+"의 상품이 삭제되었습니다.");
    }
}
