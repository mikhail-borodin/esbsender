package com.cdek.sortline.esbsender.dto;

import com.cdek.commons.serialize.JsonDateTimeDeserializer;
import com.cdek.commons.serialize.JsonDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Транспорт для сортер-мест при публикации первичного прихода по шине в другие модули {@link SorterIncomeEventDto#getSorterItems()}
 *
 */

@JsonSerialize
@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class SorterItemEventDto {

  /**
   * Номер накладной
   */
  private String waybillNumber;

  /**
   * Дата создания
   */
  @JsonDeserialize(using = JsonDateTimeDeserializer.class)
  @JsonSerialize(using = JsonDateTimeSerializer.class)
  private Date dateCreated;

  /**
   * Дата обновления
   */
  @JsonDeserialize(using = JsonDateTimeDeserializer.class)
  @JsonSerialize(using = JsonDateTimeSerializer.class)
  private Date dateUpdated;

  /**
   * Дата синхронизации с ЭК4
   */
  @JsonDeserialize(using = JsonDateTimeDeserializer.class)
  @JsonSerialize(using = JsonDateTimeSerializer.class)
  private Date dateSynced;

  /**
   * Номер места
   */
  private Long externalId;

  /**
   * СкладскиеПеремеры от Сортера.                                                                             <br/>
   * Если создали из Многоместной Накладной, то ПУСТО, т.к. не знаем, к какому Месту отнести габариты и вес.   <br/>
   * Также ПУСТО, если измерений нет.                                                                          <br/>
   */
  private String remeasureUuid;

  /**
   * Весь набор ШК из данных от Сортера (DestinationRequest/Reply)
   */
  private String barcodes;

  /**
   * СортКод, Рукав (код Рукава)
   */
  private Integer sortCode;
}
