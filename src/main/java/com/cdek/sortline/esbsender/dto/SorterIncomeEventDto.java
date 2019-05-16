package com.cdek.sortline.esbsender.dto;

import com.cdek.commons.js.EsbEntityDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Транспорт для публикации первичного прихода по шине в другие модули
 *
 */

@JsonSerialize
@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@ToString
public class SorterIncomeEventDto extends EsbEntityDto implements Serializable {

  /**
   * Тип сообщения в RabbitMQ
   */
  public static final String SORTER_INCOME_MESSAGE_TYPE = "obj.sorterincome";

  /**
   * userCode
   */
  private String userCode;

  /**
   * СортКод, Рукав (код Рукава)
   */
  private Integer sortCode;

  /**
   * Причина отправки накладной в "dump" рукав
   */
  private String dumpReason;

  /**
   * СортерМеста
   */
  private List<SorterItemEventDto> sorterItems;

}
