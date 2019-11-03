package com.gmail.dmytrod8;

import java.util.ArrayList;
import java.util.List;

public class JsonArchives {
    private final List<Archive> list;

    public JsonArchives(List<Archive> sourceList) {
        this.list = new ArrayList<>();
        for (int i = 0; i < sourceList.size(); i++)
            list.add(sourceList.get(i));
    }
}
