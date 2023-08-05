package com.benerson.benersonreceivables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customers")
public class Customer {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "invoice_number")
    public String invoiceNumber;

    @ColumnInfo(name = "total_amount")
    public int amount;

    @ColumnInfo(name = "balance")
    public int balance;

    @ColumnInfo(name = "purchase_date")
    public String purchaseDate;
}
