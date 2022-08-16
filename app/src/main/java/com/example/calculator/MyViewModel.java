package com.example.calculator;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData <List<String>> liveNumbers;
    private List <String>list;
    public void init ()
    {
        list = new ArrayList<>();
        list.add("");list.add("");list.add("");
        liveNumbers = new MutableLiveData<>();
        liveNumbers.setValue(list);
    }

    public MutableLiveData<List<String>> getmessege ()
    {
        return liveNumbers;
    }

    public void sendMessegeNumebers(String msg, int index) {
        list.set(index,msg);
        liveNumbers.setValue(list);
    }

}
