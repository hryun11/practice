package study.datajpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {

    @PersistenceContext //spring 컨테이너(jpa 영속성 컨텍스트)가 entitymanager를 가져다 줌.
    private EntityManager em;

    // 멤버 저장, 저장된 멤버 반환
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public List<Member> findAll() {
        //JPQL
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member); //null일수도있고 아닐 수도 있음
    }

    public long count() {
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }
    // id값만 넘기면 멤버 조회
    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
