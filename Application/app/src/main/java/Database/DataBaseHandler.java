package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Beam.Users;

/**
 * Created by karthikeyan on 08-Apr-16.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "UserManager";
    private static final String TABLE_USER = "userdetails";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TOTALAMOUNT = "totalAmount";
    private static final String KEY_BALANCEAMOUNT = "balanceAmount";

    private static final String CREATE_TABLE_USER = "CREATE TABLE "+ TABLE_USER + "("+ KEY_NAME + " TEXT,"+ KEY_TOTALAMOUNT + " NUMBER, "+KEY_BALANCEAMOUNT + " NUMBER );";



    public DataBaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("create", "create");
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_USER);
        onCreate(db);

    }
    public void adduser(Users userobj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, userobj.getName());
        values.put(KEY_TOTALAMOUNT,userobj.getTotalAmount());
        values.put(KEY_BALANCEAMOUNT,userobj.getBalanceAmount());
        db.insert(TABLE_USER, null, values);
        db.close();

    }
    public Users getValues(String uname){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from userdetails where name ='"+uname.trim()+"'";
        Cursor cus = db.rawQuery(sql, null);
        if (cus != null) {
            cus.moveToFirst();
            Users user = new Users(cus.getString(0), cus.getInt(1), cus.getInt(2));
            return user;
        }
        return null;
    }
    public Users updateValues(String uName ,int balAmount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE userdetails SET balanceAmount = '" + balAmount + "' WHERE name ='" + uName + "';");
        Users userss = getValues(uName);
        return userss;
    }

    public List<Users> getAllUserDetails(){
        SQLiteDatabase getDB=this.getReadableDatabase();
        List<Users> userList=new ArrayList<>();
        String sql="select * from "+TABLE_USER;
        Cursor cursor=getDB.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Users userObj=new Users
                        (cursor.getString(0),cursor.getInt(1),cursor.getInt(2));
                userList.add(userObj);
            }while (cursor.moveToNext());
            cursor.moveToFirst();


        }
        return userList;
    }

    public void delete(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM userdetails WHERE name ='"+ name + "';");
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_USER);
        return numRows;
    }
}
