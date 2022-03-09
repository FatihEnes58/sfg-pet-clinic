package guru.springframework.sfgpetclinic.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.sfgpetclinic.services.OwnerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/owners")
@Controller
public class OwnerController {

  private final OwnerService ownerService;

  public OwnerController(
      OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id");  //ordan burdan id değiştirilemesin
  }

/*  @RequestMapping({"","/", "/index", "/index.html"})
  public String listOwners(Model model) {

    model.addAttribute("owners", ownerService.findAll());

    return "owners/index";
  }*/

  @RequestMapping("/find")
  public String findOwners(Model model) {

    model.addAttribute("owner", Owner.builder().build());
    return "owners/findOwners";
  }

  @GetMapping()
  public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {

    if (owner.getLastName() == null) {
      owner.setLastName("");
    }

    List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

    if (results.isEmpty()) {

      bindingResult.rejectValue("lastName", "notFound", "not found");

      return "owners/findOwners";
    } else if (results.size() == 1) {

      owner = results.get(0);
      return "redirect:/owners/" + owner.getId();

    } else {

      model.addAttribute("selections", results);
      return "owners/ownersList";

    }

  }

  @GetMapping("/{ownerId}")
  public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
    ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
    modelAndView.addObject(ownerService.findById(ownerId));
    return modelAndView;
  }

  @GetMapping("/new")
  public String initCreationForm(Model model) {
    Owner owner = Owner.builder().build();
    model.addAttribute("owner", owner);
    return "owners/createOrUpdateOwnerForm";
  }

  @PostMapping("/new")
  public String processCreationForm(@Valid Owner owner, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "owners/createOrUpdateOwnerForm";
    } else {
      Owner savedOwner = ownerService.save(owner);
      return "redirect:/owners/" + savedOwner.getId();
    }
  }

  @GetMapping("/{ownerId}/edit")
  public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model) {
    model.addAttribute(ownerService.findById(ownerId));
    return "owners/createOrUpdateOwnerForm";
  }

  @PostMapping("/{ownerId}/edit")
  public String processUpdateOwnerForm(@Valid Owner owner, BindingResult bindingResult,
      @PathVariable("ownerId") Long ownerId) {
    if (bindingResult.hasErrors()) {
      return "owners/createOrUpdateOwnerForm";
    } else {
      Owner savedOwner = ownerService.save(owner);
      return "redirect:/owners/" + savedOwner.getId();
    }
  }

}
