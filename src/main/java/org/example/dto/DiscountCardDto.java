package org.example.dto;

import java.util.Objects;

public class DiscountCardDto {
    private long id;

    public DiscountCardDto(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCardDto that = (DiscountCardDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DiscountCardDto{" +
                "id=" + id +
                '}';
    }
}
