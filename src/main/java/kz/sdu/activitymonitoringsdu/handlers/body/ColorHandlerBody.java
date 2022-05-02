package kz.sdu.activitymonitoringsdu.handlers.body;

import kz.sdu.activitymonitoringsdu.handlers.DateHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

@Data
@AllArgsConstructor
public class ColorHandlerBody implements Serializable {
    private Date startDate;
    private Date endDate;
    private Integer duration;

    public List<String> getColor() {
//        if (startDate.getDate() != 6 || endDate.getDate() != 6) {
//            startDate.setTime(startDate.getTime() + 21600000);
//            endDate.setTime(endDate.getTime() + 21600000);
//        }
        Date dayDuration = new Date();
//        dayDuration.setDate(12);
        List<String> colors = new ArrayList<>(duration);

        String color = "";
        for (int i = 0; i < duration; i++) {
            if ((color.equals("") )/*|| color.equals(getColorToHex(new Color(218, 218, 218)))) */ &&
                    dayDuration.after(startDate) && dayDuration.before(endDate)) {
                int colorDaysEnd = DateHandler.daysBetween(dayDuration, endDate) + 1;

                if (colorDaysEnd >= 5) {
                    color = getColorToHex(new Color(51, 168, 83));
                } else if (colorDaysEnd < 2) {
                    color = getColorToHex(new Color(234, 67, 54));
                } else {

                    color = getColorToHex(new Color(251,188,5));
                }
            }

            if (dayDuration.before(startDate) || dayDuration.after(endDate)){
                color = getColorToHex(new Color(218, 218, 218));
            }
            colors.add(color);

            dayDuration.setTime(dayDuration.getTime() + 86400000L);
        }
        return colors;
    }

    private String getColorToHex(Color color) {
        int r = color.getRed(), g = color.getGreen(), b = color.getBlue();
        return String.format("#%02x%02x%02x", r, g, b).toUpperCase(Locale.ROOT);
    }
}
