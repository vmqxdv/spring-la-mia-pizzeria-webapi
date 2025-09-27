package org.lessons.java.spring_la_mia_pizzeria_relazioni.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "special_offers")
public class SpecialOffer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  // DIPENDENZA
  @ManyToOne
  @JoinColumn(name = "pizza_id", nullable = false)
  private Pizza pizza;

  @NotNull(message = "La data di inizio dell\'offerta non può essere nulla")
  @FutureOrPresent(message = "La data di inizio dell'offerta non può essere al passato")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @NotNull(message = "La data di fine dell\'offerta non può essere nulla")
  @Future(message = "La data di fine dell\'offerta deve essere nel futuro")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;

  @NotNull(message = "Il titolo dell\'offerta non può essere nullo")
  @Size(min = 5, max = 50, message = "Il titolo deve avere tra 5 e 50 caratteri")
  private String title;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Pizza getPizza() {
    return pizza;
  }

  public void setPizza(Pizza pizza) {
    this.pizza = pizza;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
