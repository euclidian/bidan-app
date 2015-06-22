package org.ei.bidan.bidan.view.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Strings;

import org.ei.bidan.R;
import org.ei.bidan.bidan.view.contract.AnakClient;
import org.ei.bidan.bidan.view.contract.KartuIbuClient;
import org.ei.bidan.util.EasyMap;
import org.ei.bidan.util.StringUtil;
import org.ei.bidan.view.contract.SmartRegisterClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.prototypes.CardWithList;
import it.gmariotti.cardslib.library.prototypes.LinearListView;

import static org.ei.bidan.util.EasyMap.create;

/**
 * Created by Dimas Ciputra on 6/20/15.
 */
public class DetailsNativeCard extends CardWithList {

    SmartRegisterClient client;

    public DetailsNativeCard(Context context) {
        super(context);
    }

    public void setClient(SmartRegisterClient client) {
        this.client = client;
    }

    @Override
    protected CardHeader initCardHeader() {
        // Add Header
        CardHeader header = new CardHeader(getContext(), R.layout.list_card_header) {
            @Override
            public void setupInnerViewElements(ViewGroup parent, View view) {
                super.setupInnerViewElements(parent, view);
                TextView subTitle = (TextView) view.findViewById(R.id.carddemo_googlenow_main_inner_lastupdate);
                if (subTitle != null) {
                    subTitle.setText("Ringkasan detail");
                }
            }
        };

        header.setTitle(client.name());
        return header;
    }

    @Override
    protected void initCard() {
        setSwipeable(true);
        setOnSwipeListener(new OnSwipeListener() {
            @Override
            public void onSwipe(Card card) {
                Toast.makeText(getContext(), "Swipe on " + card.getCardHeader().getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected List<ListObject> initKIChildren() {
        // Init the list
        List<ListObject> mObjects = new ArrayList<>();
        KartuIbuClient kartuIbuClient = (KartuIbuClient) client;

        // Add an object
        StockObject s1 = new StockObject(this);
        s1.subject = "Umur Ibu";
        s1.value = kartuIbuClient.ageInString() + " th";
        mObjects.add(s1);

        // Add an object
        StockObject s2 = new StockObject(this);
        s2.subject = "Nama Suami";
        s2.value = kartuIbuClient.husbandName();
        mObjects.add(s2);

        // Add an object
        StockObject s3 = new StockObject(this);
        s3.subject = "HTP";
        s3.value = kartuIbuClient.getEdd() + "";
        mObjects.add(s3);

        // Add an object
        StockObject s4 = new StockObject(this);
        s4.subject = "Dusun";
        s4.value = kartuIbuClient.village();
        mObjects.add(s4);

        StockObject s5 = new StockObject(this);
        s5.subject = "Posyandu";
        s5.value = kartuIbuClient.getPosyandu();
        mObjects.add(s5);

        StockObject s6 = new StockObject(this);
        s6.subject = "Golongan Darah";
        s6.value = kartuIbuClient.getGolongan_darah();
        mObjects.add(s6);

        StockObject s7 = new StockObject(this);
        s7.subject = "Puskesmas";
        s7.value = kartuIbuClient.getPuskesmas();
        mObjects.add(s7);

        StockObject s8 = new StockObject(this);
        s8.subject = "Agama";
        s8.value = kartuIbuClient.getReligion();
        mObjects.add(s8);

        StockObject s9 = new StockObject(this);
        s9.subject = "Edukasi";
        s9.value = kartuIbuClient.getEducation();
        mObjects.add(s9);

        StockObject s10 = new StockObject(this);
        s10.subject = "Pekerjaan";
        s10.value = kartuIbuClient.getJob();
        mObjects.add(s10);

        StockObject s11 = new StockObject(this);
        s11.subject = "Asuransi";
        s11.value = kartuIbuClient.getInsurance();
        mObjects.add(s11);

        StockObject s12 = new StockObject(this);
        s12.subject = "No Telepon";
        s12.value = kartuIbuClient.getPhoneNumber();
        mObjects.add(s12);

        return mObjects;
    }

    protected List<ListObject> initAnakChildren() {

        List<ListObject> mObjects = new ArrayList<>();
        AnakClient anakClient = (AnakClient) client;

        String service = anakClient.getServiceAtBirth();

        Map<String, String> map = new LinkedHashMap<>();

        map.put("Umur Anak", anakClient.format(anakClient.ageInDays()));
        map.put("Nama Ibu", anakClient.motherName());
        map.put("Nama Ayah", anakClient.fatherName());
        map.put("Tanggal Lahir", anakClient.dateOfBirth());
        map.put("Berat Lahir", anakClient.birthWeight());
        map.put("Berat Sekarang", anakClient.currentWeight());

        if(!Strings.isNullOrEmpty(service)) {
            String[] serviceAtBirth = service.split("\\s+");
            for(int i = 0; i < serviceAtBirth.length ; i++) {
                map.put("Pelayanan " + (i+1), StringUtil.humanize(serviceAtBirth[i]));
            }
        }

        Iterator it = map.entrySet().iterator();

        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            StockObject stockObject = new StockObject(this);
            stockObject.subject = pair.getKey() + "";
            stockObject.value = pair.getValue() + "";
            mObjects.add(stockObject);
        }

        return mObjects;
    }


    @Override
    protected List<ListObject> initChildren() {
        if (isAnKIClient(client)) {
            return initKIChildren();
        } else if (isAnakClient(client)) {
            return initAnakChildren();
        }

        return null;
    }

    @Override
    public View setupChildView(int i, ListObject listObject, View convertView, ViewGroup viewGroup) {
        //Setup the ui elements inside the item
        TextView textViewSubject = (TextView) convertView.findViewById(R.id.textViewCode);
        TextView textViewValue = (TextView) convertView.findViewById(R.id.textViewPerc);

        //Retrieve the values from the object
        StockObject stockObject = (StockObject) listObject;
        textViewSubject.setText(stockObject.subject);
        textViewValue.setText(stockObject.value);

        return convertView;
    }

    @Override
    public int getChildLayoutId() {
        return R.layout.detail_list_card_inner_main;
    }

    // -------------------------------------------------------------
    // Weather Object
    // -------------------------------------------------------------

    public class StockObject extends DefaultListObject {

        public String subject;
        public String value;

        public StockObject(Card parentCard) {
            super(parentCard);
            init();
        }

        private void init() {
            //OnClick Listener
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(LinearListView parent, View view, int position, ListObject object) {
                    Toast.makeText(getContext(), getObjectId() + " : " + getValue(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public String getObjectId() {
            return subject;
        }

        public String getValue() {
            return value;
        }
    }

    private boolean isAnKIClient(SmartRegisterClient client) {
        return client instanceof KartuIbuClient;
    }

    private boolean isAnakClient(SmartRegisterClient client) {
        return client instanceof AnakClient;
    }
}
