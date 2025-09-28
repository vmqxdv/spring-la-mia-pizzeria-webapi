package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {

  @Autowired
  private PizzaService pizzaService;

  @GetMapping
  public ResponseEntity<List<Pizza>> index(@RequestParam(required = false) String name) {
    List<Pizza> results;

    if (name != null && !name.isBlank())
      results = pizzaService.findByName(name);
    else
      results = pizzaService.findAll();

    if (results.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(results, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Pizza> show(@PathVariable Integer id) {
    Optional<Pizza> pizzaAttempt = pizzaService.findById(id);

    if (pizzaAttempt.isEmpty())
      return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<Pizza>(pizzaAttempt.get(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Pizza> store(@Valid @RequestBody Pizza pizza) {
    return new ResponseEntity<Pizza>(pizzaService.create(pizza), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Pizza> update(@Valid @RequestBody Pizza pizza, @PathVariable Integer id) {
    if (pizzaService.findById(id).isEmpty())
      return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);

    pizza.setId(id);

    return new ResponseEntity<Pizza>(pizzaService.update(pizza), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@Valid @PathVariable Integer id) {
    if (pizzaService.findById(id).isEmpty())
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

    pizzaService.deleteById(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}
