package org.ei.bidan.bidan.view.activity;

import android.view.View;

import org.ei.bidan.R;
import org.ei.bidan.adapter.SmartRegisterPaginatedAdapter;
import org.ei.bidan.bidan.provider.KartuIbuANCClientsProvider;
import org.ei.bidan.bidan.provider.KartuIbuPNCClientsProvider;
import org.ei.bidan.bidan.view.controller.KartuIbuANCRegisterController;
import org.ei.bidan.bidan.view.controller.KartuIbuPNCRegisterController;
import org.ei.bidan.bidan.view.dialog.KartuIbuANCOverviewServiceMode;
import org.ei.bidan.bidan.view.dialog.KartuIbuPNCOverviewServiceMode;
import org.ei.bidan.domain.form.FieldOverrides;
import org.ei.bidan.provider.SmartRegisterClientsProvider;
import org.ei.bidan.view.dialog.AllClientsFilter;
import org.ei.bidan.view.dialog.DialogOption;
import org.ei.bidan.view.dialog.FilterOption;
import org.ei.bidan.view.dialog.NameSort;
import org.ei.bidan.view.dialog.ServiceModeOption;
import org.ei.bidan.view.dialog.SortOption;

import static org.ei.bidan.AllConstants.FormNames.KARTU_IBU_ANC_REGISTRATION;
import static org.ei.bidan.AllConstants.FormNames.KARTU_IBU_PNC_REGISTRATION;

/**
 * Created by Dimas Ciputra on 3/5/15.
 */
public class NativeKIPNCSmartRegisterActivity extends BidanSecuredNativeSmartRegisterActivity{

    private SmartRegisterClientsProvider clientProvider = null;
    private KartuIbuPNCRegisterController controller;
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
                return new KartuIbuPNCOverviewServiceMode(clientsProvider());
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
                return getResources().getString(R.string.anc_label);
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
                return new DialogOption[]{new NameSort()};
            }

            @Override
            public String searchHint() {
                return getString(R.string.str_ki_search_hint);
            }
        };
    }

    @Override
    protected SmartRegisterClientsProvider clientsProvider() {
        if(clientProvider == null) {
            clientProvider = new KartuIbuPNCClientsProvider(this, clientActionHandler, controller);
        }
        return clientProvider;
    }

    @Override
    protected void onInitialization() {
        controller = new KartuIbuPNCRegisterController(context.allIbu(),
                context.listCache(),context.kartuIbuPNCClientsCache());
    }

    @Override
    protected void startRegistration() {
        FieldOverrides fieldOverrides = new FieldOverrides(context.anmLocationController().getLocationJSON());
        startFormActivity(KARTU_IBU_PNC_REGISTRATION, null, fieldOverrides.getJSONString());
    }

    private class ClientActionHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.profile_info_layout:
                    // TODO : show info of timeline event
                    // showProfileView((ECClient) view.getTag());
                    break;
                case R.id.btn_edit:
                    // TODO : show edit dialog for add ANC and PNC
                    // showFragmentDialog(new EditDialogOptionModel(), view.getTag());
                    break;
            }
        }

    }
}
