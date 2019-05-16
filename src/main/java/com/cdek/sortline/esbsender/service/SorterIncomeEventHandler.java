package com.cdek.sortline.esbsender.service;

import com.cdek.sortline.esbsender.dto.AbstractSorterIncomeEsbEventHandler;
import com.cdek.sortline.esbsender.dto.SorterIncomeEventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

@Service
public class SorterIncomeEventHandler extends AbstractSorterIncomeEsbEventHandler {

  private static final Logger LOG = LoggerFactory.getLogger(RemeasureEventHandler.class);

  @Override
  public void onSorterIncomeUpdate(SorterIncomeEventDto sorterIncome,
      MessageProperties messageProperties) {
    LOG.info("(!) Event from rabbit data: " + sorterIncome);
  }
}

