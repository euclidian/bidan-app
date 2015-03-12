package org.ei.bidan.bidan.repository;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sqlcipher.database.SQLiteDatabase;

import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.repository.DrishtiRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.TRUE;
import static net.sqlcipher.DatabaseUtils.longForQuery;
import static org.apache.commons.lang3.StringUtils.repeat;

/**
 * Created by Dimas Ciputra on 2/16/15.
 */
public class KartuIbuRepository extends DrishtiRepository{

    private static final String KI_SQL = "CREATE TABLE kartu_ibu(id VARCHAR PRIMARY KEY, details VARCHAR, isClosed VARCHAR)";
    public static final String ID_COLUMN = "id";
    public static final String DETAILS_COLUMN = "details";
    private static final String IS_CLOSED_COLUMN = "isClosed";

    public static final String KI_TABLE_NAME = "kartu_ibu";
    public static final String[] KI_TABLE_COLUMNS = new String[]{ID_COLUMN, DETAILS_COLUMN,
            IS_CLOSED_COLUMN};

    public static final String NOT_CLOSED = "false";

    @Override
    protected void onCreate(SQLiteDatabase database) {
        database.execSQL(KI_SQL);
    }

    public void add(KartuIbu kartuIbu) {
        SQLiteDatabase database = masterRepository.getWritableDatabase();
        database.insert(KI_TABLE_NAME, null, createValuesFor(kartuIbu));
    }

    public void updateDetails(String caseId, Map<String, String> details) {
        SQLiteDatabase database = masterRepository.getWritableDatabase();

        KartuIbu kartuIbu = findByCaseID(caseId);
        if (kartuIbu == null) {
            return;
        }

        ContentValues valuesToUpdate = new ContentValues();
        valuesToUpdate.put(DETAILS_COLUMN, new Gson().toJson(details));
        database.update(KI_TABLE_NAME, valuesToUpdate, ID_COLUMN + " = ?", new String[]{caseId});
    }

    public void mergeDetails(String caseId, Map<String, String> details) {
        SQLiteDatabase database = masterRepository.getWritableDatabase();

        KartuIbu kartuIbu = findByCaseID(caseId);
        if (kartuIbu == null) {
            return;
        }

        Map<String, String> mergedDetails = new HashMap<String, String>(kartuIbu.getDetails());
        mergedDetails.putAll(details);
        ContentValues valuesToUpdate = new ContentValues();
        valuesToUpdate.put(DETAILS_COLUMN, new Gson().toJson(mergedDetails));
        database.update(KI_TABLE_NAME, valuesToUpdate, ID_COLUMN + " = ?", new String[]{caseId});
    }

    public List<KartuIbu> allKartuIbus() {
        SQLiteDatabase database = masterRepository.getReadableDatabase();
        Cursor cursor = database.query(KI_TABLE_NAME, KI_TABLE_COLUMNS,
                IS_CLOSED_COLUMN + " = ?", new String[]{NOT_CLOSED}, null, null, null, null);
        return readAllKartuIbus(cursor);
    }

    public List<KartuIbu> findByCaseIDs(String... caseIds) {
        SQLiteDatabase database = masterRepository.getReadableDatabase();
        Cursor cursor = database.rawQuery(String.format("SELECT * FROM %s WHERE %s IN (%s)", KI_TABLE_NAME, ID_COLUMN,
                insertPlaceholdersForInClause(caseIds.length)), caseIds);
        return readAllKartuIbus(cursor);
    }

    private String insertPlaceholdersForInClause(int length) {
        return repeat("?", ",", length);
    }

    public KartuIbu findByCaseID(String caseId) {
        SQLiteDatabase database = masterRepository.getReadableDatabase();
        Cursor cursor = database.query(KI_TABLE_NAME, KI_TABLE_COLUMNS, ID_COLUMN + " = ?", new String[]{caseId},
                null, null, null, null);
        List<KartuIbu> kartuIbus = readAllKartuIbus(cursor);
        if (kartuIbus.isEmpty()) {
            return null;
        }
        return kartuIbus.get(0);
    }

    public long count() {
        return longForQuery(masterRepository.getReadableDatabase(), "SELECT COUNT(1) FROM " + KI_TABLE_NAME
                + " WHERE " + IS_CLOSED_COLUMN + " = '" + NOT_CLOSED + "'", new String[0]);
    }

    public void close(String caseId) {
        ContentValues values = new ContentValues();
        values.put(IS_CLOSED_COLUMN, TRUE.toString());
        masterRepository.getWritableDatabase().update(KI_TABLE_NAME, values, ID_COLUMN + " = ?", new String[]{caseId});
    }

    private ContentValues createValuesFor(KartuIbu kartuIbu) {
        ContentValues values = new ContentValues();
        values.put(ID_COLUMN, kartuIbu.getCaseId());
        values.put(DETAILS_COLUMN, new Gson().toJson(kartuIbu.getDetails()));
        values.put(IS_CLOSED_COLUMN, Boolean.toString(kartuIbu.isClosed()));
        return values;
    }

    private List<KartuIbu> readAllKartuIbus(Cursor cursor) {
        cursor.moveToFirst();
        List<KartuIbu> kartuIbus = new ArrayList<KartuIbu>();
        while (!cursor.isAfterLast()) {
            KartuIbu kartuIbu = new KartuIbu(cursor.getString(0),
                    new Gson().<Map<String, String>>fromJson(cursor.getString(1), new TypeToken<Map<String, String>>() {
                    }.getType()));
            kartuIbu.setClosed(Boolean.valueOf(cursor.getString(2)));
            kartuIbus.add(kartuIbu);
            cursor.moveToNext();
        }
        cursor.close();
        return kartuIbus;
    }
}
