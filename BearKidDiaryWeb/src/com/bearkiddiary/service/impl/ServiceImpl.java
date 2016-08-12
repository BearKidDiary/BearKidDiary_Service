package com.bearkiddiary.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.dao.FamilyDao;
import com.bearkiddiary.dao.KidDao;
import com.bearkiddiary.dao.OrgDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.service.Service;
import com.bearkiddiary.utils.ResultCode;

public class ServiceImpl implements Service {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private FamilyDao familyDao;

	public void setFamilyDao(FamilyDao familyDao) {
		this.familyDao = familyDao;
	}

	private KidDao kidDao;

	public void setKidDao(KidDao kidDao) {
		this.kidDao = kidDao;
	}

	private OrgDao orgDao;

	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	// @Override
	// public boolean Login(User user) {
	// // TODO Auto-generated method stub
	// List<User> userList = testDao.Login(user);
	// if(!userList.isEmpty()){
	// return true;
	// }
	// return false;
	// }
	/**
	 * 注册
	 */
	@Override
	public boolean Register(User user) {
		// TODO Auto-generated method stub
		if (userDao.Valid(user.getUphone()) == 0) {
			System.out.println(userDao.save(user));
			return true;
		}
		return false;
	}

	/**
	 * 登录
	 */
	@Override
	public boolean Login(String Uphone, String Upsw) {
		int userCount = userDao.Login(Uphone, Upsw);
		if (userCount > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 更新个人信息
	 */
	@Override
	public int updateUser(String Uphone, String Parameter, String value) {
		int result = 0;
		switch (Parameter) {
		case User.NAME:
			result = userDao.updateName(Uphone, value);
			break;
		case User.AREA:
			result = userDao.updateArea(Uphone, value);
			break;
		case User.EMAIL:
			result = userDao.updateEmail(Uphone, value);
			break;
		}
		return result;
	}

	@Override
	public int createFamily(String Uphone, String Fname) {
		if (Fname == null) {
			Fname = Uphone + "的家庭";
		}
		return familyDao.createFamily(Uphone, Fname);
	}

	@Override
	public Set<User> getFamilyMembers(String Uphone, Long Fid) {
		Set<User> members = null;
		if (Fid != null) {
			members = familyDao.getMembersInFamily(Fid);
		}
		if ((members == null || members.size() == 0) && Uphone != null) {
			members = familyDao.getMembersInFamily(Uphone);
		}
		if (members == null) {
			members = new HashSet<>();
		}
		return members;
	}

	@Override
	public Set<User> getFamilyMembersAndCreator(String Uphone, Long Fid) {
		Set<User> members = null;
		if (Fid != null) {
			members = familyDao.getMembersAndCreatorInFamily(Fid);
		}
		if ((members == null || members.size() == 0) && Uphone != null) {
			members = familyDao.getMembersAndCreatorInFamily(Uphone);
		}
		if (members == null) {
			members = new HashSet<>();
		}
		return members;
	}
	
	@Override
	public User getFamilyCreator(String Uphone, Long Fid) {
		User creator = null;
		if(Fid!=null){
			creator = familyDao.getCreatorInFamily(Fid);
		}
		if(creator==null && Uphone!=null){
			creator = userDao.getUser(Uphone);
		}
		return creator;
	}

	@Override
	public int addFamilyMembers(Long Fid, String creatorPhone, Long Uid, String memberPhone) {
		if (Fid != null) {
			if (Uid != null)
				return familyDao.addMemberToFamily(Fid, Uid);
			else
				return familyDao.addMemberToFamily(Fid, memberPhone);
		} else {
			if (Uid != null)
				return familyDao.addMemberToFamily(creatorPhone, Uid);
			else
				return familyDao.addMemberToFamily(creatorPhone, memberPhone);
		}
	}

	@Override
	public int removeFamilyMember(Long Fid, String creatorPhone, String memberPhone) {
		int code = ResultCode.ERROR_MISSING_PARAMETER;
		if (Fid != null) {
			code = familyDao.deleteMemberFromFamily(memberPhone, Fid);
		}
		if (code != ResultCode.SUCCESS && creatorPhone != null) {
			code = familyDao.deleteMemberFromFamily(memberPhone, creatorPhone);
		}
		return code;
	}

	@Override
	public Family getCreatedFamily(String Uphone, Long Fid) {
		Family family = null;
		if (Fid != null) {
			family = familyDao.getFamily(Fid);
		}
		if (family == null && Uphone != null) {
			family = familyDao.getCreatedFramily(Uphone);
		}
		return family;
	}

	@Override
	public Set<Family> getAttendFamily(String Uphone) {
		return familyDao.getAttendedFramily(Uphone);
	}

	@Override
	public int updateFamily(String Uphone, Long Fid, String Fname) {
		int result = ResultCode.ERROR_MISSING_PARAMETER;
		if (Fid != null) {
			result = familyDao.updateFamilyName(Fid, Fname);
		}

		if (result != ResultCode.SUCCESS && Uphone != null) {
			result = familyDao.updateFamilyName(Uphone, Fname);
		}
		return result;
	}

	@Override
	public long createOrg(String Oname, String Oaddress, String Oannounce, Long Uid) {
		long Oid = orgDao.createOrg(Oname, Oaddress, Oannounce, Uid);
		return Oid;
	}

	/**
	 * 解散机构
	 */
	@Override
	public void deleteOrg(long Oid) {
		orgDao.deleteOrg(Oid);
	}

	@Override
	public long updateOrg(long Oid, String Parameter, String value) {
		long result = 0;
		switch (Parameter) {
		case Organization.ONAME:
			result = orgDao.updateOname(Oid, value);
			break;
		case Organization.OADDRESS:
			result = orgDao.updateOaddress(Oid, value);
			break;
		case Organization.OANNOUNCE:
			result = orgDao.updateOannounce(Oid, value);
			break;
		}
		return result;
	}
}
