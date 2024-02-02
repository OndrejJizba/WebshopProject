package com.gfa.webshopproject.services;

import com.gfa.webshopproject.models.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<ShopItem> shop;

    public Shop() {
        this.shop = new ArrayList<>();
        this.shop.add(new ShopItem("GPU", "ASUS TUF GAMING GeForce RTX 4070 Ti 12GB OC", 25490, 0));
        this.shop.add(new ShopItem("CPU", "AMD Ryzen 5 7600", 6190, 6));
        this.shop.add(new ShopItem("Motherboard", "ASUS TUF GAMING B650-PLUS", 4949, 18));
        this.shop.add(new ShopItem("Storage", "Samsung 980 PRO 2TB", 4199, 25));
        this.shop.add(new ShopItem("RAM", "Kingston 32GB KIT DDR5 6400MHz CL32 FURY Renegade", 4049, 4));
    }

    public List<ShopItem> getShop() {
        return shop;
    }
}
