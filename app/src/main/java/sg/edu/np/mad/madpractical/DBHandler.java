package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "accountsDB.db"; //filename
    public static String ACCOUNTS = "Accounts"; //table
    public static String COLUMN_NAME = "name";
    public static String COLUMN_DESCRIPTION = "description";
    public static String COLUMN_ID = "id";
    public static String COLUMN_FOLLOWED = "followed";
    public static int DATABASE_VERSION = 1;

    public DBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNTS = "" +
                "CREATE TABLE " + ACCOUNTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," +
                COLUMN_DESCRIPTION  + " TEXT," + COLUMN_FOLLOWED + " INTEGER" + ")";
        db.execSQL(CREATE_ACCOUNTS);

        for (int i = 0; i < 20; ++i) {
            Log.v("Create Table", "");
            ContentValues values = new ContentValues();

            values.put(COLUMN_NAME, "Name" + rng());
            values.put(COLUMN_DESCRIPTION, "Description" + rng());
            values.put(COLUMN_FOLLOWED, rngFollow());

            Log.v("User ", String.valueOf(values));
            db.insert(ACCOUNTS, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        onCreate(db);
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> userInfoList = new ArrayList<User>();
        String query = "SELECT * FROM " + ACCOUNTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                boolean followed = cursor.getInt(3) == 1;

                userInfoList.add(new User(name, description, id, followed));
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        cursor.close();

        return userInfoList;
    }

    private boolean rngFollow() {
        return new Random().nextInt(2) == 1;
    }

    private int rng() {
        return new Random().nextInt();
    }

    public void updateUser(User user) {
        Log.v("Update", String.valueOf(user));
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL(
                "UPDATE " + ACCOUNTS + " SET " + COLUMN_FOLLOWED + " = " + (user.followed ? 1 : 0) +
                        " WHERE " + COLUMN_ID + " = " + user.id
        );
    }
}
