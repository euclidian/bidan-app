package org.ei.bidan.bidan.service;

import android.util.Log;

import org.ei.bidan.DristhiConfiguration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dimas Ciputra on 5/19/15.
 */
public class DummyNameService {

    public static List<String> getMotherDummyName(DristhiConfiguration configuration, int size, boolean isRandom) {
        List<String> motherNameList = getDummyData(configuration, "motherName");
        if(isRandom) Collections.shuffle(motherNameList);
        return motherNameList.subList(0, size);
    }

    public static List<String> getChildDummyName(DristhiConfiguration configuration, int size, boolean isRandom) {
        List<String> childNameList = getDummyData(configuration, "childName");
        if(isRandom) Collections.shuffle(childNameList);
        return childNameList.subList(0, size);
    }

    private static List<String> getDummyData(DristhiConfiguration configuration, String name) {
        List<String> dummyList = new ArrayList<String>();
        try {
            JSONObject dummyData = new JSONObject(configuration.getDummyData());
            JSONArray motherJSONArray = dummyData.getJSONArray(name);

            if (motherJSONArray != null) {
                int len = motherJSONArray.length();
                for (int i=0;i<len;i++){
                    dummyList.add(motherJSONArray.get(i).toString());
                }
            }
        } catch (Exception e) {
            Log.d("Random Name", e.getMessage());
            return null;
        }
        return dummyList;
    }
}
