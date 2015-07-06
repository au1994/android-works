package com.example.asuspc.test_version2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by ASUS PC on 06-Jul-15.
 */
public class CustomCard extends Card {

    protected TextView mTitle;
    protected TextView mSecondaryTitle;
    protected TextView mThirdTitle;
    public String uid;
    void setuid(String id)
    {
        this.uid = id;
    }

    /**
     * Constructor with a custom inner layout
     * @param context
     */
    public CustomCard(Context context) {
        this(context, R.layout.carddemo_mycard_inner_content);
    }

    /**
     *
     * @param context
     * @param innerLayout
     */
    public CustomCard(Context context, int innerLayout) {
        super(context, innerLayout);
        //setTitle("title");

    }

    /**
     * Init
     */


    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        myCardElements obj = new myCardElements();

        //Retrieve elements
        mTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_title);
      //  mSecondaryTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_secondaryTitle);
      //  mThirdTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_ThirdTitle);

        String title = Search_Result.Ttitle;
        if (mTitle!=null)
            mTitle.setText(title);

       /* if (mSecondaryTitle!=null)
            mSecondaryTitle.setText("anku 2");

        if (mThirdTitle!=null)
            mThirdTitle.setText("anku 3");*/

    }
}
