package hello.hellospring.repository;

import hello.hellospring.SpringConfig;
import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// jpa를 쓰려면 항상 트랜잭션이 있어야 한다.
// jpa 는 이 조인 들어올 때 모든 데이터 변경이 다 트랜잭션 안에서 실행이 되어야 한다.
@Transactional
public class JpaMemberRepository implements MemberRepository{

    // 스프링 부트가 자동으로 빌드그레이드 해서 jpa라이브러리 받은거로
    // properties정보를 가지고 데이터베이스랑 연결까지 다해서 EntityManager 생성해줌
    // 이걸 인젝션 받아서 쓰면 된다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist 영속하다 영구저장하다 뜻
        return member; // 이게끝... 인서트 콜 다만들고 db집어넣고 set id까지 다해줌
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    // pk기반이 아닌 나머지들은 jpql이라는 문법을 사용해야 된다.
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}
