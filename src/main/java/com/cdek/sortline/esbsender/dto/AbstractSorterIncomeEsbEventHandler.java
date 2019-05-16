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
 * Класс - общий для всех инстансов, которые хотят получать данные для формирования первичного прихода по шине.
 * Дальнейшая обработка накладной идет уже в коде инстанса-слушателя, через переопределение метода onRemeasureUpdate
 * @author proger6
 */
public abstract class AbstractSorterIncomeEsbEventHandler implements EventHandler {

  private static final Logger LOG = LoggerFactory.getLogger(AbstractSorterIncomeEsbEventHandler.class);


  @Override
  public void handleEvent(Message message) {

    String body = AmqpMessageUtils.getBodyText(message);
    if(StringUtils.isBlank(body)) {
      LOG.warn("It was event from Rabbit without payload: " + message);
      return;
    }

    SorterIncomeEventDto dto = JSONUtil.fromJSON(body, SorterIncomeEventDto.class);
    if(dto == null)
      throw new RuntimeException("Cannot parse SorterIncomeEventDto from message " + AmqpMessageUtils.getMessageType(message));

    //игнорируем события без timestamp и UUID объекта
    if(dto.getTimestamp() == null || dto.getUuid() == null)
      return;

    onSorterIncomeUpdate(dto, message.getMessageProperties());
  }

  /**
   * Переопределяй метод для обработки обновления по первичному приходу.
   * Обязательно нужно не забывать в начале метода проверять, а нужно ли это событие обрабатывать. Для этого у всех объектов есть поле
   * timestamp. Если ранее уже было обработано событие с более поздним timestamp, то текущее нужно игнорировать
   * @param sorterIncome
   * @param messageProperties
   */
  public abstract void onSorterIncomeUpdate(SorterIncomeEventDto sorterIncome, MessageProperties messageProperties);

}
