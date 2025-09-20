package hello.neardeal_server.item.service;

import hello.neardeal_server.file.FileStorage;
import hello.neardeal_server.item.dto.ItemDetailResponse;
import hello.neardeal_server.item.dto.ItemRequest;
import hello.neardeal_server.item.entity.Item;
import hello.neardeal_server.item.repository.ItemRepository;
import hello.neardeal_server.store.entity.Store;
import hello.neardeal_server.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final FileStorage fileStorage;
    private final StoreService storeService;
    private final ItemRepository itemRepository;

    /**
     * 아이템 만들기
     */
    @Transactional
    public Long createItem(ItemRequest itemRequest, Long storeId){
        Store store = storeService.findOne(storeId);
        MultipartFile image = itemRequest.getImage();

        String imageUrl = null;

        // 이미지가 있을 때만 저장
        if (image != null && !image.isEmpty()) {
            imageUrl = fileStorage.createUrl(store.getStoreName(), image, itemRequest.getIndex());
        }

        Item item = Item.create(itemRequest, store, imageUrl);

        Item save = itemRepository.save(item);

        return save.getId();
    }

    /**
     * 아이템 전체 조회하기
     */
    public Page<ItemDetailResponse> findList(Long storeId, int page, int size){
        Store store = storeService.findOne(storeId);

        Page<Item> all = itemRepository.findByStore(store, PageRequest.of(page, size));
        return all.map(item -> ItemDetailResponse.entityToResponse(item));
    }

    /**
     * 아이템 정보 수정
     */
//    public Long updateItemInfo(Long itemId, ItemRequest itemRequest){
//
//    }


    public Item findOne(Long itemId){
        return itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("아이템 없으무이다"));
    }


}
