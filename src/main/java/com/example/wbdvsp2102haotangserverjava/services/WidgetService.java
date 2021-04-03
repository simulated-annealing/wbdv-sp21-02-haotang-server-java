package com.example.wbdvsp2102haotangserverjava.services;

import java.util.List;
import java.util.Optional;

import com.example.wbdvsp2102haotangserverjava.models.Widget;
import com.example.wbdvsp2102haotangserverjava.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import lombok.NonNull;


@Service
public class WidgetService {

    @Autowired
    private WidgetRepository widgetRepository;

    @Nullable
    public Widget createWidget(String tid, Widget w) {
        w.setTopicId(tid);
        return widgetRepository.save(w);
    }

    @NonNull
    public List<Widget> findWidgetsForTopic(String tid) {
        return widgetRepository.findWidgetsForTopic(tid);
    }

    public int updateWidget(Long wid, Widget w) {
        Widget ow = findWidgetById(wid);
        if (ow == null) return -1;

        ow.setOrdered(w.getOrdered());
        ow.setHeight(w.getHeight());
        ow.setWidth(w.getWidth());
        ow.setName(w.getName());
        ow.setSize(w.getSize());
        ow.setText(w.getText());
        ow.setTopicId(w.getTopicId());
        ow.setType(w.getType());
        ow.setUrl(w.getUrl());
        ow.setValue(w.getValue());
        ow.setWidgetOrder(w.getWidgetOrder());

        widgetRepository.save(ow);
        return 0;
    }

    public int deleteWidget(Long wid) {
        widgetRepository.deleteById(wid);
        return 0;
    }

    @NonNull
    public List<Widget> findAllWidgets() {
        return (List<Widget>) widgetRepository.findAll();
    }

    @Nullable
    public Widget findWidgetById(Long wid) {
        Optional<Widget> wrapper = widgetRepository.findById(wid);
        return wrapper.isPresent() ? wrapper.get() : null;
    }
}