package hello.neardeal_server.item.entity;

import hello.neardeal_server.coupon.entity.Coupon;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    /* --- 관계 --- */
    @OneToOne(mappedBy = "coupon")
    private Coupon coupon;
}
