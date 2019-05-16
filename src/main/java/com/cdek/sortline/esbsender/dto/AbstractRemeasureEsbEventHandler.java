package com.cdek.sortline.esbsender.dto;

import com.cdek.commons.utils.JSONUtil;
import com.cdek.queue.rabbit.handler.EventHandler;
import com.cdek.queue.utils.AmqpMessageUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 * Класс - общий для всех инстансов, которые хотят получать перемеры по шине.
 * Дальнейшая обработка накладной идет уже в коде инстанса-слушателя, через переопределение метода onRemeasureUpdate
 * @author proger6
 */
public abstract class AbstractRemeasureEsbEventHandler implements EventHandler {

  private static final Logger LOG = LoggerFactory.getLogger(AbstractRemeasureEsbEventHandler.class);


  @Override
  public void handleEvent(Message message) {

    String body = AmqpMessageUtils.getBodyText(message);
    if(StringUtils.isBlank(body)) {
      LOG.warn("It was event from Rabbit without payload: " + message);
      return;
    }

    RemeasureEventDto dto = JSONUtil.fromJSON(body, RemeasureEventDto.class);
    if(dto == null)
      throw new RuntimeException("Cannot parse RemeasureEventDto from message " + AmqpMessageUtils.getMessageType(message));

    //игнорируем события без timestamp и UUID объекта
    if(dto.getTimestamp() == null || dto.getUuid() == null)
      return;

    onRemeasureUpdate(dto, message.getMessageProperties());
  }

  /**
   * Переопределяй метод для обработки обновления по перемеру.
   * Обязательно нужно не забывать в начале метода проверять, а нужно ли это событие обрабатывать. Для этого у всех объектов есть поле
   * timestamp. Если ранее уже было обработано событие с более поздним timestamp, то текущее нужно игнорировать
   * Т.к. в каждом модуле программист сам определяет, как он хранит (буферизует) накладную, соответсвенно програмист сам определяет,
   * как и что он будет проверять, чтобы понимать, нужно ли обрабатывать это событие или оно не актуально для него
   * @param remeasure
   * @param messageProperties
   */
  public abstract void onRemeasureUpdate(RemeasureEventDto remeasure, MessageProperties messageProperties);

}
