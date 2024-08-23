package service.custom.impl;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.custom.MemberDao;
import dto.MemberDto;
import entity.MemberEntity;
import service.custom.MemberService;

public class MemberServiceImpl implements MemberService{
    private MemberDao memberDao = (MemberDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.MEMBER);

    @Override
    public String save(MemberDto memberDto) throws Exception {
        MemberEntity entity = getMemberEntity(memberDto);
        return memberDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(MemberDto memberDto) throws Exception {
        MemberEntity entity = getMemberEntity(memberDto);
        return memberDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String memID) throws Exception {
        return memberDao.delete(memID) ? "Success" : "Fail";
    }

    @Override
    public MemberDto get(String memID) throws Exception {
        MemberEntity entity = memberDao.get(memID);
        if(entity != null) {
            return getMemberDto(entity);
        }
        return null;
    }

    @Override
    public ArrayList<MemberDto> getAll() throws Exception {
        ArrayList<MemberEntity> memberEntities = memberDao.getAll();
        if (memberEntities != null && !memberEntities.isEmpty()) {
            ArrayList<MemberDto> memberDtos = new ArrayList<>();

            for (MemberEntity memberEntity : memberEntities){
                memberDtos.add(getMemberDto(memberEntity));
            }
            return memberDtos;
        }
        return null;
    }

    private MemberEntity getMemberEntity(MemberDto memberDto){
        return new MemberEntity(
            memberDto.getMemId(),
            memberDto.getName(),
            memberDto.getPhone(),
            memberDto.getUserName(),
            memberDto.getPassword()
        );
    }

    private MemberDto getMemberDto(MemberEntity entity){
        return new MemberDto(
            entity.getMemId(),
            entity.getName(),
            entity.getPhone(),
            entity.getUserName(),
            entity.getPassword()
        );
    }
    
}
