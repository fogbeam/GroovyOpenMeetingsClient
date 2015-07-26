package org.fogbeam.om.client


import javax.xml.bind.JAXBContext
import javax.xml.bind.Unmarshaller
import javax.xml.transform.Source
import javax.xml.transform.stream.StreamSource

import org.fogbeam.om.client.dto.GetRoomsPublicResponse;
import org.fogbeam.om.client.dto.RoomDTO

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.6')
import groovyx.net.http.RESTClient

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.XML

class GetRoomsPublicMain 
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
		println "response from login: " + resp.data;
		
				
		// call getRoomsPublic using the SID of the logged in user
		resp = client.get( path : 'openmeetings/services/RoomService/getRoomsPublic', contentType: TEXT, 
				 					requestContentType:TEXT, query:[ SID:sid, roomtypes_id:1] );
		
		
		
		
		if( !( resp.status == 200 )) // HTTP response code; 404 means not found, etc.
		{
			throw new Exception( "Not 200 HTTP Response!   ${resp.status}" );
		}  
		else
		{
			
			/***** bind XML to Groovy object using JAXB ******/
	
			JAXBContext jaxbContext = JAXBContext.newInstance(GetRoomsPublicResponse.class)
			
			
			//class responsible for the process of de-serializing XML data into Groovy object
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Source source = new StreamSource(resp.data)
				
			GetRoomsPublicResponse response = (GetRoomsPublicResponse) jaxbUnmarshaller.unmarshal(source);
			
			println "Got ${response.rooms.size} rooms from server!"
			
			for( RoomDTO room : response.rooms )
			{
				println "Got room: ${room.id} + name: ${room.name}";
			}
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


