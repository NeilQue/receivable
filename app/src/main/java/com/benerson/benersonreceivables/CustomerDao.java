package com.benerson.benersonreceivables;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CustomerDao {
    //create new customer
    @Query("INSERT INTO customers (name, invoice_number, total_amount, balance, purchase_date)" +
            "VALUES ('Name', '000000', '000000', '000000', strftime('%Y-%m-%d', '2020-01-01'))")
    void create();

    //get newest customer entry
    @Query("SELECT * FROM customers ORDER BY id DESC LIMIT 1")
    Customer getLast();

    //save details into customer
    @Query("UPDATE customers SET name = :name, invoice_number = :invoiceNumber, " +
            "total_amount = :amount, balance = :balance, " +
            "purchase_date = strftime('%Y-%m-%d', :purchaseDate) WHERE id = :id")
    void saveDetails(int id, String name, String invoiceNumber, int amount, int balance, String purchaseDate);

    //add new entry for old customer
    @Query("INSERT INTO customers (name, invoice_number, total_amount, balance, purchase_date) " +
            "VALUES (:name, '000000', '000000', '000000', strftime('%Y-%m-%d', '2020-01-01'))")
    void addNewEntry(String name);

    //get all customer names
    @Query("SELECT * FROM (SELECT DISTINCT name FROM customers) ORDER BY name ASC")
    List<String> getAllNames();

    //get all customer details
    @Query("SELECT * FROM customers WHERE name = :name")
    List<Customer> getCustomerDetails(String name);

    //get total unpaid per customer
    @Query("SELECT SUM(balance) FROM customers WHERE name = :name")
    int totalCustomerUnpaid(String name);

    //get total debt per customer
    @Query("SELECT SUM(total_amount) FROM customers WHERE name = :name")
    int totalCustomerAmount(String name);

    //get total unpaid
    @Query("SELECT SUM(balance) FROM customers")
    int totalUnpaid();

    //get details of all unpaid
    @Query("SELECT * FROM customers WHERE balance > 0")
    List<Customer> getAllUnpaid();

    //get balance for invoice number
    @Query("SELECT balance FROM customers WHERE invoice_number = :invoiceNumber")
    int getBalance(String invoiceNumber);

    //update balance after payment
    @Query("UPDATE customers SET balance = total_amount - :totalPaid " +
            "WHERE invoice_number = :invoiceNumber")
    void updateBalance(int totalPaid, String invoiceNumber);
}
