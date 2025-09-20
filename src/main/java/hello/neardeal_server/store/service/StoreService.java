package hello.neardeal_server.store.service;

import hello.neardeal_server.file.FileStorage;
import hello.neardeal_server.member.entity.Owner;
import hello.neardeal_server.store.StoreCategory;
import hello.neardeal_server.store.dto.StoreDetailResponse;
import hello.neardeal_server.store.dto.StoreRequest;
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
    public Long createStore(StoreRequest request, MultipartFile file){

//        Long owner = 1L;

        // 멤버 폴더 생성 (nearDealImage/{memberId})
        String storeName = request.getStoreName();

        // 사진 저장
        String publicUrl = fileStorage.createUrl(storeName, file, -1);

        // todo: owner 만든 뒤 해야함
        Store store = Store.create(request, publicUrl, new Owner());
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
    public Long updateStoreInfo(Long storeId, StoreRequest storeRequest){
        Store store = findOne(storeId);
        return store.updateStore(storeRequest);
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


}
