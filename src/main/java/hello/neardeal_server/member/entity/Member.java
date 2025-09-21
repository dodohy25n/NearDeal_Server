package hello.neardeal_server.member.entity;

import hello.neardeal_server.member.dto.request.MemberUpdateRequest;
import hello.neardeal_server.member.dto.request.SignupRequest;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String phone;

    /* --- 관계 --- */

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    /* --- 메서드 --- */

    public static Member createCustomerMember(SignupRequest signupRequest, Customer customer) {
        Member member = create(signupRequest);
        member.addCustomer(customer);
        return member;
    }

    public static Member createOwnerMember(SignupRequest signupRequest, Owner owner) {
        Member member = create(signupRequest);
        member.addOwner(owner);
        return member;
    }

    private static Member create(SignupRequest signupRequest) {
        Member member = new Member();
        member.name = signupRequest.getName();
        member.email = signupRequest.getEmail();
        member.password = signupRequest.getPassword();
        member.nickName = signupRequest.getNickName();
        member.phone = signupRequest.getPhone();
        return member;
    }

    /* --- 연관관계 편의 메서드 --- */

    private void addCustomer(Customer customer) {
        this.customer = customer;
        this.owner = null;
    }

    private void addOwner(Owner owner) {
        this.owner = owner;
        this.customer = null;
    }

    public void update(MemberUpdateRequest request) {
        this.name = request.getName();
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.nickName = request.getNickName();
        this.phone = request.getPhone();
    }

}
