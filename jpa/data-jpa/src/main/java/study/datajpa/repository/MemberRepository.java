package study.datajpa.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findHelloBy();

    List<Member> findTop3HelloBy();

//    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    /*
    * 실제로 많이 씀.
    * */
    @Query("select m from Member m where m.username = :username and m.age = :age") // 복잡한 jpql을 바로 쓸 수 있음
    List<Member> findUser(@Param("username") String username, @Param("age") int age);  // @Param으로 조건 설정. 메소드 이름을 짧게 할 수 있음

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    /*
    * dto로 조회
    * */
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    /*
    * 코드 가독성과 유지보수를 위해 위치기반보다 이름기반 파라미터 바인딩 사용
    * */
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    List<Member> findListByUsername(String username); // 컬렉션
    Member findMemberByUsername(String username); // 단건
    Optional<Member> findOptionalByUsername(String username); // 단건 Optional

    @Query(value = "select m from Member m left join m.team t",
            countQuery = "select count(m) from Member m")   //카운트쿼리까지 맵핑된 테이블을 다 가져오면 너무 느려짐. 카운트쿼리는 따로 분리해서 멤버만 가져옴.
    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true) // .clear()를 자동으로 해준다.
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    /*
    * 필요한 경우만 사용
    * */
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);
}
