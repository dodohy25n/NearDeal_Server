package hello.neardeal_server;

import hello.neardeal_server.store.service.StoreService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitData {

    private final StoreService storeService;


    @PostConstruct
    public void init() {
    }

}
