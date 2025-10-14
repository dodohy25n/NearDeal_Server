package hello.neardeal_server.member.entity;

import hello.neardeal_server.member.dto.request.CustomerRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private PartnerCategory affiliation;


    public static TempMember create(CustomerRequest customerRequest) {
        TempMember tempMember = new TempMember();
        tempMember.affiliation = customerRequest.getAffiliation();
        return tempMember;
    }
}
