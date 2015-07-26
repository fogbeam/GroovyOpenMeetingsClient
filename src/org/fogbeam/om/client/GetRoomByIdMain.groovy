package org.fogbeam.om.client


import javax.xml.bind.JAXBContext
import javax.xml.bind.Unmarshaller
import javax.xml.transform.Source
import javax.xml.transform.stream.StreamSource

import org.fogbeam.om.client.dto.GetRoomResponse

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.6')
import groovyx.net.http.RESTClient

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.XML

class GetRoomByIdMain 
{

	static main(args) 
	{
		
		/***** make RestClient call to get XML *****/
		
		def client = new RESTClient( 'http://demo2.fogbeam.org:5080/' )
		
		
		// call getSession
		def resp = client.get( path : 'openmeetings/services/UserService/getSession', contentType:XML );
		String sid = resp.data.return.session_id;
		println "sessionId: $sid";
		
		// call login using the SID from getSession
		resp = client.get( path : 'openmeetings/services/UserService/loginUser', contentType:XML, query: [ SID:sid, username:'prhodes', 
																												userpass:'password' ] );
		// String xml = convertStringReaderToString( resp.data );
		println "response from login: " + resp.data;
		// sid = resp.data.return.session_id;
		
		
		resp = client.get( path : 'openmeetings/services/RoomService/getRoomById', contentType: TEXT, 
							query:[ SID:sid, rooms_id:7 ] );
				
		
		if( !( resp.status == 200 )) // HTTP response code; 404 means not found, etc.
		{
			throw new Exception( "Not 200 HTTP Response!   ${resp.status}" );
		}  
		else
		{	
			/***** bind XML to Groovy object using JAXB ******/
	
			
			JAXBContext jaxbContext = JAXBContext.newInstance(GetRoomResponse.class)
			
			//class responsible for the process of de-serializing XML data into Groovy object
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Source source = new StreamSource(resp.data)
			 
			GetRoomResponse response = (GetRoomResponse) jaxbUnmarshaller.unmarshal(source)
		
			println "Got room: ${response.room.id} + name: ${response.room.name}";
			
		}
		
		println "done";
	}

	
	static String convertStringReaderToString( StringReader reader )
	{
		int charsRead = -1;
		char[] chars = new char[100];
		StringBuilder builder = new StringBuilder();
		while( (charsRead = reader.read(chars,0,chars.length)) > 0 )
		{
			builder.append(chars,0,charsRead);
		}
		
		String stringReadFromReader = builder.toString();
		return stringReadFromReader;
	}
	
	static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
	
}


