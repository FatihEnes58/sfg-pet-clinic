package guru.springframework.sfgpetclinic.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.sfgpetclinic.repositories.SpecialityRepository;
import guru.springframework.sfgpetclinic.sfgpetclinic.services.SpecialityService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class SpecialitySDJpaService implements SpecialityService {

  private final SpecialityRepository specialityRepository;

  public SpecialitySDJpaService(
      SpecialityRepository specialityRepository) {
    this.specialityRepository = specialityRepository;
  }

  @Override
  @Profile("springdatajpa")
  public Set<Speciality> findAll() {

    Set<Speciality> specialities = new HashSet<>();

    specialityRepository.findAll().forEach(specialities::add);

    return specialities;
  }

  @Override
  public Speciality findById(Long aLong) {
    return specialityRepository.findById(aLong).orElse(null);
  }

  @Override
  public Speciality save(Speciality object) {
    return specialityRepository.save(object);
  }

  @Override
  public void delete(Speciality object) {
    specialityRepository.delete(object);
  }

  @Override
  public void deleteById(Long aLong) {
    specialityRepository.deleteById(aLong);
  }
}
