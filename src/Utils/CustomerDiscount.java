package Utils;

/**
 *
 * @author Ian Frye
 */
public enum CustomerDiscount {
    NONE("No discount", 0),
    LIKES_THE_CAR("Likes the car", 100),
    SOMEWHAT_LIKES_THE_CAR("Somewhat likes the car", 200),
    NOT_EASILY_SWAYED("Not easily swayed", 500);

    private final String description;
    private final float discount;

    CustomerDiscount(String description, float discount) {
        this.description = description;
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscount() {
        return discount;
    }

}
