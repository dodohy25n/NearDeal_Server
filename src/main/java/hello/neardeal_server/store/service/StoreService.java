package hello.neardeal_server.store.service;

import hello.neardeal_server.file.FileStorage;
import hello.neardeal_server.store.StoreCategory;
import hello.neardeal_server.store.dto.response.StoreDetailResponse;
import hello.neardeal_server.store.dto.request.StoreRequest;
import hello.neardeal_server.store.entity.Store;
import hello.neardeal_server.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final FileStorage fileStorage;


    @Transactional
    /**
     * 상점 등록
     * */
    public Long createStore(StoreRequest request){

//        Long owner = 1L;
        String publicUrl = createImageUrl(request.getImage(), request.getStoreName());

        // todo: owner 만든 뒤 owner 넣어서 new 대신 해야함
        Store store = Store.create(request, publicUrl);
        Store save = storeRepository.save(store);

        return save.getId();
    }

    /**
     * 상점 정보 상세 조회
     * */
    public StoreDetailResponse findStoreDetail(Long storeId){
        Store store = findOne(storeId);

        return StoreDetailResponse.entityToResponse(store);

    }

    /**
     * 상점 필터링 목록 조회
     * */
    public Page<StoreDetailResponse> findFilerStore(StoreCategory category, int size, int page){

        if(category == null){
            Page<Store> all = storeRepository.findAll(PageRequest.of(page, size));

            return all.map(store -> StoreDetailResponse.entityToResponse(store));
        }else {
            Page<Store> all = storeRepository.findByCategory(category, PageRequest.of(page, size));

            return all.map(store -> StoreDetailResponse.entityToResponse(store));
        }

    }

    /**
     * 상점 정보 수정
     * */
    @Transactional
    public Long updateStoreInfo(Long storeId, StoreRequest request){
        Store store = findOne(storeId);

        String imageUrl = createImageUrl(request.getImage(), request.getStoreName() );
        
        // url 없으면 삭제하기
        if (imageUrl == null) {
            fileStorage.deleteByPublicUrl(store.getImageUrl());
        }
        
        return store.updateStore(request, imageUrl);
    }


    /**
     * 상점 정보 삭제
     * */
    @Transactional
    public void deleteStore(Long storeId){
        Store store = findOne(storeId);

        // 파일 삭제
        if (store.getImageUrl() != null) {
            fileStorage.deleteByPublicUrl(store.getImageUrl());
        }

        storeRepository.delete(store);
    }

    /** todo: 이거 스탬프로 가야할듯?
     * 내 가게 스탬프 목록 조회
     * */



    public Store findOne(Long storeId){
        return storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("찾는 store가 없습니다"));
    }

    // 이미지 만들기
    private String createImageUrl(MultipartFile file, String dirName) {
        // 이미지가 있을 때만 저장
        String publicUrl = null;
        if (file != null && !file.isEmpty()) {
            publicUrl = fileStorage.createUrl(dirName, file, 0);
        }
        
        return publicUrl;
    }

}
