package guru.springframework.sfgpetclinic.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.sfgpetclinic.services.VetService;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;
  private final SpecialityService specialityService;

  public DataLoader(
      OwnerService ownerService,
      VetService vetService,
      PetTypeService petTypeService,
      SpecialityService specialityService) {

    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
    this.specialityService = specialityService;
  }

  @Override
  public void run(String... args) throws Exception {

    int count = petTypeService.findAll().size();

    if (count == 0) {
      loadData();
    }

  }

  private void loadData() {
    Owner owner1 = createOwner("Mihael", "Weston",
        "123 Brickerel", "Miami", "555555555");
    Owner owner2 = createOwner("Fiona", "Gleanne",
        "123 Brickerel", "Miami", "666666666");

    System.out.println("Loaded owners...");

    Vet vet1 = createVet("Sam", "Axe");
    Vet vet2 = createVet("Jessie", "Porter");

    System.out.println("Loaded vets...");

    PetType dog = createPetType("dog");
    PetType cat = createPetType("cat");

    Pet mikesPet = createPet(dog, owner1, "Rosco");
    owner1.getPets().add(mikesPet);

    Pet fionasCat = createPet(cat, owner2, "Just Cat");
    owner2.getPets().add(fionasCat);

    Speciality radiology = createSpeciality("Radiology");
    Speciality surgery = createSpeciality("Surgery");
    Speciality dentistry = createSpeciality("Dentistry");

    vet1.getSpecialities().add(radiology);
    vet2.getSpecialities().add(surgery);
  }

  private Owner createOwner(String firstName, String lastName, String adress, String city,
      String telephone) {
    Owner owner = new Owner();
    owner.setFirstName(firstName);
    owner.setLastName(lastName);
    owner.setAddress(adress);
    owner.setCity(city);
    owner.setTelephone(telephone);

    ownerService.save(owner);
    return owner;
  }

  private Vet createVet(String firstName, String lastName) {
    Vet vet = new Vet();
    vet.setFirstName(firstName);
    vet.setLastName(lastName);

    vetService.save(vet);

    return vet;
  }

  private PetType createPetType(String name) {
    PetType petType = new PetType();
    petType.setName(name);

    petTypeService.save(petType);

    return petType;
  }

  private Pet createPet(PetType petType, Owner owner, String name) {
    Pet pet = new Pet();
    pet.setPetType(petType);
    pet.setBirthDate(LocalDate.now());
    pet.setOwner(owner);
    pet.setName(name);

    return pet;
  }

  private Speciality createSpeciality(String description) {
    Speciality speciality = new Speciality();
    speciality.setDescription(description);
    return specialityService.save(speciality);
  }

}
