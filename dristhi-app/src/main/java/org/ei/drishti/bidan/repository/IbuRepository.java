package org.ei.drishti.bidan.repository;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sqlcipher.DatabaseUtils;
import net.sqlcipher.database.SQLiteDatabase;

import org.ei.drishti.bidan.domain.Ibu;
import org.ei.drishti.repository.DrishtiRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dimas Ciputra on 3/3/15.
 */
public class IbuRepository extends DrishtiRepository {
    private static final String IBU_SQL = "CREATE TABLE ibu(id VARCHAR PRIMARY KEY, kartuIbuId VARCHAR, type VARCHAR, referenceDate VARCHAR, details VARCHAR, isClosed VARCHAR)";
    private static final String IBU_TYPE_INDEX_SQL = "CREATE INDEX ibu_type_index ON ibu(type);";
    private static final String IBU_REFERENCE_DATE_INDEX_SQL = "CREATE INDEX ibu_referenceDate_index ON ibu(referenceDate);";
    public static final String IBU_TABLE_NAME = "ibu";
    public static final String ID_COLUMN = "id";
    public static final String KI_ID_COLUMN = "kartuIbuId";
    private static final String TYPE_COLUMN = "type";
    public static final String REF_DATE_COLUMN = "referenceDate";
    public static final String DETAILS_COLUMN = "details";
    private static final String IS_CLOSED_COLUMN = "isClosed";
    public static final String[] IBU_TABLE_COLUMNS = {ID_COLUMN, KI_ID_COLUMN, TYPE_COLUMN, REF_DATE_COLUMN, DETAILS_COLUMN, IS_CLOSED_COLUMN};

    public static final String TYPE_ANC = "ANC";
    private static final String NOT_CLOSED = "false";


    @Override
    protected void onCreate(SQLiteDatabase database) {
        database.execSQL(IBU_SQL);
        database.execSQL(IBU_TYPE_INDEX_SQL);
        database.execSQL(IBU_REFERENCE_DATE_INDEX_SQL);
    }

    public void add(Ibu ibu) {
        SQLiteDatabase database = masterRepository.getWritableDatabase();
        database.insert(IBU_TABLE_NAME, null, createValuesFor(ibu, "ANC"));
    }

    public List<Ibu> allANCs() {
        SQLiteDatabase database = masterRepository.getReadableDatabase();
        Cursor cursor = database.query(IBU_TABLE_NAME, IBU_TABLE_COLUMNS, TYPE_COLUMN + " = ? AND " + IS_CLOSED_COLUMN + " = ?", new String[]{TYPE_ANC, NOT_CLOSED}, null, null, null, null);
        return readAll(cursor);
    }

    public Ibu findById(String id) {
        SQLiteDatabase database = masterRepository.getReadableDatabase();
        Cursor cursor = database.query(IBU_TABLE_NAME, IBU_TABLE_COLUMNS, ID_COLUMN + " = ?", new String[]{id},
                null, null, null, null);
        return readAll(cursor).get(0);
    }

    public long ancCount() {
        return DatabaseUtils.longForQuery(masterRepository.getReadableDatabase(),
                "SELECT COUNT(1) FROM " + IBU_TABLE_NAME + " WHERE  " + TYPE_COLUMN + " = ? AND " + IS_CLOSED_COLUMN + " = ? ",
                new String[]{TYPE_ANC, NOT_CLOSED});
    }

    /*
        Private Methods
     */

    private ContentValues createValuesFor(Ibu ibu, String type) {
        ContentValues values = new ContentValues();
        values.put(ID_COLUMN, ibu.getId());
        values.put(KI_ID_COLUMN, ibu.getKartuIbuId());
        values.put(TYPE_COLUMN, type);
        values.put(REF_DATE_COLUMN, ibu.getReferenceDate());
        values.put(DETAILS_COLUMN, new Gson().toJson(ibu.getDetails()));
        values.put(IS_CLOSED_COLUMN, Boolean.toString(ibu.isClosed()));
        return values;
    }

    private List<Ibu> readAll(Cursor cursor) {
        cursor.moveToFirst();
        List<Ibu> ibus = new ArrayList<Ibu>();
        while (!cursor.isAfterLast()) {
            Map<String, String> details = new Gson().fromJson(cursor.getString(4), new TypeToken<Map<String, String>>() {
            }.getType());

            ibus.add(new Ibu(cursor.getString(0), cursor.getString(1), cursor.getString(2))
                    .withDetails(details)
                    .setIsClosed(Boolean.valueOf(cursor.getString(5)))
                    .withType(cursor.getString(cursor.getColumnIndex(TYPE_COLUMN))));
            cursor.moveToNext();
        }
        cursor.close();
        return ibus;
    }
}
