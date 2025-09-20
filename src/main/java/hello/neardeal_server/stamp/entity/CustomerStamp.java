package hello.neardeal_server.stamp.entity;

import hello.neardeal_server.member.entity.Customer;
import hello.neardeal_server.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_stamp_id")
    private Long id;

    @Column(nullable = false)
    private Integer currentCount;

    private Boolean isVisible;

    /* --- 관계 --- */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "stamp_id")
    private Stamp stamp;

    // == 연관관계 편의 메서드 == //
    public void addCustomer(Customer customer){
        this.customer = customer;
        customer.getCustomerStampList().add(this);
    }
    public void addStamp(Stamp stamp){
        this.stamp = stamp;
    }

    // == 생성자 메서드 == //
    public static CustomerStamp create(Customer customer, Stamp stamp){
        CustomerStamp customerStamp = new CustomerStamp();
        customerStamp.currentCount = 0;
        customerStamp.isVisible = true;

        customerStamp.addCustomer(customer);
        customerStamp.addStamp(stamp);

        return customerStamp;
    }

    // == 비즈니스 로직 == //
    /**
     * 스탬프 토글
     */
    public void toggleVisible(){
        this.isVisible = !isVisible;
    }
    /**
     * 스탬프 적립
     */
    public Integer addStamp(){
        currentCount += 1;
        return currentCount;
    }
    /**
     * 쿠폰 변환되어 0개로 돌아가기
     */
    public void changeCoupon(){
        currentCount = 0;
    }
}
