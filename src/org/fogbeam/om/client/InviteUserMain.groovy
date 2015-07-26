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

class InviteUserMain 
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
		
		SID - The SID of the User. This SID must be marked as Loggedin a valid Session Token
		username - the username of the User that he will get
		room_id - the conference room id of the invitation
		isPasswordProtected - if the invitation is password protected
		invitationpass - the password for accessing the conference room via the invitation hash
		valid - the type of validation for the hash 1: endless, 2: from-to period, 3: one-time
		validFromDate - Date in Format of dd.mm.yyyy only of interest if valid is type 2
		validFromTime - time in Format of hh:mm only of interest if valid is type 2
		validToDate - Date in Format of dd.mm.yyyy only of interest if valid is type 2
		validToTime - time in Format of hh:mm only of interest if valid is type 2
		
		Returns:
		a HASH value that can be made into a URL with http://$OPENMEETINGS_HOST :$PORT/openmeetings/?invitationHash="+invitationsHash;	 
	 
	 */
		
		resp = client.get( path : 'openmeetings/services/RoomService/getInvitationHash', contentType: XML,
			query:[ SID:sid, username:"motley.crue.fan", room_id:7, isPasswordProtected:false, invitationpass:"", valid:1, validFromDate:"", validFromTime:"",
											validToDate:"", validToTime:"" ] );
		
		println "response from getInvitationHash: ${resp.data}";
		
		if( !( resp.status == 200 )) // HTTP response code; 404 means not found, etc.
		{
			throw new Exception( "Not 200 HTTP Response!   ${resp.status}" );
		}
		else
		{
			def hash = resp.data.return;
			println "Hash: $hash";
			
			def inviteUrl = "http://demo2.fogbeam.org:5080/openmeetings/?invitationHash=$hash";
			
			println "URL: $inviteUrl";
		}
	
		
		
		
		println "done";
	}

}
