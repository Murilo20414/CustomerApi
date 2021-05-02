package com.br.project.customer.dto;

import com.br.project.customer.domain.ServiceProvided;
import com.br.project.customer.util.GeneralConversions;
import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.internal.dynalink.linker.ConversionComparator;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvidedDTO {

    @NotEmpty(message = "{campo.description.required}")
    private String description;

    @NotEmpty(message = "{campo.value.required}")
    private String value;

    @NotEmpty(message = "{campo.dateService.required}")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dateService;

    @NotNull
    private Long id_customer;


    public ServiceProvided convertToEntity() {
       ServiceProvided service = new ServiceProvided();

       service.setDescription(this.description);
       service.setValue(GeneralConversions.convertToBigDecimal(this.value));
       service.setDateService(GeneralConversions.stringToLocalDate(this.dateService));

       return service;

    }

    public ServiceProvidedDTO convertToDTO(ServiceProvided service) {
        return new ServiceProvidedDTO(
                service.getDescription(),
                service.getValue().toPlainString(),
                GeneralConversions.localDateToString(service.getDateService()),
                service.getCustomer().getId()
        );
    }

}
