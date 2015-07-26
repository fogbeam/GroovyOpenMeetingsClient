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

class DeleteRoomMain 
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
		
		/*
			deleteRoom(String SID, long rooms_id)
		 */
		
		resp = client.get( path : 'openmeetings/services/RoomService/deleteRoom', contentType: TEXT,
			query:[ SID:sid, rooms_id:11] );
		
		println "response from deleteRoom: ${resp.data}";
		
		if( !( resp.status == 200 )) // HTTP response code; 404 means not found, etc.
		{
			throw new Exception( "Not 200 HTTP Response!   ${resp.status}" );
		}
		else
		{
			println "OK";
		}
		
		println "done";
	}

}
