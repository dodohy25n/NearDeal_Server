package hello.neardeal_server;

import hello.neardeal_server.store.dto.StoreRequest;
import hello.neardeal_server.store.service.StoreService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class InitData {

    private final StoreService storeService;


    @PostConstruct
    public void init() {
    }

}
