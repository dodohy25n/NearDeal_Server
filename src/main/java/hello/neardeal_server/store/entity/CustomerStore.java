package hello.neardeal_server.store.entity;

import hello.neardeal_server.common.auditing.BaseEntity;
import hello.neardeal_server.member.entity.Customer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerStore extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_store_id")
    private Long id;

    private Boolean isLiked; // 좋아요 여부

    /* --- 관계 --- */
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    // == 연관관계 편의 메서드 == //
    public void addCustomer(Customer customer){
        this.customer = customer;
        customer.getCustomerStoreList().add(this);
    }
    public void addStore(Store store){
        this.store = store;
    }

    // == 생성자 메서드 == //
    public static CustomerStore create(Customer customer, Store store){
        CustomerStore customerStore = new CustomerStore();
        customerStore.isLiked = true;
        customerStore.addCustomer(customer);
        customerStore.addStore(store);

        return customerStore;
    }

    // == 비즈니스 로직 == //

    /**
     * 좋아요 버튼
     */
    public void like() {
        if (!this.isLiked) {
            this.isLiked = true;
        }
    }

    public void unlike() {
        if (this.isLiked) {
            this.isLiked = false;
        }

    }
}
