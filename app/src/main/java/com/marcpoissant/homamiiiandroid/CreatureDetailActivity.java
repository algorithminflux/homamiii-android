package com.marcpoissant.homamiiiandroid;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.marcpoissant.homamiiiandroid.beans.Creature;
import com.marcpoissant.homamiiiandroid.database.DBHelper;

/**
 * Displays the clicked Creature's stats, using its id sent from MainActivity.
 */
public class CreatureDetailActivity extends AppCompatActivity {
    private final static String TAG = "HomamiiiAndroid2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        setContentView(R.layout.activity_creature_detail);

        DBHelper dbh = DBHelper.getDBHelper(this);

        showCreature(dbh);
    }

    public void close(View view){
        setResult(RESULT_OK);
        finish();
    }

    private void showCreature(DBHelper dbh) {
        Log.d(TAG, "showCreature called");
        // Fields to get from the database
        String[] from = {
                DBHelper.CREATURE_COL_ID,
                DBHelper.CREATURE_COL_NAME,
                DBHelper.CREATURE_COL_ARMY,
                DBHelper.CREATURE_COL_HEALTH,
                DBHelper.CREATURE_COL_SPEED,
                DBHelper.CREATURE_COL_ATTACK,
                DBHelper.CREATURE_COL_DEFENSE,
                DBHelper.CREATURE_COL_MIN_DAMAGE,
                DBHelper.CREATURE_COL_MAX_DAMAGE,
                DBHelper.CREATURE_COL_SPECIAL,
                DBHelper.CREATURE_COL_GOLD_COST,
                DBHelper.CREATURE_COL_RESOURCE_COST,
                DBHelper.CREATURE_COL_RESOURCE_TYPE,
                DBHelper.CREATURE_COL_NUM_SHOTS,
                DBHelper.CREATURE_COL_CAN_FLY,
                DBHelper.CREATURE_COL_TIER_LEVEL,
                DBHelper.CREATURE_COL_IS_UPGRADED_FORM,
                DBHelper.CREATURE_COL_UPGRADE_ID
        };

        // Matching fields in the layout to be used with the adapter
        int[] to = {
                R.id.tv_creature_detail_id,
                R.id.tv_creature_detail_name,
                R.id.tv_creature_detail_army,
                R.id.tv_creature_detail_health,
                R.id.tv_creature_detail_speed,
                R.id.tv_creature_detail_attack,
                R.id.tv_creature_detail_defense,
                R.id.tv_creature_detail_min_damage,
                R.id.tv_creature_detail_max_damage,
                R.id.tv_creature_detail_special,
                R.id.tv_creature_detail_gold_cost,
                R.id.tv_creature_detail_resource_cost,
                R.id.tv_creature_detail_resource_type,
                R.id.tv_creature_detail_num_shots,
                R.id.tv_creature_detail_can_fly,
                R.id.tv_creature_detail_tier_level,
                R.id.tv_creature_detail_is_upgraded_form,
                R.id.tv_creature_detail_upgrade_id
        };

        // Get the relevant views.
        TextView tvCreatureDetailId = (TextView) findViewById(R.id.tv_creature_detail_id);
        TextView tvCreatureDetailName = (TextView) findViewById(R.id.tv_creature_detail_name);
        TextView tvCreatureDetailArmy = (TextView) findViewById(R.id.tv_creature_detail_army);
        TextView tvCreatureDetailHealth = (TextView) findViewById(R.id.tv_creature_detail_health);
        TextView tvCreatureDetailSpeed = (TextView) findViewById(R.id.tv_creature_detail_speed);
        TextView tvCreatureDetailAttack = (TextView) findViewById(R.id.tv_creature_detail_attack);
        TextView tvCreatureDetailDefense = (TextView) findViewById(R.id.tv_creature_detail_defense);
        TextView tvCreatureDetailMinDamage = (TextView) findViewById(R.id.tv_creature_detail_min_damage);
        TextView tvCreatureDetailMaxDamage = (TextView) findViewById(R.id.tv_creature_detail_max_damage);
        TextView tvCreatureDetailSpecial = (TextView) findViewById(R.id.tv_creature_detail_special);
        TextView tvCreatureDetailGoldCost = (TextView) findViewById(R.id.tv_creature_detail_gold_cost);
        TextView tvCreatureDetailResourceCost = (TextView) findViewById(R.id.tv_creature_detail_resource_cost);
        TextView tvCreatureDetailResourceType = (TextView) findViewById(R.id.tv_creature_detail_resource_type);
        TextView tvCreatureDetailNumShots = (TextView) findViewById(R.id.tv_creature_detail_num_shots);
        TextView tvCreatureDetailCanFly = (TextView) findViewById(R.id.tv_creature_detail_can_fly);
        TextView tvCreatureDetailTierLevel = (TextView) findViewById(R.id.tv_creature_detail_tier_level);
        TextView tvCreatureDetailIsUpgradedForm = (TextView) findViewById(R.id.tv_creature_detail_is_upgraded_form);
        TextView tvCreatureDetailUpgradeId = (TextView) findViewById(R.id.tv_creature_detail_upgrade_id);

        // Get the creature's id.
        Bundle extras = getIntent().getExtras();
        int creatureId = extras.getInt("creatureId");
        Cursor cursor = dbh.findCreatureById(creatureId);

        // Get the columns indexes.
        int creatureIdColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_ID);
        int creatureNameColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_NAME);
        int creatureArmyColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_ARMY);
        int creatureHealthColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_HEALTH);
        int creatureSpeedColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_SPEED);
        int creatureAttackColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_ATTACK);
        int creatureDefenseColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_DEFENSE);
        int creatureMinDamageColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_MIN_DAMAGE);
        int creatureMaxDamageColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_MAX_DAMAGE);
        int creatureSpecialColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_SPECIAL);
        int creatureGoldCostColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_GOLD_COST);
        int creatureResourceCostColIndex = cursor.getColumnIndex(
                DBHelper.CREATURE_COL_RESOURCE_COST);
        int creatureResourceTypeColIndex = cursor.getColumnIndex(
                DBHelper.CREATURE_COL_RESOURCE_TYPE);
        int creatureNumShotsColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_NUM_SHOTS);
        int creatureCanFlyColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_CAN_FLY);
        int creatureTierLevelColIndex = cursor.getColumnIndex(DBHelper.CREATURE_COL_TIER_LEVEL);
        int creatureIsUpgradedFormColIndex =  cursor.getColumnIndex(
                DBHelper.CREATURE_COL_IS_UPGRADED_FORM);
        int creatureUpgradeId = cursor.getColumnIndex(DBHelper.CREATURE_COL_UPGRADE_ID);



        // Set the text in the TextViews with the creature's details so long as at least one row
        // was found.
        if(cursor != null && cursor.moveToFirst()) {
            // Create the creature from the result.
            Creature creature = new Creature();
            creature.setId(cursor.getInt(creatureIdColIndex));
            creature.setName(cursor.getString(creatureNameColIndex));
            creature.setArmy(cursor.getString(creatureArmyColIndex));
            creature.setHealth(cursor.getInt(creatureHealthColIndex));
            creature.setSpeed(cursor.getInt(creatureSpeedColIndex));
            creature.setAttack(cursor.getInt(creatureAttackColIndex));
            creature.setDefense(cursor.getInt(creatureDefenseColIndex));
            creature.setMinDamage(cursor.getInt(creatureMinDamageColIndex));
            creature.setMaxDamage(cursor.getInt(creatureMaxDamageColIndex));
            creature.setSpecial(cursor.getString(creatureSpecialColIndex));
            creature.setGoldCost(cursor.getInt(creatureGoldCostColIndex));
            creature.setResourceCost(cursor.getInt(creatureResourceCostColIndex));
            creature.setResourceType(cursor.getString(creatureResourceTypeColIndex));
            creature.setNumShots(cursor.getInt(creatureNumShotsColIndex));
            int canFly = cursor.getInt(creatureCanFlyColIndex);
            if(canFly == 1) {
                creature.setCanFly(true);
            } else {
                creature.setCanFly(false);
            }
            creature.setTierLevel(cursor.getInt(creatureTierLevelColIndex));
            int isCreatureUpgradedForm = cursor.getInt(creatureIsUpgradedFormColIndex);
            if(isCreatureUpgradedForm == 1) {
                creature.setIsUpgradedForm(true);
            } else {
                creature.setIsUpgradedForm(false);
            }
            creature.setUpgradeId(cursor.getInt(creatureUpgradeId));

            Log.d(TAG, "Creature: " + creature.toString());

            // Set the creature's fields in the view accordingly.
            tvCreatureDetailId.setText(Integer.toString(creature.getId()));
            tvCreatureDetailName.setText(creature.getName());
            tvCreatureDetailArmy.setText(creature.getArmy());
            tvCreatureDetailHealth.setText(Integer.toString(creature.getHealth()));
            tvCreatureDetailSpeed.setText(Integer.toString(creature.getSpeed()));
            tvCreatureDetailAttack.setText(Integer.toString(creature.getAttack()));
            tvCreatureDetailDefense.setText(Integer.toString(creature.getDefense()));
            tvCreatureDetailMinDamage.setText(Integer.toString(creature.getMinDamage()));
            tvCreatureDetailMaxDamage.setText(Integer.toString(creature.getMaxDamage()));
            tvCreatureDetailSpecial.setText(creature.getSpecial());
            tvCreatureDetailGoldCost.setText(Integer.toString(creature.getGoldCost()));
            tvCreatureDetailResourceCost.setText(Integer.toString(creature.getResourceCost()));
            tvCreatureDetailResourceType.setText(creature.getResourceType());
            tvCreatureDetailNumShots.setText(Integer.toString(creature.getNumShots()));
            tvCreatureDetailCanFly.setText(Boolean.toString(creature.canFly()));
            tvCreatureDetailTierLevel.setText(Integer.toString(creature.getTierLevel()));
            tvCreatureDetailIsUpgradedForm.setText(Boolean.toString(creature.isUpgradedForm()));
            tvCreatureDetailUpgradeId.setText(Integer.toString(creature.getUpgradeId()));
        } else {
            Log.e(TAG, "Creature detail not found! Cursor was empty.");
            TextView tvCreatureDetailError = (TextView) findViewById(R.id.tv_creature_detail_error);
            tvCreatureDetailError.setText(R.string.creature_detail_not_found);
        }

    }
}
