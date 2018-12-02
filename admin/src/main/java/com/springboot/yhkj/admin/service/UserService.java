package com.springboot.yhkj.admin.service;

import com.springboot.yhkj.admin.model.User;
import com.springboot.yhkj.admin.dao.UserDao;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public User addUser(User user){
        return userDao.save(user);
    }

    public User findUserById(String id){
        return userDao.findUserById(id);
    }

    public Page<User> findUsers(Integer currentPage){
        if (currentPage == null){
            currentPage = 1;
        }
        Pageable pageable = new PageRequest(currentPage, 3, Sort.Direction.ASC, "id");
        return userDao.findAll(pageable);
    }

    public Page<User> findUsersByUserName(Integer currentPage, String userName){
        if (currentPage == null){
            currentPage = 1;
        }
        Pageable pageable = PageRequest.of(currentPage, 3, Sort.Direction.ASC, "id");
        return userDao.findAllByUserName(userName, pageable);
    }

    @Transactional
    public void modifyUserState(String id, String state) {
        User member = userDao.findUserById(id);
        if (member !=null){
            member.setState(state);
            userDao.save(member);
            return;
        }
        throw new RuntimeException("Member中不存在当前的id:"+id);
    }

    public void deleteUser(String userId){
        userDao.deleteById(userId);
    }

    @Transactional
    public void balanceRecharge(String id, String balance) {
        User member = userDao.findUserById(id);
        if (member != null){
            member.setBalance(member.getBalance()+Float.valueOf(balance));
            userDao.save(member);
            return;
        }
        throw new RuntimeException("Member中不存在当前的id:"+id);
    }

    @Transactional
    public User integralLottery(Integer allIntegral) {
        int count = userDao.queryAllCount();
        Random random = new Random();
        count = random.nextInt(count);
        Pageable pageable = PageRequest.of(count, 1);
        Page<User> page = userDao.findAll(pageable);
        PageImpl page1 = (PageImpl) page;
        User user = (User) page1.getContent().get(0);
        user.setUserIntegral(allIntegral + user.getUserIntegral());
        return userDao.save(user);
    }

    public List<String> findBirthdayToday() {
        List<String> email = new ArrayList<String>();
//        List<Member> members = memberDao.findByBirthday(LocalDate.now());
        List<User> members = userDao.findAll();
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();
        for (User m: members) {
            if (m.getLocalDate().getMonthValue() == month && m.getLocalDate().getDayOfMonth() == day)
                email.add(m.getEmail());
        }
        return email;
    }
}
