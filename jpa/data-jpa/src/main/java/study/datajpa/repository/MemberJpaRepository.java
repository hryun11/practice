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

    public List<Member> findByUsernameAndAgeGreaterThen(String username, int age) {
        return em.createQuery("select m from Member m where m.username = :username and m.age > :age")
                        .setParameter("username", username)
                        .setParameter("age", age)
                        .getResultList();
    }

    public List<Member> findByUsername(String username) {
        return em.createNamedQuery("Member.findByUsername", Member.class)
                        .setParameter("username", username)
                        .getResultList();
    }

    /*
    검색 조건: 나이가 10살
    정렬 조건: 이름으로 내림차순
    페이징 조건: 첫 번째 페이지, 페이지당 보여줄 데이터는 3건
* */
    public List<Member> findByPage(int age, int offset, int limit) {
        return em.createQuery("select m from Member m where m.age = :age order by m.username desc ")
                        .setParameter("age", age)
                        .setFirstResult(offset) //어디서부터 가져올지
                        .setMaxResults(limit)
                        .getResultList();
    }

    // 몇번째 페이지인지
    public long totalCount(int age) {
        return em.createQuery("select count(m) from Member m where m.age = :age", Long.class)
                        .setParameter("age", age)
                        .getSingleResult();
    }

    //벌크성 쿼리 수정
    // 나이를 한번에 변경
    // where~ 조건
    public int bulkAgePlus(int age) {
        return em.createQuery(
                "update Member m set m.age = m.age + 1" +
                        " where m.age >= :age")
                .setParameter("age", age)
                .executeUpdate();
    }
}
