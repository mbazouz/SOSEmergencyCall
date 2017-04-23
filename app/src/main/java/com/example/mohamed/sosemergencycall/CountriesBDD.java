package com.example.mohamed.sosemergencycall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Mohamed on 05/12/2016.
 */

public class CountriesBDD {

    private static final int VERSION_BDD = 1;
    private static final String NAME_BDD = "countries.db";

    private static final String TABLE_COUNTRIES = "table_countries";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "Name";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_POLICE = "Police";
    private static final int NUM_COL_POLICE = 2;
    private static final String COL_AMBULANCE = "Ambulance";
    private static final int NUM_COL_AMBULANCE = 3;
    private static final String COL_FIRE = "Fire";
    private static final int NUM_COL_FIRE = 4;

    private SQLiteDatabase bdd;

    private CountriesDatabase countriesDatabase;

    public CountriesBDD(Context context){
        //On crée la BDD et sa table
        countriesDatabase = new CountriesDatabase(context, NAME_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = countriesDatabase.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }



    public long insertCountry(Country country){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_NAME, country.getName());
        values.put(COL_POLICE, country.getPolice());
        values.put(COL_AMBULANCE, country.getAmbulance());
        values.put(COL_FIRE, country.getFire());

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_COUNTRIES, null, values);
    }

    public int updateCountry(int id, Country country){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_NAME, country.getName());
        values.put(COL_POLICE, country.getPolice());
        values.put(COL_AMBULANCE, country.getAmbulance());
        values.put(COL_FIRE, country.getFire());
        return bdd.update(TABLE_COUNTRIES, values, COL_ID + " = " +id, null);
    }

    public int removeCountryWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_COUNTRIES, COL_ID + " = " +id, null);
    }

    public Country getCountryWithName(String name){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_COUNTRIES, new String[] {COL_ID, COL_NAME, COL_POLICE, COL_AMBULANCE, COL_FIRE}, COL_NAME + " LIKE \"" + name +"\"", null, null, null, null);
        return cursorToCountry(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Country cursorToCountry(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Country country = new Country();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        country.setId(c.getInt(NUM_COL_ID));
        country.setName(c.getString(NUM_COL_NAME));
        country.setPolice(c.getInt(NUM_COL_POLICE));
        country.setAmbulance(c.getInt(NUM_COL_AMBULANCE));
        country.setFire(c.getInt(NUM_COL_FIRE));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return country;
    }
}
