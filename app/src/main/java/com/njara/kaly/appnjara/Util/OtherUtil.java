package com.njara.kaly.appnjara.Util;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

@SuppressLint("NewApi")
public class OtherUtil {

    public static String getKernelCode() {
        String buildNumber = "";
        try {
             buildNumber = runSysProperty("getprop ro.rksdk.version");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return  buildNumber;
    }

    public static String runSysProperty(String command) {
        String prop = "";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line = null;
            if ((line = in.readLine()) != null) {
                prop = line;
            }

            process.waitFor();

        } catch (Exception e) {
//            e.printStackTrace();
            prop = "";
        }
        return prop;
    }

    public static String getCurrentDateStr() {
        String curDateStr = "";

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        curDateStr = String.valueOf(year) + "";
        curDateStr += ((month < 10) ? "0" + String.valueOf(month) : String.valueOf(month)) + "";
        curDateStr += ((day < 10) ? "0" + String.valueOf(day) : String.valueOf(day));

        return curDateStr;
    }

    public static String getCurrentTimeStrWithSecond() {
        String curTimeSr = "";

        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        curTimeSr = ((hour < 10) ? "0" + String.valueOf(hour) : String.valueOf(hour)) ;
        curTimeSr += ((minute < 10) ? "0" + String.valueOf(minute) : String.valueOf(minute));
        curTimeSr += ((second < 10) ? "0" + String.valueOf(second) : String.valueOf(second));

        return curTimeSr;
    }

    public static String byteToHexString( byte[] b,int length) {
        String a = "";
        for (int i = 0; i < length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            a = a+hex;
        }

        return a;
    }
    public static Toast afficherToast(Activity depart, String texte)
    {
    	return Toast.makeText(depart, texte, Toast.LENGTH_SHORT);
    }
    public static Toast afficherToastLong(Activity depart, String texte)
    {
    	return Toast.makeText(depart, texte, Toast.LENGTH_LONG);
    }
    public static String getBoolean(boolean arg)
    {
    	if(arg) return "Oui";
    	return "Non";
    }
    public static String getBoolean(String arg)
    {
    	if(arg.equals("0")) return "false";
    	return "true";
    }
    public static String getStringByInt(int nb)
    {
    	return new Integer(nb).toString();
    }
    public static int stringToInt(String mot)
    {
    	return new Integer(mot).intValue();
    }
    public static String getStringByDouble(double nb)
    {
    	return new Double(nb).toString();
    }
    public static String formatMonnaie(double n)
    {
    	String rep="";
    	Double result=new Double(n); 
    	DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
    	DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
    	symbols.setGroupingSeparator(' ');
    	formatter.setDecimalFormatSymbols(symbols);
    	rep=formatter.format(result);
    	return rep;
    }
    public static String formatMonnaieSepciale(double n)
    {
    	double fin=OtherUtil.arrondi(n, 0);
        char[] vao = OtherUtil.getStringByDouble(fin).toCharArray();
        if (vao.length > 3)
        {
            int taille = vao.length, allongement, reste = taille % 3;
            if (reste != 0)
            {
                allongement = taille / 3;
            }
            else
            {
                allongement = (taille / 3) - 1;
            }
            int tailleNew = allongement + taille;
            char[] rep = new char[tailleNew];
            int incrementation = 3;
            for (int i = tailleNew - 1; i >= 0; i--)
            {
                if (i == tailleNew - 1 - incrementation)
                {
                    rep[i] = ' ';
                    incrementation += 4;
                    allongement -= 1;
                }
                else
                {
                    rep[i] = vao[i - allongement];
                }
            }
            return new String(rep);
        }
        return OtherUtil.getStringByDouble(n);
    }
   public static String upperFirst(String mot)
   {
	   String[]rep=mot.split(" ");
	   for(int i=0;i<rep.length;i++)
	   {
		   rep[i]=rep[i].substring(0,1).toUpperCase()+rep[i].substring(1);
	   }
	   String res=rep[0];
	   for(int i=1;i<rep.length;i++)
	   {
		   res+=" "+rep[i];
	   }
	   return res;
   }
   public static String abrev(String mot)
   {
	   String[]rep=mot.split(" ");
	   return OtherUtil.upperFirst(rep[0])+" "+rep[1].substring(0,1).toUpperCase()+".";
   }
   public static double arrondi(double a, int b) 
   {
	   return (double) ((int)(a*Math.pow(10, b)+.5))/Math.pow(10,b);
   }
   public static String setMonth(int month)
	{
		if(month<10) return "0"+month;
		return OtherUtil.getStringByInt(month);
	}
   public static void setCalendarStyle(DatePickerDialog d)
	{
		d.setCancelable(true);
		d.getDatePicker().setCalendarViewShown(false);
	}
   public static String byteArrayToString (byte[] data, int length)
	{
		String str = "";
		int indx = 0;

		while((data[indx] & 0xFF) != 0x00)
		{	
			str  += (char)(data[indx] & 0xFF);				
			indx++;
			if (indx == length)
				break;
		}
		
		return str;
	}
   public static byte[] constructBuffer(byte blockNo, int value)
   {
		byte[] result = new byte[16];
		for (int i = 0; i < 4; i++) {
			result[i] = (byte) (value % 0x100);
			value /= 0x100;
		}
		// 2nd and 3rd 4 bytes
		for (int i = 0; i < 4; i++) {
			result[4 + i] = (byte) ~result[i];
			result[8 + i] = result[i];
		}

		// last 4 bytes
		result[12] = blockNo;
		result[13] = (byte) ~blockNo;
		result[14] = blockNo;
		result[15] = (byte) ~blockNo;
		return result;
	}
   public static byte[] getBuffer(String value) throws NumberFormatException, UnsupportedEncodingException
   {		
		StringBuffer buff = new StringBuffer(16); 
		for(int i=0;i<value.length();i++)
		{
			String temp=""+value.charAt(i);
			int c=temp.charAt(0); 
			buff.append(Integer.toHexString(c)); 
		}
		for(int i=value.length();i<16;i++)
		{
			buff.append("00");
		}
		byte[] rep = new byte[buff.toString().length() / 2];
		for (int i=0; i<rep.length; i++)
		{
		     Byte byte2 = Byte.parseByte(buff.toString().substring(i*2, i*2+2), 16);
		     rep[i] = byte2;
		}
		//System.out.println(Arrays.toString(array2));
		return rep;
   }
   public static int byteToInt(byte[] value) {
		int result = 0;
		for (int i = 3; i >= 0; i--) {
			result = result * 0x100 + (value[i] & 0xff);
		}
		return result;
	}
}
