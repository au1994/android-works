package com.example.asuspc.test_version2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * Created by ASUS PC on 06-Jul-15.
 */
public class CustomHeaderInnerCard extends CardHeader {

    public CustomHeaderInnerCard(Context context) {
        super(context, R.layout.carddemo_example_inner_header);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        if (view!=null){
            TextView t1 = (TextView) view.findViewById(R.id.text_example1);
            if (t1!=null)
                t1.setText("anku 1");

            TextView t2 = (TextView) view.findViewById(R.id.text_example2);
            if (t2!=null)
                t2.setText("anku 2");
            TextView t3 = (TextView) view.findViewById(R.id.text_example3);
            if (t3!=null)
                t3.setText("anku 3");
            TextView t4 = (TextView) view.findViewById(R.id.text_example4);
            if (t4!=null)
                t4.setText("anku 4");
        }
    }
}
