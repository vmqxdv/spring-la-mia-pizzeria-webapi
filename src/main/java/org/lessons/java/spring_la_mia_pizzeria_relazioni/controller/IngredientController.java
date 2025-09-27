package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

  @Autowired
  private IngredientService ingredientService;

  @GetMapping
  public String index(Model model) {
    model.addAttribute("ingredients", ingredientService.findAll());

    return "ingredients/index";
  }

  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("ingredient", new Ingredient());

    return "ingredients/create-or-edit";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors())
      return "ingredients/create";

    ingredientService.create(formIngredient);

    return "redirect:/ingredients";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("ingredient", ingredientService.getById(id));
    return "ingredients/show";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    model.addAttribute("ingredient", ingredientService.getById(id));
    model.addAttribute("edit", true);

    return "ingredients/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("pizza") Ingredient formIngredient, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors())
      return "ingredients/create-or-edit";

    ingredientService.update(formIngredient);

    return "redirect:/ingredients/" + formIngredient.getId();
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {
    Ingredient ingredient = ingredientService.getById(id);

    for (Pizza pizza : ingredient.getPizzas()) {
      pizza.getIngredients().remove(ingredient);
    }

    ingredientService.delete(ingredient);

    return "redirect:/ingredients";
  }
}
