package com.cdek.sortline.esbsender.dto;

import com.cdek.commons.js.EsbEntityDto;
import com.cdek.commons.serialize.JsonDateTimeDeserializer;
import com.cdek.commons.serialize.JsonDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Транспорт для публикации данных перемера в другие модули по шине
 */

@JsonSerialize
@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@ToString
public class RemeasureEventDto extends EsbEntityDto implements Serializable {

  /**
   * Тип сообщения в RabbitMQ
   */
  public static final String REMEASURE_MESSAGE_TYPE = "obj.remeasure";

  /**
   * userCode
   */
  private String userCode;

  /**
   * Номер места
   */
  private Long externalId;

  /**
   * Номер накладной
   */
  private String waybillNumber;

  /**
   * Длина мм
   */
  private Integer length;

  /**
   * Ширина мм
   */
  private Integer width;

  /**
   * Высота мм
   */
  private Integer height;

  /**
   * Вес гр
   */
  private Double weight;

  /**

   /**
   * Перемер создан "доверенным источником" (на Сортере задается в настройках)
   */
  private Boolean trusted;

  /**
   * Дата выполнения перемера
   */
  @JsonDeserialize(using = JsonDateTimeDeserializer.class)
  @JsonSerialize(using = JsonDateTimeSerializer.class)
  private Date dateRemeasure;
}
