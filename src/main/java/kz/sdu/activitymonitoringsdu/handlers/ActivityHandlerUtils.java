package kz.sdu.activitymonitoringsdu.handlers;

import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;

public class ActivityHandlerUtils {

    public static ActivityDto convertToDto(Activity activity) {
        return ActivityDto.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .description(activity.getDescription())
                .status(activity.getStatus())
                .startDate(activity.getStartDate())
                .endDate(activity.getEndDate())
                .spentTime(activity.getSpentTime())
                .expectedTime(activity.getExpectedTime())
                .build();
    }

    // fixme converting to entity
    public static Activity convertToEntity(ActivityDto activityDto) {
        Activity activity = new Activity();
        activity.setTitle(activityDto.getTitle());
        activity.setDescription(activityDto.getDescription());
        activity.setStatus(activityDto.getStatus());
        activity.setStartDate(activityDto.getStartDate());
        activity.setEndDate(activityDto.getEndDate());
        activity.setSpentTime(activityDto.getSpentTime());
        activity.setExpectedTime(activityDto.getExpectedTime());
        return activity;
    }
}
