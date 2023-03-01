package com.todoList.repository;

import com.todoList.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // 저장할 장소 (회원 아이디(key), 멤버(value))
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
//        store.put(member.getName(), member);
//        store.put(member.getNickName(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                // -> : 람다문법
                // getName과 파라미터로 넘어온 name이 동일한지 확인
                .filter(member -> member.getName().equals(name))
                // 하나라도 찾는 것 > 뭐라도 하나 찾아지면 그것을 반환, 없으면 optional에 null 포함해서 반환
                .findAny();
    }

//    @Override
//    public Optional<Member> findByNickName(String nickName) {
//        return store.values().stream()
//                .filter(member -> member.getNickName().equals(nickName))
//                .findAny();
//    }

    @Override
    public List<Member> findAll() {
        // store.value() : store에 있는 member 들이 반환됨
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        // clear() : 싹 비워준다.
        store.clear();
    }

}
