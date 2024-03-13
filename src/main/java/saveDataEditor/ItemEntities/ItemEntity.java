package saveDataEditor.ItemEntities;

public interface ItemEntity {
     long id=0;
     String name="";
     Double amount=1d;
     Long price=0L;
     Long age = 0L;
     Double quality = 0d;

      long getId();
      String getName();
      Double getAmount();
      Long getPrice();
      Long getAge();
      Double getQuality();
}
