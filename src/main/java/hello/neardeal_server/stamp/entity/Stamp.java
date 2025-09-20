package hello.neardeal_server.stamp.entity;

import hello.neardeal_server.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stamp_id")
    private Long id;

    // == 연관관계 == //
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

}