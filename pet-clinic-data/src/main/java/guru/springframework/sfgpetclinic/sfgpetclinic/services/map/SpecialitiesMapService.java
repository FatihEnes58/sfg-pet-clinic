package guru.springframework.sfgpetclinic.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.sfgpetclinic.services.SpecialityService;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class SpecialitiesMapService extends AbstractMapService<Speciality, Long> implements
    SpecialityService {

  @Override
  public Set<Speciality> findAll() {
    return super.findAll();
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(Speciality object) {
    super.delete(object);
  }

  @Override
  public Speciality save(Speciality object) {
    return super.save(object);
  }

  @Override
  public Speciality findById(Long id) {
    return super.findById(id);
  }
}
