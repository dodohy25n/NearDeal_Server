package hello.neardeal_server.stamp.entity;

import hello.neardeal_server.common.auditing.BaseEntity;
import hello.neardeal_server.stamp.dto.request.StampRequest;
import hello.neardeal_server.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stamp extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stamp_id")
    private Long id;

    @Column(nullable = false)
    private Integer maxCount;

    @Column(nullable = false)
    private String reward;

    @Column(nullable = false)
    private String secretCode;

    // == 연관관계 == //
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // == 연관관계 편의 메서드 == //
    public void addStore(Store store){
        this.store = store;
        store.getStamps().add(this);
    }

    // == 생성 메서드 == //
    public static Stamp create(StampRequest request, Store store, String secretCode){
        Stamp stamp = new Stamp();
        stamp.maxCount = request.getMaxCount();
        stamp.reward = request.getReward();
        stamp.secretCode = secretCode;
        
        stamp.addStore(store);
        return stamp;
    }

    // == 비즈니스 로직 == //
    /**
     * 스탬프 정보 수정
     */
    public Long updateStampInfo(StampRequest request){
        this.maxCount = request.getMaxCount();
        this.reward = request.getReward();
        return this.id;
    }
}