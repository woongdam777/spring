package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {
// 인터페이스가 인터페이스를 받을때는 Extends라고 한다
// 스프링 데이터 JPA가 JPA리포지토리를 받고 있으면 스프링 빈에 자동으로 등록하고 구현체를 자동으로 만들어줌
// 그러면 다른 것들 어디갔어?
// JpaRepository에 가보면 기본적으로 find all find all by id 등등 Paging & Salting Repository. 페이징 처리 조회까지 해줌
// 기본적인 CRUD랑 단순 조회들이 제공됨

// 단 공통클래스는 구현 불가 그래서

    // 인터페이스 가져다가 직접 지정해주면 된다.
    //jpql -> select m from MEmber m where m.name = ?
    // 동적 쿼리는 Querydsl이라는 라이브러리 사용
    @Override
    Optional<Member> findByName(String name);
}


