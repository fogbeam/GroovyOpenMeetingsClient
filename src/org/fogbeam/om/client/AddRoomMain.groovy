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

class AddRoomMain 
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
			Parameters:
			SID - The SID of the User. This SID must be marked as Loggedin
			name - Name of the Room
			roomtypesId - Type of that room (1 = Conference, 2 = Audience, 3 = Restricted, 4 = Interview)
			comment - any comment
			numberOfPartizipants - the maximum users allowed in this room
			ispublic - If this room is public (use true if you don't deal with different Organizations)
			appointment - is it a Calendar Room (use false by default)
			isDemoRoom - is it a Demo Room with limited time (use false by default)
			demoTime - time in seconds after the user will be logged out (only enabled if isDemoRoom is true)
			isModeratedRoom - Users have to wait untill a Moderator arrives. Use the becomeModerator param in setUserObjectAndGenerateRoomHash to set a user as default Moderator

		 */
		resp = client.get( path : 'openmeetings/services/RoomService/addRoomWithModeration', contentType: TEXT,
			query:[ SID:sid, name: "Test Room", roomtypes_id: 1, comment: "", numberOfPartizipants:25, ispublic:true, appointment:false, isDemoRoom:false, demoTime:0, isModeratedRoom:false ] );
		
		println "response from addRoomWithModeration: ${resp.data}";
		
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
