package saveDataEditor.ItemEntities;

public class ResourceInformation implements ItemEntity{
    private long id;
    private String name;
    private Long amount;
    private Long price;
    private Long age;
    private Double quality;

    public ResourceInformation(long id, String name, Long amount, Long price){
        this.id=id;
        this.name=name;
        this.amount=amount;
        this.price=price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return this.name == null ? "not a resource" : name;
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

    @Override
    public Double getQuality() {
        return quality;
    }
}
