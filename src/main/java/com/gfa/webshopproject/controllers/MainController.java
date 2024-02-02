package com.gfa.webshopproject.controllers;

import com.gfa.webshopproject.models.ShopItem;
import com.gfa.webshopproject.services.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final Shop shop = new Shop();

    @GetMapping("/webshop")
    public String getWebShop(Model model) {
        List<ShopItem> shopList = shop.getShop();
        model.addAttribute("shopList", shopList);
        return "webshop";
    }

    @GetMapping("/only-available")
    public String getAvailable(Model model) {
        List<ShopItem> shopList = shop.getShop();
        shopList = shopList.stream().filter(shopItem -> shopItem.getQuantityOfStock() > 0).collect(Collectors.toList());
        model.addAttribute("shopList", shopList);
        return "webshop";
    }

    @GetMapping("/cheapest-first")
    public String cheapestFirst(Model model) {
        List<ShopItem> shopList = shop.getShop();
        shopList = shopList.stream().sorted(Comparator.comparing(ShopItem::getPrice)).collect(Collectors.toList());
        model.addAttribute("shopList", shopList);
        return "webshop";
    }

    @GetMapping("/contains-asus")
    public String containsAsus(Model model) {
        List<ShopItem> shopList = shop.getShop();
        shopList = shopList.stream().
                filter(n -> (n.getDescription().toLowerCase().contains("asus")) ||
                        (n.getName().toLowerCase().contains("asus"))).collect(Collectors.toList());
        model.addAttribute("shopList", shopList);
        return "webshop";
    }

    @GetMapping("/average-stock")
    public String averageStock(Model model) {
        List<ShopItem> shopList = shop.getShop();
        double averageStock = shopList.stream().collect(Collectors.averagingInt(ShopItem::getQuantityOfStock));
        model.addAttribute("data", "Average stock: " + averageStock);
        return "productdata";
    }

    @GetMapping("/most-expensive-available")
    public String mostExpensiveAvailable(Model model) {
        List<ShopItem> shopList = shop.getShop();
        ShopItem mostExpensiveItem = shopList.stream()
                .filter(i -> i.getQuantityOfStock() > 0)
                .max(Comparator.comparing(ShopItem::getPrice)).orElse(null);
        String mostExpensiveAvailable = mostExpensiveItem != null ? String.valueOf(mostExpensiveItem.getDescription()) : "No items available";
        model.addAttribute("data", "The most expensive item: " + mostExpensiveAvailable);
        return "productdata";
    }

    @PostMapping ("/search")
    public String searching(@RequestParam String searchFor, Model model) {
        List<ShopItem> shopList = shop.getShop();
        shopList = shopList.stream()
                .filter(i -> i.getDescription().contains(searchFor) ||
                        i.getName().contains(searchFor)).collect(Collectors.toList());
        model.addAttribute("shopList", shopList);
        return "webshop";
    }

    @GetMapping("/more-filters")
    public String getMoreFilters(Model model){
        List<ShopItem> shopList = shop.getShop();
        model.addAttribute("shopList", shopList);
        return "more-filters";
    }

    @GetMapping("/filter-by-year/{releaseYear}")
    public String filterByYear (@PathVariable int releaseYear, Model model) {
        List<ShopItem> shopList = shop.getShop();
        shopList = shopList.stream().filter(i -> i.getReleaseYear() == releaseYear).collect(Collectors.toList());
        model.addAttribute("shopList", shopList);
        return "more-filters";
    }

}
