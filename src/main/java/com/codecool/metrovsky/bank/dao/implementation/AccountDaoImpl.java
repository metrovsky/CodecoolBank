package com.codecool.metrovsky.bank.dao.implementation;

import com.codecool.metrovsky.bank.dao.AccountDao;
import com.codecool.metrovsky.bank.dao.AccountTypeDao;
import com.codecool.metrovsky.bank.dao.CustomerDao;
import com.codecool.metrovsky.bank.model.Account;
import com.codecool.metrovsky.bank.model.Customer;
import com.codecool.metrovsky.bank.model.Status;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private final Connection conn;
    private final AccountTypeDao accountTypeDao;
    private final CustomerDao customerDao;

    public AccountDaoImpl(Connection conn) {
        this.conn = conn;
        this.accountTypeDao = new AccountTypeDaoImpl(conn);
        this.customerDao = new CustomerDaoImpl(conn);
    }

    @Override
    public void add(Account account) throws SQLException {
        String query = "INSERT INTO Accounts (CustomerID, Number, AccountTypeID, AccountStatusID, " +
                "OpenDate, Balance, DebitLine, Interest) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement dbStatement = conn.prepareStatement(query);
        dbStatement.setInt(1, account.getCustomer().getId());
        dbStatement.setString(2, account.getNumber());
        dbStatement.setInt(3, account.getType().getId());
        dbStatement.setInt(4, account.getStatus());
        dbStatement.setTimestamp(5, account.getOpenDate());
        dbStatement.setObject(6, account.getBalance());
        dbStatement.setObject(7, account.getDebitLine());
        dbStatement.setInt(8, account.getInterest());

        dbStatement.execute();
        dbStatement.close();
    }

    @Override
    public Account find(Integer ID) throws SQLException {
        String query = "SELECT * FROM Accounts WHERE AccountID = ?";
        Account foundAccount = null;

        PreparedStatement dbStatement = this.conn.prepareStatement(query);
        dbStatement.setInt(1, ID);

        ResultSet resultSet = dbStatement.executeQuery();
        if (resultSet.next()) {
            foundAccount = new Account(
                    resultSet.getInt("AccountID"),
                    this.customerDao.find(resultSet.getInt("CustomerID")),
                    resultSet.getString("Number"),
                    resultSet.getTimestamp("OpenDate"),
                    resultSet.getInt("AccountStatusID"),
                    this.accountTypeDao.find(resultSet.getInt("AccountTypeID")),
                    BigInteger.valueOf(resultSet.getLong("Balance")),
                    BigInteger.valueOf(resultSet.getLong("DebitLine")),
                    resultSet.getInt("Interest")
            );
        }

        dbStatement.close();
        return foundAccount;
    }

    @Override
    public List<Account> findBy(Customer customer) throws SQLException {
        String query = "SELECT * FROM Accounts WHERE CustomerID = ?";
        List<Account> foundAccounts = new ArrayList<>();

        PreparedStatement dbStatement = conn.prepareStatement(query);
        dbStatement.setInt(1, customer.getId());

        ResultSet resultSet = dbStatement.executeQuery();
        while (resultSet.next()) {
            Account addedAccount = new Account(
                    resultSet.getInt("AccountID"),
                    customerDao.find(resultSet.getInt("CustomerID")),
                    resultSet.getString("Number"),
                    resultSet.getTimestamp("OpenDate"),
                    resultSet.getInt("AccountStatusID"),
                    accountTypeDao.find(resultSet.getInt("AccountTypeID")),
                    BigInteger.valueOf(resultSet.getLong("Balance")),
                    BigInteger.valueOf(resultSet.getLong("DebitLine")),
                    resultSet.getInt("Interest")
            );

            foundAccounts.add(addedAccount);
        }

        dbStatement.close();
        return foundAccounts;
    }

    @Override
    public List<String> getAllNumbers() throws SQLException {
        String query = "SELECT Number FROM Accounts";
        PreparedStatement dbStatement = conn.prepareStatement(query);
        ResultSet resultSet = dbStatement.executeQuery();

        List<String> numberList = new ArrayList<>();
        while (resultSet.next()) {
            numberList.add(resultSet.getString("Number"));
        }

        dbStatement.close();
        return numberList;
    }

    @Override
    public void block(Account account) throws SQLException {
        String query = "UPDATE Accounts SET AccountStatusID = ? WHERE AccountID = ?";
        PreparedStatement dbStatement = conn.prepareStatement(query);

        dbStatement.setBoolean(1, Status.CLOSED.getValue());
        dbStatement.setInt(2, account.getId());

        dbStatement.executeUpdate();
        dbStatement.close();
    }

    @Override
    public void unblock(Account account) throws SQLException {
        String query = "UPDATE Accounts SET AccountStatusID = ? WHERE AccountID = ?";
        PreparedStatement dbStatement = conn.prepareStatement(query);

        dbStatement.setBoolean(1, Status.OPEN.getValue());
        dbStatement.setInt(2, account.getId());

        dbStatement.executeUpdate();
        dbStatement.close();
    }


    @Override
    public void deposit(Account account, Long amount) throws SQLException {
        String query = "UPDATE Accounts SET Balance = ? WHERE AccountID = ?";
        PreparedStatement dbStatement = conn.prepareStatement(query);

        BigInteger updatedBalance = account.getBalance().add(BigInteger.valueOf(amount));
        account.setBalance(updatedBalance);

        dbStatement.setObject(1, updatedBalance);
        dbStatement.setInt(2, account.getId());

        dbStatement.executeUpdate();
        dbStatement.close();
    }

    @Override
    public void withdraw(Account account, Long amount) throws SQLException {
        String query = "UPDATE Accounts SET Balance = ? WHERE AccountID = ?";
        PreparedStatement dbStatement = conn.prepareStatement(query);

        BigInteger updatedBalance = account.getBalance().subtract(BigInteger.valueOf(amount));
        account.setBalance(updatedBalance);

        dbStatement.setObject(1, updatedBalance);
        dbStatement.setInt(2, account.getId());

        dbStatement.executeUpdate();
        dbStatement.close();
    }
}
