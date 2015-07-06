package com.example.asuspc.test_version2;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import it.gmariotti.cardslib.library.cards.actions.BaseSupplementalAction;
import it.gmariotti.cardslib.library.cards.actions.TextSupplementalAction;
import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.base.BaseCard;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;


public class Search_Result extends Activity {

    String url=null;
    String pimage;
    int products_length;

    static String Ttitle;
    private static final String TITLE = "title";
    private static final String PRICE = "price";
    private static final String DATE = "date";
    static final String TAG_IMAGE = "image";
    private static final String STORE = "store";
    private static final String DESCRIPTION = "description";
    private static final String LINK="link";


    final int TOTAL_CARDS = 3;
    //private CardArrayAdapter
    private CardArrayRecyclerViewAdapter mCardArrayAdapter;
    private CardRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Card> cards = new ArrayList<Card>();

        mCardArrayAdapter = new CardArrayRecyclerViewAdapter(this, cards);

        //Staggered grid view
        CardRecyclerView mRecyclerView = (CardRecyclerView) findViewById(R.id.card_recyclerview);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set the empty view
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mCardArrayAdapter);
        }
        //fetching the keyword which was added using putExtra by MainActivity.java
        Bundle extras = getIntent().getExtras();
        String[] searchterm = extras.getStringArray("SEARCH");

        //Splitting the keyword using space delimiter
        //String[] temp = searchterm.split(" ");

       // url = "http://api-v1-dotmic.in/?start-index=0&max-results=10&ua=UA-3e773e7615539a54878fcb1b4c2fe0c6&search=";

        //adding the keyword to the url
        /*int i;
        url=url+temp[0];
        for(i=1;i<temp.length;i++)
            url=url+"+"+temp[i];*/


        //Load cards
        new LoaderAsyncTask(Search_Result.this,searchterm).execute();

    }



    class LoaderAsyncTask extends AsyncTask<Void, Void, ArrayList<Card>> {

        LoaderAsyncTask() {
        }
        String[] userdetails = new String[2];
        private ProgressDialog dialog;
        private ListActivity activity;
        public LoaderAsyncTask(Context activity,String[] searchterm) {
            context = activity;
            dialog = new ProgressDialog(context);
            userdetails[0]= searchterm[0];
            userdetails[1]= searchterm[1];
        }
        private Context context;

        //A method of AsyncTask class which executes before doInBackground method
        protected void onPreExecute() {
            this.dialog.setMessage("We are searching for you :)" + userdetails[0]);
            this.dialog.show();
        }



        @Override
        protected ArrayList<Card> doInBackground(Void... params) {
            //elaborate images
            SystemClock.sleep(1000); //delay to simulate download, don't use it in a real app

            MakePostCall objPost = new MakePostCall();
            JSONArray jarray= objPost.POST(userdetails);
            /*int len = jarray.length();
            JSONObject jsonObject=null;
            try {
                 jsonObject = jarray.getJSONObject(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String title = jsonObject.optString("title").toString();
            String uid = jsonObject.optString("uid").toString();*/
            //Toast.makeText(Search_Result.this, "jarray on " + temp, Toast.LENGTH_SHORT).show();
           /* JSONParser jParser = new JSONParser(); // get JSON data from URL
            JSONArray json = jParser.getJsonDataFromUrl(url);
            System.out.println("uptu here");
            products_length=json.length();
            System.out.println("product length"+ products_length);
            String[] name = new String[products_length];
            String[] price = new String[products_length];
            String[] store = new String[products_length];
            String[] img_url = new String[products_length];
            String[] lastModified = new String[products_length];
            String[] description = new String[products_length];
            String[] link = new String[products_length];


            for (int i = 0; i < json.length(); i++)
            {
                try {
                    JSONObject c = json.getJSONObject(i);
                    String title = c.getString(TITLE);
                    price[i] = c.getString(PRICE);
                    lastModified[i] = c.getString(DATE);
                    store[i] = c.getString(STORE);
                    img_url[i] = c.getString(TAG_IMAGE);
                    String des = c.getString(DESCRIPTION);
                    link[i] = c.getString(LINK);

                    name[i] = title.trim();


                    System.out.println("pimage" + pimage);

                    if(des.length()>0){
                        description[i] = des.trim();
                        }
                    else {
                        description[i]= "No descrition found";
                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }*/





            ArrayList<Card> cards = initCard(jarray);
            return cards;

        }

        @Override
        protected void onPostExecute(ArrayList<Card> cards) {

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            //Update the adapter
            updateAdapter(cards);
            //displayList();
        }
    }



    private ArrayList<Card> initCard(JSONArray jarray) {

        //Init an array of Cards
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < jarray.length(); i++) {

            JSONObject jsonObject=null;
            try {
                jsonObject = jarray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String title = jsonObject.optString("title").toString();
            String uid = jsonObject.optString("uid").toString();
            String price1 = jsonObject.optString("price1").toString();
            String price2 = jsonObject.optString("price2").toString();
            String price3 = jsonObject.optString("price3").toString();
            myCardElements obj_myCardElements= new myCardElements(uid,price1,price2,price3);
           /* obj_myCardElements.uid= uid;
            obj_myCardElements.price1=price1;
            obj_myCardElements.price2=price2;
            obj_myCardElements.price3=price3;*/
           // Toast.makeText(Search_Result.this, "uid " + obj_myCardElements.uid, Toast.LENGTH_SHORT).show();
            //CustomCard obj = new CustomCard(this);
            //obj.setUid(uid);
            Ttitle = obj_myCardElements.uid;
           // Card card = new CustomCard(this);
                Card card = new Card(this);
            card.setTitle(uid);

            //Create a CardHeader
            //CustomHeaderInnerCard header = new CustomHeaderInnerCard(this);
            CardHeader header = new CardHeader(this);
            //Set the header title
            header.setTitle(title);

            //Set visible the expand/collapse button
            header.setPopupMenu(R.menu.popupmain, new CardHeader.OnClickCardHeaderPopupMenuListener() {
                @Override
                public void onMenuItemClick(BaseCard card, MenuItem item) {
                    Toast.makeText(Search_Result.this, "Click on " + item.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

            //Add Header to card
            card.addCardHeader(header);


            //Add ClickListener
            card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                   Toast.makeText(Search_Result.this, "Click Listener card=" + card.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

            cards.add(card);
        }

        return cards;
    }

    private void updateAdapter(ArrayList<Card> cards) {
        if (cards != null) {
            mCardArrayAdapter.addAll(cards);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
