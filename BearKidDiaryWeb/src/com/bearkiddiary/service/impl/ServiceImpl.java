package com.bearkiddiary.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bearkiddiary.bean.Course;
import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.Group;
import com.bearkiddiary.bean.Height;
import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Leave_Application;
import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.TimeLine;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.bean.Vision;
import com.bearkiddiary.bean.Weight;
import com.bearkiddiary.dao.CourseDao;
import com.bearkiddiary.dao.FamilyDao;
import com.bearkiddiary.dao.GroupDao;
import com.bearkiddiary.dao.HeightDao;
import com.bearkiddiary.dao.KidDao;
import com.bearkiddiary.dao.LADao;
import com.bearkiddiary.dao.OrgDao;
import com.bearkiddiary.dao.TimeLineDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.dao.VisionDao;
import com.bearkiddiary.dao.WeightDao;
import com.bearkiddiary.service.Service;
import com.bearkiddiary.utils.ResultCode;
import com.sun.org.apache.xpath.internal.operations.Or;

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

	private TimeLineDao timeLineDao;

	public void setTimeLineDao(TimeLineDao timeLineDao) {
		this.timeLineDao = timeLineDao;
	}

	private OrgDao orgDao;

	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	private LADao laDao;

	public void setLaDao(LADao laDao) {
		this.laDao = laDao;
	}

	private CourseDao courseDao;

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public boolean Register(User user) {
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
	public int updateUserInfo(String Uphone, User user) {
		int result = userDao.updateUser(Uphone, user);
		return result;
	}

	public Long commitApplication(Leave_Application application, Long Oid, String Uphone) {
		Organization org = orgDao.getOrg(Oid);
		if (org == null) {
			return (long) ResultCode.ERROR_NO_ORG;
		}
		User user = userDao.getUser(Uphone);
		if (user == null) {
			return (long) ResultCode.ERROR_NO_USER;
		}

		application.setLAorg(org);
		application.setLAapplicant(user);
		return laDao.commitApplicaton(application);
	}

	@Override
	public int updateApplication(Integer LAstatus, Integer LAisapproved, String Uphone, String LAcomment, Long LAid) {
		User LArover = userDao.getUser(Uphone);
		if (LArover != null) {
			int result = laDao.updateApplication(LAstatus, LAisapproved, LArover, LAcomment, LAid);
			if (result > 0) {
				return result;
			}
		}
		return ResultCode.ERROR;
	}

	@Override
	public List<Leave_Application> getOrgApplicationList(Long Oid, int LAstatus) {
		return laDao.getOrgApplicationList(Oid, LAstatus);
	}

	@Override
	public List<Leave_Application> getUserApplicationList(Long Uid) {
		return laDao.getUserApplicationList(Uid);
	}

	@Override
	public List<Leave_Application> getUserApplicationList(String Uphone) {
		return laDao.getUserApplicationList(Uphone);
	}

	@Override
	public int validAdmin(Long Oid, String Uphone) {
		int resultCode = orgDao.validAdmin(Uphone, Oid);
		return resultCode;
	}

	public User getUserInfo(String Uphone){
		User user = userDao.getUser(Uphone);
		if(user == null)
			return null;
		return user;
	}
	
	public Map<String, List<Organization>> getUserOrganizations(String Uphone){
		User user = userDao.getUser(Uphone);
		Map<String, List<Organization>> map = new HashMap<>();
		List<Organization> list_creator = orgDao.getUserCreateOrg(user);
		map.put("creator", list_creator);
		List<Organization> list_teacher = orgDao.getUserInOrgs(user);
		map.put("teacher", list_teacher);
		return map;
	}
	
	public List<Kid> getAllHisStudents(String Uphone){
		List<Course> list_course;
		list_course = new ArrayList<>(courseDao.getCourseByTeacher(null, Uphone));
		Set<Kid> kids = new HashSet<>();
		Set<Kid> kid;
		for(Course course : list_course){
			kid = new HashSet<>();
			kid = course.getStudents();
			kids.addAll(kid);
		}
		return new ArrayList<>(kids);
	}
	
	public Boolean addContact(String Uphone, String ContactPhone){
		User user = userDao.getUser(Uphone);
		User contactUser = userDao.getUser(ContactPhone);
		Set<User> contacts = user.getContacts();
		if(contacts.contains(contactUser)){
			return false;
		}
		contacts.add(contactUser);
		user.setContacts(contacts);
		userDao.update(user);
		return true;
	}
	
	public List<User> getContacts(String Uphone){
		User user = userDao.getUser(Uphone);
		Set<User> contacts = user.getContacts();
		return new ArrayList<>(contacts);
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
	public long createOrg(String Oname, String Oaddress, String Oannounce, String Uphone, String Oavatar, Long Otime) {
		long Oid = orgDao.createOrg(Oname, Oaddress, Oannounce, Uphone, Oavatar, Otime);
		return Oid;
	}

	/**
	 * 机构操作
	 */
	@Override
	public int deleteOrg(String Uphone) {
		return orgDao.deleteOrg(Uphone);
	}

	@Override
	public int updateOrg(String Uphone, String Parameter, String value) {
		int result = 0;
		switch (Parameter) {
		case Organization.ONAME:
			result = orgDao.updateOname(Uphone, value);
			break;
		case Organization.OADDRESS:
			result = orgDao.updateOaddress(Uphone, value);
			break;
		case Organization.OANNOUNCE:
			result = orgDao.updateOannounce(Uphone, value);
			break;
		case Organization.OAVATAR:
			result = orgDao.updateOavatar(Uphone, value);
			break;
		}
		return result;
	}

	@Override
	public int addOrgMember(long Oid, String Uphone, int identity) {
		int resultCode = -1;
		switch (identity) {
		case 0:
			resultCode = orgDao.addOrgTeacher(Oid, Uphone);
			break;
		case 1:
			resultCode = orgDao.addOrgParent(Oid, Uphone);
			break;
		default:
			break;
		}
		return resultCode;
	}

	public List<Organization> getAllOrgs(){
		return orgDao.getAllOrgs();
	}
	
	
	public List<User> getOrgTeachers(Long Oid, String Uphone){
		if(Oid != null){
			Organization org = orgDao.getOrg(Oid);
			List<User> list = new ArrayList<>(org.getTeachers());
			return list;
		}
		if(Uphone != null){
			Organization org = orgDao.getOrg(Uphone);
			List<User> list = new ArrayList<>(org.getTeachers());
			return list;
		}
		return null;
	}
	
	public List<User> getOrgParents(Long Oid, String Uphone){
		if(Oid != null){
			Organization org = orgDao.getOrg(Oid);
			List<User> list = new ArrayList<>(org.getParents());
			return list;
		}
		if(Uphone != null){
			Organization org = orgDao.getOrg(Uphone);
			List<User> list = new ArrayList<>(org.getParents());
			return list;
		}
		return null;
	}
	
	public List<Kid> getOrgStudents(Long Oid, String Uphone){
		List<Course> list_course = new ArrayList<>(courseDao.getCourseByOrg(Oid));
		Set<Kid> list_student = new HashSet<>();
		Set<Kid> kid_set = null;
		for(Course course : list_course){
			kid_set = new HashSet<>();
			kid_set = course.getStudents();
			list_student.addAll(kid_set);
		}
		return new ArrayList<>(list_student);
	}
	
	@Override
	public Set<Kid> getKids(Long Kid, String Uphone, Long Fid, Long Cid) {
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

		if (Cid != null) {
			Set<Kid> set = courseDao.getKidsInCourse(Cid);
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

	@Override
	public int addTimeLine(String content, Long time, String image1, String image2, String image3, String type,
			int logoType, Long Uid, String Uphone, Long Kid) {
		TimeLine timeLine = new TimeLine();
		timeLine.setTreleasecontent(content);
		timeLine.setTreleasetime(time);
		timeLine.setTimage1(image1);
		timeLine.setTimage2(image2);
		timeLine.setTimage3(image3);
		timeLine.setTtype(type);
		timeLine.setTtypelogo(logoType);
		if (Uid != null) {
			return timeLineDao.addTimeLine(timeLine, Uid, Kid);
		} else if (Uphone != null) {
			return timeLineDao.addTimeLine(timeLine, Uphone, Kid);
		} else {
			return ResultCode.ERROR_MISSING_PARAMETER;
		}
	}

	@Override
	public List<TimeLine> getTimeLine(Long Kid, Long Uid, String Uphone, String Order) {
		if (Uid != null || Uphone != null) {
			return timeLineDao.getTimeLine(Kid, Uid, Uphone, Order);
		} else {
			return timeLineDao.getTimeLine(Kid, Order);
		}
	}

	@Override
	public List<TimeLine> getTimeLine(Long Kid, Long Uid, String Uphone, String Order, int pageSize, int pageNum) {
		if (Uid != null || Uphone != null) {
			return timeLineDao.getTimeLine(Kid, Uid, Uphone, Order, pageSize, pageNum);
		} else {
			return timeLineDao.getTimeLine(Kid, Order, pageSize, pageNum);
		}
	}

	@Override
	public Set<Course> getCourse(Long Cid, Long Uid, String Uphone, Long Oid, Long Kid) {
		if (Cid != null) {
			Set<Course> set = new HashSet<>();
			Course course = courseDao.getCourse(Cid);
			if (course != null) {
				set.add(course);
				return set;
			}
		}
		if (Uid != null || Uphone != null) {
			return courseDao.getCourseByTeacher(Uid, Uphone);
		}
		if (Oid != null) {
			return courseDao.getCourseByOrg(Oid);
		}
		if (Kid != null) {
			return courseDao.getCourseByKid(Kid);
		}
		return null;
	}

	@Override
	public int addKidToCourse(Long Cid, Long Kid) {
		return courseDao.addKidToCourse(Cid, Kid);
	}

	@Override
	public int removeKidFromCourse(Long Cid, Long Kid) {
		return courseDao.removeKidFromCourse(Cid, Kid);
	}

	@Override
	public int updateCourse(Long Cid, Long Cclasstime, Long Cendtime, Long Ctime, Long Cofftime, String Cbackground,
			String Cdesc, String Cname, String Cimage, Boolean Cmonday, Boolean Ctuesday, Boolean Cwednesday,
			Boolean Cthursday, Boolean Cfriday, Boolean Csaturday, Boolean Csunday) {
		return courseDao.updateCourse(Cid, Cclasstime, Cendtime, Ctime, Cofftime, Cbackground, Cdesc, Cname, Cimage,
				Cmonday, Ctuesday, Cwednesday, Cthursday, Cfriday, Csaturday, Csunday);
	}

	@Override
	public int addCourse(Long Cclasstime, Long Cendtime, Long Ctime, Long Cofftime, String Cbackground, String Cdesc,
			String Cname, String Cimage, String Caddress, Boolean Cmonday, Boolean Ctuesday, Boolean Cwednesday, Boolean Cthursday,
			Boolean Cfriday, Boolean Csaturday, Boolean Csunday, Long teacherUid, String teacherUphone,
			Long Oid) {
		Course course = new Course();
		course.setCclasstime(Cclasstime);
		course.setCendtime(Cendtime);
		course.setCtime(Ctime);
		course.setCofftime(Cofftime);
		course.setCbackground(Cbackground);
		course.setCdesc(Cdesc);
		course.setCname(Cname);
		course.setCimage(Cimage);
		course.setCaddress(Caddress);
		course.setCmonday(Cmonday);
		course.setCtuesday(Ctuesday);
		course.setCwednesday(Cwednesday);
		course.setCthursday(Cthursday);
		course.setCfriday(Cfriday);
		course.setCsaturday(Csaturday);
		course.setCsunday(Csunday);
		return courseDao.addCourse(course, teacherUid, teacherUphone, Oid);
	}

	public Course getCourse(Long Cid){
		Course course = courseDao.getCourse(Cid);
		return course;
	}
	
	public List<Kid> getKidsInCourse(Long Cid){
		Course course = courseDao.getCourse(Cid);
		List<Kid> list;
		if(course != null){
			list = new ArrayList<>(course.getStudents());
			return list;
		}
		return null;
	}
	
	@Override
	public int createGroup(Long Oid, String Gname, List<Long> Uids, List<String> Uphones) {
		if (Uids != null)
			return groupDao.createGroupWithId(Oid, Gname, Uids);
		if (Uphones != null)
			return groupDao.createGroupWithPhone(Oid, Gname, Uphones);
		return groupDao.createGroup(Oid, Gname);
	}

	@Override
	public int addGroupMembers(Long Gid, List<Long> Uids, List<String> Uphones) {
		if (Uids != null)
			return groupDao.addMembersById(Gid, Uids);
		else
			return groupDao.addMembersByPhone(Gid, Uphones);
	}

	@Override
	public int deleteGroupMembers(Long Gid, List<Long> Uids, List<String> Uphones) {
		if (Uids != null)
			return groupDao.deleteMembersById(Gid, Uids);
		return groupDao.deleteMembersByPhone(Gid, Uphones);
	}

	@Override
	public int deleteGroup(Long Gid) {
		return groupDao.deleteGroup(Gid);
	}

	@Override
	public Set<Group> getGroup(Long Gid, Long Oid) {
		if (Gid != null) {
			Group g = groupDao.getGroup(Gid);
			if (g != null) {
				Set<Group> set = new HashSet<>();
				set.add(g);
				return set;
			}
		}
		return groupDao.getGroups(Oid);
	}
}
