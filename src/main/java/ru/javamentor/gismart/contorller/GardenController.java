package ru.javamentor.gismart.contorller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.gismart.db.DataBase;
import ru.javamentor.gismart.model.Point;
import ru.javamentor.gismart.model.Value;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
public class GardenController {

	private static int position = 0;

	@GetMapping("/getGardenPosition")
	public List<Point> getGardenPositions() {
		return DataBase.pointPositions;
	}

	@GetMapping("/addPosition")
	public void addPosition() {
		position++;
	}

	@GetMapping("/getPosition")
	public ResponseEntity<Value> getPosition() {
		return new ResponseEntity<>(new Value(position), HttpStatus.OK);
	}

	@GetMapping("/setPhoto/{id}")
	public void setPhoto(@PathVariable(name = "id") int id) {
		if (id == 1) {
			getPointByXY(2, 2).setPhotoId(1);
		}
		if (id == 2) {
			getPointByXY(3, 3).setPhotoId(2);
		}
		if (id == -1) {
			getPointByXY(2, 2).setPhotoId(0);
		}
		if (id == -2) {
			getPointByXY(3, 3).setPhotoId(0);
		}

	}

	@GetMapping("/clearPosition")
	public void clearPosition() {
		position = 0;
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


	private Point getPointByXY(int x, int y) {
		return DataBase.pointPositions.get(
				DataBase.pointPositions.indexOf(new Point(x,y, "")));
	}
}
