package org.ei.drishti.repository;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import info.guardianproject.database.sqlcipher.SQLiteDatabase;
import org.ei.drishti.Context;
import org.ei.drishti.domain.Alert;

import java.util.Date;
import java.util.List;

public class RepositoryTest extends AndroidTestCase {
    public void testShouldCheckPassword() throws Exception {
        AlertRepository alertRepository = new AlertRepository();
        Repository repository = new Repository(new RenamingDelegatingContext(getContext(), "test_"), "drishti.db" + new Date().getTime(), alertRepository);
        Context.getInstance().session().setPassword("Hello");

        List<Alert> makeCallJustToInitializeRepository = alertRepository.allAlerts();

        assertTrue(repository.canUseThisPassword("Hello"));
        assertFalse(repository.canUseThisPassword("SOMETHING-ELSE"));
    }

    public void testShouldDeleteDatabaseCompletely() throws Exception {
        String dbName = "drishti.db" + new Date().getTime();
        AlertRepository alertRepository = new AlertRepository();
        Repository repository = new Repository(new RenamingDelegatingContext(getContext(), "test_"), dbName, alertRepository);
        Context.getInstance().session().setPassword("password");

        List<Alert> makeCallJustToInitializeRepository = alertRepository.allAlerts();
        assertTrue(repository.canUseThisPassword("password"));

        repository.deleteRepository();

        assertFalse(repository.canUseThisPassword("password"));

        SQLiteDatabase database = repository.getWritableDatabase();
        assertTrue(repository.canUseThisPassword("password"));
    }
}