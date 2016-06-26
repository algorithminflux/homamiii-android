package com.marcpoissant.homamiiiandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.marcpoissant.homamiiiandroid.beans.Creature;

/**
 * Created by marc on 12/06/16.
 */
public class DBHelper extends SQLiteOpenHelper implements DAOInterface {
    private final String TAG = "HomamiiiAndroid2";
    // DB file name
    public static final String DATABASE_NAME = "homamiii.db";

    // If the version number is increased, the onUpdate() will be called
    public static final int DATABASE_VERSION = 1;

    // Static instance to share DBHelper
    public static DBHelper dbh = null;

    // Table names
    public static String TABLE_NAME_CREATURE = "creature";

    // Creature columns
    public static String CREATURE_COL_ID = "_id";
    public static String CREATURE_COL_NAME = "name";
    public static String CREATURE_COL_ARMY = "army";
    public static String CREATURE_COL_HEALTH = "health";
    public static String CREATURE_COL_SPEED = "speed";
    public static String CREATURE_COL_ATTACK = "attack";
    public static String CREATURE_COL_DEFENSE = "defense";
    public static String CREATURE_COL_MIN_DAMAGE = "min_damage";
    public static String CREATURE_COL_MAX_DAMAGE = "max_damage";
    public static String CREATURE_COL_SPECIAL = "special";
    public static String CREATURE_COL_GOLD_COST = "gold_cost";
    public static String CREATURE_COL_RESOURCE_COST = "resource_cost";
    public static String CREATURE_COL_RESOURCE_TYPE = "resource_type";
    public static String CREATURE_COL_NUM_SHOTS = "num_shots";
    public static String CREATURE_COL_CAN_FLY = "can_fly";
    public static String CREATURE_COL_TIER_LEVEL = "tier_level";
    public static String CREATURE_COL_IS_UPGRADED_FORM = "is_upgraded_form";
    public static String CREATURE_COL_UPGRADE_ID = "upgrade_id";

    // Creature Creature table
    public static String CREATE_CREATURE_TABLE = "CREATE TABLE " +
            TABLE_NAME_CREATURE + " ( " +
            CREATURE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CREATURE_COL_NAME + " TEXT NOT NULL, " +
            CREATURE_COL_ARMY + " TEXT NOT NULL, " +
            CREATURE_COL_HEALTH + " INTEGER NOT NULL, "  +
            CREATURE_COL_SPEED + " INTEGER NOT NULL, " +
            CREATURE_COL_ATTACK + " INTEGER NOT NULL, " +
            CREATURE_COL_DEFENSE + " INTEGER NOT NULL, " +
            CREATURE_COL_MIN_DAMAGE + " INTEGER NOT NULL, " +
            CREATURE_COL_MAX_DAMAGE + " INTEGER NOT NULL, " +
            CREATURE_COL_SPECIAL + " TEXT, " +
            CREATURE_COL_GOLD_COST + " INTEGER NOT NULL, " +
            CREATURE_COL_RESOURCE_COST + " INTEGER, " +
            CREATURE_COL_RESOURCE_TYPE + " TEXT, " +
            CREATURE_COL_NUM_SHOTS + " INTEGER NOT NULL, " +
            CREATURE_COL_CAN_FLY + " INTEGER NOT NULL, " +
            CREATURE_COL_TIER_LEVEL + " INTEGER NOT NULL, " +
            CREATURE_COL_IS_UPGRADED_FORM + " INTEGER NOT NULL, " +
            CREATURE_COL_UPGRADE_ID + " INTEGER" +
            ");";

    private static String DROP_CREATURE_TABLE = "DROP TABLE IF EXISTS " +
            TABLE_NAME_CREATURE + ";";

