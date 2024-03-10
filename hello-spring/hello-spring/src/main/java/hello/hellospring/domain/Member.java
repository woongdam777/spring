package hello.hellospring.domain;
import jakarta.persistence.*;


@Entity
public class Member {

    // DB가 아이디를 자동으로 생성해주는 거 = 아이덴티티 / 아이덴티티 전략 / Strategy의 아이덴티티
    // 오라클 DB => Sequence
    // DB가 알아서 생성해주는 것을 아이덴티티라고 한다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "username") 이름이 다른경우 맵핑
    private String name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}