package hello.neardeal_server.store.entity;

import hello.neardeal_server.common.DurationTime;
import hello.neardeal_server.common.auditing.BaseEntity;
import hello.neardeal_server.item.entity.Item;
import hello.neardeal_server.member.entity.Owner;
import hello.neardeal_server.stamp.entity.Stamp;
import hello.neardeal_server.store.StoreCategory;
import hello.neardeal_server.store.dto.request.StoreRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String storeName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startTime", column = @Column(name = "opening_start_time", nullable = false)),
            @AttributeOverride(name = "endTime",   column = @Column(name = "opening_end_time",   nullable = false))
    })
    private DurationTime openingTime;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startTime", column = @Column(name = "break_start_time", nullable = true)),
            @AttributeOverride(name = "endTime",   column = @Column(name = "break_end_time",   nullable = true))
    })
    private DurationTime breakTime;

    @Column(nullable = false, unique = true)
    private String address;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false)
    private float lat;

    @Column(nullable = false)
    private float lng;

    @Column(length = 512)
    @OrderColumn(name = "sort_order")
    private String imageUrl;

// todo: 리뷰 작성 시
//    private double score; // 별점

    private Integer likeCount;

    // todo: 등록날자

    // == 연관관계 == //
    @OneToMany(mappedBy = "store", cascade = ALL)
    private List<Item> items = new ArrayList<>(); // item 생성 시 add
    @OneToMany(mappedBy = "store", cascade = ALL)
    private List<Stamp> stamps = new ArrayList<>(); // stamp 생성 시 add

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

// todo:
//    private List<Coupon> coupons = new ArrayList<>();
//    private List<Review> reviews = new ArrayList<>();

    // == 연관관계 편의 메서드 == //
    public void addOwner(Owner owner){
        this.owner = owner;
        owner.getStores().add(this);
    }

    // == 생성자 메서드 == //
    public static Store create(StoreRequest request, String imageUrls, Owner owner){
        Store store = new Store();
        store.storeName = request.getStoreName();
        store.category = request.getCategory();
        store.openingTime = request.getOpeningTime();
        store.breakTime = request.getBreakTime();
        store.address = request.getAddress();
        store.introduce = request.getIntroduce();
        store.lat = request.getLat();
        store.lng = request.getLng();
        store.imageUrl = imageUrls;
        store.likeCount = 0;
        
        store.addOwner(owner);

        return store;
    }


    // == 비즈니스 로직 == //
    /**
     * 가게 정보 변경
     */
    public Long updateStore(StoreRequest request, String imageUrl){
        this.storeName = request.getStoreName();
        this.category = request.getCategory();
        this.openingTime = request.getOpeningTime();
        this.breakTime = request.getBreakTime();
        this.address = request.getAddress();
        this.introduce = request.getIntroduce();
        this.lat = request.getLat();
        this.lng = request.getLng();
        this.imageUrl = imageUrl;

        return this.id;
    }
    /**
     * 가게 대표 이미지 변경
     */
    public Long updateImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
        return this.id;
    }

    /**
     *  좋아요 수 증가
     */
    public void increaseLike(){
        this.likeCount += 1;
    }
    /**
     * 좋아요 수 감소
     */
    public void decreaseLike(){
        this.likeCount -= 1;
    }

    /** todo: 리뷰 작성 시
     * 별점 추가
     */


}
