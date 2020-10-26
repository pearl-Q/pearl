package com.qidi.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 白世鑫
 * @title: Cart
 * @projectName Tomcat
 * @description: 购物车模型
 * @date 2020/9/8  10:42 上午
 */
public class Cart {

//    private Integer totalCount;
//    private BigDecimal totalPrice;
//    private List<CartItem> items = new ArrayList<>();
    private Map<Integer,CartItem> items = new LinkedHashMap<>();


    /**
     * 添加购物车
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        if(item == null){
            items.put(cartItem.getId(),cartItem);
        }else {
            //更新商品数量
            item.setCount(item.getCount()+1);
            //更新商品总价格
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除购物车
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        if(cartItem!=null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }



    public Integer getTotalCount() {
        Integer totalCount = 0;

        for (CartItem item : items.values()) {
            totalCount += item.getCount();
        }


        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (CartItem item : items.values()) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }

        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
