package saveDataEditor.ItemEntities;

public class TreasureInformation implements ItemEntity {
    private long id;
    private String name;
    private Double quality;
    private Long amount;
    private Long price;
    private Long age;

    public TreasureInformation(long id, String name, Double quality, Long amount, Long price) {
        this.id = id;
        this.name = name;
        this.quality = quality;
        this.amount = amount;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return this.name == null ? "not a treasure" : name;
    }

    public Double getQuality() {
        return quality;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getPrice() {
        return this.price == null ? 0 : price;
    }

    @Override
    public Long getAge() {
        return age;
    }
}
