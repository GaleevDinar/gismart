package ru.javamentor.gismart.init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.javamentor.gismart.db.DataBase;
import ru.javamentor.gismart.model.Point;

import javax.annotation.PostConstruct;
import javax.xml.crypto.Data;
import java.util.LinkedList;
import java.util.List;

//@Component
public class TestDataInit implements ApplicationListener<ContextRefreshedEvent> {

	public void init() {
		Point point1 = new Point(1, 1, "#f4eb42");
		Point point2 = new Point(1, 2, "#f4eb42");
		Point point3 = new Point(1, 3, "#446ddd");
		point3.setPhotoId(1);
		Point point4 = new Point(1, 4, "#446ddd");
		Point point5 = new Point(2, 1, "#f4eb42");
		Point point6 = new Point(2, 2, "#f4eb42");
		point5.setPhotoId(2);
		Point point7 = new Point(2, 3, "#446ddd");
		Point point8 = new Point(2, 4, "#446ddd");
		point8.setPhotoId(3);
		List<Point> points = new LinkedList<>();
		points.add(point1);
		points.add(point2);
		points.add(point3);
		points.add(point4);
		points.add(point5);
		points.add(point6);
		points.add(point7);
		points.add(point8);
		DataBase.pointPositions = points;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		init();
	}
}
