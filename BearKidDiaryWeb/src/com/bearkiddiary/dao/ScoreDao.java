package com.bearkiddiary.dao;

import java.util.List;

import com.bearkiddiary.bean.Score;
import com.bearkiddiary.common.dao.BaseDao;

public interface ScoreDao extends BaseDao<Score> {
	int addScore(String Scomment, Integer Sstars, Integer Sscore, String Stheme, Long Stime, Long Kid, Long Cid);

	List<Score> getScore(Long Cid, Long kid);
}
