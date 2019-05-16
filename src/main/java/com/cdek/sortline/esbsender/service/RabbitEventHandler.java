package com.cdek.sortline.esbsender.service;

import com.cdek.queue.rabbit.handler.impl.AbstractEventHandlerMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitEventHandler extends AbstractEventHandlerMap {

  private SorterIncomeEventHandler sorterIncomeEventHandler;
  private RemeasureEventHandler remeasureEventHandler;

  @Autowired
  public RabbitEventHandler(SorterIncomeEventHandler sorterIncomeEventHandler,
      RemeasureEventHandler remeasureEventHandler) {
    this.sorterIncomeEventHandler = sorterIncomeEventHandler;
    this.remeasureEventHandler = remeasureEventHandler;
  }

  @PostConstruct
  public void init() {
    eventHandlerMap.put("obj.sorterincome", sorterIncomeEventHandler);
    eventHandlerMap.put("obj.remeasure", remeasureEventHandler);
  }
}
