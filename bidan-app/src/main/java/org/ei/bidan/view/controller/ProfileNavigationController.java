package org.ei.bidan.view.controller;

import android.content.Context;
import android.content.Intent;

import org.ei.bidan.bidan.view.activity.KIDetailActivity;
import org.ei.bidan.view.activity.ANCDetailActivity;
import org.ei.bidan.view.activity.ChildDetailActivity;
import org.ei.bidan.view.activity.EligibleCoupleDetailActivity;
import org.ei.bidan.view.activity.PNCDetailActivity;

import static org.ei.bidan.AllConstants.CASE_ID;

public class ProfileNavigationController {

    public static void navigateToECProfile(android.content.Context context, String caseId) {
        Intent intent = new Intent(context.getApplicationContext(), EligibleCoupleDetailActivity.class);
        intent.putExtra(CASE_ID, caseId);
        context.startActivity(intent);
    }

    public static void navigateToANCProfile(android.content.Context context, String caseId) {
        Intent intent = new Intent(context.getApplicationContext(), ANCDetailActivity.class);
        intent.putExtra(CASE_ID, caseId);
        context.startActivity(intent);
    }

    public static void navigateToPNCProfile(android.content.Context context, String caseId) {
        Intent intent = new Intent(context.getApplicationContext(), PNCDetailActivity.class);
        intent.putExtra(CASE_ID, caseId);
        context.startActivity(intent);
    }

    public static void navigateToChildProfile(android.content.Context context, String caseId) {
        Intent intent = new Intent(context.getApplicationContext(), ChildDetailActivity.class);
        intent.putExtra(CASE_ID, caseId);
        context.startActivity(intent);
    }

    public static void navigateToKIProfile(Context context, String caseId) {
        Intent intent = new Intent(context.getApplicationContext(), KIDetailActivity.class);
        intent.putExtra(CASE_ID, caseId);
        context.startActivity(intent);
    }
}
