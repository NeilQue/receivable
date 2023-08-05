package com.benerson.benersonreceivables;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Customer.class}, version = 1)
public abstract class CustomerDatabase extends RoomDatabase {
    public abstract CustomerDao customerDao();
}
