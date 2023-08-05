package com.benerson.benersonreceivables;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PaymentDao {
    //get all payments for invoice number
    @Query("SELECT * FROM payments WHERE invoiceNumber = :invoiceNumber")
    List<Payment> getAllPayments(String invoiceNumber);

    //add new payment
    @Query("INSERT INTO payments (invoiceNumber, amount, paymentDate) VALUES " +
            "(:invoiceNumber, '000000', strftime('%Y-%m-%d', '2020-01-01'))")
    void addNewPayment(String invoiceNumber);

    //get payment totals
    @Query("SELECT SUM(amount) FROM payments WHERE invoiceNumber = :invoiceNumber")
    int totalAmount(String invoiceNumber);

    //get last payment
    @Query("SELECT * FROM payments ORDER BY id DESC LIMIT 1")
    Payment getLastPayment();

    //save payment details
    @Query("UPDATE payments SET invoiceNumber = :invoice, amount = :amount, " +
            "paymentDate = strftime('%Y-%m-%d', :purchaseDate) WHERE id = :id")
    void saveDetails(String invoice, int amount, String purchaseDate, int id);
}
