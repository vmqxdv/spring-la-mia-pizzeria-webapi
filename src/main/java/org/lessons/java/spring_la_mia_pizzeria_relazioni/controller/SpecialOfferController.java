package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.service.SpecialOfferService;
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
@RequestMapping("/special-offers")
public class SpecialOfferController {

  @Autowired
  private SpecialOfferService specialOfferService;

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer fromSpecialOffer, BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors())
      return "specialOffers/create-or-edit";

    specialOfferService.create(fromSpecialOffer);

    return "redirect:/pizzas/" + fromSpecialOffer.getPizza().getId();
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    model.addAttribute("specialOffer", specialOfferService.getById(id));
    model.addAttribute("edit", true);

    return "specialOffers/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors())
      return "specialOffers/create-or-edit";

    specialOfferService.update(formSpecialOffer);

    return "redirect:/pizzas/" + formSpecialOffer.getPizza().getId();
  }
}
