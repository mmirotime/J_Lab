package com.jlab.ab.service;

import com.jlab.ab.domain.Item;
import com.jlab.ab.repository.ItemRepository;
import com.jlab.ab.dto.request.ItemCreateForm;
import com.jlab.ab.dto.request.ItemUpdateForm;
import com.jlab.ab.dto.response.ItemResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    private Item item;

    @BeforeEach
    void setData() {
        MockitoAnnotations.openMocks(this);

        item = Item.builder()
                .code(1)
                .name("피넛 단가라 스마일라벨 맨투맨")
                .type("TOP")
                .price(39800)
                .build();

        ReflectionTestUtils.setField(item, "id", 1001L);
    }

    @DisplayName("신규 상품 등록")
    @Test
    void addNewItem() {
        // given
        // ItemCreateForm을 받아 id가 item 객체와 같은 newItem 객체를 생성합니다
        ItemCreateForm itemForm = new ItemCreateForm();
        Item newItem = itemForm.toEntity();
        ReflectionTestUtils.setField(newItem, "id", item.getId());

        given(itemRepository.save(any(Item.class))).willReturn(newItem);

        // when
        Long itemId = itemService.createItem(itemForm);

        // then
        assertEquals(item.getId(), itemId);
    }

    @DisplayName("상품 상세 조회")
    @Test
    void getItemDetail() {
        // given
        given(itemRepository.findById(anyLong())).willReturn(Optional.of(item));

        // when
        ItemResponse response = itemService.getItem(item.getId());

        // then
        assertAll(
                () -> assertEquals(item.getCode(), response.getCode()),
                () -> assertEquals(item.getName(), response.getName()),
                () -> assertEquals(item.getType(), response.getType()),
                () -> assertEquals(item.getPrice(), response.getPrice())
        );
    }

    @DisplayName("전체 상품 목록 조회")
    @Test
    void getItemList() { // 기존 코드 수정 필요
        // TODO: 서비스 비즈니스 로직이 수정되면 getItemList() 테스트 작성하기
        // given

        // when

        // then

    }

    @DisplayName("상품 정보 수정")
    @Test
    void updateItem() {
        // given
        Integer newCode = 2;
        String newName = "(수정) 아이템";
        String newType = "BOTTOM";
        Integer newPrice = 12000;

        ItemUpdateForm updateForm = new ItemUpdateForm();
        updateForm.setCode(newCode);
        updateForm.setName(newName);
        updateForm.setType(newType);
        updateForm.setPrice(newPrice);

        given(itemRepository.findById(anyLong())).willReturn(Optional.of(item));

        // when
        Long itemId = itemService.updateItem(item.getId(), updateForm);

        // then
        assertAll(
                () -> assertEquals(item.getId(), itemId),
                () -> assertEquals(newCode, item.getCode()),
                () -> assertEquals(newName, item.getName()),
                () -> assertEquals(newType, item.getType()),
                () -> assertEquals(newPrice, item.getPrice())
        );
    }

}