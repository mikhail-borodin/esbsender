package com.cdek.sortline.esbsender.service;

import com.cdek.sortline.esbsender.dto.AbstractRemeasureEsbEventHandler;
import com.cdek.sortline.esbsender.dto.RemeasureEventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

@Service
public class RemeasureEventHandler extends AbstractRemeasureEsbEventHandler {

  private static final Logger LOG = LoggerFactory.getLogger(RemeasureEventHandler.class);

  @Override
  public void onRemeasureUpdate(RemeasureEventDto remeasure, MessageProperties messageProperties) {
    LOG.info("(!) Event from rabbit data: " + remeasure);
  }
}
