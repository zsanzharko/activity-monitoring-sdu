package kz.sdu.activitymonitoringsdu.handlers;

import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;

public class ActivityHandlerUtils {

    public static ActivityDto convertToDto(Activity activity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setTitle(activity.getTitle());
        activityDto.setDescription(activity.getDescription());
        activityDto.setStatus(activity.getStatus());
        activityDto.setStartDate(activity.getStartDate());
        activityDto.setSpentTime(activity.getSpentTime());
        activityDto.setExpectedTime(activity.getExpectedTime());
        return activityDto;
    }

    // fixme converting to entity
    public static Activity convertToEntity(ActivityDto activityDto) {
        Activity activity = new Activity();
        activity.setTitle(activityDto.getTitle());
        activity.setDescription(activityDto.getDescription());
        activity.setStatus(activityDto.getStatus());
        activity.setStartDate(activityDto.getStartDate());
        activity.setSpentTime(activityDto.getSpentTime());
        activity.setExpectedTime(activityDto.getExpectedTime());
        return activity;
    }
}
