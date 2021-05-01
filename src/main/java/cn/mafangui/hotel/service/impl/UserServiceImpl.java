package cn.mafangui.hotel.service.impl;

import cn.mafangui.hotel.entity.User;
import cn.mafangui.hotel.mapper.UserMapper;
import cn.mafangui.hotel.service.UserService;
//import cn.mafangui.hotel.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> getUserByPhone(String phone) {
        return userMapper.getUserByPhoneMapper(phone);
    }

    @Override
    public int addUser(User user) {
//        user.setPassword(MD5Utils.MD5Encode(user.getPassword())); 加密
        return userMapper.insertSelective(user);
    }

    @Override
    public String getPassword(User user) {
        return userMapper.getPasswordMapper(user);
    }

    @Override
    public int insertUser(User user) {
//        user.setPassword(MD5Utils.MD5Encode(user.getPassword())); 加密
     //   user.setPassword(user.getPassword());
        return userMapper.insert(user);
    }

    @Override
    public int deleteUser(int userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int batchDeleteUser(String[] s) {
        return userMapper.batchDeleteByPrimaryKey(s);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer getUserCount() {
        return userMapper.getUserCount();
    }

    @Override
    public User selectByUsernameAndPassword(String username, String password) {
//        String pass = MD5Utils.MD5Encode(password);
        String pass = password;
        return userMapper.selectByUsernameAndPassword(username,pass);
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }
}
