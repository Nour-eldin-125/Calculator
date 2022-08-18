package com.example.calculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ResViewModel extends ViewModel {
    private MutableLiveData<List<String>> res;
    List<String> l;
    public void init()
    {
        l = new ArrayList<>();
        l.add("");
        l.add("");
        l.add("");
        l.add("");
        res = new MutableLiveData<List<String>>();
    }

    public MutableLiveData<List<String>> getRes() {
        return res;
    }

    public void setRes(List <String> list) {
        this.res.setValue(list);
    }
}
