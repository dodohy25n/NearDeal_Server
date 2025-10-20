package hello.neardeal_server.randomstore.service;

import hello.neardeal_server.randomstore.dto.RandomStoreResponse;
import hello.neardeal_server.randomstore.repository.RandomStoreRepository;
import hello.neardeal_server.store.entity.PartnerStore;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class RandomStoreService {

    private final RandomStoreRepository repo;

    public RandomStoreService(RandomStoreRepository repo) {
        this.repo = repo;
    }

    public RandomStoreResponse getRandom(String category) {
        PartnerStore store = (category == null || category.isBlank())
                ? repo.pickRandom().orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No store found"))
                : repo.pickRandomByCategory(category).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No store in category: " + category));

        // ✅ 아래 getter 이름은 실제 PartnerStore 엔티티에 맞춰 조정
        return new RandomStoreResponse(getId(store), getStoreName(store));
    }

    // --- 필요 시 실제 필드명에 맞게 매핑 헬퍼 작성 ---
    private Long getId(PartnerStore s) {
        // 보통은 s.getId()
        // 만약 필드명이 partnerStoreId면 s.getPartnerStoreId()
        try { return (Long) s.getClass().getMethod("getId").invoke(s); }
        catch (Exception e) {
            try { return (Long) s.getClass().getMethod("getPartnerStoreId").invoke(s); }
            catch (Exception e2) { throw new IllegalStateException("PartnerStore id getter 확인 필요"); }
        }
    }

    private String getStoreName(PartnerStore s) {
        // 보통은 s.getStoreName()
        // 만약 snake_case 매핑이면 s.getStore_name()
        try { return (String) s.getClass().getMethod("getStoreName").invoke(s); }
        catch (Exception e) {
            try { return (String) s.getClass().getMethod("getStore_name").invoke(s); }
            catch (Exception e2) { throw new IllegalStateException("PartnerStore storeName getter 확인 필요"); }
        }
    }
}
