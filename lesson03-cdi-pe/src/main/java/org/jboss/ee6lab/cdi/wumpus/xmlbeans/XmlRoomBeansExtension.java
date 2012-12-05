package org.jboss.ee6lab.cdi.wumpus.xmlbeans;

import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.deltaspike.core.api.literal.ApplicationScopedLiteral;
import org.apache.deltaspike.core.util.metadata.builder.AnnotatedTypeBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlRoomBeansExtension implements Extension {
	
	private static class InjectLiteral extends AnnotationLiteral<Inject> implements Inject {} 
	
	public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery event, BeanManager beanManager) throws SecurityException, NoSuchFieldException {
		
		try {
            InputStream creatureDefs = XmlRoomBeansExtension.class.getClassLoader().getResourceAsStream("rooms.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(creatureDefs);
            
            NodeList rooms = document.getElementsByTagName("room");
            for (int i = 0; i < rooms.getLength(); ++i) {
            	Element room = (Element)rooms.item(i);
            	
            	String id = room.getAttribute("id");
            	String description = room.getAttribute("description");
            	String smell = room.getAttribute("smell");
            	String north = room.getAttribute("north");
            	String south = room.getAttribute("south");
            	String west = room.getAttribute("west");
            	String east = room.getAttribute("east");
            	
            	System.out.println("Creating room "+ id + " " + description + " " + north + " " + east + " " + south + " " + west);
            	
            	addRoom(event, beanManager, id, description, smell, north, east, south, west);
            }
            
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Error building xml parser, aborting", e);
        } catch (SAXException e) {
            throw new RuntimeException("SAX exception while parsing xml file", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading or parsing xml file", e);
        }
	}
	
	private void addRoom(BeforeBeanDiscovery event, BeanManager beanManager, String roomName, String description, String smell, String northRoom, String eastRoom, String southRoom, String westRoom) throws SecurityException, NoSuchFieldException {
		// TODO create the annotated type
		
		AnnotatedTypeBuilder<Room>  fb = new AnnotatedTypeBuilder<Room>();
		fb.readFromType(Room.class);

		fb.addToClass(new RoomName.RoomNameLiteral(roomName));
		fb.addToClass(new ApplicationScopedLiteral());

		if (northRoom != null && !northRoom.isEmpty()) {
			fb.addToField(Room.class.getDeclaredField("north"), new InjectLiteral());
			fb.addToField(Room.class.getDeclaredField("north"), new RoomName.RoomNameLiteral(northRoom));
		}

		if (eastRoom != null && !eastRoom.isEmpty()) {
			fb.addToField(Room.class.getDeclaredField("east"), new InjectLiteral());
			fb.addToField(Room.class.getDeclaredField("east"), new RoomName.RoomNameLiteral(eastRoom));
		}

		if (southRoom != null && !southRoom.isEmpty()) {
			fb.addToField(Room.class.getDeclaredField("south"), new InjectLiteral());
			fb.addToField(Room.class.getDeclaredField("south"), new RoomName.RoomNameLiteral(southRoom));
		}

		if (westRoom != null && !westRoom.isEmpty()) {
			fb.addToField(Room.class.getDeclaredField("west"), new InjectLiteral());
			fb.addToField(Room.class.getDeclaredField("west"), new RoomName.RoomNameLiteral(westRoom));
		}
		
		event.addAnnotatedType(fb.create());
	}
}
