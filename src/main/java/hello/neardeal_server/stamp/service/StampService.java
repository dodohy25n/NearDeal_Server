package hello.neardeal_server.stamp.service;

import hello.neardeal_server.common.PageResponse;
import hello.neardeal_server.common.RandomCode;
import hello.neardeal_server.stamp.dto.response.StampInfoResponse;
import hello.neardeal_server.stamp.dto.request.StampRequest;
import hello.neardeal_server.stamp.entity.Stamp;
import hello.neardeal_server.stamp.repository.StampRepository;
import hello.neardeal_server.store.entity.Store;
import hello.neardeal_server.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StampService {

    private final StoreService storeService;
    private final StampRepository stampRepository;

    /**
     * 스탬프 만들기
     */
    @Transactional
    public Long createStamp(StampRequest request, Long storeId){
        Store store = storeService.findOne(storeId);

        String secretCode = RandomCode.clearAlphaNum4();
        Stamp stamp = Stamp.create(request, store, secretCode);

        Stamp save = stampRepository.save(stamp);
        return save.getId();

    }

    /**
     * 스탬프 정보 조회
     */
    public StampInfoResponse findStampInfo(Long stampId){
        Stamp stamp = findOne(stampId);
        return StampInfoResponse.entityToResponse(stamp);
    }

    /**
     * 가게의 전체 스탬프 정보 조회
     */
    public Page<StampInfoResponse> findAllStampInfoByStore(Long storeId, int page, int size){
        Store store = storeService.findOne(storeId);
        Page<Stamp> stampPage = stampRepository.findByStore(store, PageRequest.of(page, size));
        return stampPage.map(stamp -> StampInfoResponse.entityToResponse(stamp));
    }

    /**
     * 스탬프 정보 수정
     */
    @Transactional
    public Long updateStampInfo(Long stampId, StampRequest request){
        Stamp stamp = findOne(stampId);
        return stamp.updateStampInfo(request);
    }

    /**
     * 스탬프 비밀번호 조회
     */
    public String findSecretCode(Long stampId){
        Stamp stamp = findOne(stampId);
        return stamp.getSecretCode();
    }

    /**
     * 스탬프 삭제
     */
    @Transactional
    public void deleteStampInfo(Long stampId){
        Stamp stamp = findOne(stampId);
        stampRepository.delete(stamp);
    }


    public Stamp findOne(Long stampId){
        return stampRepository.findById(stampId).orElseThrow(()-> new RuntimeException("스탬프 없어요"));
    }
}
