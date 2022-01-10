package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;

    MemoryMemberRepository memberRepositorytory;
    @BeforeEach
    public void beforeEach(){

        memberRepositorytory = new MemoryMemberRepository();
        memberService = new MemberService(memberRepositorytory);

    }

    @AfterEach
    public void afterEach(){
        memberRepositorytory.clearStore();

    }

    @Test
    void 회원가입() {
        //given 어떤상황이 주어졌을 때
        Member member = new Member();
        member.setName("Hello");

        //when  무언가를 했을 때
        Long saveId = memberService.join(member);
        //then  이런 결과가 나와야해
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외가입(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        /*try {
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.123");
        }*/
        //then
    }

    @Test
    void 회원찾기() {
        Member member1 = new Member();
        member1.setName("spring");
        memberService.join(member1);

        List<Member> list = memberService.findMembers();

        Member findMember = list.get(0);
        Assertions.assertThat(findMember).isEqualTo(member1);


    }

    @Test
    void findOne() {
    }
}