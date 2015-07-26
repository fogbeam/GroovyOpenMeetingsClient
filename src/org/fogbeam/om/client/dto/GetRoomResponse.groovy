package org.fogbeam.om.client.dto

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getRoomByIdResponse")
class GetRoomResponse 
{
	@XmlElement(name="return")
	public RoomDTO room;

}
