package guru.springframework.sfgpetclinic.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;

import guru.springframework.sfgpetclinic.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.sfgpetclinic.repositories.PetTypeRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

  public static final String LAST_NAME = "Smith";
  @Mock
  OwnerRepository ownerRepository;

  @Mock
  PetRepository petRepository;

  @Mock
  PetTypeRepository petTypeRepository;

  @InjectMocks
  OwnerSDJpaService service;

  Owner returnOwner;

  @BeforeEach
  void setUp() {
    returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
  }

  @Test
  void findAll() {

    Set<Owner> returnOwners = new HashSet<>();
    returnOwners.add(Owner.builder().id(1L).build());
    returnOwners.add(Owner.builder().id(2L).build());

    Mockito.when(ownerRepository.findAll()).thenReturn(returnOwners);

    Set<Owner> owners = service.findAll();

    assertNotNull(owners);
    assertEquals(2, owners.size());

  }

  @Test
  void findById() {

    Mockito.when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(returnOwner));

    Owner owner = service.findById(1L);

    assertNotNull(owner);

  }

  @Test
  void findByIdNotFound() {

    Mockito.when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Owner owner = service.findById(1L);

    assertNull(owner);

  }

  @Test
  void save() {

    Owner ownerToSave = Owner.builder().id(1L).build();

    Mockito.when(ownerRepository.save(Mockito.any())).thenReturn(returnOwner);

    Owner savedOwner = service.save(ownerToSave);

    assertNotNull(savedOwner);

  }

  @Test
  void delete() {

    service.delete(returnOwner);

    Mockito.verify(ownerRepository).delete(Mockito.any());

  }

  @Test
  void deleteById() {

    service.deleteById(1L);

    Mockito.verify(ownerRepository).deleteById(Mockito.anyLong());

  }

  @Test
  void findByLastName() {

    returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    Mockito.when(ownerRepository.findByLastName(Mockito.any())).thenReturn(returnOwner);
    Owner smith = service.findByLastName("Smith");
    assertEquals(LAST_NAME, smith.getLastName());
    Mockito.verify(ownerRepository).findByLastName(Mockito.any());
  }
}