package guru.springframework.sfgpetclinic.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.sfgpetclinic.model.Owner;
import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

  Owner findByLastName(String lastName);

}
