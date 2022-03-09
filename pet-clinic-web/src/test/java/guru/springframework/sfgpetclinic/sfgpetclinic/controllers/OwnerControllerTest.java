package guru.springframework.sfgpetclinic.sfgpetclinic.controllers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import guru.springframework.sfgpetclinic.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.sfgpetclinic.services.OwnerService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

  @Mock
  OwnerService ownerService;

  @InjectMocks
  OwnerController controller;

  Set<Owner> owners;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {

    owners = new HashSet<>();
    owners.add(Owner.builder().id(1L).build());
    owners.add(Owner.builder().id(2L).build());

    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

  }

/*  @Test
  void listOwners() throws Exception {

    when(ownerService.findAll()).thenReturn(owners);

    mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andExpect(MockMvcResultMatchers.view().name("owners/index"))
        .andExpect(model().attribute("owners", Matchers.hasSize(2)));

  }*/

  @Test
  void findOwners() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("owners/findOwners"))
        .andExpect(model().attributeExists("owner"));

    Mockito.verifyZeroInteractions(ownerService);

  }

  @Test
  void processCreationForm() throws Exception {

    when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

    mockMvc.perform(MockMvcRequestBuilders.get("/owners/new"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"))
        .andExpect(model().attributeExists("owner"));

    verify(ownerService).save(any());
  }

  @Test
  void initUpdateOwnerForm() throws Exception {

    when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

    mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/edit"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
        .andExpect(model().attributeExists("owner"));

    verifyZeroInteractions(ownerService);
  }

  @Test
  void initCreationForm() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/owners/new"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
        .andExpect(model().attributeExists("owner"));

    verifyZeroInteractions(ownerService);
  }

  @Test
  void processUpdateOwnerForm() throws Exception {

    when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

    mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/edit"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"))
        .andExpect(model().attributeExists("owner"));

    verify(ownerService).save(any());
  }

  @Test
  void processFindFromReturnMany() throws Exception {
    when(ownerService.findAllByLastNameLike(anyString())).thenReturn(new ArrayList<>(owners));

    mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/ownersList"))
        .andExpect(model().attribute("selections", hasSize(2)));
  }

  @Test
  void processFindFromReturnOne() throws Exception {
    when(ownerService.findAllByLastNameLike(anyString())).thenReturn(
        Arrays.asList(Owner.builder().id(1L).build()));

    mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"));
  }

  @Test
  void displayOwner() throws Exception {

    when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

    mockMvc.perform(MockMvcRequestBuilders.get("/owners/123"))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andExpect(MockMvcResultMatchers.view().name("owners/ownerDetails"))
        .andExpect(model().attribute("owner", hasProperty("id", is(1L))));

  }
}