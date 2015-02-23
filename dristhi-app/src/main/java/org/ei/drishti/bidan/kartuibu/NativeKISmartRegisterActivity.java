package org.ei.drishti.bidan.kartuibu;

import android.view.View;

import org.ei.drishti.R;
import org.ei.drishti.adapter.SmartRegisterPaginatedAdapter;
import org.ei.drishti.bidan.kartuibu.AllKartuIbuServiceMode;
import org.ei.drishti.bidan.kartuibu.KIClientsProvider;
import org.ei.drishti.bidan.kartuibu.KartuIbuRegisterController;
import org.ei.drishti.bidan.kartuibu.WifeAgeSort;
import org.ei.drishti.domain.form.FieldOverrides;
import org.ei.drishti.provider.SmartRegisterClientsProvider;
import org.ei.drishti.view.activity.SecuredNativeSmartRegisterActivity;
import org.ei.drishti.view.controller.VillageController;
import org.ei.drishti.view.dialog.AllClientsFilter;
import org.ei.drishti.view.dialog.DialogOption;
import org.ei.drishti.view.dialog.DialogOptionMapper;
import org.ei.drishti.view.dialog.FilterOption;
import org.ei.drishti.view.dialog.NameSort;
import org.ei.drishti.view.dialog.ServiceModeOption;
import org.ei.drishti.view.dialog.SortOption;

import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Iterables.toArray;
import static org.ei.drishti.AllConstants.FormNames.KARTU_IBU_REGISTRATION;

/**
 * Created by Dimas Ciputra on 2/18/15.
 */
public class NativeKISmartRegisterActivity extends SecuredNativeSmartRegisterActivity {

    private SmartRegisterClientsProvider clientProvider = null;
    private KartuIbuRegisterController controller;
    private VillageController villageController;
    private DialogOptionMapper dialogOptionMapper;

    private final ClientActionHandler clientActionHandler = new ClientActionHandler();

    @Override
    protected SmartRegisterPaginatedAdapter adapter() {
        return new SmartRegisterPaginatedAdapter(clientsProvider());
    }

    @Override
    protected DefaultOptionsProvider getDefaultOptionsProvider() {
        return new DefaultOptionsProvider() {

            @Override
            public ServiceModeOption serviceMode() {
                return new AllKartuIbuServiceMode(clientsProvider());
            }

            @Override
            public FilterOption villageFilter() {
                return new AllClientsFilter();
            }

            @Override
            public SortOption sortOption() {
                return new NameSort();
            }

            @Override
            public String nameInShortFormForTitle() {
                return getResources().getString(R.string.ki_register_title_in_short);
            }
        };
    }

    @Override
    protected NavBarOptionsProvider getNavBarOptionsProvider() {
        return new NavBarOptionsProvider() {

            @Override
            public DialogOption[] filterOptions() {
                return new DialogOption[]{new AllClientsFilter()};
            }

            @Override
            public DialogOption[] serviceModeOptions() {
                return new DialogOption[]{};
            }

            @Override
            public DialogOption[] sortingOptions() {
                return new DialogOption[]{new NameSort(), new WifeAgeSort()};
            }

            @Override
            public String searchHint() {
                return getString(R.string.str_ki_search_hint);
            }
        };
    }

    @Override
    protected SmartRegisterClientsProvider clientsProvider() {
        if (clientProvider == null) {
            clientProvider = new KIClientsProvider(
                    this, clientActionHandler, controller);
        }
        return clientProvider;
    }

    @Override
    protected void onInitialization() {
        controller = new KartuIbuRegisterController(context.allKartuIbus(),
                context.listCache(),context.kiClientsCache());
        villageController = new VillageController(context.allEligibleCouples(),
                context.listCache(), context.villagesCache());
        dialogOptionMapper = new DialogOptionMapper();
    }

    @Override
    protected void startRegistration() {
        FieldOverrides fieldOverrides = new FieldOverrides(context.anmLocationController().getLocationJSON());
        startFormActivity(KARTU_IBU_REGISTRATION, null, fieldOverrides.getJSONString());
    }

    private class ClientActionHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.profile_info_layout:
                    // showProfileView((ECClient) view.getTag());
                    break;
                case R.id.btn_edit:
                    // showFragmentDialog(new EditDialogOptionModel(), view.getTag());
                    break;
            }
        }

//        private void showProfileView(ECClient client) {
//            navigationController.startEC(client.entityId());
//        }
    }
}
