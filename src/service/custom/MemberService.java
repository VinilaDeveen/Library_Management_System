package service.custom;

import java.util.ArrayList;

import dto.MemberDto;
import service.SuperService;

public interface MemberService extends SuperService{
    String save(MemberDto memberDto) throws Exception;
    String update(MemberDto memberDto) throws Exception;
    String delete(String memID) throws Exception;
    MemberDto get(String memID) throws Exception;
    ArrayList<MemberDto> getAll() throws Exception;
}
