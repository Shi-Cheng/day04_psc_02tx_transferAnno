package com.dx.dao;

import com.dx.domain.Account;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();
    /**
     *按id查询 查询一个
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存用户
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     * @param accountId
     */
    void deleteAccount(Integer accountId);

    /**
     *按accountName查询 查询一个
     */
    Account findAccountByName(String accountName);
}
