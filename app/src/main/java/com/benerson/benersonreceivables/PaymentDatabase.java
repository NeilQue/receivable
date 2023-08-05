package com.benerson.benersonreceivables;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Payment.class}, version = 1)
public abstract class PaymentDatabase extends RoomDatabase {
    public abstract PaymentDao paymentDao();
}
