package hello.neardeal_server.store.service;

import hello.neardeal_server.member.entity.PartnerCategory;
import hello.neardeal_server.store.dto.response.PartnerStoreDetailResponse;
import hello.neardeal_server.store.dto.response.StoreDetailResponse;
import hello.neardeal_server.store.entity.PartnerStore;
import hello.neardeal_server.store.entity.Store;
import hello.neardeal_server.store.repository.PartnerStoreRepository;
import hello.neardeal_server.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnerStoreService {

    private final PartnerStoreRepository partnerStoreRepository;

    public Page<PartnerStoreDetailResponse> findFilerStore(PartnerCategory partnerCategory, int size, int page) {

        if(partnerCategory == null){
            Page<PartnerStore> all = partnerStoreRepository.findAll(PageRequest.of(page, size));

            return all.map(store -> PartnerStoreDetailResponse.entityToResponse(store));
        }else {
            Page<PartnerStore> all = partnerStoreRepository.findByPartnerCategory(partnerCategory, PageRequest.of(page, size));

            return all.map(store -> PartnerStoreDetailResponse.entityToResponse(store));
        }
    }
}
