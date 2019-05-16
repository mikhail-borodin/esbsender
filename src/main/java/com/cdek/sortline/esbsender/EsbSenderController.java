package com.cdek.sortline.esbsender;

import com.cdek.commons.js.EsbEntityDto;
import com.cdek.queue.EsbClient;
import com.cdek.queue.rabbit.EsbMessage;
import com.cdek.sortline.esbsender.dto.MacroZoneEventDto;
import org.postgresql.geometric.PGpoint;
import org.postgresql.geometric.PGpolygon;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
public class EsbSenderController {

    private final EsbClient esbClient;

    public EsbSenderController(EsbClient esbClient) {
        this.esbClient = esbClient;
    }

    @RequestMapping(value = "/send/macroZone", method = RequestMethod.POST, produces = "application/json")
    public String sendMacroZone(@RequestBody MacroZoneEventDto macroZoneEventDto) {
        macroZoneEventDto.setBorder(
                new PGpolygon(
                        new PGpoint[]{
                                new PGpoint(1, 2),
                                new PGpoint(2, 3),
                                new PGpoint(3, 4)
                        }));
        return send(macroZoneEventDto, MacroZoneEventDto.MACRO_ZONE_MESSAGE_TYPE);
    }

    private <T extends EsbEntityDto> String send(T esbEntityDto, String messageType) {
        EsbMessage<T> esbMessage = new EsbMessage<>();
        esbEntityDto.setUuid(esbEntityDto.getUuid() == null ? UUID.randomUUID() : esbEntityDto.getUuid());
        esbEntityDto.setTimestamp(ZonedDateTime.now(ZoneOffset.UTC).toInstant().toEpochMilli());
        esbMessage.setEntity(esbEntityDto);
        esbMessage.setMessageType(messageType);
        esbMessage.setRoutingKey("");
        esbClient.publishObject(esbMessage);
        return esbEntityDto.toString();
    }
}
