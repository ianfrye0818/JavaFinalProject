package Utils;

/**
 *
 * @author Ian Frye
 *
 * This enum controls how customer discount options are handled
 * Each discount incldudes the dollar amount discount it includes.
 */
public enum CustomerDiscount {
    NONE( 0),
    LIKES_THE_CAR( 100),
    SOMEWHAT_LIKES_THE_CAR(200),
    NOT_EASILY_SWAYED( 500);

    private final float discount;

    CustomerDiscount(float discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

}
