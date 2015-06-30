package com.todocodepathapp.models;


import com.todocodepathapp.utils.DateUtils;
import com.todocodepathapp.utils.TimeUtils;

import java.util.Date;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class TodoItem {

    private long mId;
    public long getId() {
        return mId;
    }

    private String mTitle;
    public String getTitle() {
        return mTitle;
    }

    private String mBody;
    public String getBody() {
        return mBody;
    }

    private Date mCreatedAt;
    public Date getCreatedAt() {
        return mCreatedAt;
    }
    public String getCreatedAtTimeAgo() {
        return TimeUtils.timeAgo(getCreatedAt());
    }

    private Date mCompletionDate;
    public Date getCompletionDate() {
        return mCompletionDate;
    }
    public String getCompletionTime() {
        return DateUtils.dateToString(getCompletionDate());
    }

    public void setId(long id) {
        mId = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public void setCreatedAt(Date createdAt) {
        mCreatedAt = createdAt;
    }

    public void setCompletionDate(Date completionDate) {
        mCompletionDate = completionDate;
    }
}
