package com.example.wbdvsp2102haotangserverjava.controllers;

import java.util.List;

import com.example.wbdvsp2102haotangserverjava.models.Widget;
import com.example.wbdvsp2102haotangserverjava.services.WidgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

    @Autowired
    WidgetService widgetService;

    @PostMapping("/api/topics/{tid}/widgets")
    Widget createWidget (@PathVariable("tid") String tid, @RequestBody Widget widget) {
        return widgetService.createWidget(tid, widget);
    }

    @GetMapping("/api/topics/{tid}/widgets") 
    List<Widget> findWidgetsForTopic(@PathVariable("tid") String tid) {
        return widgetService.findWidgetsForTopic(tid);
    }

    @PutMapping("/api/widgets/{wid}") 
    int updateWidget(@PathVariable("wid") String wid, @RequestBody Widget widget) {
        return widgetService.updateWidget(wid, widget);
    }

    @DeleteMapping("/api/widgets/{wid}")
    int deleteWidget(@PathVariable("wid") String wid) {
        return widgetService.deleteWidget(wid);
    }

    @GetMapping("/api/widgets") 
    List<Widget> findAllWidgets() {
        return widgetService.findAllWidgets();
    }

    @GetMapping("/api/widgets/{wid}") 
    Widget findWidgetById(String wid) {
        return widgetService.findWidgetById(wid);
    }
}