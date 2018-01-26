package com.njara.kaly.appnjara.Util;

import android.annotation.SuppressLint;
import android.nfc.tech.MifareClassic;

@SuppressLint("NewApi")
public class UtilCard {
	
	//mifareClassic
	public String getData(MifareClassic mfc,int sector, int block){
    	String rep="";
    	int bCount = 0;
        int bIndex = 0;
        byte[] data;
    	 try {
			mfc.connect();
			 boolean auth = false;
	    	 auth = mfc.authenticateSectorWithKeyA(sector, MifareClassic.KEY_DEFAULT);
	         if(auth){
	        	 bIndex = mfc.sectorToBlock(sector);
                 // 6.3) Read the block
                 data = mfc.readBlock(bIndex);  
                 rep= OtherUtil.byteArrayToString(data, data.length);
	         }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	return rep;
    }
	public void updateData(MifareClassic mfc, int block, String valeur){
		
		 boolean auth = false;
		 int sector =block/4;
    	 try {
    		 mfc.connect();
			auth = mfc.authenticateSectorWithKeyA(sector, MifareClassic.KEY_DEFAULT);
			if(auth){
	        	mfc.sectorToBlock(sector);
	        	 mfc.writeBlock(block, OtherUtil.getBuffer(valeur));
	         }
			mfc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
	}
	//
	public String getData(MifareClassic mfc, int block){
		int sector =block/4;
    	String rep="";
    	int bCount = 0;
        int bIndex = 0;
        byte[] data;
    	 try {
			
			 boolean auth = false;
	    	 auth = mfc.authenticateSectorWithKeyA(sector, MifareClassic.KEY_DEFAULT);
	         if(auth){
	        	 bIndex = mfc.sectorToBlock(sector);
                 // 6.3) Read the block
	        	 
                 data = mfc.readBlock(bIndex);  
                 rep=OtherUtil.byteArrayToString(data, data.length);
	         }
	         
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	return rep;
    }
	
}
