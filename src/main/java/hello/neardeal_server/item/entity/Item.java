package hello.neardeal_server.item.entity;

import hello.neardeal_server.common.auditing.BaseEntity;
import hello.neardeal_server.coupon.entity.Coupon;
import hello.neardeal_server.item.dto.request.ItemRequest;
import hello.neardeal_server.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = true)
    private String imageUrl;

    private Integer price;

    @Column(nullable = false)
    private Boolean isSoldOut;

    private String introduce;

    @Column(nullable = false)
    private Boolean isRepresentative;

    // == 연관관계 == //
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    // == 연관관계 편의 메서드 == //
    public void addStore(Store store){
        this.store = store;
        store.getItems().add(this);
    }

    public void addCoupon(Coupon coupon){
        this.coupon = coupon;
    }

    // == 생성자 메서드 == //
    public static Item create(ItemRequest itemRequest, Store store, String imageUrl){
        Item item = new Item();
        item.name = itemRequest.getName();
        item.imageUrl = imageUrl;
        item.price = itemRequest.getPrice();
        item.isSoldOut = itemRequest.getIsSoldOut();
        item.introduce = itemRequest.getIntroduction();
        item.isRepresentative = itemRequest.getIsRepresentative();

        item.addStore(store);

        return item;

    }

    // == 비즈니스 로직 == //

    /**
     * 아이템 업데이트
     */
    public Long updateItem(ItemRequest itemRequest, String imageUrl){
        this.name = itemRequest.getName();
        this.imageUrl = imageUrl;
        this.price = itemRequest.getPrice();
        this.isSoldOut = itemRequest.getIsSoldOut();
        this.introduce = itemRequest.getIntroduction();
        this.isRepresentative = itemRequest.getIsRepresentative();

        return this.id;
    }


    public Long toggleItemStatus() {
        this.isSoldOut = !this.isSoldOut;
        return this.id;

    }
}
