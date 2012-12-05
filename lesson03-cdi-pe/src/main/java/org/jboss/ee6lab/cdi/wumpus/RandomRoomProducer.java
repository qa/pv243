package org.jboss.ee6lab.cdi.wumpus;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.literal.AnyLiteral;
import org.jboss.ee6lab.cdi.wumpus.xmlbeans.Room;
import org.jboss.ee6lab.cdi.wumpus.xmlbeans.RoomName;

@ApplicationScoped
public class RandomRoomProducer {

	private java.util.Random random = new java.util.Random();

	private List<String> roomNames;

	@Inject
	BeanManager beanManager;

	@Inject
	@Any
	Instance<Room> roomInstance;

	private List<String> getRoomNames() {
		if (roomNames == null) {
			roomNames = new ArrayList<String>();

			Set<Bean<?>> roomBeans = beanManager.getBeans(Room.class,
					new AnyLiteral());
			for (Bean<?> roomBean : roomBeans) {
				for (Annotation annotation : roomBean.getQualifiers()) {
					if (annotation.annotationType().equals(RoomName.class)) {
						RoomName roomName = (RoomName) annotation;
						roomNames.add(roomName.value());

						System.out.println("XXX roomName: " + roomName.value());
					}
				}
			}
		}

		return roomNames;
	}

	@Produces
	@Random
	public Room getRandomRoom() {
		List<String> roomNames = getRoomNames();
		String roomName = roomNames.get(random.nextInt(roomNames.size()));
		return roomInstance.select(new RoomName.RoomNameLiteral(roomName))
				.get();
	}
}
