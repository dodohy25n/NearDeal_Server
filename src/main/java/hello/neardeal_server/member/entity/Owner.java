package hello.neardeal_server.member.entity;

import hello.neardeal_server.member.dto.request.OwnerRequest;
import hello.neardeal_server.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long id;

    private String businessNumber;

    // == 연관관계 == //

    @OneToMany(mappedBy = "owner", cascade = ALL)
    private List<Store> stores = new ArrayList<>();

    // == 생성 메서드 == //
    public static Owner create(OwnerRequest request){
        Owner owner = new Owner();
        owner.businessNumber = request.getBusinessNumber();

        return owner;
    }

    // == 비즈니스 로직 == //
    public Long updateInfo(OwnerRequest request){
        this.businessNumber = request.getBusinessNumber();
        return this.id;
    }



}