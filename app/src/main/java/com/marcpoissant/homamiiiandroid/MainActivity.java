package com.marcpoissant.homamiiiandroid;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.marcpoissant.homamiiiandroid.database.DBHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    private final String TAG = "HomamiiiAndroid2";
    // Use to determine if it's the app's first run.
    private final String PREFS_NAME = "shared_preferences";

    private static DBHelper dbh = null;

    private Cursor cursor = null;
    private SimpleCursorAdapter sca = null;

    SharedPreferences sharedPreferences = null;

    private OnItemClickListener itemClickListener = new OnItemClickListener() {
        /**
         * @param parent The AdapterView where the click happened.
         * @param view The view within the AdapterView that was clicked (this
         *            will be a view provided by the adapter)
         * @param position The position of the view in the adapter.
         * @param id The row id of the item that was clicked.
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d(TAG, "Launching CreatureDetailActivity from creaturesList at position: "
                    + position);
            launchCreatureDetailActivity(parent, view, position, id);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle)");

        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        DBHelper dbh = DBHelper.getDBHelper(this);

        // Create the database if this is the first run of the app.
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                                                    /* Context.MODE_PRIVATE: File creation
                                                     * mode: the default mode, where the created
                                                     * file can only be accessed by the calling
                                                     * application (or all applications sharing the
                                                     * same user ID). */
        boolean isFirstRun = sharedPreferences.getBoolean("firstRun", true); // Set it to true if
        // not able to retrieve it.
        if(isFirstRun) {
            Log.d(TAG, "First time running app. Creating database.");
            createAndPopulateDB(context, dbh);
            Log.d(TAG, "Database created.");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstRun", false);
            editor.commit();
        }

        showCreatures(dbh);
    }

    /**
     *
     */
    /**
     * Creates and populate the homamiii database with the CSV resources.
     *
     * Info for fetching data from CSV here:
     *  https://stackoverflow.com/questions/16672074/import-csv-file-to-sqlite-in-android
     *
     * @param context
     * @param dbh
     */
    private void createAndPopulateDB(Context context, DBHelper dbh) {
        Log.d(TAG, "createAndPopulateDB(Context, DBHelper)");

        String creatureCSVFile = "creature.csv";
        AssetManager manager = context.getAssets();
        InputStream inStream = null;
        try {
            // Open CSV
            inStream = manager.open(creatureCSVFile);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Log.d(TAG, "Inserting creatures from CSV into DB");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line = "";
        try {
            // Read CSV
            while ((line = buffer.readLine()) != null) {
                String[] cols = line.split(",");
                if (cols.length != 17) {
                    Log.d(TAG, "Skipping bad CSV row.");
                    continue;
                }
                // Insert values from CSV into database
                ContentValues cv = new ContentValues(17); // 17 columns
                cv.put(DBHelper.CREATURE_COL_NAME, cols[0].trim());
                cv.put(DBHelper.CREATURE_COL_ARMY, cols[1].trim());
                cv.put(DBHelper.CREATURE_COL_HEALTH, cols[2].trim());
                cv.put(DBHelper.CREATURE_COL_SPEED, cols[3].trim());
                cv.put(DBHelper.CREATURE_COL_ATTACK, cols[4].trim());
                cv.put(DBHelper.CREATURE_COL_DEFENSE, cols[5].trim());
                cv.put(DBHelper.CREATURE_COL_MIN_DAMAGE, cols[6].trim());
                cv.put(DBHelper.CREATURE_COL_MAX_DAMAGE, cols[7].trim());
                cv.put(DBHelper.CREATURE_COL_SPECIAL, cols[8].trim());
                cv.put(DBHelper.CREATURE_COL_GOLD_COST, cols[9].trim());
                cv.put(DBHelper.CREATURE_COL_RESOURCE_COST, cols[10].trim());
                cv.put(DBHelper.CREATURE_COL_RESOURCE_TYPE, cols[11].trim());
                cv.put(DBHelper.CREATURE_COL_NUM_SHOTS, cols[12].trim());
                cv.put(DBHelper.CREATURE_COL_CAN_FLY, cols[13].trim());
                cv.put(DBHelper.CREATURE_COL_TIER_LEVEL, cols[14].trim());
                cv.put(DBHelper.CREATURE_COL_IS_UPGRADED_FORM, cols[15].trim());
                cv.put(DBHelper.CREATURE_COL_UPGRADE_ID, cols[16].trim());
                dbh.createCreature(cv);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        Log.d(TAG, "Finished inserting creatures");
    }

    /**
     * Populates activity_main view with a list of creatures' names.
     *
     * @param dbh the DBHelper
     */
    private void showCreatures(DBHelper dbh) {
        // Fields to get from the database
        String[] from = {
                DBHelper.CREATURE_COL_NAME
        };

        // Matching fields in the layout to be used with the adapter
        int[] to = {R.id.tv_creature_name};

        // The ListView to be used with the adapter
        ListView lv = (ListView) findViewById(R.id.creatures_list);

        if (lv != null) {
            cursor = dbh.findAllCreatures();

            // Create the SimpleCursorAdapter
            sca = new SimpleCursorAdapter(this, R.layout.creature_rows, cursor, from, to, 0);

            // Set it in the ListView
            lv.setAdapter(sca);
            lv.setOnItemClickListener(itemClickListener);
        } else {
            Log.e(TAG, "ListView was null!");
        }
    }

    /**
     * Launches CreatureDetailActivity, sending the id of the creature clicked.
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    private void launchCreatureDetailActivity(AdapterView<?> parent, View view, int position,
                                              long id) {
        Log.d(TAG, "Launching CreatureDetailActivity");

        Intent intent = new Intent(this, CreatureDetailActivity.class);
        Log.d(TAG, "launchCreatureDetailActivity: Creature clicked id: " + id);

        // Send the id of the creature that was clicked so creature can be selected within the
        // CreatureDetailActivity class.
        intent.putExtra("creatureId", (int)id); // Need to cast id (type long) to an int.
        startActivity(intent);
    }
}
