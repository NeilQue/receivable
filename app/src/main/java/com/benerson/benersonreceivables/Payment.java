package com.benerson.benersonreceivables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "payments")
public class Payment {
    @PrimaryKey
    public int id;

    @ColumnInfo
    public String invoiceNumber;

    @ColumnInfo
    public int amount;

    @ColumnInfo
    public String paymentDate;
}
