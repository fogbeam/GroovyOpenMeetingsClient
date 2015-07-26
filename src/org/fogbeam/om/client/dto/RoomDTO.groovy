package org.fogbeam.om.client.dto

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="return", namespace="http://room.dto.db.openmeetings.apache.org/xsd") 
public class RoomDTO {
 
	@XmlElement(name = "actionsMenuHidden", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
    boolean actionsMenuHidden
	
	@XmlElement(name = "activitiesHidden", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	boolean activitiesHidden;
	
	@XmlElement(name = "allowUserQuestions", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	boolean allowUserQuestions;
	
	@XmlElement(name = "appointment", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	boolean appointment;
	
	@XmlElement(name = "audioOnly", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	boolean audioOnly;
	
	@XmlElement(name = "chatHidden", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	boolean chatHidden;
	
	@XmlElement(name = "comment", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	String comment;
	
	@XmlElement(name = "confno", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	String confno;
	
	@XmlElement(name = "demo", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	boolean demo;
	
	@XmlElement(name = "demoTime", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	String demoTime;
	
	@XmlElement(name = "filesExplorerHidden", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	boolean filesExplorerHidden;
	
	@XmlElement(name = "id", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	int id;
	
	@XmlElement(name = "moderated", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	boolean moderated;
	
	@XmlElement(name = "name", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	String name;
	
	@XmlElement(name = "numberOfPartizipants", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	int numberOfParticipants;
	
	@XmlElement(name = "public", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	boolean publicRoom;
	
	@XmlElement(name = "screenSharingHidden", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	boolean screenSharingHidden;
	
	@XmlElement(name = "topBarHidden", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	boolean topBarHidden;
	
	@XmlElement(name = "whiteboardHidden", namespace="http://room.dto.db.openmeetings.apache.org/xsd")
	boolean whiteboardHidden;
	
	// @XmlElement(name = "roomtype")
	// RoomTypeDTO roomType;
}
