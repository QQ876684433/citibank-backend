package com.example.ffbackend.bl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.ffbackend.da.UserDaService;
import com.example.ffbackend.entity.User;
import com.example.ffbackend.exception.MyRuntimeException;
import com.example.ffbackend.vo.ResponseEnums;
import com.example.ffbackend.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import lombok.var;

@Service
public class UserService {
    @Autowired
    UserDaService userDaService;

    Map<String, Pair<Date, String>> emailCaptchaDict = new HashMap<>();
    Map<String, Pair<Date, String>> phoneCaptchaDict = new HashMap<>();
    Random rand = new Random();

    public boolean insertUser(UserVo vo) {
        var oriUser = userDaService.getUserByUsername(vo.getUsername());
        if (oriUser != null)
            throw new MyRuntimeException(ResponseEnums.REPEAT_REGISTER);
        vo.setFund(0.0);
        User user = vo.createPo();
        user.setId(null);
        userDaService.insertUser(user);
        return true;
    }

    public boolean deleteUser(String username) {
        userDaService.deleteUser(username);
        return true;
    }

    public boolean updateUser(UserVo vo) {
        User user = vo.createPo();
        if (user.getId() == null)
            return false;
        userDaService.updateUser(user);
        return true;
    }

    public void updateUserFund(Integer userId, Double dMoney) {
        var po = userDaService.getUserById(userId);
        if (po == null) throw new MyRuntimeException(ResponseEnums.METHOD_NOT_ALLOWED);
        po.setFund(po.getFund() + dMoney);
        userDaService.updateUser(po);
    }

    public List<UserVo> getAllUser() {
        var pos = userDaService.getAllUser();
        var res = new ArrayList<UserVo>(pos.size());
        for (var po : pos)
            res.add(po.createVo());
        return res;
    }

    public UserVo getUserByUsername(String username) {
        var po = userDaService.getUserByUsername(username);
        if (po != null)
            return po.createVo();
        return null;
    }

    public boolean insertEmailCaptcha(String email) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++)
            sb.append(rand.nextInt(10));
        emailCaptchaDict.put(email, Pair.of(new Date(), sb.toString()));
        // TODO: 发邮件
        return true;
    }

    public boolean insertPhoneCaptcha(String phone) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++)
            sb.append(rand.nextInt(10));
        phoneCaptchaDict.put(phone, Pair.of(new Date(), sb.toString()));
        // TODO: 发短信
        return true;
    }
}