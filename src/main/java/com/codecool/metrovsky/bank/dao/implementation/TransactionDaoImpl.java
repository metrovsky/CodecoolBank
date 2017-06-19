package com.codecool.metrovsky.bank.dao.implementation;

import com.codecool.metrovsky.bank.dao.AccountDao;
import com.codecool.metrovsky.bank.dao.TransactionDao;
import com.codecool.metrovsky.bank.dao.TransactionStatusDao;
import com.codecool.metrovsky.bank.dao.TransactionTypeDao;
import com.codecool.metrovsky.bank.model.Account;
import com.codecool.metrovsky.bank.model.Transaction;
import com.codecool.metrovsky.bank.model.TransactionStatus;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    private Connection conn;
    private AccountDao accountDao;
    private TransactionTypeDao typeDao;
    private TransactionStatusDao statusDao;

    public TransactionDaoImpl(Connection conn) {
        this.conn = conn;
        this.accountDao = new AccountDaoImpl(conn);
        this.typeDao = new TransactionTypeDaoImpl(conn);
        this.statusDao = new TransactionStatusDaoImpl(conn);
    }

    @Override
    public void add(Transaction transaction) throws SQLException {
        String query = "INSERT INTO Transactions (DateOfTransaction, TransactionTypeID, Value, Description, " +
                "TransactionStatusID, SourceAccountID, DestinationAccountID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement dbStatement = conn.prepareStatement(query);

        dbStatement.setTimestamp(1, transaction.getTransactionDate());
        dbStatement.setInt(2, transaction.getType().getId());
        dbStatement.setObject(3, transaction.getValue());
        dbStatement.setString(4, transaction.getDescription());
        dbStatement.setInt(5, transaction.getStatus().getId());
        dbStatement.setInt(6, transaction.getSourceAccount().getId());
        dbStatement.setInt(7, transaction.getDestinationAccount().getId());

        dbStatement.execute();
        dbStatement.close();
    }

    @Override
    public void updateStatus(Transaction transaction, TransactionStatus status) throws SQLException {
        String query = "UPDATE Transactions SET TransactionStatusID = ? WHERE TransactionID = ?";
        PreparedStatement dbStatement = conn.prepareStatement(query);

        dbStatement.setInt(1, status.getId());
        dbStatement.setInt(2, transaction.getId());

        dbStatement.executeUpdate();
        dbStatement.close();
    }

    @Override
    public List<Transaction> findBy(Account account) throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();

        String query = "SELECT * FROM Transactions WHERE SourceAccountID = ? OR DestinationAccountID = ?";
        PreparedStatement dbStatement = conn.prepareStatement(query);

        dbStatement.setInt(1, account.getId());
        dbStatement.setInt(2, account.getId());

        ResultSet resultSet = dbStatement.executeQuery();
        while (resultSet.next()) {
            Transaction addedTransaction = new Transaction(
                    resultSet.getInt("TransactionID"),
                    resultSet.getTimestamp("DateOfTransaction"),
                    typeDao.find(resultSet.getInt("TransactionTypeID")),
                    BigInteger.valueOf(resultSet.getLong("Value")),
                    resultSet.getString("Description"),
                    statusDao.find(resultSet.getInt("TransactionStatusID")),
                    accountDao.find(resultSet.getInt("SourceAccountID")),
                    resultSet.getInt("SourceCardID"),
                    accountDao.find(resultSet.getInt("DestinationAccountID"))
            );
            transactionList.add(addedTransaction);
        }
        return transactionList;
    }
}
