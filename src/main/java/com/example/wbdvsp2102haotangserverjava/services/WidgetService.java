package com.example.wbdvsp2102haotangserverjava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.wbdvsp2102haotangserverjava.models.Widget;

import org.springframework.stereotype.Service;


@Service
public class WidgetService {

    private List<Widget> widgets = new ArrayList<Widget>();

    public Widget createWidget(String tid, Widget w) {
        w.setId(UUID.randomUUID().toString());
        w.setTopicId(tid);
        widgets.add(w);
        return w;
    }

    public List<Widget> findWidgetsForTopic(String tid) {
        return widgets.stream().filter(w -> tid.equals(w.getTopicId())).collect(Collectors.toList());
    }

    public int updateWidget(String wid, Widget w) {
        int idx = IntStream.range(0, widgets.size()).filter(i -> wid.equals(widgets.get(i).getId())).findAny().orElse(-1);
        if (idx >= 0) widgets.set(idx, w);
        return 0;
    }

    public int deleteWidget(String wid) {
        widgets.removeIf(w -> wid.equals(w.getId()));
        return 0;
    }

    public List<Widget> findAllWidgets() {
        return widgets;
    }

    public Widget findWidgetById(String wid) {
        return widgets.stream().filter(w -> wid.equals(w.getId())).findFirst().orElse(null);
    }
}