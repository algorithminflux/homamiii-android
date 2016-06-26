package com.marcpoissant.homamiiiandroid.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.marcpoissant.homamiiiandroid.beans.Creature;

/**
 * Created by marc on 12/06/16.
 */
public interface DAOInterface {
    // Create
    public long createCreature(Creature creature);
    public long createCreature(ContentValues cv);

    // Read Creature
    public Cursor findCreatureById(int id);
    public Cursor findCreaturesByArmy(String army);
    public Cursor findCreatureByName(String name);
    public Cursor findAllCreatures();
}
