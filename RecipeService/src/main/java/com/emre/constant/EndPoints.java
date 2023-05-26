package com.emre.constant;

public class EndPoints {
    public static final String VERSION = "/api/v1";
    public static final String RECIPE = VERSION + "/recipe";
    public static final String CATEGORY = VERSION + "/category";

    public static final String CATEGORY_SAVE = "/category-save";
    public static final String RECIPE_SAVE = "/recipe-save";
    public static final String CATEGORY_DELETE = "/category-delete";
    public static final String RECIPE_DELETE = "/recipe-delete";
    public static final String UPDATE = "/update";
    public static final String FIND_ALL = "/find-all";

    public static final String SEARCH_RECIPE_WITH_CATEGORY = "/search-recipe-with-category";
    public static final String SEARCH_RECIPE_WITH_FOOD_NAME = "/search-recipe-with-food-name";
    public static final String SEARCH_RECIPE_WITH_INGREDIENTS_NAME = "/search-recipe-with-ingredients-name";
    public static final String ORDER_BY_RECIPE_WITH_CALORIES = "/order-by-recipe-with-calories";

}
