package hello.neardeal_server.member.service;

import hello.neardeal_server.member.dto.*;
import hello.neardeal_server.member.entity.Customer;
import hello.neardeal_server.member.entity.Member;
import hello.neardeal_server.member.entity.Owner;
import hello.neardeal_server.member.repository.CustomerRepository;
import hello.neardeal_server.member.repository.MemberRepository;
import hello.neardeal_server.member.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService  {

    private final MemberRepository memberRepository;
    private final CustomerRepository customerRepository;
    private final OwnerRepository ownerRepository;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long signup(SignupRequest signupRequest) {
        //signupRequest.setPassword(bcryptPasswordEncoder.encode(signupRequest.getPassword()));
        if (signupRequest.getOwnerId() != null) {
            Owner owner = ownerRepository.findById(signupRequest.getOwnerId()).orElseThrow(() -> new EntityNotFoundException("Owner not found"));
            Member member = Member.createOwnerMember(signupRequest, owner);
            Member savedMember = memberRepository.save(member);
            return savedMember.getId();
        } else if (signupRequest.getCustomerId() != null) {
            Customer customer = customerRepository.findById(signupRequest.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
            Member member = Member.createCustomerMember(signupRequest, customer);
            Member savedMember = memberRepository.save(member);
            return savedMember.getId();
        } else {
            return null;
        }
    }

    public Long registerCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.create(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer.getId();
    }

    public MemberDetailResponse getMemberDetail(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return MemberDetailResponse.entityToResponse(member);
    }

    public MemberListResponse getMemberList(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Member> members = memberRepository.findAll(pageRequest);
        Page<MemberDetailResponse> memberDetailResponsePage = members.map(MemberDetailResponse::entityToResponse);
        return MemberListResponse.of(memberDetailResponsePage);
    }

    public MemberDetailResponse updateMember(Long memberId, MemberUpdateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        member.update(request);

        if (member.getCustomer() != null) {
            member.getCustomer().update(request);
        } else if (member.getOwner() != null) {
            //member.getOwner().update(request);
        }else {
            //throw new EntityNotFoundException("Customer or Owner not found");
        }

        return MemberDetailResponse.entityToResponse(member);
    }
}
