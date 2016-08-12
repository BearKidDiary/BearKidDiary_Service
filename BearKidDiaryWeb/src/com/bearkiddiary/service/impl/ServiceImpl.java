package com.bearkiddiary.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.dao.FamilyDao;
import com.bearkiddiary.dao.KidDao;
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

	// @Override
	// public boolean Login(User user) {
	// // TODO Auto-generated method stub
	// List<User> userList = testDao.Login(user);
	// if(!userList.isEmpty()){
	// return true;
	// }
	// return false;
	// }
	@Override
	public boolean Register(User user) {
		// TODO Auto-generated method stub
		if (userDao.Valid(user.getUphone()) == 0) {
			System.out.println(userDao.save(user));
			return true;
		}
		return false;
	}

	@Override
	public boolean Login(String Uphone, String Upsw) {
		int userCount = userDao.Login(Uphone, Upsw);
		if (userCount > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int update(String Uphone, String Parameter, String value) {
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
			Fname = Uphone + "�ļ�ͥ";
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
		if (Fid != null) {
			creator = familyDao.getCreatorInFamily(Fid);
		}
		if (creator == null && Uphone != null) {
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
			family = familyDao.getCreatedFamily(Uphone);
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
	public Set<Kid> getKids(Long Kid, String Uphone, Long Fid) {
		if (Kid != null) {
			Set<Kid> set = new HashSet<>();
			Kid k = kidDao.getKid(Kid);
			if (k != null) {
				set.add(k);
				return set;
			}
		}

		if (Fid != null) {
			Set<Kid> set = kidDao.getKidsInFamily(Fid);
			if (set != null)
				return set;
		}

		if (Uphone != null) {
			Set<Kid> set = kidDao.getKidsInFamily(Uphone);
			if (set != null)
				return set;
		}

		return null;
	}

	@Override
	public int removeKid(Long Kid) {
		return kidDao.removeKid(Kid);
	}

	@Override
	public int addKid(String Kname, Long Kbirthday, String Kavatar, String Ksex, String Kask, Integer Kflowers,
			Long Fid, String Uphone) {
		Kid kid = new Kid();
		kid.setKname(Kname);
		kid.setKbirthday(Kbirthday);
		kid.setKavatar(Kavatar);
		kid.setKsex(Ksex);
		kid.setKask(Kask);
		kid.setKflowers(Kflowers);

		int code = ResultCode.ERROR_NO_FAMILY;
		if (Fid != null) {
			code = kidDao.addKid(Fid, kid);
		}
		if (code != ResultCode.SUCCESS && Uphone != null) {
			code = kidDao.addKid(Uphone, kid);
		}
		return code;
	}
}
