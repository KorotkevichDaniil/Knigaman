package com.example.bank;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Loading  extends AsyncTask<String, Integer, ArrayList<Valute>> {

    HttpsURLConnection connection = null;
    final LoadingTaskFinishedListener finishedListener;
    ArrayList<Valute> valutes = new ArrayList<>();
    BufferedReader reader = null;
    String content, da;
    final String textSource = "https://www.cbr-xml-daily.ru/daily_utf8.xml";
    public Loading(LoadingTaskFinishedListener finishedListener) {
        this.finishedListener = finishedListener;
    }

    @Override
    protected ArrayList<Valute> doInBackground(String... strings) {
        if (resourceDontAlreadyExist())
            downloadResources();

        XmlPullParserFactory factory = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            String charc = "";
            String val = "";
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(content));
            boolean flag = false;
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {

                String tagName = xpp.getName();


                switch (xpp.getEventType()){
                    case XmlPullParser.START_TAG:

                        if("CharCode".equalsIgnoreCase(tagName)) {
                            charc = xpp.nextText();
                            switch(charc){
                                case "USD":
                                    flag = true;
                                    break;
                                case "EUR":
                                    flag = true;
                                    break;
                                case "CAD":
                                    flag = true;
                                    break;
                                default:

                            }

                        }
                        if("Value".equalsIgnoreCase(tagName)){
                            if(flag){
                                val =  xpp.nextText();
                                valutes.add(new Valute(charc,val));
                                flag = false;
                            }

                        }
                        break;
                }

                xpp.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return valutes;
    }

    private boolean resourceDontAlreadyExist() {
        return true;
    }

    String list = "";

    private void downloadResources() {
        try {
            URL url = new URL(textSource);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            StringBuilder buf = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null)
                buf.append(line).append("\n");
            content = buf.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Valute> valutes) {
        super.onPostExecute(valutes);
        finishedListener.onTaskFinished(valutes);

    }

    interface LoadingTaskFinishedListener {
        void onTaskFinished(ArrayList<Valute> valutes);

    }
}
