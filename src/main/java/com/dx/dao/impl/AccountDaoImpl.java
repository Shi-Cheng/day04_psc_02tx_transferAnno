package com.dx.dao.impl;

import com.dx.dao.IAccountDao;
import com.dx.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Account> findAllAccount() {
       List<Account> accounts = jdbcTemplate.query("select * from user",new BeanPropertyRowMapper<Account>(Account.class));
       for (Account account:accounts){
           System.out.println(account);
       }
       return accounts;
    }

    public Account findAccountById(Integer accountId) {
        List<Account> accounts =jdbcTemplate.query("select * from user where id=?",new BeanPropertyRowMapper<Account>(Account.class),accountId);
        return accounts.isEmpty()?null:accounts.get(0);
    }

    public void saveAccount(Account account) {
        jdbcTemplate.update("insert into user(name,money) values(?,?)",account.getName(),account.getMoney());
    }

    public void updateAccount(Account account) {
        jdbcTemplate.update("update user set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
    }

    public void deleteAccount(Integer accountId) {
        jdbcTemplate.update("delete from user where id=?",accountId);
    }

    public Account findAccountByName(String accountName) {
        try{
            List<Account> accounts = jdbcTemplate.query("select * from user where name=?",new BeanPropertyRowMapper<Account>(Account.class),accountName);
            if (accounts == null || accounts.size() ==0 ){
                return null;
            }
            if (accounts.size() > 1){
                throw new RuntimeException("结果不唯一，数据有问题");
            }
            return accounts.get(0);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
