package hello.neardeal_server.member.entity;

import hello.neardeal_server.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    // == 연관관계 == //
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Store> stores = new ArrayList<>();

    // todo: owner 만든 뒤 store저장 해야함
    
}