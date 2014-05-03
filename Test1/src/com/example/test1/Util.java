package com.example.test1;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;


import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.Toast;

public class Util {
	public static String DownloadText(String URL) throws IOException//웹 텍스트 받아오기
    {
        int BUFFER_SIZE = 2000;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return "";
        }
        
        try {
	        InputStreamReader isr = new InputStreamReader(in);
	        
	        int charRead;
	          String str = "";
	          char[] inputBuffer = new char[BUFFER_SIZE];          
	        try {
	            while ((charRead = isr.read(inputBuffer))>0)
	            {                    
	                //---convert the chars to a String---
	                String readString = 
	                    String.copyValueOf(inputBuffer, 0, charRead);                    
	                str += readString;
	                inputBuffer = new char[BUFFER_SIZE];
	            }
	            in.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "";
	        }    
	        return str;  
        } catch (NullPointerException e) {
        	return "";
        }
    }
	
	
	
	public static void toast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	
	
	public static void Destroy(ActivityManager acm, String packagename) {
		// acm : (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE)
		if(Build.VERSION.SDK_INT <= 7) {
			acm.restartPackage(packagename);
		} else {
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}
	public static Bitmap DownloadImage(String URL)//웹 이미지 받아오기
    {        
        Bitmap bitmap = null;
        InputStream in = null;        
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return bitmap;                
    }
	
	public static String SecToString(int sec) {
		String min = "";
    	if((sec/60) > 0) {
    		min = Integer.parseInt((sec/60)+"")+"분 ";
    	}
    	String second = "";
    	if(sec % 60 > 0) {
    		second = (sec%60)+"초";
    	}
    	
    	return min+second;
    	
	}
	
	public static String getTextContentSub(Node node) throws DOMException {
		String textContent = "";
		
		if(node.getNodeType() == Node.ATTRIBUTE_NODE) {
			textContent = node.getNodeValue();
		} else {
			Node child = node.getFirstChild();
			
			if(child != null) {
				Node sibling = child.getNextSibling();
				if(sibling != null) {
					StringBuffer sb = new StringBuffer();
					getTextContent(node, sb);
					textContent = sb.toString();
				} else {
					if(child.getNodeType() == Node.TEXT_NODE) {
						textContent = child.getNodeValue();
					} else {
						textContent = getTextContentSub(child);
					}
				}
			}
		}
		return textContent;
		
	}
	
	public static ProgressDialog getDialog(Context context, String title, String content) {
		ProgressDialog dlg = new ProgressDialog(context);
    	dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	dlg.setTitle(title);
    	dlg.setMessage(content);
    	dlg.setCancelable(true);
    	dlg.show();
    	return dlg;
	}
	
	
	
	///// private
	
	private static void getTextContent(Node node, StringBuffer sb) throws DOMException {
		Node child = node.getFirstChild();
		
		while(child != null) {
			if(child.getNodeType() == Node.TEXT_NODE) {
				sb.append(child.getNodeValue());
			} else {
				getTextContent(child, sb);
			}
			child = child.getNextSibling();
		}
	}
	
	
	private static InputStream OpenHttpConnection(String urlString) throws IOException
    {
        InputStream in = null;
        int response = -1;
               
        URL url = new URL(urlString); 
        URLConnection conn = url.openConnection();
                 
        if (!(conn instanceof HttpURLConnection))                     
            throw new IOException("Not an HTTP connection");
        
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect(); 

            response = httpConn.getResponseCode();                 
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();                                 
            }                     
        }
        catch (Exception ex)
        {
            throw new IOException("Error connecting");            
        }
        return in;     
    }
	



}
