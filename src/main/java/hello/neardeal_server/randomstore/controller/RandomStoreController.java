package hello.neardeal_server.randomstore.controller;

import hello.neardeal_server.randomstore.dto.RandomStoreResponse;
import hello.neardeal_server.randomstore.service.RandomStoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner-stores")
public class RandomStoreController {

    private final RandomStoreService service;

    // 명시적 생성자만 유지
    public RandomStoreController(RandomStoreService service) {
        this.service = service;
    }

    @GetMapping("/random")
    public RandomStoreResponse getRandom(@RequestParam(required = false) String category) {
        return service.getRandom(category);
    }
}
