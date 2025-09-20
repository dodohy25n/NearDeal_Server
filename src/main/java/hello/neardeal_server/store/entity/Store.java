//package hello.neardeal_server.store.entity;
//
//import hello.neardeal_server.DurationTime;
//import hello.neardeal_server.store.StoreCategory;
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Store {
//
//    @Id
//    @Column(name = "store_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String storeName;
//
//    @Column(nullable = false)
//    private StoreCategory category;
//
//    @Embedded
//    @Column(nullable = false)
//    private DurationTime openingTime;
//
//    @Embedded
//    @Column(nullable = false)
//    private DurationTime breakTime;
//
//    private Integer like;
//
//    private String address;
//
//    private String imageSrc;
//
//    private String introduce;
//
//    private double score;
//
//    // == 연관관계 == //
////    private List<Item> items = new ArrayList<>();
////    private List<Coupon> coupons = new ArrayList<>();
////    private List<Stamp> stamps = new ArrayList<>();
////    private List<Review> reviews = new ArrayList<>();
////    private Owner owner;
//
//    // == 연관관계 편의 메서드 == //
//    // == 생성자 메서드 == //
//    // == 비즈니스 로직 == //
//
//
//
//}
