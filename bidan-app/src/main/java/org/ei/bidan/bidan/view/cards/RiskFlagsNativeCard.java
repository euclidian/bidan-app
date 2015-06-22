package org.ei.bidan.bidan.view.cards;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.ei.bidan.R;
import org.ei.bidan.bidan.domain.Bidan;
import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.bidan.view.contract.BidanSmartRegisterClient;
import org.ei.bidan.bidan.view.contract.KartuIbuClient;
import org.ei.bidan.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.prototypes.CardWithList;
import it.gmariotti.cardslib.library.prototypes.LinearListView;

/**
 * Created by Dimas Ciputra on 6/20/15.
 */
public class RiskFlagsNativeCard extends CardWithList {

    BidanSmartRegisterClient bidanClient;

    public RiskFlagsNativeCard(Context context) { super(context); }

    public void setBidanClient(BidanSmartRegisterClient bidanClient) {
        this.bidanClient = bidanClient;
    }

    @Override
    protected CardHeader initCardHeader() {
        // Add Header
        CardHeader header = new CardHeader(getContext(), R.layout.list_card_header) {
            @Override
            public void setupInnerViewElements(ViewGroup parent, View view) {
                super.setupInnerViewElements(parent, view);
                TextView subTitle = (TextView) view.findViewById(R.id.carddemo_googlenow_main_inner_lastupdate);
                if(subTitle!=null) {
                    subTitle.setText("Daftar resiko yang dimiliki");
                }
            }
        };

        header.setTitle("Resiko");
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

    @Override
    protected List<ListObject> initChildren() {

        List<ListObject> mObject = new ArrayList<>();

        for(String reason : bidanClient.highRiskReason()) {
            StockObject highRiskReason = new StockObject(this);
            highRiskReason.value = StringUtil.humanize(reason);
            highRiskReason.subject = "high_risk";
            mObject.add(highRiskReason);
        }

        for(String reason1 : bidanClient.highPregnancyReason()) {
            StockObject highRiskPregnancyReason = new StockObject(this);
            highRiskPregnancyReason.value = StringUtil.humanize(reason1);
            highRiskPregnancyReason.subject = "high_risk_pregnancy";
            mObject.add(highRiskPregnancyReason);
        }

        for(String reason : bidanClient.highRiskLabourReason()) {
            StockObject highRiskLabourReason = new StockObject(this);
            highRiskLabourReason.value = StringUtil.humanize(reason);
            highRiskLabourReason.subject = "high_risk_labour";
            mObject.add(highRiskLabourReason);
        }

        for(String reason : bidanClient.highRiskPostPartumReason()) {
            StockObject hrReason = new StockObject(this);
            hrReason.value = reason;
            hrReason.subject = "high_risk_post_partum";
            mObject.add(hrReason);
        }

        return mObject;
    }

    @Override
    public View setupChildView(int i, ListObject listObject, View convertView, ViewGroup viewGroup) {
        //Setup the ui elements inside the item
        TextView textViewSubject = (TextView) convertView.findViewById(R.id.text_view_reason);
        ImageView hrpBadge = (ImageView) convertView.findViewById(R.id.img_hp_badge);
        ImageView rtpBadge = (ImageView) convertView.findViewById(R.id.img_rtp_badge);
        ImageView rtkBadge = (ImageView) convertView.findViewById(R.id.img_rtk_badge);
        ImageView hrppBadge = (ImageView) convertView.findViewById(R.id.img_hrpp_badge);

        //Retrieve the values from the object
        StockObject stockObject = (StockObject) listObject;
        textViewSubject.setText(stockObject.value);

        if(stockObject.subject.equalsIgnoreCase("high_risk")) {
            hrpBadge.setVisibility(View.VISIBLE);
        } else if(stockObject.subject.equalsIgnoreCase("high_risk_labour")) {
            rtpBadge.setVisibility(View.VISIBLE);
        } else if(stockObject.subject.equalsIgnoreCase("high_risk_pregnancy")) {
            rtkBadge.setVisibility(View.VISIBLE);
        } else if(stockObject.subject.equalsIgnoreCase("high_risk_post_partum")) {
            hrppBadge.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    @Override
    public int getChildLayoutId() {
        return R.layout.risk_flags_list_card_inner_main;
    }

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

        public String getValue() { return value; }
    }
}
