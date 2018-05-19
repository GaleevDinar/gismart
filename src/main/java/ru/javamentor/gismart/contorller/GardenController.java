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

	private String generateDroneCode(List<Point> points) {
		int maxLength = 0;
		int maxWidth = 0;
		int step = 1;
		for (Point currentPoint : points) {
			int currentLength = currentPoint.getX();
			int currentWidth = currentPoint.getY();
			if (currentLength >= maxLength) {
				maxLength = currentLength;
			}
			if (currentWidth >= maxWidth) {
				maxLength = currentLength;
			}
		}
		return String.format("import tellopy\n" +
				"import cv2.cv2 as cv2  # for avoidance of pylint error\n" +
				"import av\n" +
				"import numpy\n" +
				"import requests\n" +
				"\n" +
				"def main():\n" +
				"    drone = tellopy.Tello()\n" +
				"\n" +
				"    try:\n" +
				"        drone.connect()\n" +
				"        drone.wait_for_connection(60.0)\n" +
				"        container = av.open(drone.get_video_stream())\n" +
				"        \n" +
				"        #оставить верх\n" +
				"        #повторить куски кода ниже\n" +
				"        \n" +
				"        #взлет\n" +
				"        drone.takeoff()\n" +
				"        sleep(2)\n" +
				"        \n" +
				"        #вперед 5 фунтов == 1 метр, лететь больше 15 нужно в два отдельных запроса\n" +
				"        STEP = 1;\n" +
				"        LENGTH = %d;\n" + // maxLength
				"        WIDTH = %d;\n" + // maxWidth
				"        drone.move_forward(1)\n" +
				"        drone.move_forward(LENGTH/2)\n" +
				"        sleep(2)\n" +
				"        drone.move_forward(LENGTH/2)\n" +
				"        sleep(2)\n" +
				"        drone.set_yaw(90)\n" +
				"        drone.move_forward(WIDTH)\n" +
				"        sleep(2)\n" +
				"        drone.set_yaw(90)\n" +
				"        drone.move_forward(LENGTH / 2)\n" +
				"        sleep(2)\n" +
				"        drone.move_forward(LENGTH / 2 + STEP)\n" +
				"        drone.set_yaw(90)\n" +
				"        drone.move_forward(WIDTH)\n" +
				"\n" +
				"\n" +
				"        #поворот на 90 градусов по часовой\n" +
				"        drone.set_yaw(90)\n" +
				"        sleep(2)\n" +
				"        \n" +
				"        #фото\n" +
				"        frame = container.decode(video=0).next()\n" +
				"        image = cv2.cvtColor(numpy.array(frame.to_image()), cv2.COLOR_RGB2BGR)\n" +
				"        url = 'https://file.api.wechat.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE'\n" +
				"        files = {'media': open(image, 'rb')}\n" +
				"        requests.post(url, files = files)\n" +
				"        sleep(1)\n" +
				"\n" +
				"        #посадка\n" +
				"        drone.land()\n" +
				"        sleep(2)\n" +
				"\n" +
				"        #оставить низ\n" +
				"        \n" +
				"        \n" +
				"    except Exception as ex:\n" +
				"        print(ex)\n" +
				"    finally:\n" +
				"        drone.quit()\n" +
				"        cv2.destroyAllWindows()\n" +
				"\n" +
				"if __name__ == '__main__':\n" +
				"    main()\n" +
				"\n", maxLength, maxWidth);
	}

}
