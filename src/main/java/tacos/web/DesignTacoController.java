package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Taco;
import tacos.Ingredient.Type;


@Slf4j
@Controller
@RequestMapping("/design") //컨트롤러가 처리하는 요청의 종류 표시. 여기서는 /design으로 시작하는 경로의 요청을 처리함.

public class DesignTacoController {
	@GetMapping //(/design의 HTTP GET 요청이 수신될 때 그 요청을 처리하기 위해 showDesignForm() 메서드가 호출됨을 표시)
	  public String showDesignForm(Model model) {
	    List<Ingredient> ingredients = Arrays.asList(
	      new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
	      new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
	      new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
	      new Ingredient("CARN", "Carnitas", Type.PROTEIN),
	      new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
	      new Ingredient("LETC", "Lettuce", Type.VEGGIES),
	      new Ingredient("CHED", "Cheddar", Type.CHEESE),
	      new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
	      new Ingredient("SLSA", "Salsa", Type.SAUCE),
	      new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
	    );
	    
	    Type[] types = Ingredient.Type.values();
	    for (Type type : types) {
	      model.addAttribute(type.toString().toLowerCase(),
	          filterByType(ingredients, type));
	    }

	    model.addAttribute("taco", new Taco());

	    return "design";
	  }
	
	  private List<Ingredient> filterByType(
	      List<Ingredient> ingredients, Type type) {
	    return ingredients
	              .stream()
	              .filter(x -> x.getType().equals(type))
	              .collect(Collectors.toList());
	  }

	  	
}
