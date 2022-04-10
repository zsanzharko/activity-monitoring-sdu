package kz.sdu.activitymonitoringsdu.handlers;

import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;

import java.time.ZoneId;

public class ActivityHandlerUtils {

    public static ActivityDto convertToDto(Activity activity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setProjectId(activity.getProjectId());
        activityDto.setTitle(activity.getTitle());
        activityDto.setDescription(activity.getDescription());
        activityDto.setStatus(activity.getStatus());
        activityDto.setStartDate(java.util.Date.from(activity.getStartDate().atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant())
        );
        activityDto.setSpentTime(activity.getSpentTime());
        return activityDto;
    }

    // fixme converting to entity
    public static Activity convertToEntity(ActivityDto activityDto) {
        Activity activity = new Activity();
        activity.setProjectId(activityDto.getProjectId());
        activity.setTitle(activityDto.getTitle());
        activity.setDescription(activityDto.getDescription());
        activity.setStatus(activityDto.getStatus());
        activity.setStartDate(activityDto.getStartDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        activity.setSpentTime(activityDto.getSpentTime());
        return activity;
    }
}
