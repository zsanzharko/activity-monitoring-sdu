package kz.sdu.activitymonitoringsdu.handlers;

import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;

public class ActivityHandlerUtils {

    public static ActivityDto convertToDto(Activity activity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setProjectId(activity.getProjectId());
        activityDto.setTitle(activity.getTitle());
        activityDto.setDescription(activity.getDescription());
        activityDto.setStatus(activity.getStatus());
        activityDto.setStartDate(activity.getStartDate());
        activityDto.setSpentTime(activity.getSpentTime());
        return activityDto;
    }

    // fixme converting to entity
    public static Activity convertToEntity(ActivityDto activityDto) {
        return null;
    }
}
