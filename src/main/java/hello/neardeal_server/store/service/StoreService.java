package hello.neardeal_server.store.service;

import hello.neardeal_server.common.file.FileStorage;
import hello.neardeal_server.member.entity.Owner;
import hello.neardeal_server.member.repository.MemberRepository;
import hello.neardeal_server.member.service.MemberService;
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
    private final MemberRepository memberRepository;
    private final MemberService memberService;


    @Transactional
    /**
     * 상점 등록
     * */
    public Long createStore(StoreRequest request, Long ownerId){
        
        // todo: owner 시큐리티에서 불러오기
//        Long ownerId = 1L;
        Owner owner = memberService.findOneOwner(ownerId);
        String publicUrl = createImageUrl(request.getImage(), request.getStoreName());

        Store store = Store.create(request, publicUrl, owner);
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