    /**
     * Constructor
     *
     * Constructor is private to prevent direct instantiation. Only
     * getDBHelper() can be called to create or return the existing DBHelper.
     *
     * @param context
     */
    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the database and populates it with one dummy record.
     *
     * @param db
     * @see SQLiteOpenHelper#onCreate(android.database.sqlite
     *      .SQLiteDatabase)
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(DBHelper.class.getName(), "onCreate()");
        dropTables(db);
        createTables(db);
    }

    /**
     * Drops the tables and recreates them.
     *
     * @see SQLiteOpenHelper#onUpgrade(android.database.sqlite
     *      .SQLiteDatabase, int, int)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(DBHelper.class.getName(), "onUpgrade()");
        if(newVersion > oldVersion) {
            dropTables(db);
            createTables(db);
        }
    }

    /**
     * Called when the database is opened.
     *
     * @param SQLiteDatabase
     * @see SQLiteOpenHelper#onOpen(SQLiteDatabase)
     *      .SQLiteDatabase)
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.i(DBHelper.class.getName(), "onOpen()");
    }

    /**
     * Makes sure that only one database helper exists across the application's
     * lifecycle.
     *
     * @param context
     * @return the single DBHelper instance
     */
    public static DBHelper getDBHelper(Context context) {
        if(dbh == null) {
            dbh = new DBHelper(context.getApplicationContext());
        }

        return dbh;
    }

    private void createTables(SQLiteDatabase db) {
        Log.i(DBHelper.class.getName(), "createTables()");
        try {
            db.execSQL(CREATE_CREATURE_TABLE);
        } catch (SQLException ex) {
            Log.e(DBHelper.class.getName(), "CREATE exception " + Log.getStackTraceString(ex));
        }
    }

    private void dropTables(SQLiteDatabase db) {
        Log.i(DBHelper.class.getName(), "dropTables()");
        try {
            db.execSQL(DROP_CREATURE_TABLE);
        } catch (SQLException ex) {
            Log.e(DBHelper.class.getName(), "DROP exception " + Log.getStackTraceString(ex));
        }
    }

    @Override
    public long createCreature(Creature creature) {
        Log.i(DBHelper.class.getName(), "createCreature(Creature)");
        long code = 0;
        ContentValues cv = new ContentValues();

        cv.put(CREATURE_COL_ID, creature.getId());
        cv.put(CREATURE_COL_NAME, creature.getName());
        cv.put(CREATURE_COL_ARMY, creature.getArmy());
        cv.put(CREATURE_COL_HEALTH, creature.getHealth());
        cv.put(CREATURE_COL_SPEED, creature.getSpeed());
        cv.put(CREATURE_COL_ATTACK, creature.getAttack());
        cv.put(CREATURE_COL_DEFENSE, creature.getDefense());
        cv.put(CREATURE_COL_MIN_DAMAGE, creature.getMinDamage());
        cv.put(CREATURE_COL_MAX_DAMAGE, creature.getMaxDamage());
        cv.put(CREATURE_COL_SPECIAL, creature.getSpecial());
        cv.put(CREATURE_COL_GOLD_COST, creature.getGoldCost());
        cv.put(CREATURE_COL_RESOURCE_COST, creature.getResourceCost());
        cv.put(CREATURE_COL_RESOURCE_TYPE, creature.getResourceType());
        cv.put(CREATURE_COL_NUM_SHOTS, creature.getNumShots());
        cv.put(CREATURE_COL_CAN_FLY, creature.canFly());
        cv.put(CREATURE_COL_TIER_LEVEL, creature.getTierLevel());
        cv.put(CREATURE_COL_IS_UPGRADED_FORM, creature.isUpgradedForm());
        cv.put(CREATURE_COL_UPGRADE_ID, creature.getUpgradeId());

        code = getWritableDatabase().insert(TABLE_NAME_CREATURE, null, cv);

        return code;
    }

    @Override
    public long createCreature(ContentValues cv) {
        Log.i(DBHelper.class.getName(), "createCreature(ContentValues)");
        long code = 0;

        code = getWritableDatabase().insert(TABLE_NAME_CREATURE, null, cv);

        return code;
    }

    @Override
    public Cursor findCreatureById(int id) {
        Log.i(DBHelper.class.getName(), "findCreatureById()");
        return getReadableDatabase().query(
                TABLE_NAME_CREATURE, // FROM
                null, // SELECT *
                CREATURE_COL_ID + "=?", // WHERE
                new String[] { String.valueOf(id) }, // WHERE
                null, // GROUP BY
                null, // HAVING
                null // ORDER BY
                );
    }

    @Override
    public Cursor findCreaturesByArmy(String army) {
        Log.i(DBHelper.class.getName(), "findCreatureByArmy()");
        return getReadableDatabase().query(
                TABLE_NAME_CREATURE, // FROM
                null, // SELECT *
                CREATURE_COL_ARMY + "=?", // WHERE
                new String[] { String.valueOf(army) }, // WHERE
                null, // GROUP BY
                null, // HAVING
                null // ORDER BY
        );
    }

    @Override
    public Cursor findCreatureByName(String name) {
        Log.i(DBHelper.class.getName(), "findCreatureByName()");
        return getReadableDatabase().query(
                TABLE_NAME_CREATURE, // FROM
                null, // SELECT *
                CREATURE_COL_NAME + "=?", // WHERE
                new String[] { String.valueOf(name) }, // WHERE
                null, // GROUP BY
                null, // HAVING
                null // ORDER BY
        );
    }

    @Override
    public Cursor findAllCreatures() {
        Log.i(DBHelper.class.getName(), "findAllCreatures()");
        return getReadableDatabase().query(
                TABLE_NAME_CREATURE, // FROM
                null, // SELECT *
                null, // WHERE
                null, // WHERE
                null, // GROUP BY
                null, // HAVING
                null // ORDER BY
        );
    }
}
