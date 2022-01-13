package hello.hellospring;


import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    //private DataSource dataSource;
    //private EntityManager em;
    private final MemberRepository memberRepository;
    //인터페이스만등록해 놓으면 스프링컨테이너에서 MemberRepository를 등록 해놓진 않았지만 인터페이스의 구현체를 찾아서 등록해놓음
    //스프링데이터jpa의 특별한 기능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService(){

        //return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }
//    @Bean
//    public MemberRepository memberRepository(){
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        //return new JpaMemberRepository(em);
//
//    }
}
