package com.njara.kaly.appnjara.Util;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by NJARA on 17/05/2016.
 */
public class UtilBean {

    public static Class[] getListedFieldType(Object objet){
        int nombre=objet.getClass().getDeclaredFields().length;

        Class[] arg0=new Class[nombre];
        for(int i=0;i<nombre;i++){
            arg0[i]=objet.getClass().getDeclaredFields()[i].getType();
        }
        return arg0;

    }
    public static Field[] getListOfFields(Object objet){
        Field[] f =objet.getClass().getDeclaredFields();
        return f;

    }
    public static String upperFirstLetter(String att){
        String s=att.toUpperCase().substring(0, 1)+att.substring(1);
        return s;

    }
    public  static String buildCriteria(Object objet) throws NoSuchMethodException{

        String requette=" where ";
        String nameObjet=objet.getClass().getSimpleName();
        nameObjet=upperFirstLetter(nameObjet);

        Object attr=null;
        try{
            Field[] field= getListOfFields(objet);
            for(int i=0;i<field.length;i++){
                Method m= objet.getClass().getDeclaredMethod("get"+upperFirstLetter(field[i].getName()));
                String rep= Utilitaire.toString(m.invoke(objet,null));

                if(rep!=null)
                    requette=requette+field[i].getName()+"='"+rep+"' and ";


            }
            Method mSup= objet.getClass().getSuperclass().getDeclaredMethod("getId");
            Integer repSup=(Integer)mSup.invoke(objet,null);
            if(repSup!=null && repSup.intValue()!=0){
                requette=requette+"id"+nameObjet+"='"+repSup.intValue()+"' and ";
            }
            requette=requette+" 1<2 ";
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        Log.e("requettteeeeeeeeeee", requette);
        return requette;
    }
    public static Object createObject(Cursor cursor,Object object) {
        Object  obResultat=null;
        try{
            obResultat=object.getClass().newInstance();
            Class[] argument =new Class[1];
            argument[0]=String.class;
            Class []arg0= getListedFieldType(object);
            Field[] field= getListOfFields(object);
            Class  []  inte=new  Class[1];
            inte[0]=int.class;

            int  i=0;
            for(i=0;i<arg0.length;i++){
                argument[0]=arg0[i];
                Method m= object.getClass().getDeclaredMethod("set" + upperFirstLetter(field[i].getName()), argument);
                m.invoke(obResultat,cursor.getString(cursor.getColumnIndex(field[i].getName())));
                Log.e(""+field[i].getName(),cursor.getString(cursor.getColumnIndex(field[i].getName())));
            }
            Method  methSup=object.getClass().getSuperclass().getDeclaredMethod("setId",inte);
            Integer id=new Integer(cursor.getString(cursor.getColumnIndex("_id")));

            methSup.invoke(obResultat, id.intValue());

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return obResultat;
    }
    public static String buildInsert(Object objet) throws Exception{

        String TABLE_NAME=objet.getClass().getSimpleName();
        String nameObjet=objet.getClass().getSimpleName();
        nameObjet=upperFirstLetter(nameObjet);
        String build ="insert into "+TABLE_NAME+"(";
        String value="values('";
        Field[] field = getListOfFields(objet);
        for(int i=0;i<field.length;i++){
            Method m= objet.getClass().getDeclaredMethod("get"+upperFirstLetter(field[i].getName()));
            if(i==field.length-1){

                build=build+field[i].getName()+") ";
                value=value+m.invoke(objet,null)+"')";
            }
            else{
                build=build+field[i].getName()+",";
                value=value+m.invoke(objet,null)+"','";
            }
        }
        build=build+value;

        return build;

    }
    public static String buildUpdate(Object objet) throws Exception{
        String nameObjet=objet.getClass().getSimpleName();
        nameObjet=upperFirstLetter(nameObjet);
        String column="";
        String criteria="";
        Method mSup= objet.getClass().getSuperclass().getDeclaredMethod("getId");
        Integer repSup=(Integer)mSup.invoke(objet,null);
        criteria=" where id"+nameObjet+"="+repSup;

        Field[] fields=objet.getClass().getDeclaredFields();
        for(int i=0; i<fields.length; i++)
        {
            Method m= objet.getClass().getDeclaredMethod("get"+upperFirstLetter(fields[i].getName()));
            String rep= Utilitaire.toString(m.invoke(objet, null));
            if(rep!=null){
                if(i==fields.length-1)
                {
                    column=column+fields[i].getName().toString()+"='"+rep+"'";

                }
                else column=column+fields[i].getName().toString()+"='"+rep+"', ";
            }
        }
        String result="update "+nameObjet+" set "+column+", id"+nameObjet+"="+repSup+" "+criteria;
        return result;
    }
    public static  ArrayList<String> ToList(ArrayList<Object> list, String champ) throws Exception{
        ArrayList<String> listString=new ArrayList<String>();
        for(int i=0;i<list.size();i++){
            Method m= list.get(i).getClass().getDeclaredMethod("get"+upperFirstLetter(champ));
            String rep=(String)m.invoke(list.get(i),null);
            listString.add(rep);
        }
        return listString;
    }

    /* contents values*/
    public static ContentValues GetContentValues(Object objet) throws Exception {
        ContentValues contentValues=new ContentValues();

        Field[] fields=objet.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++) {
            Method m= objet.getClass().getDeclaredMethod("get" + UtilBean.upperFirstLetter(fields[i].getName()));
            String rep= Utilitaire.toString(m.invoke(objet, null));
            if(rep!=null) {
                contentValues.put(fields[i].getName().toUpperCase(), rep);
            }
            else
                contentValues.put(fields[i].getName().toUpperCase(), (byte[]) null);
        }
        return contentValues;
    }
}
