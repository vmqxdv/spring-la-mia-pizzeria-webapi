package org.lessons.java.spring_la_mia_pizzeria_relazioni.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

  @Autowired
  private IngredientRepository ingredientRepository;

  public List<Ingredient> findAll() {
    return ingredientRepository.findAll();
  }

  public Ingredient getById(Integer id) {
    Optional<Ingredient> ingredientAttempt = ingredientRepository.findById(id);

    if (ingredientAttempt.isEmpty()) {
    }

    return ingredientAttempt.get();
  }

  public Ingredient findById(Integer id) {
    return ingredientRepository.findById(id).get();
  }

  public Ingredient create(Ingredient ingredient) {
    return ingredientRepository.save(ingredient);
  }

  public Ingredient update(Ingredient ingredient) {
    return ingredientRepository.save(ingredient);
  }

  public void deleteById(Integer id) {
    ingredientRepository.deleteById(id);
  }

  public void delete(Ingredient ingredient) {
    deleteById(ingredient.getId());
  }

  public Boolean existById(Integer id) {
    return ingredientRepository.existsById(id);
  }

  public Boolean exist(Ingredient ingredient) {
    return existById(ingredient.getId());
  }
}
