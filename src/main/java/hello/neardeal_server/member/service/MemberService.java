package hello.neardeal_server.member.service;

import hello.neardeal_server.common.PageResponse;
import hello.neardeal_server.member.dto.request.CustomerRequest;
import hello.neardeal_server.member.dto.request.MemberUpdateRequest;
import hello.neardeal_server.member.dto.request.OwnerRequest;
import hello.neardeal_server.member.dto.request.SignupRequest;
import hello.neardeal_server.member.dto.response.CustomerDetailResponse;
import hello.neardeal_server.member.dto.response.MemberDetailResponse;
import hello.neardeal_server.member.dto.response.OwnerDetailResponse;
import hello.neardeal_server.member.entity.Customer;
import hello.neardeal_server.member.entity.Member;
import hello.neardeal_server.member.entity.Owner;
import hello.neardeal_server.member.repository.CustomerRepository;
import hello.neardeal_server.member.repository.MemberRepository;
import hello.neardeal_server.member.repository.OwnerRepository;
import hello.neardeal_server.store.dto.response.StoreDetailResponse;
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

    /**
     * 회원가입
     */
    public Long signup(SignupRequest signupRequest) {
        //signupRequest.setPassword(bcryptPasswordEncoder.encode(signupRequest.getPassword()));
        if (signupRequest.getOwnerId() != null) {
            Owner owner = findOneOwner(signupRequest.getOwnerId());
            Member member = Member.createOwnerMember(signupRequest, owner);
            Member savedMember = memberRepository.save(member);
            return savedMember.getId();
        } else if (signupRequest.getCustomerId() != null) {
            Customer customer = findOneCustomer(signupRequest.getCustomerId());
            Member member = Member.createCustomerMember(signupRequest, customer);
            Member savedMember = memberRepository.save(member);
            return savedMember.getId();
        } else {
            return null;
        }
    }

    /**
     * 회원정보 조회
     */
    public MemberDetailResponse getMemberDetail(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return MemberDetailResponse.entityToResponse(member);
    }

    /**
     * 전체회원 목록 조회
     */
    public PageResponse<MemberDetailResponse> getMemberList(int page, int size) {
        Page<Member> members = memberRepository.findAll(PageRequest.of(page, size));
        Page<MemberDetailResponse> memberDetailResponsePage = members.map(MemberDetailResponse::entityToResponse);
        return PageResponse.pageToResponse(memberDetailResponsePage);
    }

    /**
     * member 정보 변경
     */
    public MemberDetailResponse updateMember(Long memberId, MemberUpdateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        member.update(request);

        if (member.getCustomer() != null) {
            member.getCustomer().update(request.getAffiliation());
        } else if (member.getOwner() != null) {
            //member.getOwner().update(request);
        }else {
            //throw new EntityNotFoundException("Customer or Owner not found");
        }

        return MemberDetailResponse.entityToResponse(member);
    }

    /**
     * Customer 회원가입
     */
    public Long registerCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.create(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer.getId();
    }
    /**
     * Customer 정보 조회
     */
    public CustomerDetailResponse getCustomerDetail(Long customerId){
        //todo: 시큐리티에서 customerId 받아오기
        Customer findCustomer = findOneCustomer(customerId);
        return CustomerDetailResponse.entityToResponse(findCustomer);
    }
    /**
     * Customer 목록 조회
     */
    public Page<CustomerDetailResponse> getCustomerList(int page, int size){
        Page<Customer> all = customerRepository.findAll(PageRequest.of(page, size));
        return all.map(customer -> CustomerDetailResponse.entityToResponse(customer));
    }
    /**
     * Customer 정보 변경
     */
    public void updateCustomer(Long customerId, CustomerRequest request){
        //todo: 시큐리티에서 customerId 받아오기
        Customer findCustomer = findOneCustomer(customerId);
        findCustomer.update(request.getAffiliation());
    }
    /**
     * Customer 삭제
     */
    public void deleteCustomer(Long customerId){
        //todo: 시큐리티에서 customerId 받아오기
        Customer customer = findOneCustomer(customerId);
        customerRepository.delete(customer);
    }



    /**
     * Owner 회원가입
     */
    public Long registerOwner(OwnerRequest request){
        Owner owner = Owner.create(request);
        Owner save = ownerRepository.save(owner);
        return save.getId();
    }
    /**
     * Owner 정보 조회
     */
    public OwnerDetailResponse getOwnerDetail(Long ownerId){
        //todo: 시큐리티에서 ownerId 받아오기
        Owner findOwner = findOneOwner(ownerId);
        return OwnerDetailResponse.entityToResponse(findOwner);
    }
    /**
     * Owner 목록 조회
     */
    public Page<OwnerDetailResponse> getOwnerList(int page, int size){
        Page<Owner> all = ownerRepository.findAll(PageRequest.of(page, size));
        return all.map(owner -> OwnerDetailResponse.entityToResponse(owner));
    }
    /**
     * Owner 정보 변경
     */
    public Long updateOwner(Long ownerId, OwnerRequest request){
        //todo: 시큐리티에서 ownerId 받아오기
        Owner findOwner = findOneOwner(ownerId);
        return findOwner.updateInfo(request);
    }
    /**
     * Owner 삭제
     */
    public void deleteOwner(Long ownerId){
        //todo: 시큐리티에서 ownerId 받아오기
        Owner owner = findOneOwner(ownerId);
        ownerRepository.delete(owner);
    }

    public Customer findOneCustomer(Long customerId){
        return customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException("customer 없습니다"));
    }

    public Owner findOneOwner(Long ownerId){
        return ownerRepository.findById(ownerId).orElseThrow(()-> new RuntimeException("Owner 없습니다"));
    }
}
