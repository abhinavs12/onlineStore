package com.example.mansi.mansiabhinav_mapd711_onlinestore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.util.Log;
import android.database.Cursor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static com.example.mansi.mansiabhinav_mapd711_onlinestore.DatabaseManager.DATABASE_NAME;
import static org.junit.Assert.*;

public class DatabaseManagerTest {

    DatabaseManager db;
    Context appContext;

    //private final String DATABASE_TEST_NAME = DATABASE_NAME + "_test";
    private final String firstName = "TestFirstName";
    private final String lastName = "TestLastName";
    private final String email = "test@gmail.com";
    private final String password = "TestFirstName";
    private final String gender = "testGender";
    private final String dob = "TestDOB";
    private final String productName = "Test Product";
    private final String status = "Processing";
    private final String price = "25";
    private final Date now = Calendar.getInstance().getTime();

    String id;

    @Test
    public void destroyDB() throws Exception {
        appContext.deleteDatabase(DATABASE_NAME);
        appContext.openOrCreateDatabase(DATABASE_NAME, SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Cursor c = db.getAllData();
        assertEquals(0, c.getCount());
        c = db.getAllData_table1();
        assertEquals(0, c.getCount());
    }

    @Test
    public void insertCustomer() throws Exception {
        boolean isInserted =   db.insertCustomer(firstName,
            lastName,
            email,
            password,
            gender,
            dob);
        assertTrue(isInserted);
    }

    //relies on above insertion
    @Test
    public void getAllData() throws Exception {
        insertCustomer();

        Cursor userList = db.getAllData();

        assertNotEquals(0, userList.getCount());
        while (userList.moveToNext()){
            assertEquals(email, userList.getString(3));
            assertEquals(password, userList.getString(4));
        }
        //Log.d("test.getAllData", buffer.toString());
    }

    @Test
    public void insertData_table1() throws Exception {
        boolean inserted = db.insertData_table1(email, productName, status, now.toString(), price);
        assertTrue(inserted);


    }

    // relies on above insertion
    @Test
    public void getAllData_table1() throws Exception {
        insertData_table1();

        Cursor orderList = db.getAllData_table1();
        assertNotEquals(0, orderList.getCount());
        while ((orderList.moveToNext())){
            assertEquals(email, orderList.getString(1));
            assertEquals(productName, orderList.getString(2));
            assertEquals(status, orderList.getString(3));
            assertEquals(now.toString(), orderList.getString(4));
            assertEquals(price, orderList.getString(5));
        }
    }

    @Test
    public void getDataByUsername_table1() throws Exception {
        insertData_table1();
        Cursor ordersByUsername = db.getDataByUsername_table1(email);

        assertNotEquals(0, ordersByUsername.getCount());
        while(ordersByUsername.moveToNext()) {
            assertEquals(email, ordersByUsername.getString(1));
            id = ordersByUsername.getString(0);
        }}

    @Test
    public void getDataByID_table1() throws Exception {
        getDataByUsername_table1();
        assertNotNull(id);
    }

    @Test
    public void deleteOrderByID() throws Exception {
        getDataByID_table1();
        db.deleteOrderByID(id);

        Cursor order = db.getDataByID_table1(id);
        assertEquals(0, order.getCount());
    }

    private String update(String str) {
        return str + " updated";
    }

    @Test
    public void updateOrder() throws Exception {
        getDataByID_table1();

        String updatedEmail = update(email);
        String updatedName = update(productName);
        String updatedStatus = "Delivery";
        String updatedTime = Calendar.getInstance().getTime().toString();
        String updatedPrice = "30";
        db.updateOrder( id,
            updatedEmail,
            updatedName,
            updatedStatus,
            updatedTime,
            updatedPrice
        );

        Cursor order = db.getDataByID_table1(id);
        assertNotEquals(0, order.getCount());
        while (order.moveToNext()) {
            assertEquals(id, order.getString(0));
            assertEquals(updatedEmail, order.getString(1));
            assertEquals(updatedName, order.getString(2));
            assertEquals(updatedStatus, order.getString(3));
            assertEquals(updatedTime, order.getString(4));
            assertEquals(updatedPrice, order.getString(5));
        }
    }

    @Before
    public void setUp() throws Exception {
        appContext = InstrumentationRegistry.getTargetContext();
        db = new DatabaseManager(appContext);
        //Product.setDb(appContext);
    }

    @After
    public void tearDown() throws Exception {

    }
}