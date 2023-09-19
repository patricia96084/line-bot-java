package com.line.linebot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.line.linebot.mapper.LineUserMapper;
import com.line.linebot.model.LineUser;
import com.line.linebot.model.LineUserExample;


@Repository
public class UserDao implements Dao<LineUser, LineUserExample>{

	@Autowired
	private LineUserMapper lineUserMapper;
	
	public UserDao(LineUserMapper lineUserMapper) {
		this.lineUserMapper = lineUserMapper;
	}

	@Override
	public LineUser getByPrimaryKey(String id) {
		LineUser result = lineUserMapper.selectByPrimaryKey(id);
		return result;
	}

	@Override
	public List<LineUser> getAll() {
		List<LineUser> results = lineUserMapper.selectByExample(new LineUserExample());
		return results;
	}

	@Override
	public void insertInto(LineUser newT) {
		lineUserMapper.insert(newT);
	}

	@Override
	public void update(LineUser oldT, LineUserExample newTExample) {
		lineUserMapper.updateByExampleSelective(oldT, newTExample);
	}

	@Override
	public void delete(LineUserExample tExample) {
		lineUserMapper.deleteByExample(tExample);
	}
	
	public boolean isApprovedUser(String lineToken) {
		LineUserExample query = new LineUserExample();
		query.createCriteria().andUser_line_tokenEqualTo(lineToken).andUser_statusEqualTo("O");
		List<LineUser> queryResult = lineUserMapper.selectByExample(query);
		if (queryResult.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public LineUser getUser(String lineToken) {
		LineUserExample query = new LineUserExample();
		query.createCriteria().andUser_line_tokenEqualTo(lineToken);
		List<LineUser> queryResult = lineUserMapper.selectByExample(query);
		LineUser targetUser = queryResult.get(0);
		return targetUser;
	}
	
	public String getUserName(String lineToken) {
		return getUser(lineToken).getName();
	}
	
	public String getUserId(String lineToken) {
		return getUser(lineToken).getId();
	}
}
