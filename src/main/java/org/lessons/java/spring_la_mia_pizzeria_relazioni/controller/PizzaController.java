package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.IngredientRepository;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

  @Autowired
  private PizzaService pizzaService;

  @Autowired
  private IngredientRepository ingredientRepository;

  @GetMapping
  public String index(@RequestParam(name = "name", required = false) String name, Model model) {
    List<Pizza> pizzas;
    if (name != null && !name.isBlank()) {
      pizzas = pizzaService.findByName(name);
    } else
      pizzas = pizzaService.findAll();

    model.addAttribute("pizzas", pizzas);

    return "pizzas/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("pizza", pizzaService.getById(id));

    return "pizzas/show";
  }

  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("pizza", new Pizza());
    model.addAttribute("ingredients", ingredientRepository.findAll());
    model.addAttribute("edit", false);

    return "pizzas/create-or-edit";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("pizza") Pizza formPizza,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("ingredients", ingredientRepository.findAll());
      model.addAttribute("edit", false);

      return "pizzas/create-or-edit";
    }

    pizzaService.create(formPizza);

    return "redirect:/pizzas";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    Pizza pizza = pizzaService.getById(id);

    model.addAttribute("pizza", pizza);
    model.addAttribute("ingredients", ingredientRepository.findAll());
    model.addAttribute("edit", true);

    return "pizzas/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("pizza") Pizza formPizza,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("ingredients", ingredientRepository.findAll());
      model.addAttribute("edit", true);

      return "pizzas/create-or-edit";
    }

    pizzaService.update(formPizza);

    return "redirect:/pizzas/" + formPizza.getId();
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {
    pizzaService.deleteById(id);

    return "redirect:/pizzas";
  }

  @GetMapping("/{id}/special-offers")
  public String createSpecialOffer(@PathVariable Integer id, Model model) {
    SpecialOffer specialOffer = new SpecialOffer();

    specialOffer.setPizza(pizzaService.getById(id));

    model.addAttribute("specialOffer", specialOffer);

    return "specialOffers/create-or-edit";
  }
}
