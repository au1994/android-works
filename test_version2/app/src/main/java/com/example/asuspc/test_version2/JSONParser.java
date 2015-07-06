package com.example.asuspc.test_version2;


import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * This class(JSONParser) is to send the request to the given API 
 * Get the json data from the url
 * Parse the data and then return the JSONArray
 * */
public class JSONParser {
	static InputStream iStream = null;
	static JSONArray jarray = null;
	static String json = ""; 
	String line;
	public JSONParser() {};
	
	public JSONArray getJsonDataFromUrl(String url) { 
		
		//Search result is needed as a string, which we will build from the response we receive
		StringBuilder sb = new StringBuilder();
		
		//Creating an HTTP Client object for executing the request:
		HttpClient client = new DefaultHttpClient();
		
		//Creating an HTTP Get object to issue the request, passing the search URL as parameter
		HttpGet httpGet = new HttpGet(url);
		
		//Now we need to catch I/O exceptions, 
		//as is the case when we attempt to fetch data from anywhere outside our application
		try { 
			
			
			/*
			 * Executing the request, storing the results in an HTTP Response object.
			 * We will use this response object to access.
			 * the content and status of the response message received from the API
			*/
			HttpResponse response = client.execute(httpGet);
			
			//Checking the status before processing the response message.
			StatusLine sLine = response.getStatusLine();
			int statusCode = sLine.getStatusCode(); 
			
			//If the response is OK we can carry on and attempt to parse the data, 
			//otherwise we will output an error message to the user.
			if (statusCode == 200) {
				
				//We now retrieve the HTTP Entity and message content as an Input Stream
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				
				//Now we can begin bringing the message content into the program, using a Buffered Reader for managing the incoming data
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				
				//Now reading the data a line at a time, appending each line to the String Builder we created
				while ((line = reader.readLine()) != null) { 

					sb.append(line); 
				}
			}
			else { 
				Log.e("==>", "Failed to download file");
			}
		}
		catch (ClientProtocolException e) { 
			e.printStackTrace(); 
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// Parse String to JSONArray object
		try { 
			jarray = new JSONArray( sb.toString()); 
		}
		catch (JSONException ex) {
			System.out.println("parser error");

			Log.e("Parsing", "Error parsing data " + ex.toString()); 
		}
		// return JSON Object 
		return jarray;
	}
}



