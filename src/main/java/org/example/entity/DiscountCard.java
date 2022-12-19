package org.example.entity;

import java.util.Objects;

public class DiscountCard extends GeneralEntity {
    private int discountInPercent;

    private DiscountCard(DiscountCardBuilder discountCardBuilder) {
        super(discountCardBuilder.id);
        this.discountInPercent = discountCardBuilder.discountInPercent;
    }

    public static class DiscountCardBuilder {
        private long id;
        private int discountInPercent;

        public DiscountCardBuilder id(long id) {
            this.id = id;
            return this;
        }

        public DiscountCardBuilder discountInPercent(int discountInPercent) {
            this.discountInPercent = discountInPercent;
            return this;
        }

        public DiscountCard build() {
            return new DiscountCard(this);
        }
    }

    public static DiscountCardBuilder builder() {
        return new DiscountCardBuilder();
    }

    public int getDiscountInPercent() {
        return discountInPercent;
    }

    public void setDiscountInPercent(int discountInPercent) {
        this.discountInPercent = discountInPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountCard that = (DiscountCard) o;
        return discountInPercent == that.discountInPercent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discountInPercent);
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "id='" + super.getId() + '\'' +
                ", discountInPercent=" + discountInPercent +
                '}';
    }
}
