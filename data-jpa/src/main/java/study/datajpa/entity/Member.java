package study.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)  //ManyToOne에 JPA에서 모든 연관관계는 LAZY로 셋팅
    @JoinColumn(name = "team_id")
    private Team team;

    //entity는 파라미터가 없는 기본 생성자가 있어야함
    //jpa를 위해 액세스레벨 protected까지 열어야함. private X
//    protected Member() {
//    }

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    // 연관관계 세팅 메소드
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
