package com.zjKingdee.androidServer.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjKingdee.androidServer.dao.primary.LoginMapper;
import com.zjKingdee.androidServer.dao.primary.UserInfoMapper;
import com.zjKingdee.androidServer.entity.UseInfoEntity;
import com.zjKingdee.androidServer.utils.EncryptionAlgorithmUtil;

@Service
public class PDALoginInService {
	@Autowired
	private LoginMapper mapper;
	@Autowired
	private UserInfoMapper userMapper;
	/**
	 * 获取登录信息
	 * @param number
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	public UseInfoEntity getUserInfo(String number,String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(number);
		return mapper.login(URLDecoder.decode(number,"utf-8"), EncryptionAlgorithmUtil.encryptionAlgorithm(password));
	}
	/**
	 * 校验登录信息
	 * @param staffID
	 * @param staffNumber
	 * @return
	 */
	public boolean checkStaffInfo(String staffID,String staffNumber) {
		String number=mapper.selectForStaffNumber(staffID);
		if(number==null||!number.equals(staffNumber)) {
			return false;
		}
		return true;
	}
	/**
	 * 修改账号密码
	 * @throws NoSuchAlgorithmException 
	 */
	public void revisePassord(String id,String password) throws NoSuchAlgorithmException {
		UseInfoEntity entity=new UseInfoEntity();
		entity.setUserId(id);
		entity.setUserPassword(EncryptionAlgorithmUtil.encryptionAlgorithm(password));
		userMapper.reviseUseInfo(entity);
	}
	/**
	 * 查询权限项
	 * @throws NoSuchAlgorithmException 
	 */
	public String search(String roleName) throws NoSuchAlgorithmException {
		return mapper.searchPermission(roleName);
	}
}
