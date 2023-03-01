package com.todoList.repository;

import com.todoList.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
//    MemberRepository repository = new MemoryMemberRepository();
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // AfterEach : 메소드 실행이 끝날 때마다 작성한 동작을 수행한다.
    // Test에서 메소드 실행 시, 순서가 보장이 안되기 때문에 findAll()에서 이미 데이터가 있는 상태가 되어 에러가 발생한다.
    // 이를 막기 위해 메소드가 끝날 때 마다 데이터를 clear 하는 코드를 작성한다.
    // 테스트가 하나 끝날 때 마다 레파지토리를 싹 비운다. > 순서 상관 없이 수행해도 에러가 나지 않는다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setId(1L);
        member.setName("first");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // 검증 코드
        System.out.println("result = " + (result == member));
        // jupiter 사용
//        Assertions.assertEquals(member, result);
        // assertj 사용
        // alt + enter > static 형태로 변환
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        // shift + F6 > rename 가능 > 자동변경
        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

        Member result = repository.findByName("test1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }

}
