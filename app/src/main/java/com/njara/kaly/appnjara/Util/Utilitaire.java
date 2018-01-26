package com.njara.kaly.appnjara.Util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by NJARA on 17/05/2016.
 */
public class Utilitaire {
    public static String toString(Object objet){
        String rep=null;
        if(objet==null)
            return null;
        if(objet instanceof Integer){
            Integer in=(Integer)objet;
            if(in.intValue()==0)return null;

            rep=in.toString();

        }
        if(objet instanceof Double){
            Double in=(Double)objet;
            if(in.doubleValue()==0)return null;

            rep=in.toString();

        }
        if(objet instanceof String){
            String in=(String)objet;

            rep=in;
        }
        if(objet instanceof Date){
            Date d=(Date)objet;
            //rep=Utilitaire.getDateFormatString(d);
            rep=d.toString();
        }
        if(objet instanceof java.util.Date){
            java.util.Date d=(java.util.Date)objet;
            rep=Utilitaire.getDateFormatString(d);
            //rep=d.toString();
        }
        if(objet instanceof Boolean){
            Boolean d=(Boolean)objet;
            rep=d.toString();
        }
        if(objet instanceof Time){
            Time t=(Time)objet;
            rep=t.toString();
        }
        if(rep.equals("")) return null;
        return rep;
    }
    public static double convert(double valeur){
        BigDecimal bd = new BigDecimal(valeur);

        bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
        return bd.doubleValue();
    }
    public static double convertUP(double valeur){
        BigDecimal bd = new BigDecimal(valeur);

        bd= bd.setScale(1,BigDecimal.ROUND_UP);
        return bd.doubleValue();
    }
    public static double convert(String valeur){
        Double d=new Double(valeur);
        return convert(d);
    }
    public static String format(String prix){

        String rep="";
        Double result=new Double(prix);
        rep=format(result.doubleValue());
        return rep;
    }

    public static String format(double prix){
        String rep="";
        Double result=new Double(prix);
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);
        formatter.setMinimumFractionDigits(2);

        rep=formatter.format(result);
        return rep;
    }


    /* Utilitaire date*/
    public static java.util.Date getDateFormat(java.util.Date date){
        java.util.Date d=new java.util.Date();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String dat=formatter.format(d);
        return getDateFormat(dat);
    }
    public static java.util.Date getDateFormat(){
        java.util.Date d=new java.util.Date();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String dat=formatter.format(d);
        System.out.println("daaaaaa:" + dat);
        return getDateFormat(dat);
    }

    public static String getDateFormatString(java.util.Date date){

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String dat=formatter.format(date);
        return dat;
    }
    public static String getDateFormatString(){
        java.util.Date d=new java.util.Date();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String dat=formatter.format(d);
        return dat;
    }
    public static java.util.Date getDateFormat(String date1){
        java.util.Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = simpleDateFormat.parse(date1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
    public static java.util.Date getDateFormat2(String date1){
        java.util.Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
        try {
            date = simpleDateFormat.parse(date1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
    public static java.util.Date getDateFormatStandard(String date1){
        if(date1.contains("."))
            date1= date1.replace(".","/");
        else if(date1.contains("-"))
            date1=date1.replace("-","/");

        java.util.Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
        try {
            date = simpleDateFormat.parse(date1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public  static String getPath() {
        //make a new file directory inside the "sdcard" folder
        File mediaStorageDir = new File("/sdcard/");

        //if this "JCGCamera folder does not exist
        if (!mediaStorageDir.exists()) {
            //if you cannot make this folder return
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        //take the current timeStamp
        File mediaFile;
        //and make a media file:
        String rep=mediaStorageDir.getPath();
        return rep;
    }
    public  static String getPath(int id) {
        //make a new file directory inside the "sdcard" folder
        File mediaStorageDir = new File("/sdcard/", "Produits");

        //if this "JCGCamera folder does not exist
        if (!mediaStorageDir.exists()) {

            //if you cannot make this folder return
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        //take the current timeStamp
        File mediaFile;
        //and make a media file:
        String rep=mediaStorageDir.getPath() + File.separator +id+".png";
        return rep;
    }
    public static void NextIntent(Context startIntent, Class endIntent) {

        startIntent.startActivity(new Intent(startIntent, endIntent));
    }

    public static void calling(Context depart, String numero) {
        TelephonyManager manager = (TelephonyManager) depart
                .getSystemService(depart.TELEPHONY_SERVICE);
       /* PhoneListener phoneListner = new PhoneListener(depart);
        manager.listen(phoneListner, PhoneStateListener.LISTEN_CALL_STATE);*/
        Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse(numero));
        depart.startActivity(intent2);
    }

    public static String formaterNumero(String numero) {
        return "tel:+261" + numero.trim().substring(10);
    }

    public static void envoyerEmail(Context depart, String mail) {
        Intent send = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                mail, null));
        depart.startActivity(Intent.createChooser(send, "Continuer avec"));
    }

    public static void ouvrirPage(Context depart, String mail) {
        if (!mail.startsWith("http://") && !mail.startsWith("https://"))
            mail = "http://" + mail;
        Intent send = new Intent(Intent.ACTION_VIEW, Uri.parse(mail));
        depart.startActivity(Intent.createChooser(send, "Continuer avec"));
    }


    public static List<HashMap<String, String>> convertToHashMap(String[][] mot, String key1, String key2) {
        List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> element;
        for (int i = 0; i < mot.length; i++)
        {
            element = new HashMap<String, String>();
            element.put(key1, mot[i][0]);
            element.put(key2, mot[i][1]);
            liste.add(element);
        }
        return liste;
    }
}
