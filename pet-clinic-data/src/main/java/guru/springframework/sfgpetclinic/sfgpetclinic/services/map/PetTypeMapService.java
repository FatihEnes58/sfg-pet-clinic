package guru.springframework.sfgpetclinic.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.sfgpetclinic.services.PetTypeService;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {

  @Override
  public Set<PetType> findAll() {
    return super.findAll();
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(PetType object) {
    super.delete(object);
  }

  @Override
  public PetType save(PetType object) {
    return super.save(object);
  }

  @Override
  public PetType findById(Long id) {
    return super.findById(id);
  }
}
