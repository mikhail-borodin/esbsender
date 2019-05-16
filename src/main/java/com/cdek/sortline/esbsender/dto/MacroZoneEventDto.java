package com.cdek.sortline.esbsender.dto;

import com.cdek.commons.js.EsbEntityDto;
import com.cdek.sortline.esbsender.util.JsonPGpolygonDeserializer;
import com.cdek.sortline.esbsender.util.JsonPGpolygonSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.postgresql.geometric.PGpolygon;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@JsonSerialize
@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MacroZoneEventDto extends EsbEntityDto implements Serializable {

    /**
     * Тип сообщения в RabbitMQ
     */
    public static final String MACRO_ZONE_MESSAGE_TYPE = "obj.macrozone";

    /**
     * Дата создания макрозоны
     */
    private Date createDate;

    /**
     * Дата обновления макрозоны
     */
    private Date updateDate;

    /**
     * Код создавшего пользователя
     */
    private String createUserCode;

    /**
     * Код обновившего пользователя
     */
    private String updateUserCode;

    /**
     * Имя зоны
     */
    private String name;

    /**
     * Филиал
     */
    private String branchCode;

    /**
     * Граница зоны
     */
    @JsonSerialize(using = JsonPGpolygonSerializer.class)
    @JsonDeserialize(using = JsonPGpolygonDeserializer.class)
    private PGpolygon border;

    /**
     * Примечание
     */
    private String description;

    /**
     * Удалена
     */
    private boolean deleted;

    /**
     * Код курьера
     */
    private String courierCode;

    /**
     * Uuid слоя
     */
    private String layerUuid;

    /**
     * Код типа слоя
     */
    private String layerTypeCode;
}
