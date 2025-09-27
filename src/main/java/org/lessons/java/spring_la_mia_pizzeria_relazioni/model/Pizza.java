package org.lessons.java.spring_la_mia_pizzeria_relazioni.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
// import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {

  // VALORI
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @Size(min = 2, max = 50, message = "Il nome deve avere tra 2 e 50 caratteri")
  private String name;

  // @NotBlank
  // @Lob
  // @Size(min = 5, max = 500, message = "Gli ingredienti devono avere tra 5 e 500
  // caratteri")
  // private String ingredients;

  @NotNull
  @DecimalMin(value = "0.0", inclusive = true, message = "Non può avere un prezzo minore di 0.0")
  private BigDecimal price;

  @NotBlank
  private String image;

  // RELAZIONI
  @OneToMany(mappedBy = "pizza", cascade = { CascadeType.REMOVE })
  private List<SpecialOffer> specialOffers;

  @ManyToMany(cascade = { CascadeType.REMOVE })
  @JoinTable(name = "ingredient_pizza", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
  private List<Ingredient> ingredients;

  // GETTER E SETTER
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // public String getIngredients() {
  // return ingredients;
  // }

  // public void setIngredients(String ingredients) {
  // this.ingredients = ingredients;
  // }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public List<SpecialOffer> getSpecialOffers() {
    return specialOffers;
  }

  public void setSpecialOffers(List<SpecialOffer> specialOffers) {
    this.specialOffers = specialOffers;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
}
