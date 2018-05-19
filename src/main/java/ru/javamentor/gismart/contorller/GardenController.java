package ru.javamentor.gismart.contorller;

import org.springframework.web.bind.annotation.*;
import ru.javamentor.gismart.db.DataBase;
import ru.javamentor.gismart.model.Point;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
public class GardenController {

	@GetMapping("/getGardenPosition")
	public List<Point> getGardenPositions() {
		return DataBase.pointPositions;
	}

	@GetMapping("/clearAll")
	public void clearAll() {
		DataBase.pointPositions = new LinkedList<>();
	}

	@RequestMapping(value = "/updatePoint", method = RequestMethod.POST)
	public void updatePoint(@RequestBody Point point) {
		int index = DataBase.pointPositions.indexOf(point);
		if (index != -1) {
			Point p = DataBase.pointPositions.get(index);
			p.setColor(point.getColor());
		} else {
			DataBase.pointPositions.add(point);
		}
	}

}
