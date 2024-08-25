package dao.custom;

import dao.CrudDao;
import entity.MemberEntity;

public interface MemberDao extends CrudDao<MemberEntity, String>{

    public MemberEntity getByUsernameAndPassword(String username, String password) throws Exception;
}