package org.fogbeam.om.client.dto

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="roomtype", namespace="http://room.entity.db.openmeetings.apache.org/xsd")
class RoomTypeDTO 
{
	@XmlElement( name="deleted", namespace="http://room.entity.db.openmeetings.apache.org/xsd" )
	boolean deleted;
	
	@XmlElement( name="fieldvalues_id", namespace="http://room.entity.db.openmeetings.apache.org/xsd" )
	int fieldvalues_id;
	
	@XmlElement( name="name", namespace="http://room.entity.db.openmeetings.apache.org/xsd" )
	String name;
	
	@XmlElement( name="conference", namespace="http://room.entity.db.openmeetings.apache.org/xsd" )
	String conference
	
	@XmlElement( name="roomtypes_id", namespace="http://room.entity.db.openmeetings.apache.org/xsd" )
	int roomtypes_id;
	
	@XmlElement( name="starttime", namespace="http://room.entity.db.openmeetings.apache.org/xsd" )
	String starttime;
	
	@XmlElement( name="updatetime", namespace="http://room.entity.db.openmeetings.apache.org/xsd" )
	String updatetime
	


}
