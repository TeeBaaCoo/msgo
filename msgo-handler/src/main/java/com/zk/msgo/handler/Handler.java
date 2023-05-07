package com.zk.msgo.handler;

import com.zk.msgo.pojo.TaskInfo;

public interface Handler {

    boolean doHandler(TaskInfo taskInfo);
}
