package org.lessons.java.spring_la_mia_pizzeria_relazioni.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

  @Autowired
  private PizzaRepository pizzaRepository;

  public List<Pizza> findAll() {
    return pizzaRepository.findAll();
  }

  public Pizza getById(Integer id) {
    Optional<Pizza> pizzaAttempt = pizzaRepository.findById(id);

    if (pizzaAttempt.isEmpty()) {
      // not found con response entity
    }

    return pizzaAttempt.get();
  }

  public List<Pizza> findByName(String name) {
    return pizzaRepository.findByNameContainingIgnoreCase(name);
  }

  public Pizza findById(Integer id) {
    return pizzaRepository.findById(id).get();
  }

  public Pizza create(Pizza pizza) {
    return pizzaRepository.save(pizza);
  }

  public Pizza update(Pizza pizza) {
    return pizzaRepository.save(pizza);
  }

  public void delete(Pizza pizza) {
    pizzaRepository.delete(pizza);
  }

  public void deleteById(Integer id) {
    pizzaRepository.deleteById(id);
  }

  public Boolean existById(Integer id) {
    return pizzaRepository.existsById(id);
  }

  public Boolean exist(Pizza pizza) {
    return existById(pizza.getId());
  }
}
