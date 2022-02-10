package guru.springframework.sfgpetclinic.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person{

  @Builder
  public Owner(String firstName, String lastName, String address, String city,
      String telephone, Set<Pet> pets) {
    super(firstName, lastName);
    this.address = address;
    this.city = city;
    this.telephone = telephone;
    this.pets = pets;
  }

  public Owner(String address, String city, String telephone,
      Set<Pet> pets) {
    this.address = address;
    this.city = city;
    this.telephone = telephone;
    this.pets = pets;
  }

  @Column(name = "adress")
  private String address;

  @Column(name = "city")
  private String city;

  @Column(name = "telephone")
  private String telephone;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner") // if we delete the owner, the pets of these owner are also going to be deleted.
  private Set<Pet> pets = new HashSet<>();
}
