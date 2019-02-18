package com.work.matchmaking.view.callback;

import com.work.matchmaking.service.model.Response1;
import com.work.matchmaking.service.model.Result;

import java.util.List;

public interface  UserClickCallback {

     void onClick(List<Result> resultList);
     void onClickCancel();
}
