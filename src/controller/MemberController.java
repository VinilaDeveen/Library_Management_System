package controller;

import java.util.ArrayList;

import dto.MemberDto;
import service.ServiceFactory;
import service.custom.MemberService;

public class MemberController {
    private MemberService memberService = (MemberService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.MEMBER);

    public String save(MemberDto memberDto) throws Exception{
        return memberService.save(memberDto);
    }

    public String update(MemberDto memberDto) throws Exception{
        return memberService.update(memberDto);
    }

    public String delete(String memID) throws Exception{
        return memberService.delete(memID);
    }

    public ArrayList<MemberDto> getAll() throws Exception{
        return memberService.getAll();
    }

    public MemberDto get(String memId) throws Exception{
        return memberService.get(memId);
    }

    public MemberDto login(String username, String password) throws Exception {
        return memberService.login(username, password);
    }
}
