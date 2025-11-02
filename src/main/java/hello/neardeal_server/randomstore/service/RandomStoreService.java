package hello.neardeal_server.randomstore.service;

import hello.neardeal_server.randomstore.dto.RandomStoreResponse;
import hello.neardeal_server.randomstore.repository.RandomStoreRepository;
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
        Object[] row = (category == null || category.isBlank())
                ? repo.pickRandomRaw()
                : repo.pickRandomRawByCategory(category);

        if (row == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    (category == null || category.isBlank())
                            ? "No store found"
                            : "No store in category: " + category);
        }

        Long id = ((Number) row[0]).longValue();
        String name = (String) row[1];
        String benefit = (String) row[2];

        return new RandomStoreResponse(id, name, benefit);
    }
}
