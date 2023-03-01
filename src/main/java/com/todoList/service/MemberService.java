package com.todoList.service;

import com.todoList.domain.Member;
import com.todoList.repository.MemberRepository;
import com.todoList.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public long join(Member member) {
        // 동일 아이디는 회원가입 불가
        // Optional을 바로 반환하는건 좋지 않음
//        Optional<Member> result = memberRepository.findById(member.getId());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        // 그래서 아래와 같은 방식으로 사용함
        // 중복 회원 검증 메소드
        // ctrl + alt + M 단축키로 메소드 자동생성
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findById(member.getId())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
