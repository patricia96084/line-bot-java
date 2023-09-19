package com.line.linebot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.line.linebot.mapper.UserAuthMapper;
import com.line.linebot.model.UserAuth;
import com.line.linebot.model.UserAuthExample;

@Repository
public class UserAuthDao implements Dao<UserAuth, UserAuthExample> {

	@Autowired
	private UserAuthMapper userAuthMapper;

	public UserAuthDao(UserAuthMapper userAuthMapper) {
		this.userAuthMapper = userAuthMapper;
	}

	@Override
	public UserAuth getByPrimaryKey(String id) {
		return null;
	}

	@Override
	public List<UserAuth> getAll() {
		List<UserAuth> results = userAuthMapper.selectByExample(new UserAuthExample());
		return results;
	}

	@Override
	public void insertInto(UserAuth newT) {
		userAuthMapper.insert(newT);
	}

	@Override
	public void update(UserAuth oldT, UserAuthExample newTExample) {
		userAuthMapper.updateByExampleSelective(oldT, newTExample);
	}

	@Override
	public void delete(UserAuthExample tExample) {
		userAuthMapper.deleteByExample(tExample);
	}

	public List<String> getAccessibleSys(String userId) {
		UserAuthExample queryForAuth = new UserAuthExample();
		queryForAuth.createCriteria().andUser_idEqualTo(userId);
		List<UserAuth> authQueryResult = userAuthMapper.selectByExample(queryForAuth);
		List<String> systemNameList = authQueryResult.stream().map((system) -> {
			return system.getSystem_name();
		}).toList();
		return systemNameList;
	}

	public boolean canAccess(String userId, String systemName) {
		UserAuthExample query = new UserAuthExample();
		query.createCriteria().andUser_idEqualTo(userId).andSystem_nameEqualTo(systemName);
		List<UserAuth> queryResult = userAuthMapper.selectByExample(query);
		return queryResult.size() > 0;
	}

}
