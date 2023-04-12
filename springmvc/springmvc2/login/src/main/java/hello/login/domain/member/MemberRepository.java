package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    //저장
    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    // 찾기
    public Member findById(Long id) {
        return store.get(id);
    }

    //로그인 아이디로 찾기
    public Optional<Member> findByLoginId(String loginId) {
/*
        List<Member> all = findAll();
        for (Member m : all) {
            if (m.getLoginId().equals(loginId)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();

*/

        return findAll().stream()   //람다식. 위와 같은 로직.
                    .filter(m -> m.getLoginId().equals(loginId)) //filter 괄호안 조건을 만족하는 것만 선택됨
                    .findFirst();
    }
    //전체 찾기
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //key 빼고 value만 반환
    }

    //초기화
    public void clearStore() {
        store.clear();
    }
}
