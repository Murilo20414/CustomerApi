package dto;

import com.br.project.customer.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CustomerDTO {

    @NotEmpty(message = "{campo.name.required}")
    private String name;

    @NotNull(message = "{campo.cpf.required}")
    @CPF(message = "{campo.cpf.invalid}")
    private String cpf;

    public Customer convertToEntity() {
        return new Customer(this.getName(), this.getCpf());
    }

    public CustomerDTO convertToDTO(Customer customer) {
        return new CustomerDTO(customer.getName(), customer.getCpf());
    }

}
