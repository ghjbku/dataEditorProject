package saveDataEditor.ItemEntities;

public class SpiritFruitInformation implements ItemEntity {
    private final long id;
    private final String name;
    private Double amount;
    private final Long price;
    private final Long age;
    private final Double quality;

    //549-561 last one
    public SpiritFruitInformation(long id, String name, Long age, Double quality, Long price) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.quality = quality;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return this.name == null ? "not known" : name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getPrice() {
        return this.price == null ? 0 : price;
    }

    @Override
    public Long getAge() {
        return age;
    }

    @Override
    public Double getQuality() {
        return quality;
    }
}
