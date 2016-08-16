package com.bearkiddiary.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.Height;
import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Leave_Application;
import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.bean.Vision;
import com.bearkiddiary.bean.Weight;
import com.bearkiddiary.dao.FamilyDao;
import com.bearkiddiary.dao.HeightDao;
import com.bearkiddiary.dao.KidDao;
import com.bearkiddiary.dao.LADao;
import com.bearkiddiary.dao.OrgDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.dao.VisionDao;
import com.bearkiddiary.dao.WeightDao;
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

	private HeightDao heightDao;

	public void setHeightDao(HeightDao heightDao) {
		this.heightDao = heightDao;
	}

	private WeightDao weightDao;

	public void setWeightDao(WeightDao weightDao) {
		this.weightDao = weightDao;
	}

	private VisionDao visionDao;

	public void setVisionDao(VisionDao visionDao) {
		this.visionDao = visionDao;
	}

	private OrgDao orgDao;

	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}
	
	private LADao laDao;
	

	public void setLaDao(LADao laDao) {
		this.laDao = laDao;
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

	/**
	 * 提交请假申请 , 成功返回LAid
	 */
	public Long commitApplication(Leave_Application application, Long Oid, String Uphone){
		Organization org = orgDao.getOrg(Oid);
		if(org == null){
			return (long) ResultCode.ERROR_NO_ORG;
		}
		User user = userDao.getUser(Uphone);
		if(user == null){
			return (long) ResultCode.ERROR_NO_USER;
		}
		
		application.setLAorg(org);
		application.setLAapplicant(user);
		return laDao.commitApplicaton(application);
	}
	
	@Override
	public Long updateApplication(Integer LAstatus, String LAcomment, Long LAid) {
		Long result = laDao.updateApplication(LAstatus, LAcomment, LAid);
		if(result > 0){
			return result;
		}
		return (long) ResultCode.ERROR;
	}

	@Override
	public List<Leave_Application> getOrgApplicationList(Long Oid) {
		return laDao.getOrgApplicationList(Oid);
	}

	@Override
	public List<Leave_Application> getUserApplicationList(Long Uid) {
		return laDao.getUserApplicationList(Uid);
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

	@Override
	public int addOrgMember(long Oid, long Uid, int identity) {
		int resultCode = -1;
		switch (identity) {
		case 0:
			resultCode = orgDao.addOrgTeacher(Oid, Uid);
			break;
		case 1:
			resultCode = orgDao.addOrgParent(Oid, Uid);
			break;
		default:
			break;
		}
		return resultCode;
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

	@Override
	public int updateKid(Long Kid, String Kname, Long Kbirthday, String Kavatar, String Ksex, String Kask,
			Integer Kflowers) {
		Kid data = new Kid();
		data.setKname(Kname);
		data.setKbirthday(Kbirthday);
		data.setKavatar(Kavatar);
		data.setKsex(Ksex);
		data.setKask(Kask);
		data.setKflowers(Kflowers);
		return kidDao.updateKid(Kid, data);
	}

	

	@Override
	public int addKidBodyMsg(Long Kid, Float Hheight, Float Wweight, Float Vleft, Float Vright, Long time) {
		if (Hheight != null) {
			return heightDao.addHeightToKid(Kid, Hheight, time);
		}
		if (Wweight != null) {
			return weightDao.addWeightToKid(Kid, Wweight, time);
		}
		if (Vleft != null || Vright != null) {
			return visionDao.addVision(Kid, Vleft, Vright, time);
		}
		return ResultCode.ERROR_MISSING_PARAMETER;
	}

	@Override
	public List<Height> getHeight(Long Kid, String order) {
		return heightDao.getHeight(Kid, order);
	}

	@Override
	public List<Height> getHeight(Long Kid, String order, int pageNum, int pageSize) {
		return heightDao.getHeight(Kid, order, pageSize, pageNum);
	}

	@Override
	public List<Weight> getWeight(Long Kid, String order) {
		return weightDao.getWeight(Kid, order);
	}

	@Override
	public List<Weight> getWeight(Long Kid, String order, int pageNum, int pageSize) {
		return weightDao.getWeight(Kid, order, pageNum, pageSize);
	}

	@Override
	public List<Vision> getVision(Long Kid, String order) {
		return visionDao.getVision(Kid, order);
	}

	@Override
	public List<Vision> getVision(Long Kid, String order, int pageNum, int pageSize) {
		return visionDao.getVision(Kid, order, pageNum, pageSize);
	}
}
