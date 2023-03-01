package com.todoList.repository;

import com.todoList.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 저장소에 회원 정보 저장
    Member save(Member member);
    // Optional : java 8에 들어가있는 기능으로, null 처리방법이다.
    // id로 회원 찾기
    Optional<Member> findById(Long id);
    // name 으로 회원 찾기
    Optional<Member> findByName(String name);
    // nickName 으로 회원 찾기
//    Optional<Member> findByNickName(String nickName);
    // 저장된 모든 회원 list 출력
    List<Member> findAll();
}
