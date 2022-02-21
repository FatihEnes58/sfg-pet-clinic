package guru.springframework.sfgpetclinic.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import guru.springframework.sfgpetclinic.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.sfgpetclinic.services.PetTypeService;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OwnerMapServiceTest {

  OwnerMapService ownerMapService;
  final Long ownerId = 1L;
  final String lastName = "Smith";

  @BeforeEach
  void setUp() {

    ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

    ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());

  }

  @Test
  void findAll() {
    Set<Owner> owners = ownerMapService.findAll();
    assertEquals(1, owners.size());
  }

  @Test
  void deleteById() {
    ownerMapService.deleteById(ownerId);
    assertEquals(0, ownerMapService.findAll().size());

  }

  @Test
  void delete() {
    ownerMapService.delete(ownerMapService.findById(ownerId));
    assertEquals(0, ownerMapService.findAll().size());
  }

  @Test
  void save() {
    Owner owner = Owner.builder().id(2L).build();
    Owner savedOwner = ownerMapService.save(owner);
    assertEquals(2L, savedOwner.getId());
  }

  @Test
  void findById() {
    Owner owner = ownerMapService.findById(ownerId);
    assertEquals(ownerId, owner.getId());
  }

  @Test
  void findByLastName() {
    Owner smith = ownerMapService.findByLastName(lastName);
    assertNotNull(smith);
    assertEquals(ownerId, smith.getId());
  }

  @Test
  void saveNoId() {

    Owner savedOwner = ownerMapService.save(Owner.builder().build());
    assertNotNull(savedOwner);
    assertNotNull(savedOwner.getId());

  }

  @Test
  void findByLastNameNotFound() {
    Owner smith = ownerMapService.findByLastName("foo");
    assertNull(smith);
  }

}