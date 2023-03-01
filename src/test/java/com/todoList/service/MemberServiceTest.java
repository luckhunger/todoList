package com.todoList.service;

import com.todoList.domain.Member;
import com.todoList.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService();
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");
        // when
        Long saveId = memberService.join(member);
        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void duplicationException() {
        // given
        Member member1 = new Member();
        member1.setName("test");

        Member member2 = new Member();
        member2.setName("test");
        // when
        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            // 예외 터졌으므로 성공
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        }
        // then
    }




    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}